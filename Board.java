import java.util.Random;

public class Board {
    // the board size. Example, if this value was 10, so the board will be 10 x 10
    private int size;
    private Position[] board;

    private Ship[] SHIP_TYPES = new Ship[Constants.SHIPS_CONFIG.length];

    public Board(int size) {
        this.size = size;

        SHIP_TYPES = Utils.getShipsTypes();
    }

    public Board() {}

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void generate() {
        // delete a old data file
        Utils.deleteFolder("./data");

        Random random = new Random();
    }
}
