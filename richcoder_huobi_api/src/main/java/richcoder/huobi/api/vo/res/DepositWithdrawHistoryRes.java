package richcoder.huobi.api.vo.res;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class DepositWithdrawHistoryRes {

  private Long id;
  /**
   * 'deposit', 'withdraw', 子用户仅有deposit
   */
  private String type;
  private String currency;
  private String txHash;
  private String chain;
  private BigDecimal amount;
  private String address;
  private String addressTag;
  private BigDecimal fee;
  /**
   * 充值：
   * unknown	状态未知
   * confirming	确认中
   * confirmed	已确认
   * safe	已完成
   * orphan	待确认
   * 提币：
   * verifying	待验证
   * failed	验证失败
   * submitted	已提交
   * reexamine	审核中
   * canceled	已撤销
   * pass	审批通过
   * reject	审批拒绝
   * pre-transfer	处理中
   * wallet-transfer	已汇出
   * wallet-reject	钱包拒绝
   * confirmed	区块已确认
   * confirm-error	区块确认错误
   * repealed	已撤销
   */
  private String state;
  private String errorCode;
  private String errorMsg;
  private Long createdAt;
  private Long updatedAt;

}