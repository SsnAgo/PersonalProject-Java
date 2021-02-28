package info.tozzger.demo;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

class CounterHelper {
    private static final Pattern WORD_PATTERN = Pattern.compile("[a-z]{4,}[a-z0-9]+");

    static boolean isWord(String str) {
        return WORD_PATTERN.matcher(str).matches();
    }

    static boolean isWord1(String str) {
        if (str.length() < 4)
            return false;
        for (int i = 0; i < 4; i++) {
            if (!Character.isLowerCase(str.charAt(i))) {
                return false;
            }
        }
        for (int i = 4; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!Character.isLowerCase(ch) && !Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }

    static boolean isNotBlank(String str) {
        return !str.trim().isEmpty();
    }

    static Stream<String> split(String str) {
        return Arrays.stream(str.split("[^a-z0-9]+"));
    }
}
