import java.sql.Date;

public class Weight {
    private int weightid;
    private Date date;
    private int currentweight;
    private int userid;

    public Weight(int weightid, Date date, int currentweight, int userid) {
        this.weightid = weightid;
        this.date = date;
        this.currentweight = currentweight;
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Weight{" +
                "weightid=" + weightid +
                ", date=" + date +
                ", currentweight=" + currentweight +
                ", userid=" + userid +
                '}';
    }

    public int getWeightid() {
        return weightid;
    }

    public void setWeightid(int weightid) {
        this.weightid = weightid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCurrentweight() {
        return currentweight;
    }

    public void setCurrentweight(int currentweight) {
        this.currentweight = currentweight;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}