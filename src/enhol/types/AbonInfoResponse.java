package enhol.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 10.10.13
 * Time: 12:27
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbonInfoResponse", propOrder = {
        "result",
        "account"
})

public class AbonInfoResponse {
    private int result;
    private long account;

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
