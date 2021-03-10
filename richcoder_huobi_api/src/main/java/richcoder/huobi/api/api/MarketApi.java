package richcoder.huobi.api.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import  richcoder.huobi.api.config.EUrl;
import richcoder.huobi.api.parser.CandlestickParser;
import richcoder.huobi.api.rpc.UrlParamsBuilder;
import richcoder.huobi.api.vo.res.Candlestick;
import richcoder.huobi.api.vo.res.CandlestickRequest;


import javax.annotation.Resource;
import java.util.List;

/**
 * huobi api currency service
 *
 * @author richcoder
 */
@Service
public class MarketApi {

    @Resource
    private ApiClient apiClient;

    public List<Candlestick> getCandlestick(CandlestickRequest request) {
        UrlParamsBuilder paramBuilder = UrlParamsBuilder.build()
                .putToUrl("symbol", request.getSymbol())
                .putToUrl("period", request.getInterval().getCode())
                .putToUrl("size", request.getSize());
        request.setUrl(EUrl.REST_CANDLESTICK_PATH.getUrl());
        JSONObject json = apiClient.executeGet(request, paramBuilder);
        JSONArray data = json.getJSONArray("data");
        return new CandlestickParser().parseArray(data);
    }

}