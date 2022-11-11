package task1.assignment2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Hashtags {
    public static Map<String, Integer> findTopHashtags(List<String> strList) {
        if(strList == null)
            throw new IllegalArgumentException("The list is null!");

        Map<String, Integer> mapHashtags = new TreeMap<>();
        for (String el : strList) {
            List<String> uniqueWords = Arrays.stream(el.split("\\s+"))
                    .distinct()
                    .collect(Collectors.toList());
            for (String el2 : uniqueWords) {
                if (el2.startsWith("#")) {
                    if (!mapHashtags.containsKey(el2)) {
                        mapHashtags.put(el2, 1);
                    } else {
                        mapHashtags.put(el2, mapHashtags.get((el2)) + 1);
                    }
                }
            }
        }

        return mapHashtags
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));
    }
}
