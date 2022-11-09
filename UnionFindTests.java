import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class UnionFindTests {
    @Test
    public void smartUnionTest() {
        UnionFind dj = new UnionFind(10);
        dj.union(3, 2); // Set 3 is bigger than set 8 therfore 3 will be the root of 8 if the smart union is working properly.
        dj.union(3, 4);
        dj.union(3, 5);
        dj.union(8, 9);
        dj.union(9, 3);
        assertEquals(3, dj.find(8));
    }

    @Test
    public void pathCompressionTest() {
        UnionFind dj = new UnionFind(10);
        dj.union(1, 2);
        dj.union(2, 3);
        dj.union(9, 8);
        dj.union(7, 8);
        dj.union(8, 1);
        assertEquals("[0:-1 1:9 2:1 3:1 4:-1 5:-1 6:-1 7:9 8:9 9:-6 ]", dj.toString());

        dj.find(3);
        assertEquals("[0:-1 1:9 2:1 3:9 4:-1 5:-1 6:-1 7:9 8:9 9:-6 ]", dj.toString());


    }
}
