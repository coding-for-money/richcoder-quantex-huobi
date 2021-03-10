package richcoder.huobi.api.vo.res;

import lombok.Data;
import richcoder.huobi.api.enums.CandlestickIntervalEnum;
import richcoder.huobi.api.vo.req.ExApiBaseReq;


@Data

public class CandlestickRequest extends ExApiBaseReq {

    private String symbol;

    private CandlestickIntervalEnum interval;

    private Integer size;

}