public abstract class Constants {
    /**
     * This constants storages the config of ships.
     * To add a new ship, you must add a new item in the object following the structure.
     *
     * Structure: { id, name, size, count }
     */
    public static Object[][] SHIPS_CONFIG = {
        {1, "Destroyers", 3, 3},  // 10, 11, 12
        {2, "Tankers", 4, 2}, // 20, 21
        {3, "Submarine", 2, 4}, // 30, 31, 32, 33
        {4, "Aircraft Carrier", 5, 1}, // 40
    };
}
