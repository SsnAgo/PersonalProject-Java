import java.io.*;

class Lib {
    int countChar;
    InputStreamReader in;
    String inputFile;

    Lib(String inputFile) {
        countChar = 0;
        this.inputFile = inputFile;

    }

    int getCountChar() throws FileNotFoundException {
        in=new InputStreamReader(new FileInputStream(inputFile));
        int num=0;
        try {
            while(in.read()!=-1)
            {
                num++;
            }
            System.out.println(num);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }
}