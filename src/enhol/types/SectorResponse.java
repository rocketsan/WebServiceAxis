package enhol.types;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 04.09.13
 * Time: 13:59
 */

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StreetResponse", propOrder = {
        "sectorId",
        "sectorName",
        "filialName"
})

public class SectorResponse {

    protected int sectorId;
    protected String sectorName;
    protected String filialName;

    public SectorResponse() {
    }

    public SectorResponse(int sectorId, String sectorName, String filialName) {
        this.sectorId = sectorId;
        this.sectorName = sectorName;
        this.filialName = filialName;
    }

    public int getSectorId() {
        return sectorId;
    }

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getFilialName() {
        return filialName;
    }

    public void setFilialName(String filialName) {
        this.filialName = filialName;
    }
/*
    public String toString() {
        return "["+getSectorId()+"] "+getSectorName()+"  ("+getFilialName()+")";
    }
*/
}
