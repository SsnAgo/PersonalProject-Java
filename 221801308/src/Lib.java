import java.io.*;

/**
 * @author wangyu
 */
public class Lib {
    private String inputFile;
    private String outputFile;
    private int charNumber;

    public Lib(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    /**
     *
     * @return the file content
     * @throws IOException
     */
    public String readFileContent() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        StringBuilder builder = new StringBuilder();
        try {
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                builder.append(tempStr);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * get the number of characters
     * @throws IOException
     */
    public void countChars() throws IOException{
        String str = readFileContent();
        charNumber = str.length();
    }

    /**
     * write data to the file
     * @throws IOException
     */
    public void writeFileContent() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        try {
            writer.write("characters: " + charNumber + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            writer.close();
            e.printStackTrace();
        }
        System.out.println("succeed!!!");
    }
}
