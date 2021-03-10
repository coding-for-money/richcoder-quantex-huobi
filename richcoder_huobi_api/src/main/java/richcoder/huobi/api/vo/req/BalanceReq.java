package richcoder.huobi.api.vo.req;

import lombok.Data;

import java.util.List;

@Data
public class BalanceReq extends ExApiBaseReq {
    private List<String> currency;
    private Long accountKeyId;
}
