package ps5.takenoko.option;
import com.beust.jcommander.Parameter;

public class Args {
    @Parameter(names = "--2thousands", description = "play 2000 games of bots vs bots")
    private boolean twoThousand;

    @Parameter(names = "--demo", description = "play a demo game with logs")
    private boolean demo;

    @Parameter(names = "--csv", description = "play games of bots vs bots and save the results in a csv file")
    private boolean csv;

    public boolean isTwoThousand() {
        return twoThousand;
    }

    public void setTwoThousand(boolean twoThousand) {
        this.twoThousand = twoThousand;
    }

    public boolean isDemo() {
        return demo;
    }

    public void setDemo(boolean demo) {
        this.demo = demo;
    }

    public boolean isCsv() {
        return csv;
    }

    public void setCsv(boolean csv) {
        this.csv = csv;
    }
}
