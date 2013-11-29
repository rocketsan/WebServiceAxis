package enhol.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 17.09.13
 * Time: 12:22
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReadingsResponse", propOrder = {
        "filialCode",
        "filialName"
})
public class FilialResponse {
    private int filialCode;
    private String filialName;

    public FilialResponse() { }

    public FilialResponse(int filialCode, String filialName) {
        this.filialCode = filialCode;
        this.filialName = filialName;
    }

    public String getFilialName() {
        return filialName;
    }

    public void setFilialName(String filialName) {
        this.filialName = filialName;
    }

    public int getFilialCode() {
        return filialCode;
    }

    public void setFilialCode(int filialCode) {
        this.filialCode = filialCode;
    }
}
