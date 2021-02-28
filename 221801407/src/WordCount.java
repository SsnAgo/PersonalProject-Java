class WordCount{
    public static void main(String[]args){
        System.out.println("HelloWorld");
        //System.out.println(args.length);
        //String inputFile = args[0];
        //String outputFile = args[1];
        //System.out.println(inputFile+" "+outputFile);
        String inputFile = "input.txt";
        String outputFile = "output.txt";
        Lib lib = new Lib();
        System.out.println("characters:"+lib.getCountChar(inputFile));
        System.out.println("words:"+lib.getCountWord(inputFile));
        System.out.println("lines:"+lib.getCountLine(inputFile));
    }
}