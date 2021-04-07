package ai.mykg.mytool.convert;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AbvConverterTest {

  @Test
  void av2bv() {
    assertEquals(AbvConverter.av2bv(170001), "17x411w7KC");
  }

  @Test
  void bv2av() {
    assertEquals(AbvConverter.bv2av("17x411w7KC"), 170001);
  }

  @Test
  void extreme() {
    int[] nums = new int[]{1, 10, 100_0000, 9_9999_9999};
    for (int av : nums) {
      String bv = AbvConverter.av2bv(av);
      System.out.println("av: " + av);
      System.out.println("bv: " + bv);
      assertEquals(AbvConverter.bv2av(bv), av);
    }
  }
}