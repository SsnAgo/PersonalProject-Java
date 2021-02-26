import java.io.*;

class Lib{
    int countChar,countWord,countLine;
    InputStreamReader in;
    OutputStreamWriter out;
    BufferedReader br;
    String inputFile,outputFile;
    Lib(String inputFile,String outputFile){
        countChar = 0;
        countWord = 0;
        countLine = 0;
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }
    int getCountChar() {
        try {
            in = new InputStreamReader(new FileInputStream(inputFile));
            br = new BufferedReader(in);
            int x;
            while ((x = br.read()) != -1) {
                countChar++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countChar;
    }
    int getCountLine(){
        try {
            in = new InputStreamReader(new FileInputStream(inputFile));
            br = new BufferedReader(in);
            int x;
            boolean isBlankLine = true;
            while ((x = br.read()) != -1) {
                countChar++;
                if (x != 13 && x != 10) isBlankLine = false;
                else if (x == 10 && !isBlankLine) {
                    countLine++;
                    isBlankLine = true;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countLine;
    }
    void writeFile(){

    }
}