package richcoder.huobi.api.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import richcoder.huobi.api.vo.res.AccountBalanceItemRes;

import java.util.ArrayList;
import java.util.List;

public class BalanceParser implements HuobiModelParser<AccountBalanceItemRes> {

  @Override
  public AccountBalanceItemRes parse(JSONObject json) {
    AccountBalanceItemRes balance = json.toJavaObject(AccountBalanceItemRes.class);
    balance.setType(json.getString("type"));
    return balance;
  }

  @Override
  public AccountBalanceItemRes parse(JSONArray json) {
    return null;
  }

  @Override
  public List<AccountBalanceItemRes> parseArray(JSONArray jsonArray) {
    List<AccountBalanceItemRes> balanceList = new ArrayList<>(jsonArray.size());
    for (int i = 0; i < jsonArray.size(); i++) {
      JSONObject jsonObject = jsonArray.getJSONObject(i);
      balanceList.add(parse(jsonObject));
    }
    return balanceList;
  }
}
