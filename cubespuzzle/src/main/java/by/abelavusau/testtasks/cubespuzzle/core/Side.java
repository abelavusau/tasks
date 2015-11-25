package by.abelavusau.testtasks.cubespuzzle.core;

/**
 * Represents the facet side
 *
 * @author Aliaksandr Belavusau
 */
public class Side {
    public static final int SIDE_LENGTH = 5;
    public static final char EMPTY_CELL = ' ';
    public static final char FILLED_CELL = 'o';

    private String source;

    public Side(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    /**
     * Checks if the sides fit each other
     * 
     * @param other - side to try on 
     * @return true if the sides fit each other, otherwise - false
     */
    public boolean fit(Side other) {
        for (int i = 1; i < SIDE_LENGTH - 1; i++) {
            if (source.charAt(i) == other.getSource().charAt(i)) {
                return false;
            }
        }

        return checkCorners(other);
    }

    /**
     * Checks corner cells (leftmost and rightmost)
     * 
     * @param other - side to check corners with given
     * 
     * @return true if, for given side they both are empty OR one of them is empty
     * AND for the other side one of them is also empty, OR they both are
     * empty for the other side
     */
    private boolean checkCorners(Side other) {
        return (this.getLeftmostCell() == EMPTY_CELL || other.getLeftmostCell() == EMPTY_CELL)
                && (this.getRightmostCell() == EMPTY_CELL || other.getRightmostCell() == EMPTY_CELL);
    }

    /**
     * Reflects the side
     * 
     * @return reflected side
     */
    public Side reflect() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < source.length(); i++) {
            output.append(source.charAt(source.length() - 1 - i));
        }

        return new Side(output.toString());
    }

    public char getLeftmostCell() {
        return source.charAt(0);
    }

    public char getRightmostCell() {
        return source.charAt(source.length() - 1);
    }
}
