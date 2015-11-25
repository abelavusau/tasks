package by.abelavusau.testtasks.numberencoding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import by.abelavusau.testtasks.numberencoding.constants.StringPool;
import by.abelavusau.testtasks.numberencoding.util.StringUtil;

/**
 * Encodes number to words
 * 
 * @author Aliaksandr Belavusau
 */
public class Encoder {
    /**
     * Cache for storing words mapped to digit sequence
     */
    private Map<String, List<String>> wordCache = new HashMap<String, List<String>>();

    /**
     * Constructs a new encoder
     * 
     * @param dictionary - list of word for numbers encoding
     */
    public Encoder(List<String> dictionary) {
        this.initializeWordCache(dictionary);
    }

    /**
     * Encodes number to words from the dictionary
     * 
     * @param number - number to be encoded
     * @return list of words corresponding to the given number
     */
    public List<String> encode(String number) {
        return doEncode(number, StringPool.EMPTY, new LinkedList<String>(), 0, false);
    }

    /**
     * Encodes number to words from the dictionary
     * 
     * @param number - number to be encoded
     * @param encodedNumberPart - part of the encoded number
     * @param result - list with completed encodings
     * @param startPosition - position within the given number which the search of corresponding words is starting from
     * @param wasNumberJustInserted - flag to determine if the last added to the encoded part symbol was digit
     * @return list of all possible word sequences for the given number
     */
    private List<String> doEncode(String number, String encodedNumberPart, List<String> result, int startPosition,
            boolean wasNumberJustInserted) {

        // if the end of the number is reached, add words sequence to the result
        if (startPosition == number.length()) {
            result.add(encodedNumberPart);
        }

        // Look over all the digits from startPosition to length of the number
        for (int i = startPosition + 1; i <= number.length(); i++) {
            // Retrieve corresponding words from the cache adding new digit to
            // the key (piece) on every iteration
            String piece = number.substring(startPosition, i);
            List<String> words = wordCache.get(piece);

            if (words == null) {
                /*
                 * If and only if at a particular point no word at all from the dictionary can be inserted, a single
                 * digit from the phone number can be copied to the encoding instead
                 */
                if (!wasNumberJustInserted && isDigitIsertionPossible(startPosition, number) && piece.length() == 1) {
                    String digit = number.substring(startPosition, startPosition + 1);

                    if (i < number.length()) {
                        digit += StringPool.WHITESPACE;
                    }

                    /*
                     * Before next recursive call make a deep copy of the original part not to corrupt the original part in
                     * the further recursive calls and have it original when we get back to this recursion branch
                     */
                    String copyOfEncodedNumber = StringUtil.copyString(encodedNumberPart);
                    copyOfEncodedNumber += digit;
                    doEncode(number, copyOfEncodedNumber, result, startPosition + 1, true);
                }
            } else {
                for (String word : words) {

                    if (i < number.length()) {
                        word += StringPool.WHITESPACE;
                    }

                    /*
                     * Before next recursive call make a deep copy of the original part not to corrupt the original part in
                     * the further recursive calls and have it original when we get back to this recursion branch
                     */
                    String copyOfEncodedNumber = StringUtil.copyString(encodedNumberPart);
                    copyOfEncodedNumber += word;
                    doEncode(number, copyOfEncodedNumber, result, i, false);
                }
            }
        }

        return result;
    }

    /**
     * Initializes the word cache
     * 
     * @param dictionary - list of words for number encoding
     */
    private void initializeWordCache(List<String> dictionary) {
        WordDecoder wordDecoder = new WordDecoder();

        for (String word : dictionary) {
            String digits = wordDecoder.decode(word);
            List<String> words = wordCache.get(digits);

            if (words == null) {
                words = new ArrayList<String>();
                words.add(word);
                wordCache.put(digits, words);
            } else {
                words.add(word);
            }
        }
    }

    /**
     * Checks if the digit insertion is possible.
     * 
     * Look over the number string and check whether the cache contains such key. If yes, there are word(s) in the cache
     * that could be used in encoding otherwise - there are no corresponding words, insertion of a digit is possible.
     * 
     * @param startPosition - position within the given number
     * @param number - number to be encoded
     * @return true if insertion of digit is possible, otherwise - false
     */
    private boolean isDigitIsertionPossible(int startPosition, String number) {
        for (int i = startPosition + 1; i < number.length(); i++) {
            if (wordCache.containsKey(number.substring(startPosition, i))) {
                return false;
            }
        }

        return true;
    }
    
    /**
     * Decodes words to digit sequence using the mapping from letters to digits:
     * E | J N Q | R W X | D S Y | F T | A M | C I V | B K U | L O P | G H Z
     * e | j n q | r w x | d s y | f t | a m | c i v | b k u | l o p | g h z
     * 0 |   1   |   2   |   3   |  4  |  5  |   6   |   7   |   8   |   9
     * 
     * @author Aliaksandr Belavusau
     */
    private static class WordDecoder {

        /**
         * Decodes word to digit sequence
         * 
         * @param word - word to be decoded to digit sequence
         * @return digit sequence
         */
        public String decode(String word) {
            String number = StringPool.EMPTY;

            for (char c : word.toLowerCase().toCharArray()) {
                switch (c) {
                    case 'e':
                        number += "0";
                        break;
                    case 'j':
                    case 'n':
                    case 'q':
                        number += "1";
                        break;
                    case 'r':
                    case 'w':
                    case 'x':
                        number += "2";
                        break;
                    case 'd':
                    case 's':
                    case 'y':
                        number += "3";
                        break;
                    case 'f':
                    case 't':
                        number += "4";
                        break;
                    case 'a':
                    case 'm':
                        number += "5";
                        break;
                    case 'c':
                    case 'i':
                    case 'v':
                        number += "6";
                        break;
                    case 'b':
                    case 'k':
                    case 'u':
                        number += "7";
                        break;
                    case 'l':
                    case 'o':
                    case 'p':
                        number += "8";
                        break;
                    case 'g':
                    case 'h':
                    case 'z':
                        number += "9";
                        break;
                    default:
                        break;
                }
            }

            return number;
        }
    }
}
