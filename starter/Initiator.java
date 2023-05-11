package starter;
public class Initiator {
    private final boolean trustworthy;

    public Initiator(boolean trust){
        trustworthy = trust;
    }

    public boolean isTrustworthy(){
        return trustworthy;
    }

}