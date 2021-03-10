package richcoder.huobi.api.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import richcoder.huobi.api.vo.res.DepositWithdrawHistoryRes;

import java.util.ArrayList;
import java.util.List;

public class DepositWithdrawParser implements HuobiModelParser<DepositWithdrawHistoryRes> {

  @Override
  public DepositWithdrawHistoryRes parse(JSONObject json) {
    DepositWithdrawHistoryRes depositWithdraw = json.toJavaObject(DepositWithdrawHistoryRes.class);
    depositWithdraw.setTxHash(json.getString("tx-hash"));
    depositWithdraw.setAddressTag(json.getString("address-tag"));
    depositWithdraw.setCreatedAt(json.getLong("created-at"));
    depositWithdraw.setUpdatedAt(json.getLong("updated-at"));
    depositWithdraw.setErrorCode(json.getString("error-code"));
//    depositWithdraw.set(json.getString("error-msg"));
    return depositWithdraw;
  }

  @Override
  public DepositWithdrawHistoryRes parse(JSONArray json) {
    return null;
  }

  @Override
  public List<DepositWithdrawHistoryRes> parseArray(JSONArray jsonArray) {
    if (jsonArray == null || jsonArray.size() <= 0) {
      return new ArrayList<>();
    }

    List<DepositWithdrawHistoryRes> list = new ArrayList<>();
    for (int i = 0; i < jsonArray.size(); i++) {
      list.add(parse(jsonArray.getJSONObject(i)));
    }
    return list;
  }
}
