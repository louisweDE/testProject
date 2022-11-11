package task1.assignment1;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PositiveNumbers {
    public static List<Integer> sortPositiveNumbers(List<Integer> numArr) {
        if (numArr == null)
            throw new IllegalArgumentException("An Array is null!");

        return numArr
                .stream()
                .filter(num -> num >= 0)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}
