package by.abelavusau.testtasks.cubespuzzle.rule;

import java.util.List;

import by.abelavusau.testtasks.cubespuzzle.core.Facet;

/**
 * The contract for the rule that determines how the cube will be folded
 * 
 * @author Aliaksandr Belavusau
 */
public interface CubePasteRule {
    /**
     * Checks if the facet could be pasted into the cube
     * 
     * @param facet - {@code Facet} to paste
     * @param alreadyPasted - {@code List<Facet>} that contains successfully pasted facets 
     * @return - true, if it's possible to paste the facet
     */
    boolean tryPaste(Facet facet, List<Facet> alreadyPasted);
}
