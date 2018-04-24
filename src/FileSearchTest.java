import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

class FileSearchTest {
    private FileSearch FS = new FileSearch();
    private FileSearch FS1 = new FileSearch();
    private FileSearch FS2 = new FileSearch();

    @Test
    void search() throws FileNotFoundException {
        String f = new File("input/findFile").getAbsoluteFile().toString();
        FS.search("findFile", new File("input"), false);
        assertEquals(Arrays.asList(f), FS.getResult());

        String f1 = new File("test.txt").getAbsoluteFile().toString();
        FS1.search("test.txt",new File("").getAbsoluteFile(), false);
        assertEquals(Arrays.asList(f1), FS1.getResult());

        String f2 = new File("input/dir/dir3/findFile2").getAbsoluteFile().toString();
        String f3 = new File("input/dir/findFile2").getAbsoluteFile().toString();
        FS2.search("findFile2", new File("input"), true);
        assertEquals(Arrays.asList(f2, f3), FS2.getResult());
    }
}