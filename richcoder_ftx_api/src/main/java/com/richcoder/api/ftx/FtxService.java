package com.richcoder.api.ftx;


import com.richcoder.common.utils.ProxyOkHttpClient;
import com.richcoder.common.utils.ThirdDigestUtil;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class FtxService {

    private static final String BALANCE_URL = "https://ftx.com/api";
    private static final String PATH = "/wallet/balances";
    private static final String FTX_KEY = "FTX-KEY";
    private static final String FTX_SIGN = "FTX-SIGN";
    private static final String FTX_TS = "FTX-TS";
    private static final String METHOD = "GET";

    @Resource
    private ProxyOkHttpClient proxyOkHttpClient;

    public String queryAssert()   {

      String respEntity = null;
      try {


        String nNonce = String.valueOf(System.currentTimeMillis());
        String apiKey = "Sfj1vEmwsWHYllDRnjBOwIr8yLa1pbBHulNfAp-2";
        String secretKey = "dXgYBRVyApCZc4NyJksnB-2usTiN_U3gILO5v40U";

        String param = nNonce + METHOD + PATH;
        System.out.println(param);
        String sign = null;
        sign = ThirdDigestUtil.hmacSHA256Hex(secretKey, param);
        String apiHost = BALANCE_URL + PATH;


        Request request = new Request.Builder()
                .addHeader(FTX_KEY, apiKey)
                .addHeader(FTX_SIGN, sign)
                .addHeader(FTX_TS, nNonce)
                .url(apiHost)
                .get()
                .build();
        respEntity = proxyOkHttpClient.call(request);
      } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
      } catch (InvalidKeyException e) {
        e.printStackTrace();
      }

      return respEntity;
    }

}
