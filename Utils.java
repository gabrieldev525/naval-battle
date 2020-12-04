import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * This class contains general utils methods to use in system
 */
public class Utils {

    /**
     * This method delete a folder.
     *
     * @param path - the path to the folder that'll deleted
     * @return true if delete operation ocurred successfully else return false
     */
    public static boolean deleteFolder(String path) {
        try {
            File file = new File(path);
            if(file.exists()) {
                // list all files and delete one by one
                File[] files = file.listFiles();
                for(File f : files) {
                    f.delete();
                }

                // delete the empty folder
                file.delete();
            } else
                return false;
        } catch(Exception e) {
            System.out.println("An error ocurred");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * append the data in the end of file
     *
     * @param path - the path of file to append the content
     * @param data - A string to append in the end of file
     * @throws IOException
     */
    public static void appendDataToFile(String path, String data) throws IOException {
        String finalPath = "";

        // remove the file name from folder and add it to
        String allDirectories[] = path.split("/");
        if(allDirectories.length > 1) {
            if(allDirectories[allDirectories.length - 1] != "") {
                allDirectories = removeItemFromArray(allDirectories, allDirectories.length - 1);
                finalPath = String.join("/", allDirectories);
            }
        }

        // create data folder
        File dataFolder = new File(finalPath);
        if(!dataFolder.exists())
            dataFolder.mkdirs();

        // create date file
        File file = new File(path);
        if(!file.exists())
            file.createNewFile();

        // create a writer to append the new position in the file
        Files.write(Paths.get(path), data.getBytes(), StandardOpenOption.APPEND);
    }

    /**
     * This method remove a item from array
     *
     * @param array - the array that'll be used to remove a item
     * @param position - the position of array to remove
     *
     * @return the new array without removed item
     */
    public static String[] removeItemFromArray(String[] array, int position) {
        String[] temp = new String[array.length - 1];
        int pos = 0;
        for(int i = 0; i < array.length; i++) {
            if(i == position)
                continue;

            temp[pos] = array[i];
            pos++;
        }

        return temp;
    }


    /**
     * This method return all types of ships based on SHIPS_CONFIG constants
     *
     * @return - A array of all ships types
     */
    public static Ship[] getShipsTypes() {
        Object[][] config = Constants.SHIPS_CONFIG;
        Ship ships[] = new Ship[config.length];

        for(int i = 0; i < config.length; i++) {
            ships[i] = new Ship(
                (int) config[i][0],
                (String) config[i][1],
                (int) config[i][2]
            );
        }

        return ships;
    }
}
