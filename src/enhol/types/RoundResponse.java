package enhol.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 11.11.13
 * Time: 17:31
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RoundResponse", propOrder = {
        "roundType",
        "roundDate",
        "dayValue",
        "nightValue"
})
public class RoundResponse {
    private String roundType;
    private String roundDate;
    private int dayValue;
    private int nightValue;

    public RoundResponse() { }

    public RoundResponse(String roundType, String roundDate, int dayValue, int nightValue) {
        this.roundType = roundType;
        this.roundDate = roundDate;
        this.dayValue = dayValue;
        this.nightValue = nightValue;
    }

    public int getNightValue() {
        return nightValue;
    }

    public void setNightValue(int nightValue) {
        this.nightValue = nightValue;
    }

    public int getDayValue() {
        return dayValue;
    }

    public void setDayValue(int dayValue) {
        this.dayValue = dayValue;
    }

    public String getRoundDate() {
        return roundDate;
    }

    public void setRoundDate(String roundDate) {
        this.roundDate = roundDate;
    }

    public String getRoundType() {
        return roundType;
    }

    public void setRoundType(String roundType) {
        this.roundType = roundType;
    }
}
