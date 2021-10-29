package com.richcoder.huobi.main.controller;

import com.richcoder.api.bybit.BybitRestService;
import com.richcoder.api.ftx.FtxService;
import com.richcoder.api.kucoin.KuCoinsRestService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模板服务
 *
 * @author lilinjun
 */
@RestController
@RequestMapping("/v1")
@Slf4j
public class ApiController {

  @Resource
  private BybitRestService bybitRestService;
  @Resource
  private FtxService ftxservice;
  @Resource
  private KuCoinsRestService kucoinService;

  @GetMapping("/bybit/swap")
  public String byBitSwap() {
    return bybitRestService.querySwap();
  }

  @GetMapping("/bybit/spot")
  public String byBitSpot() {
    return bybitRestService.querySpot();
  }

  @GetMapping("/ftx/asset")
  public String ftxAsset() {
    return ftxservice.queryAssert();
  }

  @GetMapping("/kucoin/future")
  public String kucoinFuture() {
    return kucoinService.queryFuture();
  }

  @GetMapping("/kucoin/asset")
  public String kucoinAsset() {
    return kucoinService.queryAssert();
  }


}
