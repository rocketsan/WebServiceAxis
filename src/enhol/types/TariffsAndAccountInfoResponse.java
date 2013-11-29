package enhol.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 12.11.13
 * Time: 10:45
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TarifAndAccountResponse", propOrder = {
        "result",
        "account",
        "phone1",
        "phone2",
        "phone3",
        "email1",
        "email2",
        "typeFlat",
        "typeFund",
        "typeAccount",
        "typeMeter",
        "digitsMeter",
        "dateStartMeter",
        "dayValuePrevious",
        "nightValuePrevious",
        "dayTariff",
        "nightTariff",
        "hasMeter",
        "normaPayer"
})
public class TariffsAndAccountInfoResponse {
    private int result;
    private long account;
    private String phone1;
    private String phone2;
    private String phone3;
    private String email1;
    private String email2;
    private String typeFlat;
    private String typeFund;
    private String typeAccount;
    private String typeMeter;
    private int digitsMeter;
    private String dateStartMeter;
    private int dayValuePrevious;
    private int nightValuePrevious;
    private float dayTariff;
    private float nightTariff;
    private int hasMeter;
    private String normaPayer;

    public TariffsAndAccountInfoResponse() { }

    public TariffsAndAccountInfoResponse(int result, String phone1, String phone2, String phone3, String email1, String email2) {
        this.result = result;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
        this.email1 = email1;
        this.email2 = email2;
    }

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

    public long getAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
    }

    public String getDateStartMeter() {
        return dateStartMeter;
    }

    public void setDateStartMeter(String dateStartMeter) {
        this.dateStartMeter = dateStartMeter;
    }

    public int getDigitsMeter() {
        return digitsMeter;
    }

    public void setDigitsMeter(int digitsMeter) {
        this.digitsMeter = digitsMeter;
    }

    public String getTypeMeter() {
        return typeMeter;
    }

    public void setTypeMeter(String typeMeter) {
        this.typeMeter = typeMeter;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    public String getTypeFund() {
        return typeFund;
    }

    public void setTypeFund(String typeFund) {
        this.typeFund = typeFund;
    }

    public String getTypeFlat() {
        return typeFlat;
    }

    public void setTypeFlat(String typeFlat) {
        this.typeFlat = typeFlat;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
