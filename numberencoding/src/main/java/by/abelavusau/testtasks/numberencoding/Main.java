package by.abelavusau.testtasks.numberencoding;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import by.abelavusau.testtasks.numberencoding.constants.StringPool;
import by.abelavusau.testtasks.numberencoding.util.StringUtil;

/**
 * Starts encoding procedure
 * 
 * @author Aliaksandr Belavusau
 */
public class Main {
    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException(
                    "Argument list is empty. Please specify the paths to the files with numbers and with dictionary");
        }

        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "Argument list is invalid. Please specify the paths to the files with numbers and with dictionary");
        }

        Path numbersFilePath = Paths.get(args[0]);
        Path dictionaryFilePath = Paths.get(args[1]);

        List<String> words = Files.readAllLines(dictionaryFilePath, Charset.forName("ASCII"));
        Encoder encoder = new Encoder(words);

        // read and encode numbers line by line
        try (BufferedReader br = new BufferedReader(new FileReader(numbersFilePath.toString()))) {
            String number;

            while ((number = br.readLine()) != null) {
                List<String> encodedNumbers = encoder.encode(StringUtil.normalizeNumber(number));

                for (String encodedNumber : encodedNumbers) {
                    System.out.println((number + StringPool.COLON + StringPool.WHITESPACE + encodedNumber));
                }
            }
        }
    }
}
