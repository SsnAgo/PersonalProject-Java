import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class Print {
    String outputfile;

    Print(String file) {
        outputfile = file;
    }

    public void SortMap(Map<String,Integer> oldmap,int wordline,int wordcount,int characterscount) throws IOException {  
          
        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(oldmap.entrySet());  
          
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {  
            @Override  
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
                return o2.getValue() - o1.getValue();   
            }  
        }); 
        //输出结果保存到output.txt
        
        File file = new File(outputfile);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("characters: "+characterscount+"\r\n");
        bw.write("words: "+wordcount+"\r\n");
        bw.write("lines: "+wordline+"\r\n");
        int flag = 0;
        for(int i = 0; i<list.size(); i++) {  
        	if(flag>=10) {
                break;
            } 
        	if(list.get(i).getKey().length()>=4) {

        		bw.write(list.get(i).getKey()+ ": " +list.get(i).getValue()+"\r\n"); 
            }
        	flag++;
        }
        bw.close();
    }  
}