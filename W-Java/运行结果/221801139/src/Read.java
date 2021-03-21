import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

public class Read {
	public static String ReadFile(String pathname) throws Exception {   	
    	Reader myReader = new FileReader(pathname);
    	Reader myBufferedReader = new BufferedReader(myReader);
    	
    	CharArrayWriter  tempStream = new CharArrayWriter();
    	int i = -1;
		
    	do {
    	if(i!=-1) {
			tempStream.write(i);
		}    	
    	i = myBufferedReader.read();
    	if(i >= 65 && i <= 90) {
    		i += 32;
    	}
    	}
		while(i != -1);

    	myBufferedReader.close();
    	Writer myWriter = new FileWriter(pathname);
    	tempStream.writeTo(myWriter);
    	tempStream.flush();
    	tempStream.close();
    	myWriter.close();
		return pathname;
	}  
}