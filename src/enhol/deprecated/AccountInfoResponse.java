package enhol.deprecated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 04.09.13
 * Time: 17:49
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountInfoResponse", propOrder = {
        "result",
        "lastName",
        "firstName",
        "secondName",
        "phone1",
        "phone2",
        "phone3",
        "email1",
        "email2",
        "addrFilialName",
        "addrSectorName",
        "addrStreetName",
        "addrHouseNumber",
        "addrFlatNumber"
})
public class AccountInfoResponse {

    private String lastName;
    private String firstName;
    private String secondName;
    private String phone1;
    private String phone2;
    private String phone3;
    private String email1;
    private String email2;
    private String addrFilialName;
    private String addrSectorName;
    private String addrStreetName;
    private String addrHouseNumber;
    private String addrFlatNumber;
    private int result = 1;

    public AccountInfoResponse() {  }

    public AccountInfoResponse(int result, String firstName, String secondName, String lastName, String phone1, String phone2, String phone3, String email1, String email2, String addrFilialName, String addrSectorName, String addrStreetName, String addrHouseNumber, String addrFlatNumber) {
        this.result = result;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.phone3 = phone3;
        this.email1 = email1;
        this.email2 = email2;
        this.addrFilialName = addrFilialName;
        this.addrSectorName = addrSectorName;
        this.addrStreetName = addrStreetName;
        this.addrHouseNumber = addrHouseNumber;
        this.addrFlatNumber = addrFlatNumber;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
