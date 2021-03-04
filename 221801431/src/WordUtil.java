import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordUtil {

    //统计字符数
    public String countChar(String filename) throws IOException {
        //初始化字符数
        int charnum=0;
        File file=new File("../"+filename);
        int x=-1;
        FileReader fReader=new FileReader(file);
        //逐个字符读取文件
        while((x=fReader.read())!=-1) {
            char a=(char)x;
            /*if(a!='\n'&&a!='\r')
            {
                charnum++;
            }*/
            charnum++;
        }
        String result=""+charnum;
        //关闭流
        fReader.close();
        return result;
    }

    //统计单词总数
    public String countWord(String filename) throws IOException{
        //初始化单词数
        int wordnum=0;
        File file =new File("../"+filename);
        //缓冲区
        BufferedReader bReader;
        bReader=new BufferedReader(new FileReader(file));
        String temp = "";

        //按行读文件，用正则表达式分割
        while ((temp = bReader.readLine()) != null) {
            String[] words = temp.split("[^a-zA-Z0-9]+");
            for (String word : words) {
                word.toLowerCase();
                //碰到符合条件的单词，单词数+1
                if (word.matches("[a-zA-Z]{4}[a-zA-Z0-9]*") ) {
                    wordnum++;
                }
            }
        }

        String result=""+wordnum;//保存结果
        bReader.close();//关闭流
        return result;//返回结果
    }

    //统计行数
    public static String countLine(String filename) throws IOException{
        String temp = "";
        String regEx="[\n\\t\\r ]";
        //或：String regEx="[\\s]"; //匹配任何空白字符，包括空格、制表符、换页符等等。等价于 [ \f\n\r\t\v]

        //初始化行数
        int line=0;
        File file=new File("../"+filename);
        //缓冲区
        BufferedReader bReader;
        bReader=new BufferedReader(new FileReader(file));
        //按行读取文件，进行正则处理判断有效行数
        while((temp = bReader.readLine())!=null) {
            /*
            另解：
            String aa = " ";
            String str = "原字符串";
            String newString = str.replaceAll(regEX,aa);
            //不想保留原来的字符串可以直接写成 “str = str.replaceAll(regEX,aa);”
             */
            String aa = " ";//这里是将特殊字符换为aa字符串," "代表直接去掉
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(temp);//这里把想要替换的字符串传进来
            String newString = m.replaceAll(aa).trim();

            if(!newString.equals("")){
                //System.out.println("有效行："+newString);
                line++;
            }
        }
        bReader.close();//关闭流
        String result=""+line;//保存结果
        return result;//返回结果
    }


    //统计各单词出现次数,并排序
    public static String countWordFrequency(String filename) throws IOException {
        String temp="";
        String result = "";

        Map<String,Integer> wordMap=new HashMap<String,Integer>();
        try {
            File file =new File("../"+filename);
            //缓冲区
            BufferedReader bReader;
            bReader=new BufferedReader(new FileReader(file));
            //按行读文件，用正则表达式分割
            while ((temp = bReader.readLine()) != null){
                String[] words = temp.split("[^a-zA-Z0-9]+");
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

            //法一：返回值void
            /*
            Set<WordEntity> set=new TreeSet<WordEntity>();
            for(String s:map.keySet()){
                WordEntity wordEntry=new WordEntity(s,map.get(s));
                set.add(wordEntry);
            }
            Iterator<WordEntity> ite=set.iterator();
            int count=0;
            while(ite.hasNext()){
                if(count>=10)
                    break;
                System.out.println(ite.next());
                count++;
            }*/
            //法二：返回值 String
            if(!wordMap.isEmpty()){
                //按单词个数排序
                List<HashMap.Entry<String, Integer>> wordList = Sort(wordMap);
                for (HashMap.Entry h : wordList){
                    result += h.getKey() + ": " + h.getValue() + "\n";
                }
            } else{
                result += "无单词";
            }

            bReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }


    //单词排序函数，将字典依次按照出现次数排序，次数相同的按字典序排序
    public  static List<HashMap.Entry<String, Integer>> Sort(Map m){
        List<HashMap.Entry<String, Integer>> wordList = new ArrayList<HashMap.Entry<String, Integer>>(m.entrySet());

        Comparator<Map.Entry<String, Integer>> com = new Comparator<Map.Entry<String, Integer>>(){

            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue()==o2.getValue())
                    return o1.getKey().compareTo(o2.getKey());//字典序
                return o2.getValue()-o1.getValue();//从大到小
            }
        };
        wordList.sort(com);
        return wordList;
    }

}
