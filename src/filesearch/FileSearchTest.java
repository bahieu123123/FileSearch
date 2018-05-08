package filesearch;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

class FileSearchTest {
    private FileSearch FS = new FileSearch();
    private FileSearch FS1 = new FileSearch();
    private FileSearch FS2 = new FileSearch();
    private FileSearch FS3 = new FileSearch();
    private FileSearch FS4 = new FileSearch();
    private FileSearch FS5 = new FileSearch();

    @Test
    void search() {
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

        String f4 = new File("input/dir/dir1").getAbsoluteFile().toString();
        String f5 = new File("input/dir/dir1/dir1").getAbsoluteFile().toString();
        String f6 = new File("input/dir/dir1/dir1/dir1").getAbsoluteFile().toString();
        FS3.search("dir1",new File("input/dir"),true);
        assertEquals(Arrays.asList(f6,f5,f4),FS3.getResult());

        FS4.search("dir",new File("inptu"),true);
        assertEquals(Arrays.asList("Directory not found!"),FS4.getResult());

        FS5.search("dri",new File("input"),false);
        assertEquals(Arrays.asList("There is no files in this directory or file you want to check does not exist!"),FS5.getResult());
    }
}