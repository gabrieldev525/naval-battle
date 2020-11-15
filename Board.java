import java.util.Random;

public class Board {
    // the board size. Example, if this value was 10, so the board will be 10 x 10
    private int size;
    private Position[] board;

    private Ship[] SHIP_TYPES = new Ship[Constants.SHIPS_CONFIG.length];

    // storage the count of ship type in the board
    private int[] BOARD_SHIP_TYPES_COUNT;

    /**
     * @param size - the size of the board. Is used to create the matriz
     */
    public Board(int size) {
        this.size = size;

        // get all types possibles of ships
        SHIP_TYPES = Utils.getShipsTypes();

        // initialize the variable with 0
        BOARD_SHIP_TYPES_COUNT = new int[SHIP_TYPES.length];
        for(int i = 0; i < SHIP_TYPES.length; i++) {
            BOARD_SHIP_TYPES_COUNT[i] = 0;
        }

        // initialize board
        board = new Position[size * size];
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Generate the board of naval battle
     * This add the ships vetically and horizontally random
     * All this proccess is random and a new board is generated for each call
     */
    public void generate() {
        // delete old data file
        Utils.deleteFolder("./data");

        Random random = new Random();

        for(Ship currentShip : SHIP_TYPES) {
            for(int i = 0; i < currentShip.getCount(); i++) {
                boolean canAddShip = true;

                do {
                    canAddShip = true;

                    // if generated number was 1, the ship will be add horizontally however, will be added vertically
                    boolean horizontal = random.nextInt(2) == 1;

                    /**
                     * remove the size of the ship from the possible starting positions depending on the
                     * direction, in order to have space in relation to the edge of the board
                     */
                    int sizeX, sizeY;
                    sizeX = sizeY = size;
                    if(horizontal)
                        sizeX -= currentShip.getSize();
                    else
                        sizeY -= currentShip.getSize();

                    int randomX = random.nextInt(sizeX);
                    int randomY = random.nextInt(sizeY);

                    // check if the random position can be added a new ship depends of ship size and orientation
                    for(Position pos : board) {
                        if(pos != null) {
                            if(horizontal) {
                                if(pos.getX() >= randomX && pos.getX() <= randomX + currentShip.getSize() && pos.getY() == randomY)
                                    canAddShip = false;
                            } else {
                                if(pos.getX() == randomX && pos.getY() >= randomY && pos.getY() <= randomY + currentShip.getSize())
                                    canAddShip = false;
                            }
                        }

                        if(!canAddShip)
                            break;
                    }

                    // add the ship to the board
                    if(canAddShip) {
                        for(int j = 0; j < currentShip.getSize(); j++) {
                            int x = horizontal ? randomX + j : randomX;
                            int y = horizontal ? randomY : randomY + j;
                            Position pos = new Position(currentShip, x, y);
                            board[(x * y)] = pos;
                        }
                    }
                } while(!canAddShip);
            }
        }

        System.out.println("BOARD GENERATE SUCCESSFULLY");
    }
}
