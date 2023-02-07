package ps5.takenoko.option;

import com.beust.jcommander.JCommander;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArgsTest {
    private static Args args;

    @BeforeEach
    void setUp() {
        args = new Args();
    }

    @Test
    void testArgs() {
        assertFalse(args.isCsv());
        assertFalse(args.isDemo());
        assertFalse(args.isTwoThousand());

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse("--csv");
        assertFalse(args.isDemo());
        assertFalse(args.isTwoThousand());
        assertTrue(args.isCsv());
    }
}