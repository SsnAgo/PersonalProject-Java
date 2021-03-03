import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;


public class WordCount {

	public static String readTextFile(String filePath){
		StringBuilder Text = new StringBuilder();
		try {
                File file=new File(filePath);
                if(file.isFile() && file.exists()){
                    InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(read);
                    int x;
                    while((x=bufferedReader.read()) !=-1){
                    	Text.append((char)x);
                    }
                    read.close();

                }else{
                	System.out.println("找不到该文档！");
                }
        } catch (Exception e) {
            System.out.println("打开文档出错！");
            e.printStackTrace();
        }
		return Text.toString();
     
    }
	
	
	
    public static int countChars(String str) {			//返回字符数量
    	int sum=0;
    	char[] cs=str.toCharArray();
    	for(int i=0;i<cs.length;i++) {
    		if(cs[i]>=0&&cs[i]<128)sum++;
    	}
    	return sum;
    }
    
    
    
    
    public static int countWords(String str) {			//返回单词数量
    	str=str.toLowerCase();
    	String[] strArray=str.split("[^a-z0-9]+");		//先以除了数字字母的字符来分割
    	int words=0;
    	/*List<String> list = new ArrayList<String>();
    	for(int i=0;i<strArray.length; i++) {
    	   if (!list.contains(strArray[i])) {
    	       list.add(strArray[i]);
    	   }
    	}
    	strArray = list.toArray(new String[list.size()]);//把字符串数组过滤为没有重复元素的*/
    	for(int i=0;i<strArray.length;i++) {
    		if(strArray[i].length()<4)continue;
    		else {
    			String temp=strArray[i].substring(0,4);
    			if(temp.matches("[a-z]*")) {			//判断是否符合单词规则
    				words++;
    			}
    		}
    	}
    	return words;
    }
    
    
    public static int countLines(String str) {			//返回行数
    	String[] LINE=str.split("\\s");					//按空白符分割
    	int lines=LINE.length;
    	for(int i=0;i<LINE.length;i++) {				//扣除其中空白的行数
    		if(LINE[i].isEmpty())lines--;
    	}
    	return lines;
    }
    
    
    
    
    public static String countTimes(String str) {		//统计单词出现频率并排序
    	str=str.toLowerCase();
    	String[] strArray=str.split("[^a-z0-9]+");
    	List<String> temp = new ArrayList<String>();
    	//temp用于存放符合单次规则的字符串
    	for(int i=0;i<strArray.length;i++) {
    		if(strArray[i].length()<4)continue;
    		else {
    			String strtemp=strArray[i].substring(0,4);
    			if(strtemp.matches("[a-z]*")) {			//判断是否符合单词规则，符合规则的加入到temp中
    				temp.add(strArray[i]);
    			}
    		}
    	}
    	strArray=temp.toArray(new String[temp.size()]);
    	Map<String,Integer> map = new HashMap<String,Integer>();
    	for (int i = 0; i < strArray.length; i++) {			//把符合规则的字符串加入到map里面
    		if(map.get(strArray[i])==null){
    			map.put(strArray[i], 1);
    		}else{
    			map.put(strArray[i], map.get(strArray[i])+1);
    		}
    	}
    	Set<String> key= map.keySet();					// 获取到map集合中的所有key值
    	StringBuilder finalstr=new StringBuilder();		//finalstr用于存放一会儿最后的结果
		while(!map.isEmpty()) {
			int i=0,maxvalue=0;							//i用于统计加入到finalstr里面的单词的数量,intmax用于记录出现最多的单词的出现次数
			String maxstr=" ";							//maxstr表示出现次数最多的单词
			for(String s:key) {
				int k=map.get(s);						//k表示当前检索到的单词的出现频率
				if(k>maxvalue) {
					maxvalue=k;
					maxstr=s;
				}
				else if(k==maxvalue) {					//如果出现两个单词出现频率一样大，则比较ASCII码的大小
					if(maxstr.compareTo(s)>0) {
						maxvalue=k;
						maxstr=s;
					}
				}
			}
			finalstr.append(maxstr+":"+maxvalue+"\n");
			map.remove(maxstr);
			if(i==9)break;							//如果i统计了10个单词的量还没结束则强制退出
		}
    	return finalstr.toString();
    }
    
    
    
    public static void main(String argv[]){
        String str=readTextFile("input.txt");
        System.out.println("characters:"+countChars(str));
        System.out.println("words:"+countWords(str));
        System.out.println("lines:"+countLines(str));
        System.out.print(countTimes(str));
    }
     
}
