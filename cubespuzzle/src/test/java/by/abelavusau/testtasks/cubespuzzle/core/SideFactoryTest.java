package by.abelavusau.testtasks.cubespuzzle.core;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Aliaksandr Belavusau
 */
public class SideFactoryTest {
    private List<String> lines = Arrays.asList("  o  ", " oooo", "oooo ", " oooo", "  o  ");

    @Test
    public void getTopSideSuccess() {
        Assert.assertTrue(SideFactory.getInstance().newSide(lines, SideType.TOP).getSource().equals(lines.get(0)));
    }

    @Test
    public void getRightSideSuccess() {
        Assert.assertTrue(SideFactory.getInstance().newSide(lines, SideType.RIGHT).getSource().equals(" o o "));
    }

    @Test
    public void getBottomSideSuccess() {
        Assert.assertTrue(SideFactory.getInstance().newSide(lines, SideType.BOTTOM).getSource()
                .equals(lines.get(lines.size() - 1)));
    }

    @Test
    public void getLeftSideSuccess() {
        Assert.assertTrue(SideFactory.getInstance().newSide(lines, SideType.LEFT).getSource().equals("  o  "));
    }
}
