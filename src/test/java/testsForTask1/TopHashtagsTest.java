package testsForTask1;

import org.junit.Test;
import task1.assignment2.Hashtags;

import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TopHashtagsTest {
    @Test
    public void testWithHashtags() {
        List<String> testStrList = List.of(
                "#hi cat how are #you #you are #good #so #hi #again",
                "#no #hi #again and  #listen to #you #man",
                "#you #no stay #here #good and #nice #job",
                "#hi #again so #you look #like cutie #man",
                "#you #like a #good #man",
                "#again #you doing #well #man #b #c",
                "#man #well but #not good #yeah");

        Map<String, Integer> result = Hashtags.findTopHashtags(testStrList);

        Map<String, Integer> expectedResult = Map.of(
                "#you", 6,
                "#man", 5,
                "#again", 4,
                "#hi", 3,
                "#good", 3);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testNullList() {
        List<String> nullList = null;

        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () -> {
                    Hashtags.findTopHashtags(nullList);
                }, "Allowed null");

        assertEquals("The list is null!", ex.getMessage());
    }
}
