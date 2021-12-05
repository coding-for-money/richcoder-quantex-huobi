package com.richcoder.api.deribit;

import com.richcoder.common.utils.ProxyOkHttpClient;
import javax.annotation.Resource;
import okhttp3.HttpUrl;
import okhttp3.Request;
import org.springframework.stereotype.Component;

@Component

public class DeribitRestService {

  @Resource
  private ProxyOkHttpClient proxyOkHttpClient;
  private String API_PATH_PREFIX = "https://www.deribit.com";
  private String apiKey = "";
  private String secret = "";

  public String auth() {
    String url = "/public/auth";
    String client_id = "hq2dUWzx";
    String client_secret = "x0PIsqcw_M04TaLPdTqjjxTdzH3U1DuB_oBUmHcBBpk";
    String grant_type = "client_credentials";
    //client_credentials
    HttpUrl.Builder httpBuilder = HttpUrl.parse(API_PATH_PREFIX + url).newBuilder();
    httpBuilder.addQueryParameter("client_id", client_id);
    httpBuilder.addQueryParameter("client_secret", client_secret);
    httpBuilder.addQueryParameter("grant_type", grant_type);

    Request request = new Request.Builder()
        .addHeader("Content-Type", "application/json")
        .url(httpBuilder.build()).get().build();

    String call = proxyOkHttpClient.call(request);
    System.out.println(call);
    return call;

  }


}
