package by.abelavusau.testtasks.cubespuzzle.core;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Aliaksandr Belavusau
 */
public class FacetTest {
    private Facet facet;
    private String[] lines;
    private String[] clonedLines;

    @Before
    public void setUp() {
        lines = new String[] { "  o  ", " oooo", "oooo ", " oooo", "  o  " };
        facet = new Facet(Arrays.asList(lines));
        clonedLines = lines.clone();
    }

    @Test
    public void getTopSuccess() {
        Assert.assertTrue(facet.getTop().getSource().equals(lines[0]));
    }

    @Test
    public void getRightSuccess() {
        Assert.assertTrue(facet.getRight().getSource().equals(" o o "));
    }

    @Test
    public void getBottomSuccess() {
        Assert.assertTrue(facet.getBottom().getSource().equals(lines[lines.length - 1]));
    }

    @Test
    public void getLeft() {
        Assert.assertTrue(facet.getLeft().getSource().equals("  o  "));
    }

    @Test
    public void rotateSuccess() {
        Facet clonedFacet = new Facet(Arrays.asList(clonedLines));
        clonedFacet.rotate();

        Assert.assertTrue(facet.getTop().getSource().equals(clonedFacet.getLeft().getSource()));
        Assert.assertTrue(facet.getRight().getSource().equals(clonedFacet.getTop().getSource()));
        Assert.assertTrue(facet.getBottom().getSource().equals(clonedFacet.getRight().getSource()));
        Assert.assertTrue(facet.getLeft().getSource().equals(clonedFacet.getBottom().getSource()));
    }

    @Test
    public void reflect() {
        facet.reflect();
        Assert.assertTrue(facet.getTop().getSource().equals("  o  "));
        Assert.assertTrue(facet.getRight().getSource().equals("  o  "));
        Assert.assertTrue(facet.getBottom().getSource().equals("  o  "));
        Assert.assertTrue(facet.getLeft().getSource().equals(" o o "));
    }

    @After
    public void tearDown() {
        lines = null;
        facet = null;
        clonedLines = null;
    }
}
