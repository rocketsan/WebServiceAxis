package enhol.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 27.09.13
 * Time: 11:01
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HistoryResponse", propOrder = {
        "result",
        "callId"
})
public class PutHistoryResponse {

    private int result;
    private long callId;

    public PutHistoryResponse() { }

    public PutHistoryResponse(int result, long callId) {
        this.result = result;
        this.callId = callId;
    }

    public long getCallId() {
        return callId;
    }

    public void setCallId(long callId) {
        this.callId = callId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

}
