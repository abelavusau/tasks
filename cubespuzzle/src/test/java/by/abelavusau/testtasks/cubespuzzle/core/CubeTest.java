package by.abelavusau.testtasks.cubespuzzle.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import by.abelavusau.testtasks.cubespuzzle.rule.TFormCubePasteRule;

/**
 * @author Aliaksandr Belavusau
 */
public class CubeTest {
    private Cube cube;
    private List<Facet> facets;

    @Before
    public void setUp() {
        List<String> lines1 = Arrays.asList("  o  ", " ooo ", "ooooo", " ooo ", "  o  ");
        List<String> lines2 = Arrays.asList("o o o", "ooooo", " ooo ", "ooooo", "o o o");
        List<String> lines3 = Arrays.asList("  o  ", " oooo", "oooo ", " oooo", "  o  ");
        List<String> lines4 = Arrays.asList(" o o ", "oooo ", " oooo", "oooo ", "oo o ");
        List<String> lines5 = Arrays.asList(" o o ", "ooooo", " ooo ", "ooooo", "o o  ");
        List<String> lines6 = Arrays.asList(" o o ", " oooo", "oooo ", " oooo", "oo oo");

        facets = new ArrayList<>(Cube.FACET_NUM);

        facets.add(new Facet(lines1));
        facets.add(new Facet(lines2));
        facets.add(new Facet(lines3));
        facets.add(new Facet(lines4));
        facets.add(new Facet(lines5));
        facets.add(new Facet(lines6));

        cube = new Cube(new TFormCubePasteRule());
    }

    @Test
    public void solveSuccess() {
        List<Facet> solved = cube.solve(facets, new ArrayList<Facet>());
        Assert.assertEquals(Cube.FACET_NUM, solved.size());
    }

    @After
    public void tearDown() {
        facets = null;
        cube = null;
    }
}
