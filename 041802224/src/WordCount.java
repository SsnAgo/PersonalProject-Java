public class WordCount {

    public static void main(String[] args)  {
        if(args.length!=2){
            System.out.println("参数输入个数有误，请重新输入");
            return ;
        }
        String openFilePath = args[0];
        String writeFilePath = args[1];
        Lib lib = new Lib(openFilePath,writeFilePath);
        lib.open();
        lib.write();
    }
}
