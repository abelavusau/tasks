package by.abelavusau.testtasks.cubespuzzle.core;

import java.util.List;

/**
 * Factory class for creating the sides
 * 
 * @author Aliaksandr Belavusau
 */
public class SideFactory {
    private SideFactory() {
    }

    private static class Holder {
        private static final SideFactory INSTANCE = new SideFactory();
    }

    public static SideFactory getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Factory method for getting the new side by type.
     * 
     * @param lines - string facet representation
     * @param type {@code SideType} of the side: top, right, bottom, left
     * @return new side
     */
    public Side newSide(List<String> lines, SideType type) {
        StringBuilder sb;

        switch (type) {
            case TOP:
                return new Side(lines.get(0));
            case RIGHT:
                sb = new StringBuilder();

                for (String line : lines) {
                    char c = line.charAt(line.length() - 1);
                    sb.append(c);
                }

                return new Side(sb.toString());
            case BOTTOM:
                return new Side(lines.get(lines.size() - 1));
            case LEFT:
                sb = new StringBuilder();

                for (String line : lines) {
                    char c = line.charAt(0);
                    sb.append(c);
                }

                return new Side(sb.toString());
            default:
                return null;
        }
    }
}
