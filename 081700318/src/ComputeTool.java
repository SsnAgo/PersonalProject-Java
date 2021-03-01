import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComputeTool {

    String TargetString;
    ArrayList<String> Rows;
    ArrayList<String> ValidRows;

    /**
     * @description      构造函数
     */
    public ComputeTool(String TargetString)
    {
        this.TargetString=TargetString;
        Rows =new ArrayList<String>(Arrays.asList(TargetString.split("\n")));
        ValidRows =new ArrayList<String>();

        for (String Row:Rows)
        {
            if(!(Row.trim().isEmpty()))
                ValidRows.add(Row);//将有效行加入集合
        }

    }


    /**
     * @description      统计文件的字符数
     */
    public int countCharNums()
    {
        return  TargetString.length();
    }


    /**
     * @description      统计文件的行数
     */
    public int countRowNums()
    {
        return  ValidRows.size();
    }

    /**
     * @description      统计文件的单词总数
     */    /**
     * @description      有效行数
     */
    public int countWordNums()
    {
        return  Rows.size();
    }

}
