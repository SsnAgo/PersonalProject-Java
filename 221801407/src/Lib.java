import java.io.*;
import java.util.HashMap;

class Lib{
    int charCount, wordCount, lineCount;
    InputStreamReader in;
    OutputStreamWriter out;
    BufferedReader br;
    HashMap<String,Integer> wordMap = new HashMap<String, Integer>();
    Lib(){
        charCount = 0;
        wordCount = 0;
        lineCount = 0;
    }
    int getCountChar(String inputFile) {
        try {
            in = new InputStreamReader(new FileInputStream(inputFile));
            br = new BufferedReader(in);
            int x;
            while ((x = br.read()) != -1) {
                charCount++;
            }
            br.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return charCount;
    }
    int getCountLine(String inputFile){
        try {
            in = new InputStreamReader(new FileInputStream(inputFile));
            br = new BufferedReader(in);
            int x;
            boolean isBlankLine = true;
            while ((x = br.read()) != -1) {
                charCount++;
                if (x != 13 && x != 10) isBlankLine = false;
                else if (x == 10 && !isBlankLine) {
                    lineCount++;
                    isBlankLine = true;
                }
            }
            br.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineCount;
    }
   int getCountWord(String inputFile){
        try{
            in = new InputStreamReader(new FileInputStream(inputFile));
            br = new BufferedReader(in);
            int x,letterCount=0;
            String word="";
            while((x = br.read())!= -1) {
                if(((x<='Z'&&x>='A')||(x<='z'&&x>='a'))&&letterCount!=-1){
                    word += (char)x;
                    letterCount++;;
                }
                else if (((x<='Z'&&x>='A')||(x<='z'&&x>='a'))&&letterCount==-1){
                    letterCount=-1;
                }
                else if(x<='9'&&x>='0'&&letterCount<4){
                    letterCount=-1;
                    word = "";
                }
                else if (x<='9'&&x>='0'&&letterCount>=4){
                    word += (char)x;
                }
                //分隔符
                else{
                    if(letterCount>=4){
                        wordCount++;
                        System.out.println(word);
                    }
                    letterCount = 0;
                    word = "";
                }
            }
            br.close();
            in.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return wordCount;
    }

    void writeFile(){

    }
}