package by.abelavusau.testtasks.cubespuzzle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.abelavusau.testtasks.cubespuzzle.core.Cube;
import by.abelavusau.testtasks.cubespuzzle.core.Facet;
import by.abelavusau.testtasks.cubespuzzle.rule.TFormCubeOutputRule;
import by.abelavusau.testtasks.cubespuzzle.rule.TFormCubePasteRule;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException(
                    "Argument list is empty. Please specify the file where the result will be output.");
        }

        List<String> lines1 = Arrays.asList("  o  ", " ooo ", "ooooo", " ooo ", "  o  ");
        List<String> lines2 = Arrays.asList("o o o", "ooooo", " ooo ", "ooooo", "o o o");
        List<String> lines3 = Arrays.asList("  o  ", " oooo", "oooo ", " oooo", "  o  ");
        List<String> lines4 = Arrays.asList(" o o ", "oooo ", " oooo", "oooo ", "oo o ");
        List<String> lines5 = Arrays.asList(" o o ", "ooooo", " ooo ", "ooooo", "o o  ");
        List<String> lines6 = Arrays.asList(" o o ", " oooo", "oooo ", " oooo", "oo oo");
        List<Facet> facets = new ArrayList<>(Cube.FACET_NUM);

        facets.add(new Facet(lines1));
        facets.add(new Facet(lines2));
        facets.add(new Facet(lines3));
        facets.add(new Facet(lines4));
        facets.add(new Facet(lines5));
        facets.add(new Facet(lines6));

        Cube cube = new Cube(new TFormCubePasteRule());
        List<Facet> solved = cube.solve(facets, new ArrayList<Facet>());
        TFormCubeOutputRule outputRule = new TFormCubeOutputRule();
        outputRule.out(solved, args[0]);
    }
}
