import java.io.*;

public class WordCount {

    public static Reader readFile(String fileName) throws IOException {
        return new BufferedReader(new FileReader(fileName));
    }

    public static int charactersCount(String fileName) throws IOException {
        Reader reader = readFile(fileName);
        int ch = 0, cnt = 0;
        while ((ch = reader.read()) != -1) {
            cnt++;
            //0-127?
        }
        reader.close();
        return cnt;
    }

    public static int linesCount(String fileName) throws IOException {
        BufferedReader reader = (BufferedReader) readFile(fileName);
        int cnt = 0;
        String curLine = null;
        while ((curLine = reader.readLine()) != null) {
            if (!curLine.replaceAll("\r|\n", "").trim().equals("")) {
                cnt++;
            }
        }
        return cnt;
        //test
    }

    public static void main(String[] args) throws IOException {
        String test = "input.txt";
        System.out.println(charactersCount(test));
        System.out.println(linesCount(test));
    }
}