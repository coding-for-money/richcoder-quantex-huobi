package richcoder.huobi.quantex.service;

import org.springframework.stereotype.Service;
import richcoder.huobi.api.api.MarketApi;

import javax.annotation.Resource;

/**
 * 价格服务
 *
 * @author richcoder
 */
@Service
public class MarketService {
    @Resource
    private MarketApi marketApi;
    /**
     * 获取行情 日线行情 ，4小时行情，15分钟行情
     */


}
