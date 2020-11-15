class Main {
    public static void main(String[] args) {
        Board boardPlayer1 = new Board(10);
        boardPlayer1.generate();

        Board boardPlayer2 = new Board(10);
        boardPlayer2.generate();

        Player player1 = new Player(1, boardPlayer1);
        Player player2 = new Player(2, boardPlayer2);
    }
}