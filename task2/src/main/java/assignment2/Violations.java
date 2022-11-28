package assignment2;

import assignment1.PersonsParser;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Violations {
    public static void finesStatistics(String nameOfDirectory) {
        ClassLoader classLoader = PersonsParser.class.getClassLoader();
        File[] listOfFiles = new File(classLoader.getResource(nameOfDirectory).getPath()).listFiles();

        List<File> violations = Arrays.stream(listOfFiles)
                .filter(el -> el.getName().endsWith(".xml"))
                .collect(Collectors.toList());

        Map<String, Double> statisticsMap = getSortedFinesFromXML(violations);
        violationsStatisticsToJson(statisticsMap);
    }

    public static Map<String, Double> getSortedFinesFromXML(List<File> violations) {
        Map<String, Double> fines = new HashMap<>();
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        for (File violationsFile : violations) {
            try (FileInputStream fi = new FileInputStream(violationsFile)) {
                XMLEventReader reader = xmlInputFactory.createXMLEventReader(fi);

                while (reader.hasNext()) {
                    XMLEvent nextEvent = reader.nextEvent();

                    if (nextEvent.isStartElement()) {
                        StartElement startElement = nextEvent.asStartElement();

                        if ("violation".equals(startElement.getName().getLocalPart())) {
                            Attribute type = startElement.getAttributeByName(new QName("type"));
                            Attribute fine_amount = startElement.getAttributeByName(new QName("fine_amount"));

                            if (fines.containsKey(type.getValue())) {
                                fines.put(type.getValue(),
                                        (fines.get(type.getValue()) + Double.valueOf(fine_amount.getValue())));
                            } else {
                                fines.put(type.getValue(), Double.valueOf(fine_amount.getValue()));
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fines.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldV, newV) -> oldV, LinkedHashMap::new));
    }

    public static void violationsStatisticsToJson(Map<String, Double> violationsStatistics) {
        try {
            File resultFile = new File("violationsStatistics.json");
            resultFile.createNewFile();
            PrintWriter out = new PrintWriter(new FileWriter(resultFile));

            JsonFactory factory = new JsonFactory();
            StringWriter jsonObjectWriter = new StringWriter();
            JsonGenerator generator = factory.createJsonGenerator(jsonObjectWriter);
            generator.useDefaultPrettyPrinter(); // pretty print JSON
            generator.writeStartObject();
            generator.writeFieldName("violations_statistics");
            generator.writeStartArray();
            for (var entry : violationsStatistics.entrySet()) {
                generator.writeStartObject();
                generator.writeFieldName("type");
                generator.writeString(entry.getKey());
                generator.writeFieldName("fine_amount");
                generator.writeNumber(entry.getValue());
                generator.writeEndObject();
            }
            generator.writeEndArray();
            generator.writeEndObject();
            generator.close();
            out.print(jsonObjectWriter);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
