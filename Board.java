public class Board {
    // the board size. Example, if this value was 10, so the board will be 10 x 10
    private int size;
    private Ship[] shipTypes = new Ship[4];

    public Board(int size) {
        this.size = size;

        shipTypes[0] = new Ship("Submarine", 2, 4);
        shipTypes[1] = new Ship("Destroyers", 3, 3);
        shipTypes[2] = new Ship("Tankers", 4, 2);
        shipTypes[3] = new Ship("Aircraft Carrier", 5, 1);
    }

    public Board() {}

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void generate() {

    }
}
