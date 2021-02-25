import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * @author 张海浪
 * 用于查询的工具类
 * 
 * */
public class Lib {
    private static String CHINESE_RE="[\\u4e00-\\u9fa5]";
    
    //获取当前工程的路径
    public static String DIR=System.getProperty("user.dir");
    
    
    /*
     * 用正则表达式CHINESE_RE过滤中文
     * @param str
     * @return newStr
     * */
    public static String filterChinese(String str) {
        Pattern pattern=Pattern.compile(CHINESE_RE);
        Matcher matcher=pattern.matcher(str);
        String newStr=matcher.replaceAll("");
        return newStr;
    }
    
    
    /*
     * 统计文件行数
     * @param filePath
     * @return count
     * */
    public static int getLineCount(String filePath) {
	    //行数的统计量
	    int count=0;
	    //循环变量
	    String str="";
	    
	    //得到输入流
	    FileInputStream is=null;
	    InputStreamReader isr=null;
	    BufferedReader br=null;
	    
	    try {
		    is=new FileInputStream(filePath);
		    isr=new InputStreamReader(is);
		    br=new BufferedReader(isr);
		    while((str=br.readLine())!=null) {
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
     * 统计文件的字符数
     * @ filePath
     * @ return count
     * */
    public static int getCharactersCount(String filePath) {
        //字符数的统计量
        int count=0;
        //循环变量
        String temp="";
        
        //得到输入流
	    FileInputStream is=null;
	    InputStreamReader isr=null;
	    BufferedReader br=null;
	    try {
	        is=new FileInputStream(filePath);
	        isr=new InputStreamReader(is);
	        br=new BufferedReader(isr);
	        while((temp=br.readLine())!=null) {
	            if(!"".equals(temp)) {
	                char[] chars=temp.toCharArray();
	                for(int i=0;i<chars.length;i++) {
	                    if(chars[i]>=0&&chars[i]<=127) {
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
    
}
