class WordCount{
public static void main(String[]args){
        System.out.println("HelloWorld");
        //System.out.println(args.length);
        //String inputFile = args[0];
        //String outputFile = args[1];
        //System.out.println(inputFile+" "+outputFile);
        String inputFile = "input.txt";
        String outputFile = "output.txt";
        Lib lib = new Lib(inputFile,outputFile);
        lib.readFile();
        System.out.println(lib.countChar);
    }
}