package by.abelavusau.testtasks.cubespuzzle.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import by.abelavusau.testtasks.cubespuzzle.rule.CubePasteRule;

/**
 * Represents a solved cube as {@code List} of {@code Facet} elements
 * 
 * @author Aliaksandr Belavusau
 */
public class Cube {
    public static final int FACET_NUM = 6;
    private static final int ALL_POSSIBLE_FACET_POSITIONS = 8;

    private CubePasteRule pasteRule;

    public Cube(CubePasteRule pasteRule) {
        this.pasteRule = pasteRule;
    }

    /**
     * Try to solve the cube according to the rule recursively
     * 
     * @param pool - {@code List} of existing {@code Facet} elements to be pasted in the cube
     * @param result - {@code List} of successfully pasted facets in the cube
     * @return empty list if the solving operation did not succeed and list of pasted facets if the solving operation
     *         has completed successfully
     */
    public List<Facet> solve(List<Facet> pool, List<Facet> result) {
        for (Facet facet : pool) {
            int rotateCount = 0;
            /*
             * go through the all facet positions
             */
            while (rotateCount < ALL_POSSIBLE_FACET_POSITIONS) {
                /*
                 * it's time to reflect the facet
                 */
                if (rotateCount == ALL_POSSIBLE_FACET_POSITIONS / 2) {
                    facet.reflect();
                }

                if (pasteRule.tryPaste(facet, result)) {
                    /*
                     * Make a deep copy of the result and pool lists to work with actual copy when we get back to this
                     * recursion branch, i.e - not to corrupt the particular recursion branch data in the further
                     * recursive calls
                     */
                    List<Facet> resultCopy = new ArrayList<>(result);

                    /*
                     * If the facet has been pasted successfully, add it to the result
                     */
                    resultCopy.add(facet);

                    List<Facet> poolCopy = new ArrayList<>(pool);

                    /*
                     * If the facet has been pasted successfully, remove it from the pool with eligible facets
                     */
                    poolCopy.remove(facet);

                    List<Facet> solved = solve(poolCopy, resultCopy);

                    /*
                     * floating up ("take me higher") ...
                     */
                    if (!solved.isEmpty()) {
                        return solved;
                    }
                }

                /*
                 * rotate the facet
                 */
                facet.rotate();
                rotateCount++;
            }
        }

        /*
         * returning from very last recursion call...
         */
        if (result.size() == FACET_NUM) {
            return result;
        }

        /*
         * return the empty list if we went through the all facets and the solving operation did not succeed
         */
        return Collections.emptyList();
    }
}
