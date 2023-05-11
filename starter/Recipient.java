package starter;
public class Recipient {
    private double trusting;

    public Recipient(double trustStart){
        trusting = trustStart;
    }

    public double getTrusting(){
        return trusting;
    }

    public void updateTrusting(double change){
        trusting += change;
        if(trusting > 1) {trusting = 1.0;}
        if(trusting < 0) {trusting = 0.0;}
    }
}