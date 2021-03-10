package richcoder.huobi.api.api;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import richcoder.huobi.api.config.EUrl;
import richcoder.huobi.api.parser.AccountBalanceParser;
import richcoder.huobi.api.rpc.UrlParamsBuilder;
import richcoder.huobi.api.vo.req.BalanceReq;
import richcoder.huobi.api.vo.res.AccountBalanceRes;

import javax.annotation.Resource;
import java.net.MalformedURLException;

/**
 * huobi api currency service
 *
 * @author richcoder
 */
@Service
public class BalanceApi {

    @Resource
    private ApiClient apiClient;

    /**
     * GET /v1/account/accounts/{account-id}/balance
     * 账户余额
     * API Key 权限：读取
     * 限频值（NEW）：100次/2s
     * 查询指定账户的余额，支持以下账户：
     * spot：现货账户， point：点卡账户
     *
     * @param request
     * @return
     */
    public AccountBalanceRes getBalance(BalanceReq request) {
        try {
            request.setUrl(EUrl.GET_ACCOUNT_BALANCE_PATH.getUrl()
                            .replace("{account-id}", request.getAccountKeyId() + ""));
            JSONObject jsonObject = apiClient
                    .executeGetWithSignature(request, UrlParamsBuilder.build());
            JSONObject data = jsonObject.getJSONObject("data");
            return new AccountBalanceParser().parse(data);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

}