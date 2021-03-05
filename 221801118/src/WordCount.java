import java.util.Scanner;

public class WordCount
{
    public static void main(String[] args)
    {
        Lib lib = new Lib();
        //test path " C:\\Users\\HUAWEI\\Desktop\\input.txt ";
        String inPath;    // file path
        String outPath;    // file path
        String result;    // final output
        //Scanner scanner = new Scanner(System.in);
        //inPath = scanner.nextLine();
        //inPath = args[0].toString();
        //outPath = args[1].toString();


        inPath = "C:\\Users\\HUAWEI\\Desktop\\input.txt";
        outPath = "C:\\Users\\HUAWEI\\Desktop\\output.txt";
        String test = lib.getRandomString();
        lib.writeToFile(test, inPath);


        lib.lineCount(inPath);
        lib.charCount(inPath);
        lib.wordCount(inPath);
        lib.wordFrequencyCount(inPath);

        result = "characters: " + lib.sumChars + "\n";
        result += "words: " + lib.sumWords + "\n";
        result += "lines: " + lib.sumLines + "\n";
        result += lib.sortResultStr;
        lib.outputFile(result, outPath);

        System.out.println(result);
    }
}
