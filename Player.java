import java.io.IOException;
import java.util.ArrayList;

public class Player {
    // storage the positions that the player already play
    private ArrayList<Position> playedMoves = new ArrayList<Position>();
    private int id;
    private Board board;

    /**
     * @param id - the id of player
     * @param board - the board of player
     */
    public Player(int id, Board board) {
        this.id = id;
        this.board = board;
    }

    public ArrayList<Position> getPlayedMoves() {
        return playedMoves;
    }

    /**
     * Add a new player moves to the array
     * Each position is move during the game
     *
     * @param position
     * @return true if the write data occurred was successfully
     */
    public boolean addPlayedMoves(Position position) {
        // Player can't play in the same position
        if(playedMoves.contains(position))
            return false;

        playedMoves.add(position);

        // storage the players moves in a file
        try {
            // append the data to player file
            // format the string with the following format: x,y
            Utils.appendDataToFile("./data/attack" + id + ".txt", position.getX() + "," + position.getY() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Checks if the player already move in a position
     *
     * @param position
     * @return true if already move, otherwise return false.
     */
    public boolean isPositionAlreadyChosen(Position position) {
        return playedMoves.contains(position);
    }

    /**
     * This method check if a ship already is completed in the board.
     *
     * @param ship - The ship that will be used to compare and check if already complete it
     * @return true if the ship passed by params already is completed
     */
    public boolean completeShip(Ship ship) {
        Integer shipParts[] = new Integer[ship.getSize()];

        // Iterate each played moves and search for the same ship that was passed by params
        // And add the parts in a array to check later.
        for(Position pos : playedMoves) {
            Ship currentShip = pos.getShip();

            // If the position don't contains a ship
            if(currentShip == null)
                continue;

            // If is a ship with same id and same type, add the part of him in a array
            if(currentShip.getId() == ship.getId() && currentShip.getTypeId() == ship.getTypeId())
                shipParts[pos.getShipPartNumber()] = pos.getShipPartNumber();
        }

        // check if player complete all ship parts
        for(Integer part : shipParts) {
            if(part == null || part == -1)
                return false;
        }

        return true;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
