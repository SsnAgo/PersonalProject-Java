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
    void readFile(){
        try {
            in = new InputStreamReader(new FileInputStream(inputFile));
            br = new BufferedReader(in);
            int x ;
            while((x=br.read())!=-1){
                if(x>0&&x<255){
                    System.out.println(x);
                    countChar++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    void writeFile(){

    }
}