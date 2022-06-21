package software_dev_ca;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class WriteFileMethodTest {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void successfulWriteTest() {
		File file = null;
		try {
			//Write temporary file
			file = folder.newFile("testwritefile.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String stringFileName = file.toString();
		FileManagement.writeFile("1",stringFileName);
		FileReader fr = null;
		try {
			fr = new FileReader(stringFileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
        BufferedReader fileRead = new BufferedReader(fr);
        String line = null;
		try {
			line = fileRead.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals("1", line);
		
	}

}
