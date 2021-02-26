import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * @author 张海浪
 * 用于查询的工具类
 * 
 * */
public class Lib {
    private static String WORDS_RE = "(?<=[^a-zA-Z0-9])[a-zA-Z]{4,}[a-zA-Z0-9]*";
    
    //获取当前工程的路径
    public static String DIR = System.getProperty("user.dir");
    
    //Map表用于存放单词以及相对应的个数
    private static Map<String, Integer> words = new HashMap<String, Integer>();
    
    
    /*
     * 统计文件的字符数
     * @ filePath
     * @ return count
     * */
    public static int getCharactersCount(String filePath) {
        //字符数的统计量
        int count = 0;
        //循环变量
        String str = "";
        
        //得到输入流
        FileInputStream is = null;
	    InputStreamReader isr = null;
	    BufferedReader br = null;
	    
	    try {
	        is = new FileInputStream(filePath);
	        isr = new InputStreamReader(is);
	        br = new BufferedReader(isr);
	        while((str = br.readLine()) != null) {
	            if(!"".equals(str)) {
	                char[] chars = str.toCharArray();
	                for(int i = 0; i < chars.length; i++) {
	                    if(chars[i] >= 0 && chars[i] <= 127) {
	                        count++;
	                    }
	                }
	                count++;
	            }
	        }
	        count--;
	    }catch(FileNotFoundException e) {
	        e.printStackTrace();
	    }catch(IOException e) {
	        e.printStackTrace();
	    }finally {
	        try {
	            //关闭输入流
	            is.close();
	            isr.close();
	        }catch(IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return count;
    }
    
    
    /*
     * 统计文件行数
     * @param filePath
     * @return count
     * */
    public static int getLineCount(String filePath) {
        //行数的统计量
	    int count = 0;
	    //循环变量
	    String str = "";
	    
	    //得到输入流
	    FileInputStream is = null;
	    InputStreamReader isr = null;
	    BufferedReader br = null;
	    
	    try {
		    is = new FileInputStream(filePath);
		    isr = new InputStreamReader(is);
		    br = new BufferedReader(isr);
		    while((str = br.readLine()) != null) {
			    if(!"".equals(str)) {
				    count++;
			    }
		    }
	    }catch(FileNotFoundException e) {
		    e.printStackTrace();
        }catch(IOException e) {
		    e.printStackTrace();
	    }finally {
		    try {
			    //关闭输入流
			    is.close();
			    isr.close();
			    br.close();
		    }catch(IOException e) {
			    e.printStackTrace();
		    }
	    }
	    return count;
    }
    
    
    /*
     * 统计文件的单词
     * @param filePath
     * @return count
     * */
    public static int getWordsCount(String filePath) {
        //单词数的统计量
        int count = 0;
        //循环变量
        String str = "";
        
        //得到输入流
        FileInputStream is = null;
	    InputStreamReader isr = null;
	    BufferedReader br = null;
	    
	    try {
	        is = new FileInputStream(filePath);
	        isr = new InputStreamReader(is);
	        br = new BufferedReader(isr);
	        while((str = br.readLine()) != null) {
	            Pattern pattern = Pattern.compile(WORDS_RE);
	            Matcher matcher = pattern.matcher(str);
	            while(matcher.find()) {
	            	String temp = matcher.group();
	            	temp = temp.toLowerCase();
	            	if(words.containsKey(temp)) {
	            		int num = words.get(temp);
	            		words.put(temp, 1 + num);
	            	}
	            	else {
	            		words.put(temp, 1);
	            	}
	                count++;
	            }
	        }
	    }catch(FileNotFoundException e) {
	    	e.printStackTrace();
	    }catch(IOException e) {
	    	e.printStackTrace();
	    }finally {
	    	try {
	    		is.close();
	    		isr.close();
	    		br.close();
	    	}catch(IOException e) {
	    		e.printStackTrace();
	    	}
	    }
	    return count;
    }
    
    
    /*
     * 用比较器实现单词排序
     * @param 无参
     * @return 无返回值
     * */
    public static void sortHashmap() {
    	//将words.entrySet()转换为list
    	List<Map.Entry<String, Integer>> list;
    	list = new ArrayList<Map.Entry<String, Integer>>(words.entrySet());
    	//通过比较器实现排序
    	Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
    		public int compare(Entry<String, Integer> m1, Entry<String, Integer>m2) {
    			return m2.getValue().compareTo(m1.getValue());
    		}
    	});
    	int i = 0;
    	for(Map.Entry<String, Integer> map : list){
    		if(i < 10) {
    			System.out.println(map.getKey() + ":" + map.getValue());
    			i++;
    		}
    	}
    }
    
}
