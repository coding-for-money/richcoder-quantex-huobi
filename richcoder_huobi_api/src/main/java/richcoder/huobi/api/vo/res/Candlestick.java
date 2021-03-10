package richcoder.huobi.api.vo.res;

import lombok.Data;

import java.math.BigDecimal;

@Data

public class Candlestick {

    private Long id;

    private BigDecimal amount;

    private BigDecimal count;

    private BigDecimal open;

    private BigDecimal high;

    private BigDecimal low;

    private BigDecimal close;

    private BigDecimal vol;

}