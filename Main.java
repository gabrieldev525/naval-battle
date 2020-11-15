class Main {
    public static void main(String[] args) {
        Player player1 = new Player(1);
        Player player2 = new Player(2);

        Board board = new Board(10);
        board.generate();

        player1.addPlayedMoves("C1");
        player1.addPlayedMoves("C3");
        player1.addPlayedMoves("C5");
        player1.addPlayedMoves("C6");
    }
}