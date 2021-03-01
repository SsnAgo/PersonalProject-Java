import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Lib {

    static Map<String,Integer> WordsMap=new TreeMap<>();

    static int GetCharactersNum(File file){

        int num=0;

        try {
            InputStreamReader Reader=new InputStreamReader(new FileInputStream(file));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return num;

    }

    static int GetWordsNum(File file){

        int num=0;

        return num;

    }

    static int GetLinesNum(File file){

        int num=0;

        return num;

    }

}
