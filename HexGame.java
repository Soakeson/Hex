public class HexGame {
    public UnionFind sets;
    private char[] board;
    private int size;
    private int boardArea;
    private int right, left, top, bottom;
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";

    public HexGame(int size) {
        this.size = size;
        this.boardArea = size * size;
        this.sets = new UnionFind(boardArea + 4);

        this.board = new char[boardArea + 4];
        this.right = boardArea;
        this.left = boardArea + 1;
        this.top = boardArea + 2;
        this.bottom = boardArea + 3;

        for (int i=0; i<board.length; i++)
            board[i] = '0';

        board[right] = 'B'; board[left] = 'B'; // Blue fences
        board[top] = 'R'; board[bottom] = 'R'; // Red fences
    }

    public void update(char player, int cell) {
        if (player == 'R')
            board[cell] = player;
        if (player == 'B')
            board[cell] = player;
        findNeighbors(cell);
    }

    /**
     * This is a wrapper function to check for any special cases.
     * Passes the offsets off to a private function to iterate through all the neighbors
     * @param cell 
     */
    private void findNeighbors(int cell) {
        int[] offsets = {-size, -size+1, -1, 1, size-1, size};
        if (cell == 0) { // Top Left Corner
            int[] slice = {offsets[3], offsets[5]};
            findNeighbors(cell, slice);
            return;
        }
        if (cell == size-1) { // Top Right Corner
            int[] slice = {offsets[2], offsets[4], offsets[5]};
            findNeighbors(cell, slice);
            return;
        }
        if (cell == boardArea - size) { // Bottom Left Corner
            int[] slice = {offsets[0], offsets[1], offsets[3]};
            findNeighbors(cell, slice);
            return;
        }
        if (cell == boardArea-1) { // Bottom Right Corner
            int[] slice = {offsets[0], offsets[2]};
            findNeighbors(cell, slice);
            return;
        }
        if (cell < size) { // Top Edge
            int[] slice = {offsets[2], offsets[3], offsets[4], offsets[5]};
            findNeighbors(cell, slice);
            return;
        }
        if (cell % size == 0) { // Left Edge
            int[] slice = {offsets[0], offsets[1], offsets[3], offsets[5]};
            findNeighbors(cell, slice);
            return; 
        }
        if ((cell + 1) % size == 0) { // Right Edge
            int[] slice = {offsets[0], offsets[2], offsets[4], offsets[5]};
            findNeighbors(cell, slice);
            return;
        }
        if (cell > (size * size) - size) { // Bottom Edge
            int[] slice = {offsets[0], offsets[1], offsets[2], offsets[3]};
            findNeighbors(cell, slice);
            return;
        }
        findNeighbors(cell, offsets); // Middle cell
    }

    private void findNeighbors(int cell, int[] offsets) {
        for (int o: offsets) {
            if (board[cell] == board[cell+o]) {
                sets.union(cell, cell+o);
            }
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<boardArea; i++) {
            String color = ANSI_RESET;
            if (board[i] != '0') // If cell is non-empty decide the color depending on player
                color = (board[i] == 'R') ? (ANSI_RED):(ANSI_BLUE);

            if (i % size == 0) { // If the cell is a new line
                sb.append("\n");
                for(int j=i/size; j>0; j--) // Formats the spaces to get a rhombus
                    sb.append(" ");
            }

            sb.append(color + board[i] + " ");
        }
        return sb.toString();
    }

    public void debug() {
        System.out.println(toString());
        System.out.println(sets);
    }
}
