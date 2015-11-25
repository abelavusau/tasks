package by.ableavusau.testtasks.numberencoding;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import by.abelavusau.testtasks.numberencoding.Encoder;

/**
 * @author Aliaksandr Belavusau
 */
public class EncoderTest {
	private Encoder encoder;
	private List<String> numbers;
	private List<String> words;

	@Before
	public void setUp() throws IOException, URISyntaxException {
		Path numbersFilePath = Paths
				.get(Thread.currentThread().getContextClassLoader().getResource("input.txt").toURI());
		Path dictionaryFilePath = Paths
				.get(Thread.currentThread().getContextClassLoader().getResource("dictionary.txt").toURI());

		numbers = Files.readAllLines(numbersFilePath);
		words = Files.readAllLines(dictionaryFilePath);

		encoder = new Encoder(words);
	}

	@Test
	public void testEncode() {
		Pattern pattern = Pattern.compile("(.)*(\\d\\s\\d)(.)*");

		for (String number : numbers) {
			List<String> results = encoder.encode(number);
			assertNotNull(results);

			for (String encode : results) {
				Matcher matcher = pattern.matcher(encode);
				assertFalse(matcher.matches());
			}
		}
	}

	@After
	public void tearDown() {
		encoder = null;
		numbers = null;
		words = null;
	}
}
