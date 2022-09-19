package models;

public class Depertment {
    private int depertmnetId;
    private String depertmentName;

    public Depertment(int depertmnetId, String depertmentName) {
        this.depertmnetId = depertmnetId;
        this.depertmentName = depertmentName;
    }

    public int getDepertmnetId() {
        return depertmnetId;
    }

    public void setDepertmnetId(int depertmnetId) {
        this.depertmnetId = depertmnetId;
    }

    public String getDepertmentName() {
        return depertmentName;
    }

    public void setDepertmentName(String depertmentName) {
        this.depertmentName = depertmentName;
    }

    @Override
    public String toString() {
        return "Depertment{" +
                "depertmnetId=" + depertmnetId +
                ", depertmentName='" + depertmentName + '\'' +
                '}';
    }
}
