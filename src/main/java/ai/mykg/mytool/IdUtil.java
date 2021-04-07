package ai.mykg.mytool;

import ai.mykg.mytool.convert.AbvConverter;
import ai.mykg.mytool.convert.BaseConverter;
import ai.mykg.mytool.convert.ConvertException;
import java.nio.ByteBuffer;
import java.util.UUID;
import org.apache.commons.codec.binary.Base64;

/**
 * @author Goddy [goddy@mykg.ai] 2021/3/5
 * @since 0.0.1
 */
public class IdUtil {

  private static BaseConverter shortConverter;

  /**
   * generate uuid
   *
   * @return uuid string
   * @since 0.0.1
   */
  public static String newUuid() {
    return UUID.randomUUID().toString();
  }

  /**
   * generate short uuid
   *
   * @return short uuid
   * @since 0.0.1
   */
  public static String shortUuid() {
    String uuid = newUuid();
    return shortUuid(uuid);
  }

  /**
   * generate short uuid from uuid
   *
   * @param uuid uuid
   * @return short uuid
   * @since 0.0.1
   */
  public static String shortUuid(String uuid) {
    return shortConverter.convert(
        uuid.toLowerCase().replaceAll("-", ""));
  }

  /**
   * revert short uuid to normal uuid
   *
   * @param shortUuid short uuid
   * @return uuid
   * @since 0.0.1
   */
  public static String fromShortUuid(String shortUuid) {
    String uuid = shortConverter.revert(shortUuid);
    for (int i = 0, len = 32 - uuid.length(); i < len; i++) {
      uuid = "0".concat(uuid);
    }
    return uuid.replaceFirst(
        "([0-9a-fA-F]{8})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]+)",
        "$1-$2-$3-$4-$5");
  }

  /**
   * revert short uuid from base64
   *
   * @return short uuid
   * @since 0.0.5
   */
  public static String uuid2base64() {
    String uuid = newUuid();
    return uuid2base64(uuid);
  }

  /**
   * generate short uuid from base64
   *
   * @param str uuid string
   * @return short uuid
   * @since 0.0.5
   */
  public static String uuid2base64(String str) {
    UUID uuid = UUID.fromString(str);
    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    bb.putLong(uuid.getMostSignificantBits());
    bb.putLong(uuid.getLeastSignificantBits());
    return Base64.encodeBase64URLSafeString(bb.array());
  }

  /**
   * revert short uuid from base64
   *
   * @param str short uuid
   * @return uuid string
   * @since 0.0.5
   */
  public static String uuidFromBase64(String str) {
    byte[] bytes = Base64.decodeBase64(str);
    ByteBuffer bb = ByteBuffer.wrap(bytes);
    UUID uuid = new UUID(bb.getLong(), bb.getLong());
    return uuid.toString();
  }

  /**
   * generate bv
   *
   * @param av av id
   * @return bv str
   * @since 0.0.5
   */
  public static String av2bv(long av) {
    return AbvConverter.av2bv(av);
  }

  /**
   * revert bv to av
   * @param bv bv string
   * @return av id
   */
  public static long bv2av(String bv) {
    return AbvConverter.bv2av(bv);
  }

  // === private ===

  static {
    try {
      shortConverter = new BaseConverter(BaseConverter.HEX, BaseConverter.BASE58);
    } catch (ConvertException e) {
      e.printStackTrace();
    }
  }
}
