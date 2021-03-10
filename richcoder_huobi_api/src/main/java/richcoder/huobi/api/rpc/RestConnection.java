package richcoder.huobi.api.rpc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;

import richcoder.huobi.api.common.SdkException;
import richcoder.huobi.api.config.AbstractEtfResult;
import richcoder.huobi.api.vo.res.*;
import richcoder.huobi.api.vo.req.*;
/**
 * 调用client前需要进行报文头等处理
 *
 * @author richcoder
 */
@Slf4j
@Component
public class RestConnection {

    @Resource
    private ClientFactory clientFactory;

    public JSONObject executeGet(String path, UrlParamsBuilder paramsBuilder, Options options) {
        String url = options.getRestHost() + path + paramsBuilder.buildUrl();
        Request executeRequest = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();
        String resp = clientFactory.execute(executeRequest);
        return checkAndGetResponse(resp, url);
    }

    public String executeGetString(String url, UrlParamsBuilder paramsBuilder) {
        String realUrl = url + paramsBuilder.buildUrl();
        Request executeRequest = new Request.Builder()
                .url(realUrl)
                .addHeader("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();
        String resp = clientFactory.execute(executeRequest);
        return resp;
    }

    public JSONObject executeGetWithSignature(String path, UrlParamsBuilder paramsBuilder,
                                              Options options)
            throws MalformedURLException {
        String host = new URL(options.getRestHost()).getHost();
        String requestUrl = options.getRestHost() + path;
        new ApiSignature()
                .createSignature(options.getApiKey(), options.getSecretKey(), "GET", host, path,
                        paramsBuilder);
        requestUrl += paramsBuilder.buildUrl();
        Request executeRequest = new Request.Builder().url(requestUrl)
                .addHeader("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE).build();
        String resp = clientFactory.execute(executeRequest);
        return checkAndGetResponse(resp, requestUrl);
    }

    public JSONObject executePostWithSignature(String path, UrlParamsBuilder paramsBuilder,
                                               Options options)
            throws MalformedURLException {
        String host = new URL(options.getRestHost()).getHost();
        String requestUrl = options.getRestHost() + path;
        new ApiSignature()
                .createSignature(options.getApiKey(), options.getSecretKey(), "POST", host, path,
                        paramsBuilder);
        requestUrl += paramsBuilder.buildUrl();

        Request executeRequest = new Request.Builder().url(requestUrl)
                .post(paramsBuilder.buildPostBody())
                .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE).build();
        String resp = clientFactory.execute(executeRequest);
        return checkAndGetResponse(resp, requestUrl);
    }


    private JSONObject checkAndGetResponse(String resp, String requestUrl) {
        JSONObject json = JSON.parseObject(resp);
        try {
            if (json.containsKey("status")) {
                String status = json.getString("status");
                if ("error".equals(status)) {
                    log.error(resp);
                    String errCode = json.getString("err-code");
                    String errMsg = json.getString("err-msg");
                    log.error(SdkException.EXEC_ERROR +
                            "[Executing] " + errCode + ": " + errMsg + requestUrl);
                } else if (!"ok".equals(status)) {
                    log.error(SdkException.RUNTIME_ERROR +
                            "[Invoking] Response is not expected: " + status + requestUrl);
                }
            } else if (json.containsKey("success")) {
                boolean success = json.getBoolean("success");
                if (!success) {
                    log.error(resp);
                    String errCode = AbstractEtfResult.checkResult(json.getInteger("code"));
                    String errMsg = json.getString("message");
                    if ("".equals(errCode)) {
                        log.error(SdkException.EXEC_ERROR + "[Executing] " + errMsg + requestUrl);
                    } else {
                        log.error(SdkException.EXEC_ERROR +
                                "[Executing] " + errCode + ": " + errMsg + requestUrl);
                    }
                }
            } else if (json.containsKey("code")) {
                int code = json.getInteger("code");
                if (code != HttpStatus.OK.value()) {
                    log.error(resp);
                    String message = json.getString("message");
                    log.error(SdkException.EXEC_ERROR + "[Executing]" + message + requestUrl);
                }
            } else {
                log.error(SdkException.RUNTIME_ERROR +
                        "[Invoking] Status cannot be found in response." + requestUrl);
            }
        } catch (SdkException e) {
            throw e;
        } catch (Exception e) {
            throw new SdkException(SdkException.RUNTIME_ERROR,
                    "[Invoking] Unexpected error: " + e.getMessage() + requestUrl);
        }

        return json;
    }

}
