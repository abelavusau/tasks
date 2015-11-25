package by.abelavusau.testtasks.cubespuzzle.rule;

import java.io.IOException;
import java.util.List;

import by.abelavusau.testtasks.cubespuzzle.core.Facet;

/**
 * The contract for the rule that determines how the cube will be output
 * 
 * @author Aliaksandr Belavusau
 */
public interface CubeOutputRule {
    /**
     * 
     * @param facets - list of facets to output
     * @param filename - name of the file to print the result
     * @throws IOException if any errors occur
     */
    void out(List<Facet> facets, String filename) throws IOException;
}