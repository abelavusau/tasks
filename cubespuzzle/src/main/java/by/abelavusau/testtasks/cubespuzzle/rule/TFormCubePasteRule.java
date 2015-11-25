package by.abelavusau.testtasks.cubespuzzle.rule;

import java.util.List;

import by.abelavusau.testtasks.cubespuzzle.core.Facet;
import by.abelavusau.testtasks.cubespuzzle.core.Side;

/**
 * Determines the rule to fold the cube in the t-form
 * 
 * @author Aliaksandr Belavusau
 */
public class TFormCubePasteRule implements CubePasteRule {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean tryPaste(Facet facet, List<Facet> alreadyPasted) {
        switch (alreadyPasted.size()) {
            case 0:
                return true;
            case 1:
                return getFirstFacet(alreadyPasted).getRight().fit(facet.getLeft());
            case 2:
                return getSecondFacet(alreadyPasted).getRight().fit(facet.getLeft());
            case 3:
                return getFirstFacet(alreadyPasted).getBottom().fit(facet.getLeft())
                        && getSecondFacet(alreadyPasted).getBottom().fit(facet.getTop())
                        && getThirdFacet(alreadyPasted).getBottom().fit(facet.getRight())

                        && checkJointCellsFor3Facets(getFirstFacet(alreadyPasted).getBottom().getRightmostCell(),
                                getSecondFacet(alreadyPasted).getBottom().getLeftmostCell(),
                                facet.getTop().getLeftmostCell())

                        && checkJointCellsFor3Facets(getSecondFacet(alreadyPasted).getBottom().getRightmostCell(),
                                getThirdFacet(alreadyPasted).getBottom().getLeftmostCell(),
                                facet.getTop().getRightmostCell());
            case 4:
                return getFirstFacet(alreadyPasted).getLeft().fit(facet.getLeft())
                        && getThirdFacet(alreadyPasted).getRight().fit(facet.getRight())
                        && getFourthFacet(alreadyPasted).getBottom().fit(facet.getTop())

                        && checkJointCellsFor3Facets(getFirstFacet(alreadyPasted).getBottom().getLeftmostCell(),
                                getFourthFacet(alreadyPasted).getBottom().getLeftmostCell(),
                                facet.getTop().getLeftmostCell())

                        && checkJointCellsFor3Facets(getThirdFacet(alreadyPasted).getBottom().getRightmostCell(),
                                getFourthFacet(alreadyPasted).getBottom().getRightmostCell(),
                                facet.getTop().getRightmostCell());
            case 5:
                return getFifthFacet(alreadyPasted).getBottom().fit(facet.getTop())
                        && getFirstFacet(alreadyPasted).getTop().fit(facet.getLeft())
                        && getSecondFacet(alreadyPasted).getTop().fit(facet.getBottom())
                        && getThirdFacet(alreadyPasted).getTop().fit(facet.getRight())

                        && checkJointCellsFor3Facets(getFirstFacet(alreadyPasted).getTop().getLeftmostCell(),
                                getFifthFacet(alreadyPasted).getBottom().getLeftmostCell(),
                                facet.getTop().getLeftmostCell())

                        && checkJointCellsFor3Facets(getFirstFacet(alreadyPasted).getTop().getRightmostCell(),
                                getSecondFacet(alreadyPasted).getTop().getLeftmostCell(),
                                facet.getBottom().getLeftmostCell())

                        && checkJointCellsFor3Facets(getSecondFacet(alreadyPasted).getTop().getRightmostCell(),
                                getThirdFacet(alreadyPasted).getTop().getLeftmostCell(),
                                facet.getBottom().getRightmostCell())

                        && checkJointCellsFor3Facets(getThirdFacet(alreadyPasted).getTop().getRightmostCell(),
                                getFifthFacet(alreadyPasted).getBottom().getRightmostCell(),
                                facet.getTop().getRightmostCell());
            default:
                return false;
        }
    }

    private Facet getFirstFacet(List<Facet> facets) {
        return facets.get(0);
    }

    private Facet getSecondFacet(List<Facet> facets) {
        return facets.get(1);
    }

    private Facet getThirdFacet(List<Facet> facets) {
        return facets.get(2);
    }

    private Facet getFourthFacet(List<Facet> facets) {
        return facets.get(3);
    }

    private Facet getFifthFacet(List<Facet> facets) {
        return facets.get(4);
    }

    /**
     * Checks joint cells for 3 facets
     * 
     * @param cell1 - first side corner cell
     * @param cell2 - second side corner cell
     * @param cell3 - third side corner cell
     * @return true, if one and only one cell is filled
     */
    private boolean checkJointCellsFor3Facets(char cell1, char cell2, char cell3) {
        return (cell1 == Side.FILLED_CELL && cell2 == Side.EMPTY_CELL && cell3 == Side.EMPTY_CELL)
                || (cell1 == Side.EMPTY_CELL && cell2 == Side.FILLED_CELL && cell3 == Side.EMPTY_CELL)
                || (cell1 == Side.EMPTY_CELL && cell2 == Side.EMPTY_CELL && cell3 == Side.FILLED_CELL);
    }
}
