package ai.mykg.mytool;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class IdUtilTest {

  private final String uuid = UUID.randomUUID().toString();

  @Test
  void testShortUuid() {
    String shortUuid = IdUtil.shortUuid(uuid);
    System.out.println(uuid);
    System.out.println(shortUuid);
    assertEquals(uuid, IdUtil.fromShortUuid(shortUuid));
  }
}