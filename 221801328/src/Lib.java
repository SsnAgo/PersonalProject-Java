import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Lib {

    private int words = 0;
    private int chars = 0;
    private int lines = 0;
    private ArrayList<Map.Entry<String,Integer>> wordList;
    private String inputFileName;
    private String outputFileName;

    public Lib(String input,String output){
        inputFileName = input;
        outputFileName = output;
    }

    public void startCount() {
        words = countWords(inputFileName);
        chars = countChars(inputFileName);
        lines = countLines(inputFileName);
        wordList = countWordFrequency(inputFileName);
    }

    public void outputResult() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFileName));
            bw.write("characters: "+chars +"\n");
            bw.write("words: "+words +"\n");
            bw.write("lines: "+lines +"\n");

            for(int i = 0;i < wordList.size() && i < 10;i++){
                // System.out.println(list.get(i).getKey()+ ": " +list.get(i).getValue());
                bw.write(wordList.get(i).getKey()+ ": " +wordList.get(i).getValue()+"\n");
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*统计单词数*/
    public int countWords(String inputFile) {
        List<String> wordList = getWordList(inputFile);
        int count = wordList.size();
        return count;
    }

    /*获取单词列表*/
    public List<String> getWordList(String inputFile) {
        List<String> wordList = new ArrayList();//单词列表
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line = null;
            //一行一行读取文件
            while((line = br.readLine()) != null){
                //分割符：空格，非字母数字符号,以分割符来分割出单词
                String[] words = line.split("[^a-zA-Z0-9]");
                //单词：至少以4个英文字母开头
                Pattern pattern = Pattern.compile("[a-zA-Z]{4}[a-zA-Z0-9]*");
                for (String word : words){
                    if (word.length() >= 4) {
                        //正则表达式判断单词是否合法
                        if (pattern.matcher(word).matches()){
                            //统一转小写
                            wordList.add(word.toLowerCase());
                        }
                    }
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordList;
    }


    /*统计ascii字符数*/
    public int countChars(String inputFile) {
        int count = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            while ((br.read()) != -1)
                count++;
//            int ch = -1;
//            while ((ch = br.read()) != -1) {
//                //属于ascii码，就计下
//                if (ch <=127 )
//                    count++;
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    /*统计文件的有效行数,任何包含 非空白 字符的行，都需要统计。*/
    public int countLines(String inputFile){
        int count = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line = "";
            while((line = br.readLine())!=null){
                //判断是否为空白行
                //if (!line.isBlank())java8没有这个
                //   count++;
                if (!(line.trim().isEmpty()))
                    count++;
            }
//            System.out.println(count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    /*统计词频，只输出出现最多的10个*/
    public ArrayList countWordFrequency(String inputFile)
    {
        List<String> wordList = getWordList(inputFile);
        //key 单词  value 出现次数
        Map<String, Integer> words = new TreeMap<String,Integer>();

        //如果有这个单词 count ++
        for (String word : wordList){
            if (words.containsKey(word))
                words.put(word,words.get(word)+1);
            else//如果map里没有这个单词，添加进去，count=1
                words.put(word,1);
        }
        ArrayList<Map.Entry<String,Integer>> list = sortMap(words);    //按值进行排序

        return list;
    }

    /*对map按value排序*/
    public ArrayList sortMap(Map<String,Integer> oldmap){

        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(oldmap.entrySet());

        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //降序排序
                return o2.getValue() - o1.getValue();
            }
        });
        return list;
    }

    public void printWords(ArrayList<Map.Entry<String,Integer>> list) {

        for(int i = 0;i < list.size() && i < 10;i++){
            System.out.println(list.get(i).getKey()+ ": " +list.get(i).getValue());
        }
    }
}
