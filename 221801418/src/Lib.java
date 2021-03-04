import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Lib {
	public String readTextFile(String filePath){
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
                	System.out.println("�Ҳ������ĵ���");
                }
        } catch (Exception e) {
            System.out.println("���ĵ�����");
            e.printStackTrace();
        }
		return Text.toString();
     
    }
	
	public void writeTextFile(int chars,int words,int lines,String times){
		String str="charcters:"+chars+"\nwords:"+words+"\nlines"+lines+"\n"+times;
		String filePath="output.txt";
		FileOutputStream p = null;
		try {
			p = new FileOutputStream(filePath);
			p.write(str.getBytes());
			p.close();
		} catch (IOException e) {
			System.out.print("输出文件时出错！");
			e.printStackTrace();
		}
	}
	
    public static int countChars(String str) {			//�����ַ�����
    	int sum=0;
    	char[] cs=str.toCharArray();
    	for(int i=0;i<cs.length;i++) {
    		if(cs[i]>=0&&cs[i]<128)sum++;
    	}
    	return sum;
    }
    
    
    
    
    public static int countWords(String str) {			//���ص�������
    	str=str.toLowerCase();
    	String[] strArray=str.split("[^a-z0-9]+");		//���Գ���������ĸ���ַ����ָ�
    	int words=0;
    	for(int i=0;i<strArray.length;i++) {
    		if(strArray[i].length()<4)continue;
    		else {
    			String temp=strArray[i].substring(0,4);
    			if(temp.matches("[a-z]*")) {			//�ж��Ƿ���ϵ��ʹ���
    				words++;
    			}
    		}
    	}
    	return words;
    }
    
    
    public static int countLines(String str) {			//��������
    	String[] LINE=str.split("\\s");					//���հ׷��ָ�
    	int lines=LINE.length;
    	for(int i=0;i<LINE.length;i++) {				//�۳����пհ׵�����
    		if(LINE[i].isEmpty())lines--;
    	}
    	return lines;
    }
    
    
    
    
    public String countTimes(String str) {		//ͳ�Ƶ��ʳ���Ƶ�ʲ�����
    	str=str.toLowerCase();
    	String[] strArray=str.split("[^a-z0-9]+");
	    List<String> temp = new ArrayList<String>();	//temp���ڴ�ŷ��ϵ��ʹ�����ַ���
    	for(int i=0;i<strArray.length;i++) {
    		if(strArray[i].length()<4)continue;
    		else {
    			String strtemp=strArray[i].substring(0,4);
    			if(strtemp.matches("[a-z]*")) {			//�ж��Ƿ���ϵ��ʹ��򣬷��Ϲ���ļ��뵽temp��
    				temp.add(strArray[i]);
    			}
    		}
    	}
    	strArray=temp.toArray(new String[temp.size()]);
    	Map<String,Integer> map = new HashMap<String,Integer>();
    	for (int i = 0; i < strArray.length; i++) {			//�ѷ��Ϲ�����ַ������뵽map����
    		if(map.get(strArray[i])==null){
    			map.put(strArray[i], 1);
    		}else{
    			map.put(strArray[i], map.get(strArray[i])+1);
    		}
    	}
    	Set<String> key= map.keySet();					//��ȡ��map�����е�����keyֵ
    	StringBuilder finalstr=new StringBuilder();		//finalstr���ڴ��һ������Ľ��
		while(!map.isEmpty()) {
			int i=0,maxvalue=0;							//i����ͳ�Ƽ��뵽finalstr����ĵ��ʵ�����,intmax���ڼ�¼�������ĵ��ʵĳ��ִ���
			String maxstr=" ";							//maxstr��ʾ���ִ������ĵ���
			for(String s:key) {
				int k=map.get(s);						//k��ʾ��ǰ�������ĵ��ʵĳ���Ƶ��
				if(k>maxvalue) {
					maxvalue=k;
					maxstr=s;
				}
				else if(k==maxvalue) {					//��������������ʳ���Ƶ��һ������Ƚ�ASCII��Ĵ�С
					if(maxstr.compareTo(s)>0) {
						maxvalue=k;
						maxstr=s;
					}
				}
			}
			finalstr.append(maxstr+":"+maxvalue+"\n");
			map.remove(maxstr);
			if(i==9)break;							//���iͳ����10�����ʵ�����û������ǿ���˳�
		}
    	return finalstr.toString();
    }



}
