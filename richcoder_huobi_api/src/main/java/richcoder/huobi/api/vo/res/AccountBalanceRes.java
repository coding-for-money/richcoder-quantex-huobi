package richcoder.huobi.api.vo.res;

import lombok.Data;

import java.util.List;

/**
 * @author richcoder
 */
@Data
public class AccountBalanceRes {

    /**
     * 账户 ID
     */
    private long id;
    /**
     * 账户类型	spot：现货账户，point：点卡账户
     */
    private String type;

    /**
     * 账户状态	working：正常 lock：账户被锁定
     */
    private String state;
    private String subType;

    private List<AccountBalanceItemRes> list;
}