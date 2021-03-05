import java.io.File;
import java.util.List;
import java.util.Map;

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
        String file_info = lib.fileToString(args[0]);
        System.out.print(lib.getAsciiCount(file_info)+"\n");
        System.out.print(lib.getWordsCount(file_info)+"\n");
        System.out.print(lib.getLinesCount(args[0])+"\n");
        List<Map.Entry<String,Integer>> words_arr = lib.getMostFrequentlyWords(file_info);
        //判断一下是否有十个单词
        int len = words_arr.size()>=10?10:words_arr.size();
        for (int i=0;i<len;i++){
            Map.Entry<String,Integer> entry = words_arr.get(i);
            System.out.print(entry.getKey()+": "+entry.getValue());
            if (i!=len-1) System.out.print("\n");
        }
    }
}
