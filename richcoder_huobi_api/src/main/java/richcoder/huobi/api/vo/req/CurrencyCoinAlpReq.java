package richcoder.huobi.api.vo.req;

import lombok.Data;

@Data
public class CurrencyCoinAlpReq extends ExApiBaseReq {

    /**
     * 币种
     */
    private String currency;
    /**
     * 已认证用户 true,or false,
     * 默认true ,可不填
     */
    private boolean authorizedUser = true;
}
