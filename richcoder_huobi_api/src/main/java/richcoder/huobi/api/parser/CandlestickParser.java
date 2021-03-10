package richcoder.huobi.api.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import richcoder.huobi.api.vo.res.Candlestick;

import java.util.List;

@Service
public class CandlestickParser implements HuobiModelParser<Candlestick> {

    @Override
    public Candlestick parse(JSONObject json) {
        return json.toJavaObject(Candlestick.class);
    }

    @Override
    public Candlestick parse(JSONArray json) {
        return null;
    }

    @Override
    public List<Candlestick> parseArray(JSONArray jsonArray) {
        return jsonArray.toJavaList(Candlestick.class);
    }
}