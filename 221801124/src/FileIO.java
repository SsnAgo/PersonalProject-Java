import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class FileIO {

    public static Reader getReader(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Reader reader = null;
        reader = new InputStreamReader(new FileInputStream(file));
        return reader;
    }

    public static ArrayList<String> readFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        BufferedReader reader = null;
        ArrayList<String> strs = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String temp;
            while ((temp = reader.readLine()) != null) {
                strs.add(temp);
            }
            reader.close();
            return strs;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeToFile(String filePath, String str) throws IOException {
        File file = new File(filePath);
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(str);
        bufferedWriter.close();
    }
}
