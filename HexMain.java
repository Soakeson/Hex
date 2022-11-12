public class HexMain {
    public static void main(String[] args) {
        HexGame hex = new HexGame(11, "moves.txt");
        hex.play();

        HexGame hex5 = new HexGame(5, "5X5moves.txt");
        hex5.play();
    }
}
