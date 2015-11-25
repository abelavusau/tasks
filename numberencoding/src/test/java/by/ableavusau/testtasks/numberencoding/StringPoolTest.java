package by.ableavusau.testtasks.numberencoding;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import by.abelavusau.testtasks.numberencoding.constants.StringPool;

/**
 * @author Aliaksandr Belavusau
 */
public class StringPoolTest {
	@Test(expected = InvocationTargetException.class)
	public void testPrivateConstructorCall()
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<?>[] ctors = StringPool.class.getDeclaredConstructors();
		assertEquals("Constant holder class should only have one constructor", 1, ctors.length);
		Constructor<?> ctor = ctors[0];
		assertFalse("Constant holder class constructor should be inaccessible", ctor.isAccessible());
		ctor.setAccessible(true);
		ctor.newInstance();
	}
}
