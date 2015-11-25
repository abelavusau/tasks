package by.abelavusau.testtasks.cubespuzzle.core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Aliaksandr Belavusau
 */
public class SideTest {
    private Side side1;
    private Side side2;
    private Side side3;

    @Before
    public void setUp() {
        side1 = new Side("o oo ");
        side2 = new Side(" o  o");
        side3 = new Side("oo oo");
    }

    @Test
    public void fitSuccess() {
        Assert.assertTrue(side1.fit(side2));
        Assert.assertFalse(side1.fit(side3));
    }

    @Test
    public void reflectSuccess() {
        Assert.assertTrue(side1.reflect().getSource().equals(" oo o"));
    }

    @After
    public void tearDown() {
        side1 = side2 = side3 = null;
    }
}
