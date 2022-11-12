/*
 * Hex State keeps track of the current state of the game.
 * It contains methods to update the state of the game and format the current state to a String.
 */
public class HexState {
    private int winner = 0;
    private UnionFind sets;
    private char[] board;
    private int size;
    private int boardArea;
    private final int right, left, top, bottom;
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";

    public HexState(int size) {
        this.size = size;
        this.boardArea = (size * size) + 1;
        this.sets = new UnionFind(boardArea + 4);
        this.board = new char[boardArea + 4];
        for (int i=1; i<board.length; i++) // Initialize board and fill it with empty spaces
            board[i] = '0';

        this.right = boardArea; this.left = boardArea+1; this.top = boardArea+2; this.bottom = boardArea+3; // Calculate fence positions
        board[right] = 'B'; board[left] = 'B'; // Blue fences
        board[top] = 'R'; board[bottom] = 'R'; // Red fences
    }

    /**
     * Updates game state everytime a player takes control of a new cell. 
     * @param player taking control of a given cell. 'R'(red) || 'B'(blue)
     * @param cell index
     */
    public void update(char player, int cell) {
        if (player == 'R' || player == 'B') {
            board[cell] = player;
        }

        findNeighbors(cell);
        if (sets.find(right) == sets.find(left)) {
            winner = -1;
        }
        if (sets.find(top) == sets.find(bottom)) {
            winner = 1;
        }
    }

    /**
     * This is a wrapper function to check for any special cases.
     * Calculates the neighboring values for the given cell index.
     * Passes the neighbor values off to a private function to iterate through all the neighbor cells.
     * @param cell index center point to calculate neighbors around.
     */
    private void findNeighbors(int cell) {
        // Calculate all possible neighbors
        int[] offset = {cell-size, cell-size+1, cell-1, cell+1, cell+size-1, cell+size};
        if (cell == 1) { // Top Left Corner
            int[] neighbors = {offset[3], offset[5], left, top};
            findNeighbors(cell, neighbors);
            return;
        }
        if (cell == size) { // Top Right Corner
            int[] neighbors = {offset[2], offset[4], offset[5], right, top};
            findNeighbors(cell, neighbors);
            return;
        }
        if (cell == boardArea - (size -1)) { // Bottom Left Corner
            int[] neighbors = {offset[0], offset[1], offset[3], left, bottom};
            findNeighbors(cell, neighbors);
            return;
        }
        if (cell == boardArea) { // Bottom Right Corner
            int[] neighbors = {offset[0], offset[2], right, bottom};
            findNeighbors(cell, neighbors);
            return;
        }
        if (cell <= size) { // Top Edge
            int[] neighbors = {offset[2], offset[3], offset[4], offset[5], top};
            findNeighbors(cell, neighbors);
            return;
        }
        if (cell % size == 1) { // Left Edge
            int[] neighbors = {offset[0], offset[1], offset[3], offset[5], left};
            findNeighbors(cell, neighbors);
            return; 
        }
        if (cell % size == 0) { // Right Edge
            int[] neighbors = {offset[0], offset[2], offset[4], offset[5], right};
            findNeighbors(cell, neighbors);
            return;
        }
        if (cell > (size * size) - (size - 1)) { // Bottom Edge
            int[] neighbors = {offset[0], offset[1], offset[2], offset[3], bottom};
            findNeighbors(cell, neighbors);
            return;
        }
        findNeighbors(cell, offset); // Middle cell
    }

    /** Iterates through a given list of neihboring cells and unions the ones that are owned by the same player.
     * @param cell index.
     * @param neighbors list of neihboring cell values to check.
     */
    private void findNeighbors(int cell, int[] neighbors) {
        for (int neighbor: neighbors) {
            if (board[cell] == board[neighbor]) {
                sets.union(cell, neighbor);
            }
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();

        for (int i=1; i<boardArea; i++) {
            String color = ANSI_RESET;
            if (board[i] != '0') // If cell is non-empty decide the color depending on player
                color = (board[i] == 'R') ? (ANSI_RED):(ANSI_BLUE);

            if (i % size == 1) { // If the next row needs to start
                sb.append("\n");
                for(int j=i/size; j>0; j--) // Formats the spaces to make a rhombus shape
                    sb.append(" ");
            }

            sb.append(color + board[i] + " ");
        }
        return sb.toString();
    }

    public int getWinner() {
        return winner;
    }

    public void debug() {
        System.out.println(sets);
    }
}
