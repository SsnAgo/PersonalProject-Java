//import java.io.IOException;
//import java.util.List;
//
//public class Test {
//
//    /**
//     * 测试功能1
//     */
//    public void testCharnums() throws IOException {
//        Utils utils=new Utils();
//        String message;
//        StringBuilder stringBuilder = new StringBuilder();
//        //测试所有ascii码是否都能正确计数
//        for(int i=0;i<128;i++){
//            stringBuilder.append((char)i);
//        }
//        message=stringBuilder.toString();
//        utils.writeTo("1.txt",message);
//        System.out.println("characters: "+utils.charNums("1.txt"));
//    }
//
//    /**
//     * 测试功能2,验证单词有效无效的判定
//     */
//    public void testWordnums(){
//        Utils utils=new Utils();
//        StringBuilder stringBuilder = new StringBuilder();
//        //添加测试，包括基本可能出现的情况
//        stringBuilder.append("cold111").append("\n"); //是
//        stringBuilder.append("col111").append("\n");  //不是
//        stringBuilder.append("col]dl11").append("\n"); //不是
//        stringBuilder.append("col\nd111").append("\n"); //不是
//        stringBuilder.append("cola! sssb. aaaa, abbd\t dsds123").append("\n"); //5个都是
//
//        System.out.println("words: "+utils.wordNums(stringBuilder));
//    }
//
//    /**
//     * 测试功能3,非空字符全部算行，
//     * 1.txt内容:
//     * ssss444 563ff 11d fase11 windows95 windows98 windows2000
//     *
//     * 1
//     *
//     * 2
//     */
//    public void testLinenums() {
//        try {
//            Utils utils=new Utils();
//            System.out.println("lines："+utils.lineNums("1.txt"));
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//
//    }
//
//    /**
//     * 测试功能4，验证了是否按词频排序，且词频相同时通过字典序来比较，且无效词并不计入。
//     */
//    public void testMapnums(){
//        Utils utils=new Utils();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < 9; i++) {
//            for (int j = 2000; j < 2010; j++) {
//                stringBuilder.append("windows").append(j).append(",\n");
//            }
//        }
//        for (int i = 0; i < 10; i++) {
//            stringBuilder.append("windows2021,\n");
//            stringBuilder.append("WINDOWS2000.\n").append("windows98!\n").append("windows95?\n").append("windosa,\n");
//            stringBuilder.append("sjy%dsd*,\n");
//     }
//        List list;
//        list = utils.mapNums(stringBuilder);
//        StringBuilder mapStringbuild = new StringBuilder();
//        for (int i = 1; i <= list.size(); i++) {
//            mapStringbuild.append(list.get(i - 1)).append("\n");
//        }
//        String mapWord = mapStringbuild.toString().replace("=", ":");
//        System.out.println(mapWord);
//    }
//
//
//    public static void main(String []args) throws IOException {
//        Test test=new Test();
//        test.testCharnums();
//        test.testWordnums();
//        test.testLinenums();
//        test.testMapnums();
//    }
//}
