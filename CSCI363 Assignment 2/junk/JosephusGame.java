package josephus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class JosephusGame {
    private int count;
    private int crossedOut;
    public JosephusGame(int count, int crossedOut){
        this.count = count;
        this.crossedOut = crossedOut;
    }

    public static List<Integer> Sequence(int count, int crossedOut) {
        if (count < 1) {
            throw new IllegalArgumentException(count + " must be greater than 0");
        }
    
        if (crossedOut < 1) {
            throw new IllegalArgumentException(crossedOut + " must be greater than 0");
        }
    
        LinkedList<Integer> circle = new LinkedList<>();
        List<Integer> eliminationOrder = new ArrayList<>();

        // Initialize the linked list with numbers from 1 to count
        for (int i = 1; i <= count; i++) {  // (1) Loop
            circle.add(i);
        }

        Iterator<Integer> iterator = circle.iterator();

        while (circle.size() > 1) {  // (2) Loop
            // Move the iterator 'crossedOut - 1' steps forward
            for (int i = 0; i < crossedOut - 1; i++) {  // (3) Loop
                if (!iterator.hasNext()) {  // (4) If condition
                    iterator = circle.iterator();  // Reset iterator
                }
                iterator.next();
            }

            // Remove the crossed-out person
            if (!iterator.hasNext()) {  // (5) If condition
                iterator = circle.iterator();  // Reset iterator
            }
            int crossedOutPerson = iterator.next();
            iterator.remove();

            // Store elimination order
            eliminationOrder.add(crossedOutPerson);
        }
        return eliminationOrder;
    }

    
    
    public int GetCount(){
        return this.count;
    }
    public int GetCrossedOut(){
        return this.crossedOut;
    }
    public List<Integer> GetSequence(){
        List<Integer> seq = Sequence(this.count, this.crossedOut);
    return seq;
    }
}

