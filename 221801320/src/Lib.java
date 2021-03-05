package wordcount;

import java.io.*;
import java.util.stream.Collectors;
import java.util.*;

public class Lib {
	
public static Reader openInputFile(String fileName) {
		
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
	
	public static void writeToFile(String fileName ,String str) throws IOException {
		File file = new File(fileName);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));

		bw.write(str);
	}
		
		public static String output = "";
		
		public static boolean isValidChar(int temp) {
			if((temp >= 97 && temp <= 122) || (temp >= 65 && temp <= 90) || (temp >= 48 && temp <= 57)) {
	            return true;
	        } else {
	            return false;
	        }
		}
	
	    public static boolean isNum(int temp) {
	        if (temp >= 48 && temp <= 57) {
	            return true;
	        } else {
	            return false;
	        }
	    }

	    public static boolean isValidChars(char[] chars) {
	        if (chars.length >= 4 && !isNum(chars[0]) && !isNum(chars[1]) && !isNum(chars[2]) && !isNum(chars[3])) {
	            return true;
	        } else {
	            return false;
	        }
	    }
		
		public static void charactersCount(String inputFile)throws IOException{
			Reader reader = openInputFile(inputFile);
			int numCount = 0;
			int temp;
			
			while((temp = reader.read()) != -1) {
				numCount++;
			}
			
			output.concat("characters: " + numCount + '\n');
			System.out.println("1"+ output);
			System.out.println(numCount);
			reader.close();
			
		}
		
		public static void wordsCount(String inputFile)throws IOException{
			Reader reader = openInputFile(inputFile);
			int temp;
			int numCount = 0;
			String word = null;
			
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
			output.concat("words: " + numCount + '\n');
			reader.close();
		}
		
		
		public static void linesCount(String inputFile) throws IOException{
			Reader reader = openInputFile(inputFile);
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
			output.concat("lines: " + numCount + "\n");
		}
		public static void wordNum(String inputFile) throws IOException {
	        Reader reader = openInputFile(inputFile);
	       
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
	        printWords(result);
	        reader.close();
	    }


	    public static void printWords(Map<String, Integer> map) throws IOException {
	        int i = 0;
	        for (Map.Entry<String, Integer> entry : map.entrySet()) {
	            output.concat(entry.getKey() + ": " + entry.getValue() + "\n");
	            if (i++ >= 9) {
	            }
	        }
	    }

	}

