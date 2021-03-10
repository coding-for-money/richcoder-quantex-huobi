package richcoder.huobi.api.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import richcoder.huobi.api.vo.res.AccountBalanceRes;

import java.util.ArrayList;
import java.util.List;

public class AccountBalanceParser implements HuobiModelParser<AccountBalanceRes> {

    @Override
    public AccountBalanceRes parse(JSONObject json) {
        String subType = json.getString("subtype");
        if (subType == null) {
            subType = json.getString("symbol");
        }
        AccountBalanceRes accountBalance = json.toJavaObject(AccountBalanceRes.class);
        accountBalance.setType(json.getString("type"));
        accountBalance.setState(json.getString("state"));
        accountBalance.setSubType(subType);
        accountBalance.setList(new BalanceParser().parseArray(json.getJSONArray("list")));

        return accountBalance;
    }

    @Override
    public AccountBalanceRes parse(JSONArray json) {
        return null;
    }

    @Override
    public List<AccountBalanceRes> parseArray(JSONArray jsonArray) {

        if (jsonArray == null || jsonArray.size() <= 0) {
            return new ArrayList<>();
        }

        List<AccountBalanceRes> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            list.add(parse(jsonObject));
        }

        return list;
    }
}

