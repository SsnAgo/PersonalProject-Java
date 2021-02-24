import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WordCount {
	public static void main(String[] args) throws IOException{
			int i;
			FileInputStream fin;
			FileOutputStream fout;

			try{
			//open input file
			try{
			fin=new FileInputStream(args[0]);
			}
			catch(FileNotFoundException exc){
			System.out.println("Input file not found!");
			return;
			}

			//open output file
			try{
			fout=new FileOutputStream(args[1]);
			}
			catch(FileNotFoundException exc){
			System.out.println("Error opening Output file!");
			return;
			}
			}
			catch(ArrayIndexOutOfBoundsException exc){
			System.out.println("Usage:CopyFile From To");
			return;
			}

			//copy file
			try{
			do{
			i=fin.read();
			if(i!=-1) fout.write(i);
			}while(i!=-1);
			}
			catch(IOException exc){
			System.out.println("File error!");
			}

			fin.close();
			fout.close();
			}
}
