package richcoder.huobi.api.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import richcoder.huobi.api.config.EUrl;
import richcoder.huobi.api.parser.AccountParser;
import richcoder.huobi.api.rpc.UrlParamsBuilder;
import richcoder.huobi.api.vo.req.ExApiBaseReq;
import richcoder.huobi.api.vo.res.AccountAlpRes;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;

/**
 * huobi api currency service
 *
 * @author richcoder
 */
@Service
public class AccountApi {

    @Resource
    private ApiClient apiClient;

    public List<AccountAlpRes> accounts(ExApiBaseReq req) {
        try {
            req.setUrl(EUrl.GET_ACCOUNTS_PATH.getUrl());
            UrlParamsBuilder builder = UrlParamsBuilder.build();
            JSONObject jsonObject = apiClient.executeGetWithSignature(req, builder);
            JSONArray data = jsonObject.getJSONArray("data");
            return new AccountParser().parseArray(data);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
