package enhol.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 14.08.13
 * Time: 9:17
 *
 * Java class for DebtInfoResponse complex type.
 *
 */


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DebtInfoResponse", propOrder = {
        "requestId",
        "result",
        "debtsValue",
        "tariffScheme",
        "dayValue",
        "nightValue"
})
public class DebtInfoResponse {

    @XmlElement(name = "sectorId", required = true)
    protected String requestId;
    @XmlElement(name = "sectorName", required = true)
    protected int result;
    @XmlElement(name = "filialName")
    protected int debtsValue;
    @XmlElement(name = "tariffScheme")
    protected int tariffScheme;
    @XmlElement(name = "dayValue")
    protected int dayValue;
    @XmlElement(name = "nightValue")
    protected int nightValue;

    public DebtInfoResponse() {
    }

    public DebtInfoResponse(String requestId, int result, int debtsValue, int tariffScheme, int dayValue, int nightValue) {
        this.requestId = requestId;
        this.result = result;
        this.tariffScheme = tariffScheme;
        this.debtsValue = debtsValue;
        this.dayValue = dayValue;
        this.nightValue = nightValue;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getDebtsValue() {
        return debtsValue;
    }

    public void setDebtsValue(int debtsValue) {
        this.debtsValue = debtsValue;
    }

    public int getTariffScheme() {
        return tariffScheme;
    }

    public void setTariffScheme(int tariffScheme) {
        this.tariffScheme = tariffScheme;
    }

    public int getDayValue() {
        return dayValue;
    }

    public void setDayValue(int dayValue) {
        this.dayValue = dayValue;
    }

    public int getNightValue() {
        return nightValue;
    }

    public void setNightValue(int nightValue) {
        this.nightValue = nightValue;
    }

}
