import java.io.*;

class Lib {

    InputStreamReader in;
    BufferedReader br;
    String inputFile;

    Lib(String inputFile) {

        this.inputFile = inputFile;

    }
    //读取文件字符数
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
    //读取文件有效行数
     int getLine() throws IOException{
        int linenum=0;
        in = new InputStreamReader(new FileInputStream(inputFile));
        br = new BufferedReader(in);
        String line = null;
        while ((line = br.readLine()) != null) {
            if (line.trim().equals(""))
                continue;
            else
                linenum++;
        }
        System.out.println(linenum);
        return linenum;
    }
    //读取文件中单词个数
    int getWordNum() throws IOException {
        int wordnum=0;
        in = new InputStreamReader(new FileInputStream(inputFile));
        br = new BufferedReader(in);
        String words;
        while ((words = br.readLine()) != null) {
            String[] strs=words.split("[^a-zA-Z0-9]");
            String regexs = "^[a-zA-Z]{4,}.*";
            for(int i=0;i<strs.length;i++)
            {
                if(strs[i].matches(regexs))
                {
                    wordnum++; 
                }
            }
        }
        System.out.println(wordnum);
        return wordnum;
    }
}