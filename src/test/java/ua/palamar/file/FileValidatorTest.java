package ua.palamar.file;

import junit.framework.TestCase;

import java.io.File;

public class FileValidatorTest extends TestCase {

    private final FileValidatorImpl fileValidator = new FileValidatorImpl();

    public void testDirectoryHasOnlyCsvFiles() {
        // given
        File dir = new File("src/test/resources/dir");

        // when
        fileValidator.directoryHasOnlyCsvFiles(dir);

        // then
        // Should not throw exception
    }
}