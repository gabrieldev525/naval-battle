import java.util.Random;

public class Board {
    // the board size. Example, if this value was 10, so the board will be 10 x 10
    private int size;
    private Position[] board;

    private Ship[] SHIP_TYPES = new Ship[Constants.SHIPS_CONFIG.length];

    /**
     * @param size - the size of the board. Is used to create the matriz
     */
    public Board(int size) {
        this.size = size;

        // get all types possibles of ships
        SHIP_TYPES = Utils.getShipsTypes();
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

                    // check if the random position can be added a new ship depends of ship size and orientation (vertically and horizontally)
                    for(int j = 0; j < currentShip.getSize(); j++) {
                        int index = randomX + (randomY * 10);
                        if(horizontal)
                            index += j;
                        else
                            index += 10 * j;

                        if(board[index] != null) {
                            canAddShip = false;
                            break;
                        }
                    }

                    // add the ship to the board
                    if(canAddShip) {
                        for(int j = 0; j < currentShip.getSize(); j++) {
                            int x = horizontal ? randomX + j : randomX;
                            int y = horizontal ? randomY : randomY + j;
                            Position pos = new Position(currentShip, x, y);

                            // calculate the index of matrix to append the ship
                            int index = randomX + (randomY * 10);
                            if(horizontal)
                                index += j;
                            else
                                index += 10 * j;
                            board[index] = pos;
                        }
                    }
                } while(!canAddShip);
            }
        }

        System.out.println("BOARD GENERATE SUCCESSFULLY");
    }

    public void print() {
        for(int i = 0; i < board.length; i++) {
            if(i % 10 == 0)
                System.out.println();

            if(board[i] != null)
                System.out.print(board[i].getShip().getId() + "\t");
            else
                System.out.print("\t");
        }
    }
}
