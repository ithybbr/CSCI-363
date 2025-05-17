package josephus;

import java.util.Stack;
public class BackspaceStrings {
    public boolean backspaceCompare(String s, String t) {
        Stack<Character> sc = new Stack<>();
        Stack<Character> tc = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == '#') {
                if (!sc.isEmpty()) sc.pop();
            } else {
                sc.push(ch);
            }
        }

        for (char ch : t.toCharArray()) {
            if (ch == '#') {
                if (!tc.isEmpty()) tc.pop();
            } else {
                tc.push(ch);
            }
        }

        return sc.equals(tc);
    }
}
