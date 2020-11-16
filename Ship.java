public class Ship {
    private String id;
    private String name;
    private int size;
    private int count;
    private int typeId;

    /**
     * @param typeId - The id of ship type.
     * @param name   - The name of ship.
     * @param size   - The size of ship. How many of board this ship will fill
     * @param count  - How many ships of this type will has in the board
     */
    public Ship(int typeId, String name, int size, int count) {
        this.typeId = typeId;
        this.name = name;
        this.size = size;
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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