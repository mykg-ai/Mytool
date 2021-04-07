package ai.mykg.mytool.convert;

import java.util.HashMap;

/**
 * @author Goddy [goddy@mykg.ai] 2021/4/7
 *
 * <pre>
 *   av:
 *   bv:
 * </pre>
 *
 * <pre>
 * reference:
 *   - @see <a href="https://www.bilibili.com/blackboard/activity-BV-PC.html">bilibili-announcement</a>
 *   - @see <a href="https://github.com/Goodjooy/BilibiliAV2BV/blob/master/src/net/augcloud/BilibiliA2B.java">github-BilibiliA2B.java</a>
 *   - @see <a href="https://blog.csdn.net/weixin_44735156/article/details/107580637">csdn-av_BV</a>
 * </pre>
 * @since 0.0.5
 */
public class AbvConverter {

  private static final String alphabet = "fZodR9XQDSUm21yCkr6zBqiveYah8bt4xsWpHnJE7jL5VG3guMTKNPAwcF";
  private static final String[] wordList = alphabet.split("");
  private static final int[] s = {11, 10, 3, 8, 4, 6};
  private static final int xor = 177451812;
  private static final long add = 8728348608L;
  private static final HashMap<String, Integer> number2wordMap;

  static {
    number2wordMap = new HashMap<String, Integer>();
    for (int i = 0; i < wordList.length; i++) {
      number2wordMap.put(wordList[i], i);
    }
  }

  /**
   * @param av av
   * @return bv
   */
  public static String av2bv(long av) {
    av = (av ^ xor) + add;
    String r = "BV1  4 1 7  ";
    for (int i = 0; i < 6; i++) {
      int updatedIndex = (int) ((av / Math.pow(58, i)) % 58);
      r = topOffIndex(r, wordList[updatedIndex], s[i]);
    }
    return r.replace("BV", "");
  }

  /**
   * @param str bv
   * @return av
   */
  public static long bv2av(String str) {
    str = "BV" + str;
    long r = 0;
    String[] wordList = str.split("");
    for (int i = 0; i < 6; i++) {
      r += number2wordMap.get(wordList[s[i]]) * (long)Math.pow(58, i);
    }
    return (r - add) ^ xor;
  }

  // === private ===

  private static String topOffIndex(String before, String updated, int index) {
    return String.format("%s%s%s",
        before.substring(0, index),
        updated,
        before.substring(index + 1));
  }
}
