package richcoder.huobi.api.rpc;

import com.sun.istack.internal.NotNull;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import richcoder.huobi.api.common.SdkException;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * 对okhttp 或者其他client进行封装配置
 *
 * @author richcoder
 */
@Component
public class ClientFactory {

    private static final Logger log = LoggerFactory.getLogger(ClientFactory.class);
    private static ConnectionPool connectionPool =
            new ConnectionPool(20, 300, TimeUnit.SECONDS);

    /**
     * 由于ip限制，开发环境需要代理，线上环境不需要代理
     *
     * @return
     */
    private OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .followSslRedirects(false)
                .followRedirects(false)
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .connectionPool(connectionPool)
                .addNetworkInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request request = chain.request();
                        Response response = chain.proceed(request);
                        return response;
                    }
                })
                .build();
    }

    public String execute(Request request) {
        Response response = null;
        String str = null;
        try {
            OkHttpClient client = getClient();
            response = client.newCall(request).execute();
            if (response.code() != HttpStatus.OK.value()) {
                throw new SdkException(SdkException.EXEC_ERROR,
                        "[Execute] Response Status Error : " + response.code() + " message:" + response
                                .message());
            }
            if (response.body() != null) {
                str = response.body().string();
                response.close();
            } else {
                throw new SdkException(SdkException.ENV_ERROR,
                        "[Execute] Cannot get the response from server");
            }
            return str;
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            throw new SdkException(SdkException.RUNTIME_ERROR,
                    "[Execute] Cannot get the response from server");
        }

    }
}
