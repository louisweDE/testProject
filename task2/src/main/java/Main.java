import assignment1.PersonsParser;
import assignment2.Violations;

import java.io.File;

public class Main {
    public static void main(String[] args){
        ClassLoader classLoader = PersonsParser.class.getClassLoader();
        File file = new File(classLoader.getResource("persons.xml").getFile());
        PersonsParser.parsePersons(file);
        Violations.finesStatistics("violations");
    }
}
