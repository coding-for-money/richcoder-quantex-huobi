package richcoder.huobi.api.enums;

import lombok.Getter;

@Getter
public enum ExchangeEnum {

  GLOBAL("global","global"),
  KOREA("korea","韩国站")
  ;
  private final String code;
  private final String name;
  ExchangeEnum(String code,String name) {
    this.code = code;
    this.name=name;
  }

  public static String getName(String code) {
    for (ExchangeEnum obj : ExchangeEnum.values()) {
      if (obj.getCode().equals(code)) {
        return obj.getName();
      }
    }
    return "";
  }

}