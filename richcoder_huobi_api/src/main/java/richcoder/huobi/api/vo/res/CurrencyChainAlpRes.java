package richcoder.huobi.api.vo.res;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author richcoder
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyChainAlpRes {

  /**
   * 链
   */
  private List<ChainRes> chains;
  private String currency;
  /**
   * 币种状态 normal ,delisted
   */
  private String instStatus;
}