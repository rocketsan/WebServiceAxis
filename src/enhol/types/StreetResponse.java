package enhol.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 04.09.13
 * Time: 15:36
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StreetResponse", propOrder = {
        "streetId",
        "streetName",
        "sectorName"
})
public class StreetResponse {

    protected int streetId;
    protected String streetName;
    private String sectorName;

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public StreetResponse() {
    }

    public StreetResponse(int streetId, String streetName) {
        this.streetId = streetId;
        this.streetName = streetName;
    }

    public StreetResponse(int streetId, String streetName, String sectorName) {
        this.streetId = streetId;
        this.streetName = streetName;
        this.sectorName = sectorName;
    }

    public int getStreetId() {
        return streetId;
    }

    public void setStreetId(int streetId) {
        this.streetId = streetId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

}
