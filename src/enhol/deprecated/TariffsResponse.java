package enhol.deprecated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 01.10.13
 * Time: 13:29
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TariffsResponse", propOrder = {
        "dayValuePrevious",
        "nightValuePrevious",
        "dayTariff",
        "nightTariff",
        "hasMeter",
        "normaPayer"
})

public class TariffsResponse {
    private int dayValuePrevious;
    private int nightValuePrevious;
    private float dayTariff;
    private float nightTariff;
    private int hasMeter;
    private String normaPayer;

    public String getNormaPayer() {
        return normaPayer;
    }

    public void setNormaPayer(String normaPayer) {
        this.normaPayer = normaPayer;
    }

    public int getHasMeter() {
        return hasMeter;
    }

    public void setHasMeter(int hasMeter) {
        this.hasMeter = hasMeter;
    }

    public TariffsResponse() { }

    public TariffsResponse(int dayValuePrevious, int nightValuePrevious, String normaPayer, int hasMeter) {
        this.hasMeter = hasMeter;
        this.dayValuePrevious = (hasMeter==0) ? 0 : dayValuePrevious;
        this.nightValuePrevious = (hasMeter==0) ? 0 : nightValuePrevious;
        this.normaPayer = (hasMeter==0) ? normaPayer : null;
    }

    public float getNightTariff() {
        return nightTariff;
    }

    public void setNightTariff(float nightTariff) {
        this.nightTariff = nightTariff;
    }

    public float getDayTariff() {
        return dayTariff;
    }

    public void setDayTariff(float dayTariff) {
        this.dayTariff = dayTariff;
    }

    public int getNightValuePrevious() {
        return nightValuePrevious;
    }

    public void setNightValuePrevious(int nightValuePrevious) {
        this.nightValuePrevious = nightValuePrevious;
    }

    public int getDayValuePrevious() {
        return dayValuePrevious;
    }

    public void setDayValuePrevious(int dayValuePrevious) {
        this.dayValuePrevious = dayValuePrevious;
    }

}
