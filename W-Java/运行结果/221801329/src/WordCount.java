import sun.misc.Cleaner;
import sun.nio.ch.DirectBuffer;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class WordCount{
    private static String WORD_REGEX = "[a-z]{4}[a-z0-9]*";
    private static String FLITER_REGEX = "[^a-zA-Z0-9]";
    private static String CONTAIN_N_REGEX = "\\s*";
    public static class Solver {
        private static int                               lines;
        private static int                               chars;
        private static int                               allLines;
        private static String                            str;
        private static String                            inputPath;
        private static String                            outputPath;
        private static AtomicInteger                     words;
        private static StringTokenizer                   strArr;
        private static ConcurrentHashMap<String,Integer> wordMap;
        private static ExecutorService pool = new ThreadPoolExecutor(
                8, Math.max(Runtime.getRuntime().availableProcessors() << 1, 16), 30, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(Integer.MAX_VALUE), r -> new Thread(null, r, "", 0),
                new ThreadPoolExecutor.CallerRunsPolicy());
        /**
         * @description      构造函数
         * @param inputPath  文件读入路径
         * @param outputPath 文件输出路径
         */
        public Solver(String inputPath,String outputPath) {
            this.inputPath = inputPath;
            this.outputPath = outputPath;
            this.chars = 0;
            this.words = new AtomicInteger(0);
            this.allLines = 0;
            this.lines = 0;
            this.wordMap = new ConcurrentHashMap<>(1<<18);
        }
        /**
         * @description 读取对应文件
         */
        public static String readFileInit() {
            FileChannel fileChannel = null;
            MappedByteBuffer byteBuffer = null;
            try {
                File file = new File(inputPath);
                fileChannel = new RandomAccessFile(file,"r").getChannel();
                byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY,0,file.length());
                if(byteBuffer != null) {
                    return StandardCharsets.UTF_8.decode(byteBuffer).toString();
                } else {
                    return "";
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (byteBuffer != null) {
                    Cleaner cleaner = ((DirectBuffer)byteBuffer).cleaner();
                    if (cleaner != null) {
                        cleaner.clean();
                    }
                }
                if (fileChannel != null) {
                    try {
                        fileChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return "";
        }
        /**
         * @description 调用读取文件函数 初始化字符串
         */
        public void setList() {
            str = readFileInit();
            chars = str.length();
            strArr = new StringTokenizer(str,"\n");
            allLines = strArr.countTokens();
        }
        /**
         * @description 计算空白行数
         */
        private static void countLine() {
            while(strArr.hasMoreTokens()){
                String line = strArr.nextToken();
                if (line.matches(CONTAIN_N_REGEX)) lines++;
            }
        }
        /**
         * @description 统计[begin,end]区间内的词频
         * @param begin
         * @param end
         */
        private static void countMap(int begin, int end) {
            String tempStr = str.substring(begin,end);
            StringTokenizer tempSt = new StringTokenizer(tempStr);
            while (tempSt.hasMoreTokens()) {
                String line = tempSt.nextToken();
                line = line.toLowerCase().replaceAll(FLITER_REGEX," ");
                StringTokenizer tmpWords = new StringTokenizer(line);
                int cnt = 0;
                while (tmpWords.hasMoreTokens()) {
                    String word = tmpWords.nextToken();
                    if (Pattern.matches(WORD_REGEX, word)) {
                        cnt++;
                        if (wordMap.containsKey(word)) {
                            add(word);
                        } else {
                            add(word);
                        }
                    }
                }
                words.getAndAdd(cnt);
            }
        }
        /**
         * @description 创建线程池求解
         */
        public void poolSolve() {
            int begin = 0;
            int j;
            pool.execute(Solver::countLine);
            int MAX_LEN = chars >> 3;
            for (int i = 0;i<chars;i += MAX_LEN) {
                if (i > begin) {
                    for (j = i;j<chars;j++) {
                        if(str.charAt(j) == '\n') {
                            break;
                        }
                    }
                    int finalBegin = begin;
                    int finalEnd = j;
                    pool.execute(() -> countMap(finalBegin,finalEnd));
                    begin = j + 1;
                }
            }
            if (begin < chars) {
                int finalBegin = begin;
                pool.execute(() ->countMap(finalBegin,chars));
            }
            pool.shutdown();
            try {
                while(!pool.awaitTermination(1, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lines = allLines - lines;
            outputToFile();
        }

        /**
         * @description 保证添加到ConcurrentHashMap中的数据正确
         * @param word
         */
        private static void add(String word) {
            Integer value;
            while (true) {
                value = wordMap.get(word);
                if (null == value) {
                    if (wordMap.putIfAbsent(word, 1) == null) break;
                } else {
                    if (wordMap.replace(word, value, value + 1)) break;
                }
            }
        }
        /**
         * @description 将结果写入文件
         */
        public static void outputToFile() {
            BufferedWriter bw = null;
            StringBuilder str = new StringBuilder("characters: " + chars + "\n"
                    + "words: " + words + "\n"
                    + "lines: " + lines + "\n");
            int cnt = 0;
            try {
                bw = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(outputPath), StandardCharsets.UTF_8));
                List<HashMap.Entry<String, Integer>> sortedList = Lib.getSortedList(wordMap,false);
                for(HashMap.Entry<String,Integer> entry:sortedList) {
                    if(cnt<=9) {
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

    }
    /**
     * @description 程序主入口
     */
    public static void main(String[] args) {
        if (args.length!=2) {
            System.out.println("请输入\"输入文件\"和\"输出文件\"的路径");
            return;
        }
        long startTime = System.currentTimeMillis();
        Solver solver = new Solver(args[0],args[1]);
        solver.setList();
        solver.poolSolve();
        System.out.println(System.currentTimeMillis()-startTime + "ms");
    }
}
