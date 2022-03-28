package pl.jreclaw.onlinelibrary.helpers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TagsChooser {
    public static Set<String> getTags(String content) {
        Map<String, Integer> mapOfWords =
                Arrays.stream(content.replaceAll("[^A-Za-z0-9]", " ")
                        .split(" "))
                        .filter(s -> s.length() >= 3)
                        .map(String::toLowerCase)
                        .collect(Collectors.groupingBy(w -> w,
                            Collectors.summingInt(w -> 1)));
        return mapOfWords.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
