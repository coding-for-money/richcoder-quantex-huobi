package richcoder.huobi.api.api;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import richcoder.huobi.api.config.ApiSetting;
import richcoder.huobi.api.rpc.RestConnection;
import richcoder.huobi.api.rpc.UrlParamsBuilder;
import richcoder.huobi.api.vo.req.ExApiBaseReq;
import richcoder.huobi.api.vo.res.Options;

import javax.annotation.Resource;
import java.net.MalformedURLException;

/**
 * @author richcoder
 */
@Component
@Slf4j
public class ApiClient {

    @Resource
    private RestConnection restConnection;


    public JSONObject executeGetWithSignature(ExApiBaseReq request,
                                              UrlParamsBuilder paramsBuilder) throws MalformedURLException {
        Options options = Options.builder().apiKey(ApiSetting.ACCESS_KEY)
                .secretKey(ApiSetting.SECRET)
                .restHost(ApiSetting.REST_HOST)
                .build();
        return restConnection.executeGetWithSignature(request.getUrl(), paramsBuilder, options);
    }

    public JSONObject executePostWithSignature(ExApiBaseReq request,
                                               UrlParamsBuilder paramsBuilder) throws MalformedURLException {
        Options options = Options.builder().apiKey(ApiSetting.ACCESS_KEY)
                .secretKey(ApiSetting.SECRET)
                .restHost(ApiSetting.REST_HOST)
                .build();
        return restConnection.executePostWithSignature(request.getUrl(), paramsBuilder, options);
    }

    public JSONObject executeGet(ExApiBaseReq request, UrlParamsBuilder builder) {
        Options options = Options.builder().apiKey(ApiSetting.ACCESS_KEY)
                .secretKey(ApiSetting.SECRET)
                .restHost(ApiSetting.REST_HOST)
                .build();
        return restConnection.executeGet(request.getUrl(), builder, options);

    }
}
