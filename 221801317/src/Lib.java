import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
public class Lib {

    public static class TextFileSolver{
        String filePath;
        StringBuilder fileText;
        List<String> strings;
        Map<String,Long> wordFrequencyMap;
        int fileCharNum;
        int validLineNum;

        public TextFileSolver(String filePath) throws IOException{
            //数据初始化
            this.filePath = filePath;
            fileCharNum = 0;
            validLineNum=0;
            fileText = new StringBuilder();
            strings = IOUtil.getStrings(filePath);
            for (String s: strings) {
                solveString(s);
            }

            //正则表达式切分字符串
            wordFrequencyMap = Arrays.asList(fileText.toString().split("\\s+"))
                    .stream()
                    .filter(word->{ //过滤单词
                        if (word.length()<4) return false;
                        char[] chars = word.toCharArray();
                        for (int i = 0 ; i < 4 ; i++){
                            if (! Character.isLetter(chars[i])) return false;
                        }
                        return true;
                    })
                    .collect(Collectors.groupingBy(String::toLowerCase,Collectors.counting()));

        }

        /**
         * 获取字符数
         * @return
         */
        public int getFileCharNum(){
                return fileCharNum+strings.size()-1;
            }

        /**
         * 获取单词数
         * @return
         */
        public long getWordNum(){
                long num = 0;
                for (Map.Entry<String,Long> pair: wordFrequencyMap.entrySet()) {
                    num += pair.getValue();
                }
                return num;
            }

        /**
         * 获取有效行数
         * @return
         */
        public int getValidLineNum(){
                return validLineNum;
            }

        /**
         * 获取有序的前size个数据
         * @param size
         * @return
         */
        public Map<String,Long> getOrderedWordFrequencyMap(int size){
                return  wordFrequencyMap
                        .entrySet() //获取set
                        .stream()   //获取流
                        .sorted(Map.Entry.<String, Long> comparingByValue() //按照数值排序，默认升序
                                .reversed()//倒序
                                .thenComparing(Map.Entry.comparingByKey()))//按照key排序
                        .limit(size) //选择最前面的十个
                        .collect(  //以map形式返回
                                Collectors.toMap(
                                        Map.Entry::getKey,
                                        Map.Entry::getValue,
                                        (oldVal, newVal) -> oldVal,
                                        LinkedHashMap::new
                                )
                        );
            }

        /**
         * 统计s的字符数
         * 统计有效行数
         * 将其中的非数字非英文字符转化为空白字符
         * 将转化后的字符串并入到fileText中用于进行单词统计
         * @param s
         */
        private void solveString(String s){
                fileCharNum+=s.length(); //统计file中所有字符数目
                if (s.isEmpty()){       //空则返回
                    return;
                }
                validLineNum++;
                char[] chars = s.toCharArray();
                for (int i = 0; i < chars.length; i++) { //非数字 非英文字符 转化为空白字符
                    char cur = chars[i];
                    if (!Character.isLetterOrDigit(cur)){
                        chars[i] =' ';
                    }
                }
                fileText.append(' ').append(chars);
            }

    }
    public static class IOUtil{
        /**
         * 按照acsii编码读取文件内容
         * @param inputFilePath 文件路径
         * @return {@code List<String>} 一项String代表一行
         */
        public static List<String> getStrings(String inputFilePath) throws IOException {
            ArrayList<String> strings = new ArrayList<>();
            File inputFile = new File(inputFilePath);
            InputStreamReader fileInputStream = new InputStreamReader(
                        new FileInputStream(inputFile),"ascii");
            BufferedReader reader = new BufferedReader(fileInputStream);
            for (String temp = reader.readLine() ;temp !=null ; temp=reader.readLine()) {
                strings.add(temp);
            }
            reader.close();
            return strings;
        }

        /**
         * 按照utf-8的编码向文件写入文本
         * @param outputFilePath 文件路径
         * @param content 文本内容
         */
        public static void writeTo(String outputFilePath,String content){
            File outputFile = new File(outputFilePath);
            BufferedWriter writer=null;
            try{
                writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(outputFile), "utf-8"));
                writer.write(content);
            }catch(IOException e){
                e.printStackTrace();
                System.out.println("文件写入错误");
            }finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        System.out.println("输出流关闭异常");
                    }
                }
            }
        }

    }
}