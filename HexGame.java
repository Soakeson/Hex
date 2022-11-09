public class HexGame {
    private UnionFind board;
    private char[] owners;
    private int size;
    private int boardArea;
    private int right, left, top, bottom;

    public HexGame(int size) {
        this.size = size;
        this.boardArea = size * size;
        this.board = new UnionFind(boardArea + 4);

        this.owners = new char[boardArea + 4];
        this.right = boardArea;
        this.left = boardArea + 1;
        this.top = boardArea + 2;
        this.bottom = boardArea + 3;

        for (int i=0; i<owners.length; i++)
            owners[i] = '0';

        owners[right] = 'B'; owners[left] = 'B'; // Blue fences
        owners[top] = 'R'; owners[bottom] = 'R'; // Red fences
    }

    public void takeOwnership(boolean player, int space) {

    }

    public void updateSets() {

    }

    public String toString(){
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_RESET = "\u001B[0m";
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<boardArea; i++) {
            String color = ANSI_RESET;
            if (owners[i] != '0') // If cell is non-empty decide the color
                color = (owners[i] == 'R') ? (ANSI_RED):(ANSI_BLUE);

            if (i % size == 0) {
                sb.append("\n");
                for(int j=i/size; j>0; j--)
                    sb.append(" ");
            }

            sb.append(color + owners[i] + " " + ANSI_RESET);
        }
        return sb.toString();
    }
}
