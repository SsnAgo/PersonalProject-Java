public class Filetest {
    public String message;
    public StringBuilder stringBuilder=new StringBuilder();

    public static void main(String []args){
        Filetest test=new Filetest();
        for (int i = 0; i < 1000000; i++) {
            for (int j = 0; j < 10 ; j++){
                test.stringBuilder.append("windows").append(i).append(" ");
            }
        }
        for (int i = 0; i < 10; i++) {
            test.stringBuilder.append("sjy ");
        }
        for (int i = 0; i < 10; i++) {
            test.stringBuilder.append("sjydl12121, \n");
        }
        test.message=test.stringBuilder.toString();
        Utils.writeTo("test.txt",test.message);
    }
}
