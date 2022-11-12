import java.io.File;
import java.util.Scanner;


/**
 * HexGame takes a size and a filePath to create a game loop 
 */
public class HexGame {
    private HexState gameState;
    private Scanner scanner;
    
    public HexGame(int size, String filePath) {
        try {
            gameState = new HexState(size);
            File f = new File(filePath);
            scanner = new Scanner(f);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void play() {
        int i = 0;
        while (scanner.hasNextInt() && gameState.hasWon == 0) {
            i++;
            gameState.update((i % 2 == 0) ? ('R') : ('B'), scanner.nextInt());
        }

        if (gameState.hasWon == -1)
            System.out.println("BLUE WON");
        if (gameState.hasWon == 1)
            System.out.println("RED WON");
        if (gameState.hasWon == 0)
            System.out.println("NO WINNER");

        System.out.print(gameState);
    }

}
