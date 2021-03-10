package richcoder.huobi.api.vo.res;

import lombok.Data;

@Data
public class AddressRes {

  public String currency;
  public String chain;
  public String note;
  public String addressTag;
  public String address;
}
