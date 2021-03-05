import java.io.File;

public class WordCount {
    public static void main(String[] args) {

        //验证命令行参数
        if (args.length!=2){
            if (args.length<2 && args.length>=0){
                System.err.println("参数数量过少，请输入两个参数，参数一：输入文件的地址，参数二：输出文件的地址。");
            }else{
                System.err.println("参数数量过多，请输入两个参数，参数一：输入文件的地址，参数二：输出文件的地址。");
            }
            return;
        }

        //验证输入文件路径是否存在
        File input_file = new File(args[0]);
        if (!input_file.exists()){
            System.err.println("输入文件路径不存在");
            return;
        }

        Lib lib = new Lib();
        System.out.println(lib.getLinesCount(args[0]));
    }
}
