package josephus;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;

class JosephusSurvivorTest {

    @Test
    void testInvalidCountThrowsException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, 
            () -> JosephusGameSurvive.getSurvivor(0, 3));
        assertEquals("0 must be greater than 0", thrown.getMessage());
    }

    @Test
    void testInvalidCrossedOutThrowsException() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, 
            () -> JosephusGameSurvive.getSurvivor(5, 0));
        assertEquals("0 must be greater than 0", thrown.getMessage());
    }

    @Test
    void testSinglePerson_ReturnsOne() {
        // Path 3A: If only one person, they should be the survivor
        Assertions.assertEquals(1, JosephusGameSurvive.getSurvivor(1, 1));
    }

    @Test
    void testNormalElimination() {
        // Reverse Josephus(5, 2) → Expected survivor = 4
        assertEquals(3, JosephusGameSurvive.getSurvivor(5, 2));
    }
    @Test
    void testCrossedOutBigger() {
        // Reverse Josephus(5, 2) → Expected survivor = 4
        assertEquals(4, JosephusGameSurvive.getSurvivor(5, 6));
    }
}
