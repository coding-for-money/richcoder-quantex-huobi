package richcoder.huobi.quantex.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import richcoder.huobi.api.api.BalanceApi;
import richcoder.huobi.api.config.ApiSetting;
import richcoder.huobi.api.vo.req.BalanceReq;
import richcoder.huobi.api.vo.res.AccountBalanceRes;

import javax.annotation.Resource;

/**
 * 余额服务
 *
 * @author richcoder
 */
@Service
@Slf4j
public class BalanceService {
    @Resource
    BalanceApi balanceApi;

    public void queryBalance() {
        BalanceReq balanceReq = new BalanceReq();
        balanceReq.setAccountKeyId(ApiSetting.ACCOUNT_ID_1);
        AccountBalanceRes balance = balanceApi.getBalance(balanceReq);
        log.info("账户余额：");
        log.info(JSON.toJSONString(balance));
    }
}
