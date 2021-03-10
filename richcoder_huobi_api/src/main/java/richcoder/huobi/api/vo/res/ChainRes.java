package richcoder.huobi.api.vo.res;

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
public class ChainRes {

  private String chain;
  private String displayName;
  private String baseChain;
  private String baseChainProtocol;
  private Boolean isDynamic;
  /**
   * 充币状态 allowed,prohibited
   */
  private String depositStatus;
  /**
   * 单次最大提币金额
   */
  private String maxWithdrawAmt;
  /**
   * 单次最小充币金额
   */
  private String minDepositAmt;
  /**
   * 单次最小提币金额
   */
  private String minWithdrawAmt;
  /**
   * 安全上账所需确认次数（达到确认次数后允许提币）
   */
  private Integer numOfConfirmations;
  /**
   * 快速上账所需确认次数（达到确认次数后允许交易但不允许提币）
   */
  private Integer numOfFastConfirmations;
  /**
   * 单次提币手续费（仅对固定类型有效，withdrawFeeType=fixed）
   */
  private String transactFeeWithdraw;
  /**
   * 提币手续费类型（特定币种在特定链上的提币手续费类型唯一）
   * fixed,circulated,ratio
   */
  private String withdrawFeeType;
  /**
   * 提币精度
   */
  private Integer withdrawPrecision;

  /**
   * 单次提币手续费率（仅对比例类型有效，withdrawFeeType=ratio）
   */
  private String transactFeeRateWithdraw;
  /**
   * 当日提币额度（新加坡时区）
   */
  private String withdrawQuotaPerDay;
  /**
   * 当年提币额度
   */
  private String withdrawQuotaPerYear;
  /**
   * 总提币额度
   */
  private String withdrawQuotaTotal;
  /**
   * 提币状态	allowed,prohibited
   */
  private String withdrawStatus;
  /**
   * 最小单次提币手续费（仅对区间类型和有下限的比例类型有效，withdrawFeeType=circulated or ratio）
   */
  private String minTransactFeeWithdraw;
  /**
   * 最大单次提币手续费（仅对区间类型和有上限的比例类型有效，withdrawFeeType=circulated or ratio）
   */
  private String maxTransactFeeWithdraw;
}
