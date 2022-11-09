public class UnionFind {
    public int[] sets;

    public UnionFind(int numSets) {
        sets = new int[numSets];
        for (int i=0; i<numSets; i++) {
            sets[i] = -1;
        }
    }

    /**
     * Unions the smaller set to the larger set and updates the size of the set (Smart union).
     * @param a Set a to be unioned.
     * @param b Set b to be unioned.
     */
    public void union(int a, int b) {
        int aR = find(a); // Root of a
        int bR = find(b); // Root of b
        if (aR == bR) return;
        if (sets[aR] <= sets[bR]) { // The number of nodes is kept as a negative integer. (Smart union)
            sets[aR] += sets[bR];
            sets[bR] = aR;
        } else { // bRoot is larger
            sets[bR] += sets[aR];
            sets[aR] = bR;
        }       
    }
    
    /**
     * Finds the root of a given value, k. Uses path compression to increase performance.
     * @param k The values whose root will be searched for.
     * @return The values root value.
     */
    public int find(int k) {
        int root = k;
        if (sets[k] >= 0) {
            root = find(sets[root]);
            sets[k] = root; // Sets the values to the root value to decrease search time. (Path Compression)
        }
        return root;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i=0; i<sets.length; i++) {
            sb.append(i + ":" + sets[i] + " ");
        }
        sb.append("]");
        return sb.toString();
    }
}