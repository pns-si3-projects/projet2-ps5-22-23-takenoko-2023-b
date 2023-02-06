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
    public boolean isDemo() {
        return demo;
    }
    public boolean isCsv() {
        return csv;
    }
}
