package groupeb.takenoko.lanceur;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class CustomHandler extends Handler {
    @Override
    public void publish(LogRecord record) {
        System.out.println(record.getMessage());
    }
    @Override
    public void flush() {}
    @Override
    public void close() throws SecurityException {}
}
