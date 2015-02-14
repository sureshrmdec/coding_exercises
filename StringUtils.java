import java.io.*;
import java.util.*;
import java.security.SignatureException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class StringUtils {
  private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

  public static class Option<T> {
    private final T value;
    private Option(T value) {
      this.value = value;
    }
    public static <T> Option<T> Some(T value) {
      return new Option(value);
    }
    public static <T> Option<T> None() {
      return new Option(null);
    }
    public String toString() {
      return "" + value;
    }
  }
  public static Option<Character> getNonrepeatingCharacter(String s) {
    Map<Character, Integer> counts = new LinkedHashMap<>();
    for (int i=0; i<s.length(); i++) {
      char ch = s.charAt(i);
      Integer count = counts.get(ch);
      if (count == null) {
        count = 1;
      } else {
        count++;
      }
      counts.put(ch, count);
    }
    for (Map.Entry<Character, Integer> e : counts.entrySet()) {
      if (e.getValue() == 1) {
        return Option.Some(e.getKey());
      }
    }
    return Option.None();
  }
  public static String calculateRFC2104HMAC(String data, String key)
    throws java.security.SignatureException {
    String result;
    try {
      // get an hmac_sha1 key from the raw key bytes
      SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);

      // get an hmac_sha1 Mac instance and initialize with the signing key
      Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
      mac.init(signingKey);

      // compute the hmac on input data bytes
      byte[] rawHmac = mac.doFinal(data.getBytes());

      // base64-encode the hmac_sha1 
      result = Base64.encodeBytes(rawHmac);
      //
    } catch (Exception e) {
      throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
    }
    return result;
  }
  public static void main(String[] args) {
    Option<Character> oc = getNonrepeatingCharacter("aaab");
    System.out.println(oc);
    oc = getNonrepeatingCharacter("ababcd");
    System.out.println(oc);
  }
}

