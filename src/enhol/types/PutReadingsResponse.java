package enhol.types;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 14.08.13
 * Time: 11:16
 */
public class PutReadingsResponse {

    @XmlElement(name = "requestId", required = true)
    protected String requestId;
    @XmlElement(name = "result", required = true)
    protected int result;

    public PutReadingsResponse() {
    }

    public PutReadingsResponse(String requestId, int result) {
        this.requestId = requestId;
        this.result = result;
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


}
