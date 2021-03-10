package richcoder.huobi.api.config;

public enum EUrl {
    CREATE_WITHDRAW_PATH("/v1/dw/withdraw/api/create", "创建提币任务"),
    GET_DEPOSIT_ADDRESS_PATH("/v2/account/deposit/address", "充币地址查询"),
    GET_WITHDRAW_ADDRESS_PATH("/v2/account/withdraw/address", "提币地址查询"),
    GET_ACCOUNT_BALANCE_PATH("/v1/account/accounts/{account-id}/balance", "账户余额查询"),
    GET_CURRENCY_CHAINS_PATH("/v2/reference/currencies", "币种链表查询"),
    DEPOSIT_WITHDRAW_PATH("/v1/query/deposit-withdraw", "充提记录查询"),
    GET_ACCOUNTS_PATH("/v1/account/accounts", "账户查询"),
    GET_CURRENCY("/v1/common/currencys", "站点支持币种查询"),
    REST_CANDLESTICK_PATH("/market/history/kline", "K线数据");;

    private String url;

    private String desc;

    EUrl(String url, String desc) {
        this.url = url;
        this.desc = desc;
    }

    public static String getValueByType(String type) {
        for (EUrl businessType : EUrl.values()) {
            if (businessType.getUrl().equals(type)) {
                return businessType.getDesc();
            }
        }
        return null;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }
}
