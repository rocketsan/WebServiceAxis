package enhol.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 05.09.13
 * Time: 9:51
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentResponse", propOrder = {
        "createDate",
        "payDate",
        "totalPaySum",
        "peniSum"
})
public class PaymentResponse {
    private String createDate;
    private String payDate;
    private String totalPaySum;
    private String peniSum;

    public String getPeniSum() {
        return peniSum;
    }

    public void setPeniSum(String peniSum) {
        this.peniSum = peniSum;
    }

    public PaymentResponse() { }

    public PaymentResponse(String createDate, String payDate, String totalPaySum, String peniSum) {
        this.createDate = createDate;
        this.payDate = payDate;
        this.totalPaySum = totalPaySum;
        this.peniSum = peniSum;
    }

    public String getTotalPaySum() {
        return totalPaySum;
    }

    public void setTotalPaySum(String totalPaySum) {
        this.totalPaySum = totalPaySum;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
