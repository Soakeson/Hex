import javax.sound.midi.Soundbank;

public class DisjointSet {
    private int[] sets;

    public DisjointSet(int numSets) {
        sets = new int[numSets];
        for (int i=0; i<numSets; i++) {
            sets[i] = -1;
        }
    }

    public void union() {
        
    }

    public void find() {
        
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int set: sets) {
            sb.append(set + ", ");
        }
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        DisjointSet dj = new DisjointSet(12);
        System.out.println(dj);

    }
}