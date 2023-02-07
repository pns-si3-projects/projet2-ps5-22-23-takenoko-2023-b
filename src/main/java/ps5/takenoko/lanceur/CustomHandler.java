package ps5.takenoko.lanceur;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

class CustomHandler extends Handler {
    @Override
    public void publish(LogRecord record) {
        System.out.println(record.getMessage());
    }
    @Override
    public void flush() {}
    @Override
    public void close() throws SecurityException {}
}
