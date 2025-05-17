package josephus;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;

class JosephusSequenceTest {

    @Test
    void testInvalidCountThrowsException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, 
            () -> JosephusGame.Sequence(0, 3));
        assertEquals("0 must be greater than 0", thrown.getMessage());
    }

    @Test
    void testInvalidCrossedOutThrowsException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, 
            () -> JosephusGame.Sequence(5, 0));
        assertEquals("0 must be greater than 0", thrown.getMessage());
    }

    @Test
    void testSinglePerson_ReturnsOne() {
        // Path 3A: If only one person, they should be the survivor
        Assertions.assertEquals(List.of(), JosephusGame.Sequence(1, 1));
    }

    @Test
    void testNormalElimination() {
        // Reverse Josephus(5, 2) → Expected survivor = 4
        assertEquals(List.of(2,4,1,5), JosephusGame.Sequence(5, 2));
    }
    @Test
    void testCrossedOutBigger() {
        // Reverse Josephus(5, 2) → Expected survivor = 4
        assertEquals(List.of(1,3,2,5), JosephusGame.Sequence(5, 6));
    }
}
