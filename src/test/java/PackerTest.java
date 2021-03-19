import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PackerTest {
    private void assertFileContext(String name, String expectedContent) throws IOException {
        String content = Files.lines(Paths.get(name)).reduce("", String::concat);
        Assertions.assertEquals(expectedContent,content);
    }

    @Test
    void testPacking() throws IOException {
        Packer packer = new Packer("files/testPacking.txt", "files/temp.txt");
        packer.pack();
        assertFileContext("files/temp.txt", "ABCABCABCD3F10");
    }
    @Test
    void testUnpacking() throws IOException {
        Packer packer = new Packer("files/testUnpacking.txt", "files/temp.txt");
        packer.unPack();
        assertFileContext("files/temp.txt", "ABCABCABCDDDFFFFFFFFFF");
    }
}
