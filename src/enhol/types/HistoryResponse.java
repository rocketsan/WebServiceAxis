package enhol.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Created with IntelliJ IDEA.
 * User: uvv
 * Date: 05.09.13
 * Time: 10:18
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HistoryResponse", propOrder = {
        "callDate",
        "description",
        "operName",
        "clid",
        "actions",
        "activityName1",
        "activityName2",
        "activityName3"
})
public class HistoryResponse {

    private String callDate;
    private String description;
    private String operName;
    private long clid;
    private String actions;
    private String activityName1;
    private String activityName2;
    private String activityName3;

    public HistoryResponse() { }

    public HistoryResponse(String callDate, String description, String operName, long clid, String actions, String activityName1, String activityName2, String activityName3) {
        this.callDate = callDate;
        this.description = description;
        this.operName = operName;
        this.clid = clid;
        this.actions = actions;
        this.activityName1 = activityName1;
        this.activityName2 = activityName2;
        this.activityName3 = activityName3;
    }

    public String getActivityName3() {
        return activityName3;
    }

    public void setActivityName3(String activityName3) {
        this.activityName3 = activityName3;
    }

    public String getActivityName2() {
        return activityName2;
    }

    public void setActivityName2(String activityName2) {
        this.activityName2 = activityName2;
    }

    public String getActivityName1() {
        return activityName1;
    }

    public void setActivityName1(String activityName1) {
        this.activityName1 = activityName1;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public long getClid() {
        return clid;
    }

    public void setClid(long clid) {
        this.clid = clid;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = this.description;
    }

}
