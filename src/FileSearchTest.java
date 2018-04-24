import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

class FileSearchTest {
    private FileSearch fs = new FileSearch();
    private FileSearch fs1 = new FileSearch();
    private FileSearch fs2 = new FileSearch();

    @Test
    void search() throws FileNotFoundException {
        String f = new File("input/findFile").getAbsoluteFile().toString();
        fs.search("findFile", new File("input"), false);
        assertEquals(Arrays.asList(f), fs.getResult());

        String f1 = new File("test.txt").getAbsoluteFile().toString();
        fs1.search("test.txt",new File("").getAbsoluteFile(), false);
        assertEquals(Arrays.asList(f1), fs1.getResult());

        String f2 = new File("input/dir/dir3/findFile2").getAbsoluteFile().toString();
        String f3 = new File("input/dir/findFile2").getAbsoluteFile().toString();
        fs2.search("findFile2", new File("input"), true);
        assertEquals(Arrays.asList(f2, f3), fs2.getResult());
    }
}