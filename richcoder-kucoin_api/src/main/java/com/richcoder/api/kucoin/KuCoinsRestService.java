package com.richcoder.api.kucoin;


import com.richcoder.common.utils.ProxyOkHttpClient;
import com.richcoder.common.utils.ThirdDigestUtil;
import javax.annotation.Resource;
import okhttp3.Request;
import org.springframework.stereotype.Component;

@Component
public class KuCoinsRestService {

  private static final String BALANCE_URL = "https://openapi-v2.kucoin.com";
  private static final String FUTURE_URL = "https://api-futures.kucoin.com";
  private static final String ASSET = "/api/v1/accounts";
  private static final String FUTURE = "/api/v1/account-overview";
  private static final String KC_API_KEY = "KC-API-KEY";
  private static final String KC_API_SIGN = "KC-API-SIGN";
  private static final String KC_API_TIMESTAMP = "KC-API-TIMESTAMP";
  private static final String KC_API_PASSPHRASE = "KC-API-PASSPHRASE";
  private static final String METHOD = "GET";

  @Resource
  private ProxyOkHttpClient proxyOkHttpClient;

  public String queryAssert() {
    String nNonce = String.valueOf(System.currentTimeMillis());
    String apiKey = "617ba070e87a7700017af298";
    String secretKey = "924397dd-dd6d-43de-a83b-5faec2790d16";
    String passphrase = "12345678";

    String param = nNonce + METHOD + ASSET;

    String sign = null;
    try {
      sign = ThirdDigestUtil.hmacSHA256Base64(secretKey, param);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String apiHost = BALANCE_URL + ASSET;
    Request request = new Request.Builder()
        .addHeader(KC_API_KEY, apiKey)
        .addHeader(KC_API_SIGN, sign)
        .addHeader(KC_API_TIMESTAMP, nNonce)
        .addHeader(KC_API_PASSPHRASE, passphrase)
        .url(apiHost)
        .get()
        .build();
    String res = proxyOkHttpClient.call(request);
    System.out.println(res);
    return res;
  }


  public String queryFuture() {
    String nNonce = String.valueOf(System.currentTimeMillis());
    String apiKey = "617b9c383088150001156557";
    String secretKey = "8d4e4afe-8be2-48d4-933a-747e3181bcb7";
    String passphrase = "12345678";

    String param = nNonce + METHOD + FUTURE+"?currency=USDT";

    String sign = null;
    try {
      sign = ThirdDigestUtil.hmacSHA256Base64(secretKey, param);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String apiHost = FUTURE_URL + FUTURE+"?currency=USDT";
    Request request = new Request.Builder()
        .addHeader(KC_API_KEY, apiKey)
        .addHeader(KC_API_SIGN, sign)
        .addHeader(KC_API_TIMESTAMP, nNonce)
        .addHeader(KC_API_PASSPHRASE, passphrase)
        .url(apiHost)
        .get()
        .build();
    String res = proxyOkHttpClient.call(request);
    System.out.println(res);
    return res;
  }

}
