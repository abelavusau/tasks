package by.abelavusau.testtasks.cubespuzzle.core;

/**
 * Enumeration with side types
 *  
 * @author Aliaksandr Belavusau
 */
public enum SideType {
    TOP(0), RIGHT(1), BOTTOM(2), LEFT(4);

    private final int type;

    SideType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
