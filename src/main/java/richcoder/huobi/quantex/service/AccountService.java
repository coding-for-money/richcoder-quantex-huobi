package richcoder.huobi.quantex.service;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import richcoder.huobi.api.api.AccountApi;
import richcoder.huobi.api.vo.req.ExApiBaseReq;
import richcoder.huobi.api.vo.res.AccountAlpRes;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class AccountService {
    @Resource
    private AccountApi accountApi;

    public void accountInfo() {
        ExApiBaseReq req = new ExApiBaseReq();
        log.info("账户信息：");
        List<AccountAlpRes> accounts = accountApi.accounts(req);
        log.info(JSONArray.toJSONString(accounts));
    }
}
