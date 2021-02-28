import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

public class WordCount {
    private static String WORD_REGEX = "[a-z]{4}[a-z0-9]*";
    private static String FLITER_REGEX = "[^a-zA-Z0-9]";
    private static String CONTAIN_N_REGEX = "\\s*";
    public static class Solver {
        private static int                      allLines;
        private static String                   inputPath;
        private static String                   outputPath;
        private static AtomicInteger            words;
        private static AtomicInteger            lines;
        private static AtomicInteger            chars;
        private static ArrayList<String>[]      listArr;
        private static HashMap<String,Integer>  wordMap;
        private static Lock lock = new ReentrantLock();
        /*
        * @ description      构造函数
        * @ param inputPath  文件读入路径
        * @ param outputPath 文件输出路径
        * */
        public Solver(String inputPath,String outputPath) {
            this.inputPath = inputPath;
            this.outputPath = outputPath;
            this.words = new AtomicInteger(0);
            this.chars = new AtomicInteger(0);
            this.lines = new AtomicInteger(0);
            this.allLines = 0;
            wordMap = new HashMap<>();
        }
        /*
        * @ description 读取对应文件 分八块存至ArrayList中
        * @ return
        * */
        @SuppressWarnings("unchecked")
        public void readFileInit() {
            try {
                listArr = new ArrayList[8];
                for(int i = 0;i<8;i++) listArr[i] = new ArrayList<>();
                int j = 0;
                File file = new File(inputPath);
                FileChannel fileChannel = new RandomAccessFile(file,"r").getChannel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1 << 15);
                byte[] temp = new byte[0];
                while(fileChannel.read(byteBuffer) != -1) {
                    byte[] bs = new byte[byteBuffer.position()];
                    byteBuffer.flip();
                    byteBuffer.get(bs);
                    byteBuffer.clear();
                    int startNum = 0;
                    boolean isNewLine = false;
                    for (int i = 0;i<bs.length;i++) {
                        if (bs[i] == 10) {
                            isNewLine = true;
                            allLines++;
                            startNum = i;
                        }
                    }
                    if (isNewLine) {
                        byte[] toTemp = new byte[temp.length + startNum];
                        System.arraycopy(temp, 0, toTemp, 0, temp.length);
                        System.arraycopy(bs, 0, toTemp, temp.length, startNum);
                        String line = new String(toTemp);
                        listArr[j%8].add(line);
                        j++;
                        temp = new byte[bs.length - startNum - 1];
                        System.arraycopy(bs, startNum + 1, temp, 0, bs.length - startNum - 1);
                    } else {
                        byte[] toTemp = new byte[temp.length + bs.length];
                        System.arraycopy(temp, 0, toTemp, 0, temp.length);
                        System.arraycopy(bs, 0, toTemp, temp.length, bs.length);
                        temp = toTemp;
                    }
                }
                allLines++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /*
        * @description 具体求解方法
        * @param i     对应队列编号
        * @return
        * */
        private static void solve(int i) {
            int tempChars = 0;
            int tempLines = 0;
            int tempWords = 0;
            HashMap<String,Integer> tempMap = new HashMap<>();
            System.out.println("[Thread]: No." + i + " thread is running");
            for (String line : listArr[i]) {
                line = line.replaceAll("\r\n","\n");
                if (line.matches(CONTAIN_N_REGEX))  tempLines++;
                tempChars += line.length();
                tempWords += countWords(line,tempMap);
            }
            words.getAndAdd(tempWords);
            chars.getAndAdd(tempChars);
            lines.getAndAdd(tempLines);
            lock.lock();
            tempMap.forEach((key,value)->wordMap.merge(key, value, Integer::sum));
            lock.unlock();
        }
        /*
        * @description 创建线程池求解
        * @return
        * */
        public void poolSolve() {
            ExecutorService pool = Executors.newCachedThreadPool();
            for(int i = 0;i<8;i++) {
                int finalI = i;
                pool.execute(() -> solve(finalI));
            }
            pool.shutdown();
            try {
                while(!pool.awaitTermination(1, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            chars.getAndAdd(lines.get());
            lines.set(allLines - lines.get());
            outputToFile();
        }
        /*
        * @description 将结果写入文件
        * @return
        * */
        public static void outputToFile() {
            BufferedWriter bw = null;
            StringBuilder str = new StringBuilder("characters: " + chars + "\n"
                    + "words: " + words + "\n"
                    + "lines: " + lines + "\n");
            int cnt = 0;
            try {
                bw = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(outputPath), StandardCharsets.UTF_8));
                List<HashMap.Entry<String, Integer>> sortedList = getSortedList(wordMap);
                for(HashMap.Entry<String,Integer> entry:sortedList) {
                    if(cnt<=9){
                        str.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                    }
                    cnt++;
                }
                bw.write(str.toString());
            } catch (IOException e) {
                System.out.println("文件写入出错");
                e.printStackTrace();
            } finally {
                if (bw != null) {
                    try {
                        bw.flush();
                        bw.close();
                    } catch (IOException e) {
                        System.out.println("输出流关闭错误");
                        e.printStackTrace();
                    }
                }
            }
        }
        /*
        * @description   将字符串中含有的单词正则处理并记录至map中
        * @param line    待处理字符串
        * @param wordMap 用于存放统计结果的HashMap
        * @return cnt    当前字符串含有单词数
        * */
        public static int countWords(String line,HashMap<String,Integer> wordMap) {
            line = line.replaceAll(FLITER_REGEX," ").toLowerCase();
            int cnt = 0;
            StringTokenizer tmpWords = new StringTokenizer(line);
            while (tmpWords.hasMoreTokens()) {
                String word = tmpWords.nextToken();
                if (Pattern.matches(WORD_REGEX, word)) {
                    cnt++;
                    if (wordMap.containsKey(word)) {
                        wordMap.put(word, wordMap.get(word) + 1);
                    } else {
                        wordMap.put(word, 1);
                    }
                }
            }
            return cnt;
        }
        /*
        * @description     将map按照value降序 key字典序升序排序
        * @param wordMap   存放有词频的无序HashMap
        * @return wordList 排序后的List
        * */
        public static List<HashMap.Entry<String, Integer>> getSortedList(HashMap<String,Integer> wordMap) {
            List<HashMap.Entry<String, Integer>> wordList = new ArrayList<>(wordMap.entrySet());
            Comparator<Map.Entry<String, Integer>> cmp = (o1, o2) -> {
                if(o1.getValue().equals(o2.getValue()))
                    return o1.getKey().compareTo(o2.getKey());
                return o2.getValue()-o1.getValue();
            };
            wordList.sort(cmp);
            return wordList;
        }
    }
    /*
    * @description 程序主入口
    * */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Solver solver = new Solver("test2e6.txt","mmp2e6.txt");
        solver.readFileInit();
        solver.poolSolve();
        System.out.println(System.currentTimeMillis()-startTime + "ms");
    }
}
