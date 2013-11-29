package enhol.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 16.09.13
 * Time: 12:32
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlatResponse", propOrder = {
        "flatNumber"
})
public class FlatResponse {
    private String flatNumber;

    public FlatResponse() { }

    public FlatResponse(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }
}
