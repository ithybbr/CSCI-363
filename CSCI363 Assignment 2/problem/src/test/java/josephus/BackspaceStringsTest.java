package josephus;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BackspaceStringsTest {
     @Test
    public void testBackspaceCompare() {
        BackspaceStrings sol = new BackspaceStrings();

        assertFalse(sol.backspaceCompare("a", ""));
        assertFalse(sol.backspaceCompare("", "b"));
        assertTrue(sol.backspaceCompare("#", ""));
        assertTrue(sol.backspaceCompare("", "#"));
        assertFalse(sol.backspaceCompare("a#", ""));
        assertFalse(sol.backspaceCompare("", "a#"));
    }
}
