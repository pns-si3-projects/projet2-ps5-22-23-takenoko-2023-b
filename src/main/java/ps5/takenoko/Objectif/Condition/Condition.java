package ps5.takenoko.Objectif.Condition;

public interface Condition {
    public boolean check();
    public void claim();
    public boolean checkAndClaim();
}
