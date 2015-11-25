package by.ableavusau.testtasks.numberencoding;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import by.abelavusau.testtasks.numberencoding.Encoder;

/**
 * @author Aliaksandr Belavusau
 */
public class WordDecoderTest {
	@Test
	public void testDecode() throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException {
		String encoderClassName = Encoder.class.getCanonicalName();
		Class<?> wordDecoderClazz = Class.forName(encoderClassName + "$WordDecoder");
		Constructor<?> constructor = wordDecoderClazz.getDeclaredConstructor();
		constructor.setAccessible(true);
		Object o = constructor.newInstance();
		Pattern pattern = Pattern.compile("^[\\d]+$");
		Method decodeMethod = o.getClass().getMethod("decode", String.class);
		decodeMethod.setAccessible(true);
		String number = (String) decodeMethod.invoke(o, "Abbaufortschritt \" ");
		Matcher matcher = pattern.matcher(number);
		assertTrue(matcher.matches());
	}
}
