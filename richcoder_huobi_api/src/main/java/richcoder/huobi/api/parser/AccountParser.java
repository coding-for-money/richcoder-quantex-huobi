package richcoder.huobi.api.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import richcoder.huobi.api.vo.res.AccountAlpRes;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountParser implements HuobiModelParser<AccountAlpRes> {

    @Override
    public AccountAlpRes parse(JSONObject json) {
        AccountAlpRes account = json.toJavaObject(AccountAlpRes.class);
        account.setType(json.getString("type"));
        account.setState(json.getString("state"));

        return account;
    }

    @Override
    public AccountAlpRes parse(JSONArray json) {
        return null;
    }

    @Override
    public List<AccountAlpRes> parseArray(JSONArray jsonArray) {
        List<AccountAlpRes> accountList = new ArrayList<>(jsonArray.size());
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            accountList.add(parse(jsonObject));
        }

        return accountList;
    }
}
