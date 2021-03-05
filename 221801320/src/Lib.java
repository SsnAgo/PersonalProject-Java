package wordcount;

import java.io.*;
import java.util.stream.Collectors;
import java.util.*;

public class Lib {//各项方法
	
public static Reader openInputFile(String fileName) {//创建输入文件流
		
		String ERROR_MESSAGE = "文件路径不存在";
		
		File file = new File(fileName);
		Reader reader = null;
		
		try {
			reader = new InputStreamReader(new FileInputStream(file));
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(ERROR_MESSAGE);
		}
		return reader;
	}
	
	public static BufferedWriter openOutputFile(String fileName) throws IOException {//生成输出流
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(fileName),true),"utf-8"));
		return bw;
	}
		
		
		
		public static boolean isValidChar(int temp) {//判断是否是有效字符
			if((temp >= 97 && temp <= 122) || (temp >= 65 && temp <= 90) || (temp >= 48 && temp <= 57)) {
	            return true;
	        } else {
	            return false;
	        }
		}
	
	    public static boolean isNum(int temp) {//判断是否是数字
	        if (temp >= 48 && temp <= 57) {
	            return true;
	        } else {
	            return false;
	        }
	    }

	    public static boolean isValidChars(char[] chars) {//判断是否是单词
	        if (chars.length >= 4 && !isNum(chars[0]) && !isNum(chars[1]) && !isNum(chars[2]) && !isNum(chars[3])) {
	            return true;
	        } else {
	            return false;
	        }
	    }
		
		public static void charactersCount(String inputFile,String outputFile)throws IOException{
		
			int numCount = 0;
			int temp;
			
			Reader reader = openInputFile(inputFile);
			Writer writer = new FileWriter(outputFile);
			
			while((temp = reader.read()) != -1) {
				numCount++;
			}
			writer.append("characters: " + numCount + '\n');
		
			reader.close();
			writer.close();
		}
		
		public static void wordsCount(String inputFile,String outputFile)throws IOException{
			int temp;
			int numCount = 0;
			String word = null;
			
			Reader reader = openInputFile(inputFile);
			Writer writer = openOutputFile(outputFile);
			
			while((temp = reader.read()) != -1) {
				if(isValidChar(temp)) {
				word += (char)temp;
				temp =reader.read();
				}
				else
				{
					temp = reader.read();
				}
				
				char[] chars = word.toCharArray();
				if(isValidChars(chars)) {
					numCount++;
				}
				word = "" + (char)temp;
			}
			writer.append("words: " + numCount + '\n');
			reader.close();
			writer.close();
		}
		
		
		public static void linesCount(String inputFile,String outputFile) throws IOException{
			
			Reader reader = openInputFile(inputFile);
			Writer writer = openOutputFile(outputFile);
			int temp;
			int numCount=0;
			String line = "";
			while((temp = reader.read()) != -1) {
				while(temp != -1 &&(char) temp != '\n') {
					if((char)temp != ' '&&(char)temp != '\t' && (char)temp != '\r' && (char) temp != '\n') {
						line += (char)temp;
				}
				temp = reader.read();
			}
			if(line != "") {
				numCount++;
			}
			line = "";
			}
			reader.close();
			
			writer.append("lines: " + numCount + "\n");
			writer.close();
		}
		public static void wordNum(String inputFile,String outputFile) throws IOException {
			
			Reader reader = openInputFile(inputFile);
			Writer writer = openOutputFile(outputFile);
	       
	        int temp;
	        String word = "";
	        Map<String, Integer> words = new HashMap<String, Integer>();
	        while ((temp = reader.read()) != -1) {
	            while (isValidChar(temp)) {
	                if (temp >= 65 && temp <= 90) {
	                    temp += 32;
	                }
	                word += (char) temp;
	                temp = reader.read();
	            }
	            
	            while (!isValidChar(temp) && temp != -1) {
	                temp = reader.read();
	            }
	            char[] chars = word.toCharArray();
	            
	            if (isValidChars(chars)) {
	                if (words.get(word) == null) {
	                    words.put(word, Integer.valueOf(1));
	                } else {
	                    words.put(word, Integer.valueOf(words.get(word).intValue() + 1));
	                }
	            }
	            if (temp >= 65 && temp <= 90) {
	                temp += 32;
	            }
	            word = "" + (char) temp;
	        }
	        Map<String, Integer> result = words.entrySet().stream()
	                .sorted(new Comparator<Map.Entry<String, Integer>>() {

	                    @Override
	                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
	                        if (o1.getValue().equals(o2.getValue())) {
	                            return o1.getKey().compareTo(o2.getKey());
	                        } else {
	                            return o2.getValue().compareTo(o1.getValue());
	                        }
	                    }
	                })
	                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
	                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
	        printWords(result,writer);
	        reader.close();
	        writer.close();
	    }


	    public static void printWords(Map<String, Integer> map , Writer writer) throws IOException {
	        int i = 0;
	        
	        for (Map.Entry<String, Integer> entry : map.entrySet()) {
	        	writer.append(entry.getKey() + ": " + entry.getValue() + "\n");
	            if (i++ >= 9) {
	            }
	        }
	       
	    }

	}

