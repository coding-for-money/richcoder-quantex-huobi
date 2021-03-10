package richcoder.huobi.api.common;

import lombok.Getter;

@Getter
public class SdkException extends RuntimeException {

  public static final String INPUT_ERROR = "InputError";

  public static final String RUNTIME_ERROR = "RuntimeError";

  public static final String ENV_ERROR = "EnvironmentError";

  public static final String EXEC_ERROR = "ExecuteError";

  public static final String KEY_MISSING = "KeyMissingError";

  public static final String SYS_ERROR = "SystemError";

  /**
   * 币种在充币方不存在
   */
  public static final String FIELD_ERROR="invalid field value";
  /**
   * 未在提币网站配置提币地址
   */
  private final String errCode;

  public SdkException(String errCode, String errMsg) {
    super(errMsg);
    this.errCode = errCode;
  }

}