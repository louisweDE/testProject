package assignment2;

import assignment1.PersonsParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ViolationsRunner {
    public static void finesStatistics(String nameOfDirectory) {
        ClassLoader classLoader = PersonsParser.class.getClassLoader();
        File[] listOfFiles = new File(classLoader.getResource(nameOfDirectory).getPath()).listFiles();

        List<File> violations = Arrays.stream(listOfFiles)
                .filter(el -> el.getName().endsWith(".xml"))
                .collect(Collectors.toList());

        ArrayList<Violation> violationsList = getViolationsFromXml(violations);
        violationsToJson(getResultsForViolations(violationsList));

    }

    public static ArrayList<ViolationResult> getResultsForViolations(List<Violation> violations) {
        ArrayList<ViolationResult> results = new ArrayList<>();

        Map<String, Double> fines = new HashMap<>();
        for (Violation violation : violations) {
            if (fines.containsKey(violation.type)) {
                fines.put(violation.type, fines.get(violation.type) + Double.parseDouble(violation.fine_amount));
            } else {
                fines.put(violation.type, Double.parseDouble(violation.fine_amount));
            }
        }
        for (String type : fines.keySet()) {
            results.add(new ViolationResult(type, fines.get(type)));
        }
        // iterate through hashmap and create results
        return results;
    }

    public static ArrayList<Violation> getViolationsFromXml(List<File> violations) {
        XmlMapper mapper = new XmlMapper();
        ArrayList<Violation> violationList = new ArrayList<>();
        for (File violationsFile : violations) {
            try {
                Violations violationsClass = mapper.readValue(violationsFile, Violations.class);
                violationList.addAll(violationsClass.violation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return violationList;
    }

    public static void violationsToJson(List<ViolationResult> violations) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File("violationsStatisticsTEST.json"), violations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
