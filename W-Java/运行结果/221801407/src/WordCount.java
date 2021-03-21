class WordCount{
    public static void main(String[]args){
        String inputFile = args[0];
        String outputFile = args[1];
        System.out.println(inputFile+" "+outputFile);
        Lib lib = new Lib();
        lib.fileCount(inputFile);
        lib.getWordTopTen();
        lib.writeFile(outputFile);
    }
}