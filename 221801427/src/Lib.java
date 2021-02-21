import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lib {
    static void wordCount() {
        List<String> lines = Arrays.asList("hello java", "hello world");
        Map<String, Long> words = lines.stream()
           .flatMap(w -> Stream.of(w.split(" ")))
           .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        System.out.println(words);
    }
}
