package enhol.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 03.10.13
 * Time: 16:54
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountInfoResponse", propOrder = {
        "result",
        "account",
        "firstName",
        "secondName",
        "lastName",
        "addrFilialName",
        "addrSectorName",
        "addrStreetName",
        "addrHouseNumber",
        "addrFlatNumber"
})
public class SearchAccountResponse {
    private int result;
    private long account;
    private String firstName;
    private String secondName;
    private String lastName;
    private String addrFilialName;
    private String addrSectorName;
    private String addrStreetName;
    private String addrHouseNumber;
    private String addrFlatNumber;

    public SearchAccountResponse() { }

    public SearchAccountResponse(long account) {
        this.account = account;
    }

    // deprecated
    public SearchAccountResponse(int result, long account, String firstName, String secondName, String lastName, String addrFilialName, String addrSectorName, String addrStreetName, String addrHouseNumber, String addrFlatNumber) {
        this.result = result;
        this.account = account;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.addrFilialName = addrFilialName;
        this.addrSectorName = addrSectorName;
        this.addrStreetName = addrStreetName;
        this.addrHouseNumber = addrHouseNumber;
        this.addrFlatNumber = addrFlatNumber;
    }

    public String getAddrFlatNumber() {
        return addrFlatNumber;
    }

    public void setAddrFlatNumber(String addrFlatNumber) {
        this.addrFlatNumber = addrFlatNumber;
    }

    public String getAddrHouseNumber() {
        return addrHouseNumber;
    }

    public void setAddrHouseNumber(String addrHouseNumber) {
        this.addrHouseNumber = addrHouseNumber;
    }

    public String getAddrStreetName() {
        return addrStreetName;
    }

    public void setAddrStreetName(String addrStreetName) {
        this.addrStreetName = addrStreetName;
    }

    public String getAddrSectorName() {
        return addrSectorName;
    }

    public void setAddrSectorName(String addrSectorName) {
        this.addrSectorName = addrSectorName;
    }

    public String getAddrFilialName() {
        return addrFilialName;
    }

    public void setAddrFilialName(String addrFilialName) {
        this.addrFilialName = addrFilialName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
