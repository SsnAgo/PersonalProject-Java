public class EffectiveLineCounter {
    public int effectiveLineNumber=0;

    private boolean isNewLine=true;
    private static StringBuffer lineContent;
    private final int INIT_LINE_MAX_LENGTH =200;

    public boolean countEffectiveLine(char lineChar, boolean isEndOfFile) {
        if (isNewLine) {
            isNewLine = false;
            lineContent = new StringBuffer(INIT_LINE_MAX_LENGTH);
        }
        if (lineChar==Config.NEW_LINE_CHAR||isEndOfFile) {
            isNewLine = true;
            if (isEffectiveLine()) {
                effectiveLineNumber++;
            }
        }
        if (!isEndOfFile) {
            lineContent.append(lineChar);
        }
        return isNewLine;
    }

    public boolean isEffectiveLine(){

        return !(lineContent.toString().isBlank());
    }
}
