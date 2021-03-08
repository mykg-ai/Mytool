package ai.mykg.mytool.convert;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @since 0.0.1
 * @author Goddy <goddy@mykg.ai> 2021/3/5
 */
public class BaseConverter implements Serializable {

  private static final long serialVersionUID = -343663115496966264L;
  // BINARY
  public static final String BIN = "01";
  // OCTAL
  public static final String OCT = "01234567";
  // DECIMAL
  public static final String DEC = "0123456789";
  // HEXADECIMAL
  public static final String HEX = "0123456789abcdef";
  // delete 0, l, I, O
  public static final String BASE58 = "123456789abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ";

  private final String srcAlphabet;
  private final String distAlphabet;
  private final Boolean isSameAlphabet;

  public BaseConverter(String srcAlphabet, String distAlphabet) throws ConvertException {
    checkAlphabet(srcAlphabet);
    checkAlphabet(distAlphabet);
    this.srcAlphabet = srcAlphabet;
    this.distAlphabet = distAlphabet;
    this.isSameAlphabet = srcAlphabet.equals(distAlphabet);
  }

  /**
   * convert str from srcAlphabet to distAlphabet
   *
   * @param str words from srcAlphabet
   * @return words from distAlphabet
   */
  public String convert(String str) {
    if (this.isSameAlphabet) {
      return str;
    }
    return convert(this.srcAlphabet, this.distAlphabet, str);
  }

  public String revert(String str) {
    if (this.isSameAlphabet) {
      return str;
    }
    return convert(this.distAlphabet, this.srcAlphabet, str);
  }


  // === private ===

  private static String convert(String srcAlphabet, String distAlphabet, String str) {
    int fromBase = srcAlphabet.length();
    int toBase = distAlphabet.length();
    int len = str.length();
    Integer[] numArray = new Integer[str.length()];

    for (int i = 0; i < len; i++) {
      numArray[i] = srcAlphabet.indexOf(str.charAt(i));
    }

    int divide;
    int newLen;
    String result = "";
    do {
      divide = 0;
      newLen = 0;
      for (int i = 0; i < len; i++) {
        divide = divide * fromBase + numArray[i];
        if (divide >= toBase) {
          numArray[newLen] = divide / toBase;
          newLen++;
          divide = divide % toBase;
        } else if (newLen > 0) {
          numArray[newLen] = 0;
          newLen++;
        }
      }
      len = newLen;
      result = distAlphabet.substring(divide, divide + 1).concat(result);
    } while (newLen != 0);

    return result;
  }

  /**
   * @param alphabet string without blank or duplicate char
   */
  private void checkAlphabet(String alphabet) throws ConvertException {
    int strLen;
    if (alphabet == null || (strLen = alphabet.length()) == 0) {
      throw new ConvertException("Empty alphabet!");
    }
    Set<Character> chars = new HashSet<Character>();
    for (int i = 0; i < strLen; i++) {
      Character c = alphabet.charAt(i);
      if (Character.isWhitespace(c) || chars.contains(c)) {
        throw new ConvertException("Bad alphabet!");
      } else {
        chars.add(c);
      }
    }
  }
}
