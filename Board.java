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
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                board[x + (y * size)] = new Position(null, x, y, -1);
            }
        }
    }

    // getter and setter
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Position getPositionIndex(int index) {
        return board[index];
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

                    // define the id of ship to distinct it
                    currentShip.setId(Integer.toString(currentShip.getTypeId()) + i);

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

                    final int randomX = random.nextInt(sizeX);
                    final int randomY = random.nextInt(sizeY);

                    /**
                     * checks if can add a new ship at random position, depending on the size and orientation of the ship (vertically and horizontally)
                     * You need to check if you can add the complete ship to the board using the random position x and y
                     * You can't just verify one position and add it already, because a position can be free, but the position on your side may not be
                     */
                    for(int j = 0; j < currentShip.getSize(); j++) {
                        int index = randomX + (randomY * size);
                        if(horizontal)
                            index += j;
                        else
                            index += size * j;

                        if(board[index].getShip() != null) {
                            canAddShip = false;
                            break;
                        }
                    }

                    // Only add the ship, if the space needed is free
                    if(canAddShip) {
                        for(int j = 0; j < currentShip.getSize(); j++) {
                            int x = horizontal ? randomX + j : randomX;
                            int y = horizontal ? randomY : randomY + j;
                            Position pos = new Position(currentShip, x, y, j);

                            // calculate the index of matrix to append the ship
                            int index = randomX + (randomY * size);
                            if(horizontal)
                                index += j;
                            else
                                index += size * j;
                            board[index] = pos;
                        }
                    }
                } while(!canAddShip);
            }
        }

        System.out.println("BOARD GENERATE SUCCESSFULLY");
    }

    /**
     * Print the board with the ship positions
     */
    public void print() {
        for(int i = 0; i < board.length; i++) {
            if(i % 10 == 0)
                System.out.println();

            if(board[i].getShip() != null)
                System.out.print(board[i].getShip().getTypeId() + "\t");
            else
                System.out.print("-\t");
        }
    }
}
