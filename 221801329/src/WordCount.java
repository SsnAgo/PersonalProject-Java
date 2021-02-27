import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

public class WordCount {
    private static String WORD_REGEX = "[a-z]{4}[a-z0-9]*";
    private static String FLITER_REGEX = "[^a-zA-Z0-9]";
    private static String CONTAIN_N_REGEX = "\\s*";
    public static class Solver {
        private static Lock lock = new ReentrantLock();
        private static int words;
        private static int lines;
        private static int chars;
        private static int allLines;
        private static ArrayList<String>[] listArr;
        private static HashMap<String,Integer> wordMap;
        private static String inputPath;
        private static String outputPath;
        private static InputStream is;

        public Solver(String inputPath,String outputPath) {
            this.inputPath = inputPath;
            this.outputPath = outputPath;
            this.words = 0;
            this.chars = 0;
            this.lines = 0;
            this.allLines = 0;
            wordMap = new HashMap<>();
        }
        @SuppressWarnings("unchecked")
        public void readFileInit() {
            try {
                is = new FileInputStream(inputPath);
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                listArr = new ArrayList[8];
                for(int i = 0;i<8;i++) listArr[i] = new ArrayList<>();
                String line;
                int i = 0;
                while ((line = reader.readLine()) != null) {
                    allLines++;
                    listArr[i%8].add(line);
                    i++;
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        private static void solve(int i) {
            int tempChars = 0;
            int tempLines = 0;
            int tempWords = 0;
            HashMap<String,Integer> tempMap = new HashMap<>();
            System.out.println("[Thread]: No."+i+" thread is running");
            for (String line : listArr[i]) {
                line = line.replaceAll("\r\n","\n");
                if (line.matches(CONTAIN_N_REGEX))  tempLines++;
                tempChars += line.length();
                tempWords += countWords(line,tempMap);
            }
            lock.lock();
            words += tempWords;
            chars += tempChars;
            lines += tempLines;
            tempMap.forEach((key,value)->wordMap.merge(key, value, Integer::sum));
            lock.unlock();
        }
        public void poolSolve(){
            ExecutorService pool = Executors.newCachedThreadPool();
            for(int i = 0;i<8;i++) {
                int finalI = i;
                pool.execute(() -> solve(finalI));
            }
            pool.shutdown();
            try {
                while(!pool.awaitTermination(1, TimeUnit.SECONDS));
                is.close();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            lines = allLines - lines;
            outputToFile();
        }
        public static void outputToFile(){
            BufferedWriter bw = null;
            StringBuilder str = new StringBuilder("characters: " + chars + "\n"
                    + "words: " + words + "\n"
                    + "lines: " + lines + "\n");
            int cnt = 0;
            try {
                bw = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(outputPath), StandardCharsets.UTF_8));
                List<HashMap.Entry<String, Integer>> sortedList= getSortedList(wordMap);
                for(HashMap.Entry<String,Integer> entry:sortedList) {
                    if(cnt<=9){
                        str.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
                    }
                    cnt++;
                }
                bw.write(str.toString());
            } catch (IOException e){
                System.out.println("文件写入出错");
                e.printStackTrace();
            } finally {
                if (bw != null){
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
        public static int countChars(String str) {
            int count = 0;
            char[] c = str.toCharArray();
            for (int i = 0;i<c.length;i++) {
                if (c[i]<=127) {
                    //if (c[i]==9 || c[i]==10 || c[i]==13) System.out.println((int)c[i]);
                    count++;
                }
            }
            return count;
        }
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
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Solver solver = new Solver(args[0],args[1]);
        solver.readFileInit();
        solver.poolSolve();
        System.out.println(System.currentTimeMillis()-startTime + "ms");
    }
}
