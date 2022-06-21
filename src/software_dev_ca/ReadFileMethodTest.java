package software_dev_ca;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;

public class ReadFileMethodTest {

	@Test
	public void successfulReadTest() {
		String validPathForRead = "testpackread.txt";
		
		ArrayList<Card> isReadSuccessful = FileManagement.readFile(validPathForRead);
		
		assertEquals(1, isReadSuccessful.size());
		assertEquals(0, isReadSuccessful.get(0).getCardNumber());
	}
	
	@Test
	public void contentNotInteger() {
		String validPathForRead = "testpacknotint.txt";
		try {
			FileManagement.readFile(validPathForRead);
		}
		catch(NumberFormatException e){
			assertEquals("Content is not an integer.", e.getMessage());
		}
	}
	
	@Test
	public void invalidFilePath() throws IOException {
		FileManagement.readFile("this-file-doesnt-exist.txt");
		//cannot properly test an IOException with try and catch
	}
}
