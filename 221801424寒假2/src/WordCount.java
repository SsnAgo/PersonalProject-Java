import java.util.List;

class WordCount{
    public static void main(String []args){
        StringBuilder stringBuilder=new StringBuilder();
        long begintime = Utils.getTime();
        StringBuilder tempStringbuild=Utils.readIn("test.txt");
        long endTime = Utils.getTime();
        long time = endTime - begintime;
        System.out.println("读入successful,耗时"+time+"毫秒");
        //处理特殊字符，以免被误以为是单词的一部分
        String handleCharacters = tempStringbuild.toString()
                .replace(".", " ").replace(",", " ")
                .replace("!", " ").replace("?", " ");
        String characters=tempStringbuild.toString();
        begintime=Utils.getTime();
        stringBuilder.append("characters:").append(Utils.charNums(characters)).append("\n");
        endTime=Utils.getTime();
        time=endTime-begintime;
        System.out.println("获取所有字符数successful,耗时"+time+"毫秒");
        begintime=Utils.getTime();
        stringBuilder.append("words:").append(Utils.wordNums(handleCharacters)).append("\n");
        endTime=Utils.getTime();
        time=endTime-begintime;
        System.out.println("获取所有单词数successful,耗时"+time+"毫秒");
        begintime=Utils.getTime();
        stringBuilder.append("lines:").append(Utils.lineNums("test.txt")).append("\n");
        endTime=Utils.getTime();
        time=endTime-begintime;
        System.out.println("获取行数successful,耗时"+time+"毫秒");
        List list;
        begintime=Utils.getTime();
        list=Utils.mapNums(handleCharacters);
        StringBuilder mapStringbuild=new StringBuilder();
        for(int i=1;i<=list.size();i++){
            mapStringbuild.append(list.get(i - 1)).append("\n");
        }
        String mapWord=mapStringbuild.toString().replace("=",":");
        endTime=Utils.getTime();
        time=endTime-begintime;
        System.out.println("获取所有词频successful,耗时"+time+"毫秒");
        System.out.println(stringBuilder+mapWord);
        begintime=Utils.getTime();
        Utils.writeTo("result.txt", (stringBuilder+mapWord));
        endTime=Utils.getTime();
        time=endTime-begintime;
        System.out.println("写入文件successful,耗时"+time+"毫秒");
    }
}