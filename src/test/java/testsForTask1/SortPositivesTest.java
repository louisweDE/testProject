package testsForTask1;

import org.junit.Test;
import task1.assignment1.PositiveNumbers;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class SortPositivesTest {
    @Test
    public void testNormalNums() {
        List<Integer> testList = List.of(8, -3, 4, 0, -12, 1);

        List<Integer> res = PositiveNumbers.sortPositiveNumbers(testList);

        List<Integer> expectedRes = List.of(8, 4, 1, 0);

        assertEquals(expectedRes, res);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullArr() {
        List<Integer> nullArr = null;

        PositiveNumbers.sortPositiveNumbers(nullArr);
    }

    @Test(expected = NullPointerException.class)
    public void testArrWithNull() {
        List<Integer> listWithNull = new ArrayList<>();
        listWithNull.add(-12);
        listWithNull.add(null);

        PositiveNumbers.sortPositiveNumbers(listWithNull);
    }
}
