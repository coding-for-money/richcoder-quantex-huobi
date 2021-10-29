package com.richcoder.api.bybit;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.richcoder.common.utils.ProxyOkHttpClient;
import com.richcoder.common.utils.ThirdDigestUtil;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class BybitRestService {

  //https://api-testnet.bybit.com
  //https://api.bytick.com
  //https://api.bybit.com
  private static final String BASE_URL = "https://api.bybit.com";
  private static final String SPOT = "/spot/v1/account";
  private static final String SWAP = "/v2/private/wallet/balance";
  private static final String API_KEY = "api_key";
  private static final String API_SIGN = "sign";
  private static final String API_TIMESTAMP = "timestamp";

  @Resource
  private ProxyOkHttpClient proxyOkHttpClient;

  private TreeMap<String, String> getParam(Map<String, String> paramMap, String apiKey) {
    String nNonce = String.valueOf(System.currentTimeMillis());
    paramMap.put(API_KEY, apiKey);
    paramMap.put(API_TIMESTAMP, nNonce);
    return ThirdDigestUtil.sortedByKey(paramMap);
  }


  public String querySwap() {
    String respEntity = "";
    String apiKey = "vPw6XZSeLkTo8bNhnY";
    String sec = "lwgv8Hu4WgSYTkjWH5IQjf4UxqmxNCtQXIXz";
    //拼要进行加密的参数
    Map<String, String> paramPam = new TreeMap<String, String>();
    TreeMap<String, String> stringStringTreeMap = getParam(paramPam, apiKey);
    //得到加密后的sign :使用HMAC_SHA256算法對第1步中拼接的query string簽名，並轉換為16進製字符串，得出sign參數
    String urlParamStr = ThirdDigestUtil.genQueryString(stringStringTreeMap, sec);
    String apiHost = BASE_URL + SWAP + "?" + urlParamStr;
    Request request = new Request.Builder().url(apiHost).get().build();
    String rs = proxyOkHttpClient.call(request);
    JSONObject jsonObject =JSONObject.parseObject(rs);
    JSONObject result = jsonObject.getJSONObject("result");
    Set<String> keySet = result.keySet();
    for (String s : keySet) {
      Object walletBalance = result.getJSONObject(s).get("wallet_balance");
      System.out.println(walletBalance);
      System.out.println(s);
    }


    return rs;
  }


  public String querySpot() {
    String respEntity = "";
    String apiKey = "vPw6XZSeLkTo8bNhnY";
    String sec = "lwgv8Hu4WgSYTkjWH5IQjf4UxqmxNCtQXIXz";
    //拼要进行加密的参数
    Map<String, String> paramPam = new TreeMap<String, String>();
    TreeMap<String, String> stringStringTreeMap = getParam(paramPam, apiKey);
    //得到加密后的sign :使用HMAC_SHA256算法對第1步中拼接的query string簽名，並轉換為16進製字符串，得出sign參數
    String urlParamStr = ThirdDigestUtil.genQueryString(stringStringTreeMap, sec);
    String apiHost = BASE_URL + SPOT + "?" + urlParamStr;
    Request request = new Request.Builder().url(apiHost).get().build();
    String rs = proxyOkHttpClient.call(request);
    return rs;
  }



}
