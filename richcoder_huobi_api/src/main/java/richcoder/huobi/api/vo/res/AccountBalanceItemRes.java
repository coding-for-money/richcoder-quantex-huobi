package richcoder.huobi.api.vo.res;

import lombok.Data;

@Data
public class AccountBalanceItemRes {

    /**
     * 币种
     */
    private String currency;
    /**
     * 类型	trade: 交易余额，frozen: 冻结余额
     */
    private String type;
    /**
     * 余额
     */
    private String balance;
}
