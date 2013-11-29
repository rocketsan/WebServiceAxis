package enhol.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 04.09.13
 * Time: 16:01
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HouseResponse", propOrder = {
        "houseId",
        "houseNumber"
})
public class HouseResponse {

    protected int houseId;
    protected String houseNumber;

    public HouseResponse() { }

    public HouseResponse(int houseId, String houseNumber) {
        this.houseId = houseId;
        this.houseNumber = houseNumber;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
}
