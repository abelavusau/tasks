package by.abelavusau.testtasks.cubespuzzle.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the facet (one construction piece - puzzle)
 * 
 * @author Aliaksandr Belavusau
 */
public class Facet {
    private static final String FILLED_AREA = "ooo";

    private Side top;
    private Side right;
    private Side bottom;
    private Side left;

    public Facet(List<String> lines) {
        this.initializeSides(lines);
    }

    public Side getTop() {
        return top;
    }

    public Side getRight() {
        return right;
    }

    public Side getBottom() {
        return bottom;
    }

    public Side getLeft() {
        return left;
    }

    /**
     * Rotates the facet counter clockwise
     */
    public void rotate() {
        Side tmp = top;
        top = right;
        right = bottom.reflect();
        bottom = left;
        left = tmp.reflect();
    }

    public void reflect() {
        Side tmp = left;
        top = top.reflect();
        bottom = bottom.reflect();
        left = right;
        right = tmp;
    }

    private void initializeSides(List<String> lines) {
        top = SideFactory.getInstance().newSide(lines, SideType.TOP);
        left = SideFactory.getInstance().newSide(lines, SideType.LEFT);
        bottom = SideFactory.getInstance().newSide(lines, SideType.BOTTOM);
        right = SideFactory.getInstance().newSide(lines, SideType.RIGHT);
    }

    /**
     * Obtains the facet source (facet string representation) - {@code List<String>} object
     * 
     * @return {@code List<String>} object
     */
    public List<String> getSource() {
        List<String> source = new ArrayList<>(Side.SIDE_LENGTH);
        source.add(top.getSource());
        
        /*
         * considering middle area always filled:
         *     T  
         *    ---
         *   |ooo|
         * L |ooo| R
         *   |ooo|
         *    ---
         *     B
         */  
        source.add(left.getSource().charAt(1) + FILLED_AREA + right.getSource().charAt(1));
        source.add(left.getSource().charAt(2) + FILLED_AREA + right.getSource().charAt(2));
        source.add(left.getSource().charAt(3) + FILLED_AREA + right.getSource().charAt(3));
        
        source.add(bottom.getSource());

        return Collections.unmodifiableList(source);
    }
}
