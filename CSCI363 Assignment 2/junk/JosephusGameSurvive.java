package josephus;

public class JosephusGameSurvive {
    private int count;
    private int crossedOut;
    private int survivor;
    public JosephusGameSurvive(int count, int crossedOut){
        this.count = count;
        this.crossedOut = crossedOut;
        this.survivor = getSurvivor(count, crossedOut);
    }

    public static int getSurvivor(int count, int crossedOut) {
        if (count < 1) {
            throw new IllegalArgumentException(count + " must be greater than 0");
        }

        if (crossedOut < 1) {
            throw new IllegalArgumentException(crossedOut + " must be greater than 0");
        }

        int winner = 0;
        for (int i = 1; i <= count; i++) {
            winner = (crossedOut + winner) % i;
        }

        return winner + 1;
    }

    
    
    public int GetCount(){
        return this.count;
    }
    public int GetCrossedOut(){
        return this.crossedOut;
    }
    public int GetSurvivor(){
        return this.survivor;
    }
}

