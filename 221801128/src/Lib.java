
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Lib {
	
	static int readline;
	static int len = 0;
	static String word = "";
	static int x = 0;//前一位是数字变1
	static int num = 0;
	static HashMap<String, Integer> hash=new HashMap<String,Integer>();
		
	/**
	 * 转化为小写字母
	 */
	public static char toLower(char ch){
		if(ch >= 'A' && ch <= 'Z'){
			return (char) ((ch-'A')+'a');
		} 
		return ch;
	}
	
	/**
	 * 统计总字符数
	 */
	public static int countChar(String inputfile) throws IOException {
		int charCount = 0;
		int ch = 0;
		BufferedReader br = new BufferedReader(new FileReader(inputfile));
		while((ch=br.read())!=-1){
			if(ch<128 && ch>0)
				charCount++;
		}
		br.close();
		return charCount;
	}
	 
	/**
	 * 统计总单词数
	 */
		public static int countWords() {
			int countnum = 0;
			Set<Map.Entry<String, Integer>> set=hash.entrySet();
			Iterator<Map.Entry<String, Integer>> it=set.iterator();
			while(it.hasNext()){
				Map.Entry<String, Integer> e=it.next();
				countnum+=e.getValue();
			}
			return countnum;
		}
		
		/**
		 * 统计有效行数
		 * @throws IOException 
		 */
		   public static int countLines(String inputfile) throws IOException {
			   BufferedReader br = new BufferedReader(new FileReader(inputfile));
		        String str = null;
		        String pattern = "[^ ].*";
		        int countLine = 0;

		        while((str= br.readLine())!=null){
		            if(str.matches(pattern)){
		                countLine++;
		            }
		        }
		        br.close();
		        return countLine;
		    }
	/**
	 * 将单词加入数组
	 */
	public static void towords(String word) {
		int m = 0;
		Set<Map.Entry<String, Integer>> set=hash.entrySet();
	    Iterator<Map.Entry<String, Integer>> it=set.iterator();
	    while(it.hasNext()){
	        Map.Entry<String, Integer> e=it.next();
	        if(!word.equals(null)) {
	        	if(word.equals(e.getKey())) {
	        		hash.put(word, (e.getValue()+1));
	        		m = 1;
	        	}
	        }
	    }
	    if(m == 0) {
	    	hash.put(word, 1);
	    }
	}
	
	/**
	 * 按频率从大到小排序
	 */
	public static List<HashMap.Entry<String, Integer>> getSortedList(HashMap<String,Integer> wordMap) {
        List<HashMap.Entry<String, Integer>> wordOfList = new ArrayList<>(wordMap.entrySet());
        Comparator<Map.Entry<String, Integer>> cmp = (o1, o2) -> {
            if(o1.getValue().equals(o2.getValue()))
                return o1.getKey().compareTo(o2.getKey());
            return o2.getValue()-o1.getValue();
        };
        wordOfList.sort(cmp);
        return wordOfList;
    }
	

	   /**
	    * 对取得数据的处理
	    */
	  public static void solve(BufferedReader br) throws IOException {
		  while((readline = br.read())!= -1) {
				char c =Lib.toLower((char)readline);
				if(!String.valueOf(c).matches("[\u4e00-\u9fa5]")) {
					num++;
				}
				if(len<4) {
					if(c>='a' && c<='z') {
						if(x == 0) {
							word+=c;
							len+=1;
						}
						else {
							len = 0;
							word = "";
							x = 0;
						}
					}
					else if(c>='0' && c<='9'){
						len = 0;
						word = "";
						x = 1;
					}
					else {
						len = 0;
						word = "";
						x = 0;
					}
					
					
				}
				else if(len>=4){
					if(c>='a' && c<='z') {
						if(x == 0) {
							word+=c;
							len+=1;
						}
						else {
							Lib.towords(word);
							word = "";
							len = 0;
							x = 0;
						}
					}
					else if(c>='0' && c<='9') {
						if(x == 0) {
							x = 1;
							word = word+""+c;
							len+=1;
						}
						else {
							word = word+""+c;
							len+=1;
						}
					}
					else if(!((c>='a' && c<='z')||(c>=0 && c<=9))) {
						Lib.towords(word);
						word = "";
						len = 0;
						x = 0;
					}
				}
			}
			if((readline = br.read()) == -1 && len>=4) {
				Lib.towords(word);
			}
	  }
		
	  /**
	   * 打印
	 * @throws IOException 
	   */
	 public static void printall(String inputfile) throws IOException {
		 StringBuilder str = new StringBuilder("characters: " + Lib.countChar(inputfile) + "\n"
	                + "words: " + Lib.countWords() + "\n"
	                + "lines: " + Lib.countLines(inputfile) + "\n");
		 int cnt = 0;
		 List<HashMap.Entry<String, Integer>> sortedList = getSortedList(hash);
	        for(HashMap.Entry<String,Integer> entry:sortedList) {
	            if(cnt<=9){
	                str.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
	            }
	            cnt++;
	        }
	        System.out.print(str.toString());
			
	 }
	 
	 /**
	  * 写入指定文件
	  */
	 public static void writeIn(String inputfile,String outputfile) throws IOException {
		 Path path1 = Paths.get(outputfile);
		BufferedWriter writer = null;
		StringBuilder str = new StringBuilder("characters: " + Lib.countChar(inputfile) + "\n"
                + "words: " + Lib.countWords() + "\n"
                + "lines: " + Lib.countLines(inputfile) + "\n");
		int cnt = 0;
		try {
			writer = Files.newBufferedWriter(path1, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<HashMap.Entry<String, Integer>> sortedList = getSortedList(hash);
        for(HashMap.Entry<String,Integer> entry:sortedList) {
            if(cnt<=9){
                str.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            cnt++;
        }
        writer.write(str.toString());
		writer.close();
	 }
}
