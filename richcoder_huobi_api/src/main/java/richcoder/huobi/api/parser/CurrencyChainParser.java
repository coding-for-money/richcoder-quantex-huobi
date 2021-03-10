package richcoder.huobi.api.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import richcoder.huobi.api.vo.res.CurrencyChainAlpRes;

import java.util.List;

public class CurrencyChainParser implements HuobiModelParser<CurrencyChainAlpRes> {

  @Override
  public CurrencyChainAlpRes parse(JSONObject json) {
    return json.toJavaObject(CurrencyChainAlpRes.class);
  }

  @Override
  public CurrencyChainAlpRes parse(JSONArray json) {
    return null;
  }

  @Override
  public List<CurrencyChainAlpRes> parseArray(JSONArray jsonArray) {
    return jsonArray.toJavaList(CurrencyChainAlpRes.class);
  }
}
