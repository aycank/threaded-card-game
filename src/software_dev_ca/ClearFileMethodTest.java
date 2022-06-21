package software_dev_ca;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ClearFileMethodTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void test() {
        File file = null;
		try {
			file = folder.newFile("myfile1.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        FileManagement.clearFile("myfile1.txt");
      //assertTrue - jUnit boolean condition which checks whether the expected value is true or not.
        assertTrue(file.length() == 0);
    }

}