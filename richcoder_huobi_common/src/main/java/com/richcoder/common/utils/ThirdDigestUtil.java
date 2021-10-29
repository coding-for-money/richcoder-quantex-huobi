package com.richcoder.common.utils;

import static java.nio.charset.StandardCharsets.UTF_8;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class ThirdDigestUtil {

  private static final String HMAC_SHA_256 = "HmacSHA256";
  private static final String HMAC_SHA_384 = "HmacSHA384";
  private static final String HMAC_SHA_512 = "HmacSHA512";
  private static final String HMAC_SHA_1 = "hmacSHA1";
  private static final String HMAC_MD_5 = "HmacMD5";

  private ThirdDigestUtil() {
  }

  public static String hmacSHA256Hex(String key, String data) throws NoSuchAlgorithmException, InvalidKeyException {
    Mac sha256HMAC = Mac.getInstance(HMAC_SHA_256);
    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(UTF_8), HMAC_SHA_256);
    sha256HMAC.init(secretKey);
    return Hex.encodeHexString(sha256HMAC.doFinal(data.getBytes(UTF_8)));
  }

  public static String hmacSHA256Base64(String key, String data) throws NoSuchAlgorithmException, InvalidKeyException {
    Mac sha256HMAC = Mac.getInstance(HMAC_SHA_256);
    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(UTF_8), HMAC_SHA_256);
    sha256HMAC.init(secretKey);
    byte[] bytes = sha256HMAC.doFinal(data.getBytes(UTF_8));
    return Base64.getEncoder().encodeToString(bytes);
  }

  public static String hmacSHA384Hex(String key, String data) throws NoSuchAlgorithmException, InvalidKeyException {
    Mac hmacMD5Mac = Mac.getInstance(HMAC_SHA_384);
    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(UTF_8), HMAC_SHA_384);
    hmacMD5Mac.init(secretKey);
    return Hex.encodeHexString(hmacMD5Mac.doFinal(data.getBytes(UTF_8)));
  }

  public static String hmacSHA512Hex(String key, String data) throws NoSuchAlgorithmException, InvalidKeyException {
    Mac hmacMD5Mac = Mac.getInstance(HMAC_SHA_512);
    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(UTF_8), HMAC_SHA_512);
    hmacMD5Mac.init(secretKey);
    return Hex.encodeHexString(hmacMD5Mac.doFinal(data.getBytes(UTF_8)));
  }

  public static String hmacSHA512Base64(String key, String data) throws NoSuchAlgorithmException, InvalidKeyException {
    Mac hmacMD5Mac = Mac.getInstance(HMAC_SHA_512);
    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(UTF_8), HMAC_SHA_512);
    hmacMD5Mac.init(secretKey);
    byte[] bytes = hmacMD5Mac.doFinal(data.getBytes(UTF_8));
    byte[] encode = new Hex().encode(bytes);
    return Base64.getEncoder().encodeToString(encode);
  }

  public static String hmacMD5Hex(String key, String data) throws NoSuchAlgorithmException, InvalidKeyException {
    Mac hmacMD5Mac = Mac.getInstance(HMAC_MD_5);
    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(UTF_8), HMAC_MD_5);
    hmacMD5Mac.init(secretKey);
    return Hex.encodeHexString(hmacMD5Mac.doFinal(data.getBytes(UTF_8)));
  }

  public static String md5HexUpper(String data) {
    return DigestUtils.md5Hex(data).toUpperCase();
  }

  private static byte[] hmacSHA1(String key, String data) throws NoSuchAlgorithmException, InvalidKeyException {
    Mac instance = Mac.getInstance(HMAC_SHA_1);
    SecretKey secretKey = new SecretKeySpec(key.getBytes(UTF_8), HMAC_SHA_1);
    instance.init(secretKey);
    return instance.doFinal(data.getBytes(UTF_8));
  }

  public static String fCoinHmacSHA1(String key, String data) throws InvalidKeyException, NoSuchAlgorithmException {
    byte[] encode = Base64.getEncoder().encode(data.getBytes(UTF_8));
    String base64Str = new String(encode, UTF_8);
    byte[] bytes = hmacSHA1(key, base64Str);
    byte[] twiceBase64 = Base64.getEncoder().encode(bytes);
    return new String(twiceBase64, UTF_8);
  }



  public static String coinEggHmacSHA256(String value, String key) throws NoSuchAlgorithmException, InvalidKeyException {
    // Get an hmac_sha1 key from the raw key bytes
    byte[] keyBytes = key.getBytes();
    SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA_256);
    // Get an hmac_sha1 Mac instance and initialize with the signing key
    Mac mac = Mac.getInstance(HMAC_SHA_256);
    mac.init(signingKey);
    // Compute the hmac on input data bytes
    byte[] rawHmac = mac.doFinal(value.getBytes());
    // Convert raw bytes to Hex
    String hexBytes = "";
    String stmp = "";
    for (int n = 0; n < rawHmac.length; n++) {
      stmp = (Integer.toHexString(rawHmac[n] & 0xFF));
      if (stmp.length() == 1) {
        hexBytes = hexBytes + "0" + stmp;
      } else {
        hexBytes = hexBytes + stmp;
      }
    }
    return hexBytes;

  }


  public static String coinEggMD5(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    byte[] hash = MessageDigest.getInstance("MD5").digest(
        string.getBytes("UTF-8"));
    StringBuilder hex = new StringBuilder(hash.length * 2);
    for (byte b : hash) {
      if ((b & 0xFF) < 0x10) {
        hex.append("0");
      }
      hex.append(Integer.toHexString(b & 0xFF));
    }
    return hex.toString();
  }


  /**
   * 私钥签名
   *
   * @param content
   * @param privateKey
   * @return
   */
  public static String lbankRSA_sign(String content, String privateKey) {
    try {
      PrivateKey priKey = getPrivateKey(privateKey);
      Signature signature = Signature.getInstance("SHA256WithRSA");
      signature.initSign(priKey);
      signature.update(content.getBytes(CharEncoding.UTF_8));
      byte[] signed = signature.sign();
      return new String(Base64.getEncoder().encode(signed));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private static PrivateKey getPrivateKey(String key) {
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));
    PrivateKey privateKey = null;
    try {
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      privateKey = keyFactory.generatePrivate(keySpec);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      e.printStackTrace();
    }
    return privateKey;
  }

  public static TreeMap<String, String> sortedByKey(Map<String, String> map) {
    TreeMap<String, String> sortedMap = new TreeMap<String, String>(
        new Comparator<String>() {
          public int compare(String obj1, String obj2) {
            //sort in alphabet order
            return obj1.compareTo(obj2);
          }
        });
    if (null != map && map.size() > 0) {
      for (Map.Entry<String, String> entry : map.entrySet()) {
        sortedMap.put(entry.getKey(), entry.getValue());
      }
    }
    return sortedMap;
  }

  /**
   * for byBit
   * @param params
   * @param secret
   * @return
   */
  public static String genQueryString(TreeMap<String, String> params, String secret) {
    try {
      Set<String> keySet = params.keySet();
      Iterator<String> iter = keySet.iterator();
      StringBuilder sb = new StringBuilder();
      while (iter.hasNext()) {
        String key = iter.next();
        sb.append(key + "=" + params.get(key));
        sb.append("&");
      }
      sb.deleteCharAt(sb.length() - 1);
      Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
      SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
      sha256_HMAC.init(secret_key);
      return sb + "&sign=" + bytesToHex(sha256_HMAC.doFinal(sb.toString().getBytes()));
    } catch (NoSuchAlgorithmException e) {
    } catch (InvalidKeyException e) {
    }
    return "";
  }

  private static String bytesToHex(byte[] hash) {
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < hash.length; i++) {
      String hex = Integer.toHexString(0xff & hash[i]);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }

}
