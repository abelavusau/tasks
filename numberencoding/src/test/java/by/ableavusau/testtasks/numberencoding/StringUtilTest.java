package by.ableavusau.testtasks.numberencoding;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import by.abelavusau.testtasks.numberencoding.util.StringUtil;

/**
 * @author Aliaksandr Belavusau
 */
public class StringUtilTest {
	@Test
	public void testNormalizeNumber() {
		String number = StringUtil.normalizeNumber("4222710/-/-36798622899592771806--82-170075/257/331 ");
		assertEquals(-1, number.indexOf("/"));
		assertEquals(-1, number.indexOf("-"));
		assertEquals(-1, number.indexOf(" "));
	}

	@Test
	public void testCopyString() {
		String source = "4222710";
		String copy = StringUtil.copyString(source);
		assertTrue(source.equals(copy));
		assertFalse(source == copy);
	}

	@Test(expected = InvocationTargetException.class)
	public void testPrivateConstructorCall()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<?>[] ctors = StringUtil.class.getDeclaredConstructors();
		assertEquals("Utility class should only have one constructor", 1, ctors.length);
		Constructor<?> ctor = ctors[0];
		assertFalse("Utility class constructor should be inaccessible", ctor.isAccessible());
		ctor.setAccessible(true);
		ctor.newInstance();
	}
}
