public class WordCount {

    public static void main(String[] args)  {
        String openFilePath = args[0];
        String writeFilePath = args[1];
        Lib lib = new Lib(openFilePath,writeFilePath);
        lib.open();
        lib.write();
    }
}
