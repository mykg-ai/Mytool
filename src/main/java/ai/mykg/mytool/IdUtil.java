package ai.mykg.mytool;

import ai.mykg.mytool.convert.BaseConverter;
import ai.mykg.mytool.convert.ConvertException;
import java.util.UUID;

/**
 * @author Goddy [goddy@mykg.ai] 2021/3/5
 * @since 0.0.1
 */
public class IdUtil {

  private static BaseConverter shortConverter;

  /**
   * generate short uuid
   *
   * @return short uuid
   * @since 0.0.1
   */
  public static String shortUuid() {
    String uuid = UUID.randomUUID().toString();
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

  // === private ===

  static {
    try {
      shortConverter = new BaseConverter(BaseConverter.HEX, BaseConverter.BASE58);
    } catch (ConvertException e) {
      e.printStackTrace();
    }
  }
}
