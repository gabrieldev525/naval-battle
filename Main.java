import java.io.IOException;
import java.util.Random;

class Main {
    public static void main(String[] args) {
        Random random = new Random();

        // initialize players boards
        Board boardPlayer1 = new Board(10);
        boardPlayer1.generate();

        Board boardPlayer2 = new Board(10);
        boardPlayer2.generate();

        // initialize players
        Player player1 = new Player(1, boardPlayer1);
        Player player2 = new Player(2, boardPlayer2);

        // storage what player will play now
        Player playerTurn = random.nextInt(2) == 1 ? player1 : player2;
        Board boardToAttack = playerTurn.getId() == 1 ? player2.getBoard() : player1.getBoard();

        // storage if the game is finished
        boolean gameover = false;

        System.out.println("The player " + playerTurn.getId() + " will start the game");

        while (!gameover) {
            System.out.println("Player turn " + playerTurn.getId());

            Position chosenPosition;
            boolean playerHit = false;

            do {
                int chosenPositionIndex = random.nextInt((int) Math.pow(boardToAttack.getSize(), 2));
                chosenPosition = boardToAttack.getPositionIndex(chosenPositionIndex);

                // Position has not yet been chosen
                if (!playerTurn.isPositionAlreadyChosen(chosenPosition) && chosenPosition != null) {  // TODO: Fix this, the second position is wrong, because it's needed to register all moves, even if there isn't ship in that position
                    // register the move of player
                    playerTurn.addPlayedMoves(chosenPosition);

                    // if the position contains a ship
                    if (chosenPosition.getShip() != null) {
                        System.out.println("Player " + playerTurn.getId() + " hit a ship part");

                        // if complete the ship, the game is over
                        if (playerTurn.completeShip(chosenPosition.getShip())) {
                            gameover = true;
                            break;
                        } else
                            playerHit = true;
                    } else
                        System.out.println("Player " + playerTurn.getId() + " missed");
                }
            } while (playerTurn.isPositionAlreadyChosen(chosenPosition));

            // if player hit the ship, he can play again.
            if (playerHit || gameover)
                continue;

            // alter between players
            if (playerTurn == player1) {
                playerTurn = player2;
                boardToAttack = player1.getBoard();
            } else {
                playerTurn = player1;
                boardToAttack = player2.getBoard();
            }
        }

        // when the game is over, the result will be added to a file "saida.txt" in data folder.
        try {
            Utils.appendDataToFile("./data/saida.txt", "Jogador que venceu: " + playerTurn.getId()
                    + "\nJogadas realizadas: " + playerTurn.getPlayedMoves().size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("FIM DE JOGO");
        System.out.println("The player " + playerTurn.getId() + " win");
    }
}