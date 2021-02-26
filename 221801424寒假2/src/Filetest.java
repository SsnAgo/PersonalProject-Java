public class Filetest {
    public String message;
    public StringBuilder stringBuilder=new StringBuilder();

    public static void main(String []args){
        Filetest test=new Filetest();
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 5 ; j++){
                test.stringBuilder.append("windows").append(i).append(" ");
            }
        }
        for (int i = 0; i < 10; i++) {
            test.stringBuilder.append("sjy1 ");
        }
        for (int i = 0; i < 10; i++) {
            test.stringBuilder.append("sjydl12121,\n");
        }
        for (int i = 0; i < 2; i++) {
            test.stringBuilder.append("wyc811,\n");
        }
        for (int i = 0; i < 6; i++) {
            test.stringBuilder.append("wycsjy2099,\n");
        }
        test.message=test.stringBuilder.toString();
        Utils.writeTo("test.txt",test.message);
    }
}
