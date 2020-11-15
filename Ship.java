public class Ship {
    private String name;
    private int size;
    private int count;
    private int id;

    /**
     * @param id - The id of ship. Used to distinct it
     * @param name - The name of ship.
     * @param size - The size of ship. How many of board this ship will fill
     * @param count - How many ships of this type will has in the board
     */
    public Ship(int id, String name, int size, int count) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ship() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}