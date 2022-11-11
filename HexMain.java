public class HexMain {
    public static void main(String[] args) {
        HexGame hex = new HexGame(11);
        hex.update('R', 0);
        hex.update('R', 11);
        hex.update('R', 12);
        hex.update('R', 23);
        hex.update('R', 120);
        hex.update('R', 119);

        hex.debug();
    }
}
