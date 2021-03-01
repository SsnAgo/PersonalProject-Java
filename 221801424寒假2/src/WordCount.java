import javax.swing.text.html.parser.Parser;
import java.io.IOException;
import java.util.List;

class WordCount {
    public static void main(String[] args) {
        Utils utils = new Utils();
        /**
         * 直接运行,进行覆盖率测试
         */
        if (args.length != 2) {
            try {
                System.out.println("格式错误,使用默认文件(input.txt),生成(output.txt)");
                String readPath = "input.txt";
                String writePath = "output.txt";
//           long begintime = Utils.getTime();
                //生成一个input.txt文件
                Filetest File = new Filetest();
                File.fileTest();

                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder tempStringbuild = utils.readIn(readPath);

                String characters = tempStringbuild.toString();
                long begintime = Utils.getTime();
                stringBuilder.append("characters: ").append(utils.charNums(characters)).append("\n");
                stringBuilder.append("words: ").append(utils.wordNums(tempStringbuild)).append("\n");
                stringBuilder.append("lines: ").append(utils.lineNums(readPath)).append("\n");

                List list;
                list = utils.mapNums(tempStringbuild);
                StringBuilder mapStringbuild = new StringBuilder();
                for (int i = 1; i <= list.size(); i++) {
                    mapStringbuild.append(list.get(i - 1)).append("\n");
                }
                String mapWord = mapStringbuild.toString().replace("=", ":");

                System.out.println(stringBuilder + mapWord);

                utils.writeTo(writePath, (stringBuilder + mapWord));
                long endTime = Utils.getTime();
                long time = endTime - begintime;
                System.out.println("运行成功,耗时" + time + "毫秒");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 命令行输入运行
         */
        else {
            try {
                String readPath = args[0];
                String writePath = args[1];
//            long begintime = Utils.getTime();
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder tempStringbuild = utils.readIn(readPath);
                //处理特殊字符，以免被误以为是单词的一部分
                String characters = tempStringbuild.toString();
                stringBuilder.append("characters: ").append(utils.charNums(characters)).append("\n");
                stringBuilder.append("words: ").append(utils.wordNums(tempStringbuild)).append("\n");
                stringBuilder.append("lines: ").append(utils.lineNums(readPath)).append("\n");
                List list;
                list = utils.mapNums(tempStringbuild);
                StringBuilder mapStringbuild = new StringBuilder();
                for (int i = 1; i <= list.size(); i++) {
                    mapStringbuild.append(list.get(i - 1)).append("\n");
                }
                String mapWord = mapStringbuild.toString().replace("=", ":");
                System.out.println(stringBuilder + mapWord);
                utils.writeTo(writePath, (stringBuilder + mapWord));
//            long endTime = Utils.getTime();
//            long time = endTime - begintime;
//            System.out.println("运行成功,耗时" + time + "毫秒");

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}