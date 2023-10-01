import java.util.Date;

public class Employee {
    private String positionId;
    private String status;
    private Date inTime;
    private Date outTime;
    private String timeCardHours;
    private Date payStart;
    private Date payEnd;
    private String name;

    @Override
    public String toString() {
        return "Employee{" +
                "positionId='" + positionId + '\'' +
                ", status='" + status + '\'' +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                ", timeCardHours='" + timeCardHours + '\'' +
                ", payStart=" + payStart +
                ", payEnd=" + payEnd +
                ", name='" + name + '\'' +
                ", fileNumber='" + fileNumber + '\'' +
                '}';
    }

    private String fileNumber;

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getTimeCardHours() {
        return timeCardHours;
    }

    public void setTimeCardHours(String timeCardHours) {
        this.timeCardHours = timeCardHours;
    }

    public Date getPayStart() {
        return payStart;
    }

    public void setPayStart(Date payStart) {
        this.payStart = payStart;
    }

    public Date getPayEnd() {
        return payEnd;
    }

    public void setPayEnd(Date payEnd) {
        this.payEnd = payEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }
}
