import java.io.*;

class Lib{

    InputStreamReader in;

    String inputFile;

    Lib(String inputFile){

        this.inputFile = inputFile;

    }
    void readFile(){
        try {

            in = new InputStreamReader(new FileInputStream(inputFile));
            System.out.println("Input file has found!");

        }catch(Exception e){

            System.out.println("Input file not found!");

        }
    }

}