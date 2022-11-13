import java.io.File;
import java.util.Scanner;

/**
 * HexGame takes a size and a filePath to intitalize the game state and play the game.
 * It contains a play method which is the main gameloop.
 */
public class HexGame {
    private HexState gameState;
    private Scanner scanner;
    private int moves = 0;
    
    public HexGame(int size, String filePath) {
        try {
            this.gameState = new HexState(size);
            File f = new File(filePath);
            this.scanner = new Scanner(f);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Starts the main game loop of the hex and updates the game state everytime a move is executed.
     * Reads each integer from the file and uses it as a cell index to take ownership of the indexed cell.
     * Loops until either there is no more moves or a winner is decided. 
     */
    public void play() {
        while (scanner.hasNextInt() && gameState.getWinner() == 0) {
            moves++;
            gameState.update((moves % 2 == 0) ? ('R') : ('B'), scanner.nextInt()); // Swaps between players
        }
        if (gameState.getWinner() == -1)
            System.out.printf("\n--------> BLUE WON after %d moves", moves);
        if (gameState.getWinner() == 1)
            System.out.printf("\n--------> RED WON after %d moves", moves);
        if (gameState.getWinner() == 0)
            System.out.printf("\n--------> NO WINNER after %d moves", moves);

        System.out.println(gameState);
    }

}
