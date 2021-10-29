package com.richcoder.common.utils;

import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProxyOkHttpClient {


  public String call(Request request) {
    try {
      OkHttpClient okHttpClient = new OkHttpClient.Builder()
          //.proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port)))
          .connectTimeout(30, TimeUnit.SECONDS)
          .readTimeout(30, TimeUnit.SECONDS)
          .build();
      Response response = okHttpClient.newCall(request).execute();
      return response.body().string();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}