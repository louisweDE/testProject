package testsForTask1;

import org.junit.Test;
import task1.assignment1.PositiveNumbers;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SortPositivesTest {
    @Test
    public void testNormalNums() {
        List<Integer> testList = List.of(8, -3, 4, 0, -12, 1);

        List<Integer> res = PositiveNumbers.sortPositiveNumbers(testList);

        List<Integer> expectedRes = List.of(8, 4, 1, 0);

        assertEquals(expectedRes, res);
    }

    @Test
    public void testNullArr() {
        List<Integer> nullArr = null;

        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () -> {
                    PositiveNumbers.sortPositiveNumbers(nullArr);
                }, "Allowed null");

        assertEquals("An Array is null!", ex.getMessage());
    }
}
