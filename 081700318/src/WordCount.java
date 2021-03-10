import java.io.File;
import java.io.IOException;
import java.io.Writer;

public class WordCount{

    public static void main(String[] args)
    {
        FileTool FileIo=new FileTool();
        FileIo.getReader(args[0]);
        FileIo.getWriter(args[1]);
        ComputeTool ComputeCore=new ComputeTool(FileIo.getFileString());
        ComputeCore.compute();
        FileIo.writeResult(ComputeCore.CharNums,ComputeCore.RowNums,ComputeCore.WordNums,ComputeCore.TopList);
        FileIo.closeReader();
        FileIo.closeWriter();
    }

    }
