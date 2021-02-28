class WordCount{
    public static void main(String[]args){
        String inputFile = args[0];
        String outputFile = args[1];
        System.out.println(inputFile+" "+outputFile);
        Lib lib = new Lib();
        System.out.println("characters:"+lib.getCountChar(inputFile));
        System.out.println("words:"+lib.getCountWord(inputFile));
        System.out.println("lines:"+lib.getCountLine(inputFile));
        lib.getWordTopTen();
        lib.writeFile(outputFile);
    }
}