package ai.mykg.mytool;

/**
 * @author Goddy <goddy@mykg.ai> 2021/3/6
 * @since 0.0.1
 */
public class StringUtil {

  /**
   * <pre>
   * StringUtils.isBlank(null)      = true
   * StringUtils.isBlank("")        = true
   * StringUtils.isBlank(" ")       = true
   * StringUtils.isBlank("bob")     = false
   * StringUtils.isBlank("  bob  ") = false
   * </pre>
   *
   * @param cs the CharSequence to check, may be null
   * @return true if the CharSequence is null, empty or whitespace only
   * @since 0.0.1
   */
  public static boolean isBlank(final CharSequence cs) {
    int strLen;
    if (cs == null || (strLen = cs.length()) == 0) {
      return true;
    }
    for (int i = 0; i < strLen; i++) {
      if (!Character.isWhitespace(cs.charAt(i))) {
        return false;
      }
    }
    return true;
  }

  /**
   * <pre>
   * StringUtils.isNotBlank(null)      = false
   * StringUtils.isNotBlank("")        = false
   * StringUtils.isNotBlank(" ")       = false
   * StringUtils.isNotBlank("bob")     = true
   * StringUtils.isNotBlank("  bob  ") = true
   * </pre>
   *
   * @param cs the CharSequence to check, may be null
   * @return true if the CharSequence is
   *  not empty and not null and not whitespace only
   * @since 0.0.1
   */
  public static boolean isNotBlank(final CharSequence cs) {
    return !isBlank(cs);
  }

  /**
   * <pre>
   * StringUtils.isEmpty(null)      = true
   * StringUtils.isEmpty("")        = true
   * StringUtils.isEmpty(" ")       = false
   * StringUtils.isEmpty("bob")     = false
   * StringUtils.isEmpty("  bob  ") = false
   * </pre>
   *
   * @param cs the CharSequence to check, may be null
   * @return true if the CharSequence is empty or null
   * @since 0.0.1
   */
  public static boolean isEmpty(final CharSequence cs) {
    return cs == null || cs.length() == 0;
  }

  /**
   * <pre>
   * StringUtils.isNotEmpty(null)      = false
   * StringUtils.isNotEmpty("")        = false
   * StringUtils.isNotEmpty(" ")       = true
   * StringUtils.isNotEmpty("bob")     = true
   * StringUtils.isNotEmpty("  bob  ") = true
   * </pre>
   *
   * @param cs the CharSequence to check, may be null
   * @return true if the CharSequence is not empty and not null
   * @since 0.0.1
   */
  public static boolean isNotEmpty(final CharSequence cs) {
    return !isEmpty(cs);
  }
}
