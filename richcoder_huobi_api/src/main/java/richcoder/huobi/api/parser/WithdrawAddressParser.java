package richcoder.huobi.api.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import richcoder.huobi.api.vo.res.AddressRes;

import java.util.List;

public class WithdrawAddressParser implements HuobiModelParser<AddressRes> {

  @Override
  public AddressRes parse(JSONObject json) {
    return null;
  }

  @Override
  public AddressRes parse(JSONArray json) {
    return null;
  }

  @Override
  public List<AddressRes> parseArray(JSONArray jsonArray) {
    return jsonArray.toJavaList(AddressRes.class);
  }
}
