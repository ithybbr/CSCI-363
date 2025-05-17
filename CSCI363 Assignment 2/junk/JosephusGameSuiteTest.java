package josephus;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.jupiter.api.Assertions;
import java.util.List;

@Suite
@SelectClasses({JosephusSequenceTest.class})
public class JosephusGameSuiteTest {
    @Test
    void TestTheRest(){
        JosephusGame game = new JosephusGame(2, 5);
        Assertions.assertNotNull(game);
        Assertions.assertEquals(2, game.GetCount());
        Assertions.assertEquals(5, game.GetCrossedOut());
        Assertions.assertEquals(List.of(1), game.GetSequence());
    }
}
