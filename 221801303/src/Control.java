import java.io.File;


public class Control {
    public File in;
    Lib cou = new Lib();
    //将输入输出位置赋值
    public void set(String i,String o){
        in = new File(i);
        cou.set(o);
    }

    public void run(){
        cou.charcount(in);
        cou.linecount(in);
        cou.wordcount(in);
    }

}
