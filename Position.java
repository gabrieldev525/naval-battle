/**
 * This class is used to set the position in a board
 */
public class Position {
    private Ship ship;
    private int x;
    private int y;
    private int shipPartNumber;

    /**
     *
     * @param ship - the instance of ship that has a part in this position
     * @param x    - the position x in the board
     * @param y    - the position y in the board
     */
    public Position(Ship ship, int x, int y, int shipPartNumber) {
        this.ship = ship;
        this.x = x;
        this.y = y;
        this.shipPartNumber = shipPartNumber;
    }

    public int getShipPartNumber() {
        return shipPartNumber;
    }

    public void setShipPartNumber(int shipPartNumber) {
        this.shipPartNumber = shipPartNumber;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

}
