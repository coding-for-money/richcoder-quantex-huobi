package richcoder.huobi.api.rpc;


import richcoder.huobi.api.common.SdkException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;


/**
 * copy of sdk demo
 *
 * @author richcoder
 */
public class ApiSignature {


    public static final String OP = "op";
    public static final String OP_VALUE = "auth";
    private static final String ACCESS_KEY_ID = "AccessKeyId";
    private static final String SIGNATURE_METHOD = "SignatureMethod";
    private static final String SIGNATURE_METHOD_VALUE = "HmacSHA256";
    private static final String SIGNATURE_VERSION = "SignatureVersion";
    private static final String SIGNATURE_VERSION_VALUE = "2";
    private static final String TIMESTAMP = "Timestamp";
    private static final String SIGNATURE = "Signature";


    private static final DateTimeFormatter DT_FORMAT = DateTimeFormatter
            .ofPattern("uuuu-MM-dd'T'HH:mm:ss");
    private static final ZoneId ZONE_GMT = ZoneId.of("Z");


    private static long epochNow() {
        return Instant.now().getEpochSecond();
    }

    static String gmtNow() {
        return Instant.ofEpochSecond(epochNow()).atZone(ZONE_GMT).format(DT_FORMAT);
    }

    public void createSignature(String accessKey, String secretKey, String method, String host,
                                String uri, UrlParamsBuilder builder) {
        StringBuilder sb = new StringBuilder(1024);

        if (accessKey == null || "".equals(accessKey) || secretKey == null || "".equals(secretKey)) {
            throw new SdkException(SdkException.KEY_MISSING,
                    "API key and secret key are required");
        }
        sb.append(method).append('\n')
                .append(host).append('\n')
                .append(uri).append('\n');

        builder.putToUrl(ACCESS_KEY_ID, accessKey)
                .putToUrl(SIGNATURE_VERSION, SIGNATURE_VERSION_VALUE)
                .putToUrl(SIGNATURE_METHOD, SIGNATURE_METHOD_VALUE)
                .putToUrl(TIMESTAMP, gmtNow());

        sb.append(builder.buildSignature());
        Mac hmacSha256;
        try {
            hmacSha256 = Mac.getInstance(SIGNATURE_METHOD_VALUE);
            SecretKeySpec secKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8),
                    SIGNATURE_METHOD_VALUE);
            hmacSha256.init(secKey);
        } catch (NoSuchAlgorithmException e) {
            throw new SdkException(SdkException.RUNTIME_ERROR,
                    "[Signature] No such algorithm: " + e.getMessage());
        } catch (InvalidKeyException e) {
            throw new SdkException(SdkException.RUNTIME_ERROR,
                    "[Signature] Invalid key: " + e.getMessage());
        }
        String payload = sb.toString();
        String actualSign = getSignDemo(hmacSha256, payload);
        builder.putToUrl(SIGNATURE, actualSign);
    }

    private String getSignDemo(Mac hmacSha256, String payload) {
        byte[] hash = hmacSha256.doFinal(payload.getBytes(StandardCharsets.UTF_8));
        String actualSign = Base64.getEncoder().encodeToString(hash);
        return actualSign;
    }

}
