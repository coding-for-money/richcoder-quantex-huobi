package richcoder.huobi.api.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import richcoder.huobi.api.vo.res.AddressRes;

import java.util.Collections;
import java.util.List;

public class DepositAddressParser implements HuobiModelParser<AddressRes> {

  @Override
  public AddressRes parse(JSONObject json) {
    return json.toJavaObject(AddressRes.class);
  }

  @Override
  public AddressRes parse(JSONArray json) {
    return null;
  }

  @Override
  public List<AddressRes> parseArray(JSONArray jsonArray) {
    if (null != jsonArray) {
      return jsonArray.toJavaList(AddressRes.class);
    } else {
      return Collections.emptyList();
    }
  }
}
