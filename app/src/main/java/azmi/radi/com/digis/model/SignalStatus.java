package azmi.radi.com.digis.model;

public class SignalStatus {
    // Reference Signal Received Power
    private int RSRP;
    // Reference Signal Received Quality
    private int RSRQ;
    // Signal-to-interference-plus-noise ratio
    private int SINR;
     //Empty Constructor
    public SignalStatus() {
    }

    //Initialization  Constructor
    public SignalStatus(int RSRP, int RSRQ, int SINR) {
        this.RSRP = RSRP;
        this.RSRQ = RSRQ;
        this.SINR = SINR;
    }

    // Encapsulation
    public int getRSRP() {
        return RSRP;
    }

    public void setRSRP(int RSRP) {
        this.RSRP = RSRP;
    }

    public int getRSRQ() {
        return RSRQ;
    }

    public void setRSRQ(int RSRQ) {
        this.RSRQ = RSRQ;
    }

    public int getSINR() {
        return SINR;
    }

    public void setSINR(int SINR) {
        this.SINR = SINR;
    }
}
