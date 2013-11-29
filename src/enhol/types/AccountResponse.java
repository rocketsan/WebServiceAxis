package enhol.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 12.11.13
 * Time: 11:41
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountResponse", propOrder = {
        "periodAccount",
        "typeAccount",
        "dayValueFrom",
        "dayValueTo",
        "nightValueFrom",
        "nightValueTo",
        "totalSum",
        "accountSum",
        "payedSum"
})
public class AccountResponse {
    private String periodAccount;
    private String typeAccount;
    private int dayValueFrom;
    private int dayValueTo;
    private int nightValueFrom;
    private int nightValueTo;
    private float totalSum;
    private float accountSum;
    private float payedSum;

    public AccountResponse() { }

    public AccountResponse(String periodAccount, String typeAccount, int dayValueFrom, int dayValueTo, int nightValueFrom, int nightValueTo, float totalSum, float accountSum, float payedSum) {
        this.periodAccount=periodAccount;
        this.typeAccount=typeAccount;
        this.dayValueFrom=dayValueFrom;
        this.dayValueTo=dayValueTo;
        this.nightValueFrom=nightValueFrom;
        this.nightValueTo=nightValueTo;
        this.totalSum=totalSum;
        this.accountSum=accountSum;
        this.payedSum=payedSum;
    }

    public float getPayedSum() {
        return payedSum;
    }

    public void setPayedSum(float payedSum) {
        this.payedSum = payedSum;
    }

    public float getAccountSum() {
        return accountSum;
    }

    public void setAccountSum(float accountSum) {
        this.accountSum = accountSum;
    }

    public float getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(float totalSum) {
        this.totalSum = totalSum;
    }

    public int getNightValueTo() {
        return nightValueTo;
    }

    public void setNightValueTo(int nightValueTo) {
        this.nightValueTo = nightValueTo;
    }

    public int getNightValueFrom() {
        return nightValueFrom;
    }

    public void setNightValueFrom(int nightValueFrom) {
        this.nightValueFrom = nightValueFrom;
    }

    public int getDayValueTo() {
        return dayValueTo;
    }

    public void setDayValueTo(int dayValueTo) {
        this.dayValueTo = dayValueTo;
    }

    public int getDayValueFrom() {
        return dayValueFrom;
    }

    public void setDayValueFrom(int dayValueFrom) {
        this.dayValueFrom = dayValueFrom;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    public String getPeriodAccount() {
        return periodAccount;
    }

    public void setPeriodAccount(String periodAccount) {
        this.periodAccount = periodAccount;
    }
}
