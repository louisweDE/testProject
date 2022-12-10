package assignment2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Violation {
    public String type;
    public String fine_amount;
    public String first_name;
    public String last_name;

    @JacksonXmlElementWrapper(localName = "date_time")
    public String dateTime;
}
