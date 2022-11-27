package assignment1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonsParser {
    public static void parsePersons(File fileToParse) {
        if (fileToParse == null) return;

        File resultFile = new File("editedPersons.xml");

        try (PrintWriter out = new PrintWriter(new FileWriter(resultFile));
             Scanner scanner = new Scanner(fileToParse).useDelimiter(">")) {

            resultFile.createNewFile();

            final Pattern patternSurname = Pattern.compile("\\ssurname\\s*=\\s*\\\"(\\S+)\\\"\\s*", Pattern.DOTALL);
            final Pattern patternName = Pattern.compile("\\sname\\s*=\\s*\\\"(\\S+)\\\"\\s*", Pattern.DOTALL);

            String line;
            while (scanner.hasNext()) {
                String full_name = "";

                line = scanner.next();

                Matcher matcher = patternSurname.matcher(line);
                if (matcher.find()) {
                    full_name = matcher.group(1).concat(full_name);
                }

                matcher = patternName.matcher(line);
                if (matcher.find()) {
                    full_name = matcher.group(1).concat(" " + full_name);
                    line = line.replaceAll(matcher.group(1), full_name);
                }
                out.print(line.replaceAll("\\ssurname\\s*=\\s*\\\"(\\S+)\\\"", "") + ">");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}