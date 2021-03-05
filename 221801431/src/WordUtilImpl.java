import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordUtilImpl implements WordUtil{

    //父目录
    public static final String PARENTDIRECTORY = ".." + File.separator;
    //获取当前绝对目录路径：
    public static final String ABSOLUTE_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator;

    //统计字符数
    public Integer countChar(String fileName) throws IOException{
        return countChar(fileName, ABSOLUTE_PATH);
    }
    public Integer countChar(String fileName, String fileDirectory) throws IOException {
        if(!rightDocument(fileName)){
            System.out.println("文件格式错误！非txt后缀文件");
            System.exit(0);
        }

        //初始化字符数
        int charNum=0;
        int x=-1;
        File file=new File(fileDirectory + fileName);
        BufferedReader bReader;
        try{
            bReader = new BufferedReader(new FileReader(file));
            //逐个字符读取文件
            while((x=bReader.read())!=-1) {
                char a=(char)x;
            /*if(a!='\n'&&a!='\r')
            {
                charNum++;
            }*/
                charNum++;
            }
            String result=""+charNum;
            //关闭流
            bReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("文件未找到! "+e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return charNum;
    }

    //统计单词总数
    public Integer countWord(String fileName) throws IOException{
        return countWord(fileName, ABSOLUTE_PATH);
    }
    public Integer countWord(String fileName, String fileDirectory) throws IOException{
        if(!rightDocument(fileName)){
            System.out.println("文件格式错误！非txt后缀文件");
            System.exit(0);
        }

        //初始化单词数
        int wordNum=0;
        File file = new File(fileDirectory + fileName);
        //缓冲区
        BufferedReader bReader;
        try{
            bReader = new BufferedReader(new FileReader(file));
            String temp = "";

            //按行读文件，用正则表达式分割
            while ((temp = bReader.readLine()) != null) {
                String[] words = temp.split("[^a-zA-Z0-9]+");
                for (String word : words) {
                    word.toLowerCase();
                    //碰到符合条件的单词，单词数+1
                    if (word.matches("[a-zA-Z]{4}[a-zA-Z0-9]*") ) {
                        wordNum++;
                    }
                }
            }

            //String result=""+wordnum;
            //关闭流
            bReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("文件未找到! "+e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return wordNum;
    }


    //统计行数
    public Integer countLine(String fileName) throws IOException{
        return countLine(fileName, ABSOLUTE_PATH);
    }
    public Integer countLine(String fileName, String fileDirectory) throws IOException{
        if(!rightDocument(fileName)){
            System.out.println("文件格式错误！非txt后缀文件");
            System.exit(0);
        }

        String temp = "";
        String regEx="[\n\\t\\r ]";
        //或：String regEx="[\\s]"; //匹配任何空白字符，包括空格、制表符、换页符等等。等价于 [ \f\n\r\t\v]

        //初始化行数
        int line = 0;
        File file = new File(fileDirectory + fileName);
        //缓冲区
        BufferedReader bReader;
        try{
            bReader = new BufferedReader(new FileReader(file));
            //按行读取文件，进行正则处理判断有效行数
            while((temp = bReader.readLine())!=null) {
                /*
                //法一：替换法
                String aa = " ";
                String str = "原字符串";
                String newString = str.replaceAll(regEX,aa);
                //不想保留原来的字符串可以直接写成 “str = str.replaceAll(regEX,aa);”
                */

                //法二：
                //这里是将特殊字符换为aa字符串," "代表直接去掉
                String aa = " ";
                Pattern p = Pattern.compile(regEx);
                //这里把想要替换的字符串传进来
                Matcher m = p.matcher(temp);
                String newString = m.replaceAll(aa).trim();

                if(!newString.equals("")){
                    //System.out.println("有效行："+newString);
                    line++;
                }

            }
            //关闭流
            bReader.close();
            //String result=""+line;
        } catch (FileNotFoundException e) {
            System.out.println("文件未找到! "+e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return line;
    }


    //统计各单词出现次数,并排序
    //通过HashMap排序
    public List<HashMap.Entry<String, Integer>> countWordFrequency(String fileName) throws IOException{
        return countWordFrequency(fileName, ABSOLUTE_PATH);
    }
    public List<HashMap.Entry<String, Integer>> countWordFrequency(String fileName, String fileDirectory) throws IOException {
        if(!rightDocument(fileName)){
            System.out.println("文件格式错误！非txt后缀文件");
            System.exit(0);
        }

        String temp = "";
        List<HashMap.Entry<String, Integer>> wordList = null;

        Map<String,Integer> wordMap=new HashMap<String,Integer>();
        //缓冲区
        BufferedReader bReader;
        try {
            File file = new File(fileDirectory + fileName);
            bReader = new BufferedReader(new FileReader(file));
            //按行读文件，用正则表达式分割
            while ((temp = bReader.readLine()) != null){
                String[] words = temp.split("[^a-zA-Z0-9]+");
                //在分割出单词之后，将单词遍历储存在hashmap当中，在储存前先判断是否为合法单词
                for (String word : words) {
                    word = word.toLowerCase();
                    if (word.matches("[a-zA-Z]{4}[a-zA-Z0-9]*") ) {
                        //若此单词已经记录过，value + 1
                        if(wordMap.containsKey(word)){
                            wordMap.put(word, wordMap.get(word)+1);
                        } else {
                            //若未记录，初始化value = 1
                            wordMap.put(word, 1);
                        }
                    }
                }
            }

            if(!wordMap.isEmpty()){
                //按单词个数排序
                wordList = Sort(wordMap);
            }
            //关闭流
            bReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("文件未找到! " + e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return wordList;
    }

    //统计各单词出现次数,并排序
    //通过Set排序
    public Set<WordEntity> countWordFrequency2(String fileName) throws IOException{
        return countWordFrequency2(fileName, ABSOLUTE_PATH);
    }
    public Set<WordEntity> countWordFrequency2(String fileName, String fileDirectory) throws IOException {
        if(!rightDocument(fileName)){
            System.out.println("文件格式错误！非txt后缀文件");
        }

        String temp = "";
        List<HashMap.Entry<String, Integer>> wordList = null;

        Map<String,Integer> wordMap=new HashMap<String,Integer>();
        //缓冲区
        BufferedReader bReader;
        //进行排序的Set
        Set<WordEntity> wordSet = new TreeSet<WordEntity>();
        try {
            File file = new File(fileDirectory + fileName);
            bReader = new BufferedReader(new FileReader(file));
            //按行读文件，用正则表达式分割
            while ((temp = bReader.readLine()) != null){
                String[] words = temp.split("[^a-zA-Z0-9]+");
                //在分割出单词之后，将单词遍历储存在hashmap当中，在储存前先判断是否为合法单词
                for (String word : words) {
                    word = word.toLowerCase();
                    if (word.matches("[a-zA-Z]{4}[a-zA-Z0-9]*") ) {
                        //若此单词已经记录过，value + 1
                        if(wordMap.containsKey(word)){
                            wordMap.put(word, wordMap.get(word)+1);
                        } else {
                            //若未记录，初始化value = 1
                            wordMap.put(word, 1);
                        }
                    }
                }
            }

            //Set<WordEntity> wordSet = new TreeSet<WordEntity>();
            for(String s : wordMap.keySet()){
                WordEntity wordEntry = new WordEntity(s, wordMap.get(s));
                wordSet.add(wordEntry);
            }

            bReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("文件未找到! " + e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return wordSet;
    }


    //单词排序函数，将字典依次按照出现次数排序，次数相同的按字典序排序
    public  List<HashMap.Entry<String, Integer>> Sort(Map m){
        List<HashMap.Entry<String, Integer>> wordList = new ArrayList<HashMap.Entry<String, Integer>>(m.entrySet());

        Comparator<Map.Entry<String, Integer>> com = new Comparator<Map.Entry<String, Integer>>(){

            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue() == o2.getValue()){
                    //字典序
                    return o1.getKey().compareTo(o2.getKey());
                }
                //从大到小
                return o2.getValue()-o1.getValue();
            }
        };
        wordList.sort(com);
        return wordList;
    }

    //判断文件名是否为文本文件
    public boolean rightDocument(String fileName){
        boolean result = false;
        if (fileName.matches("[\\s\\S]*.txt") ){
            result = true;
        }
        return result;
    }

}
