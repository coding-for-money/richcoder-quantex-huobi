package richcoder.huobi.api.vo.res;

import lombok.Builder;
import lombok.Data;
import richcoder.huobi.api.enums.ExchangeEnum;

/**
 * @author richcoder
 */
@Data
@Builder
public class Options {

  String apiKey;

  String secretKey;

  ExchangeEnum exchange;

  String restHost ;

}