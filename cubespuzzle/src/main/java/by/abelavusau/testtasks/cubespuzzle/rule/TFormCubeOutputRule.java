package by.abelavusau.testtasks.cubespuzzle.rule;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import by.abelavusau.testtasks.cubespuzzle.core.Facet;
import by.abelavusau.testtasks.cubespuzzle.core.Side;

/**
 * Outputs the cube into the file in the t-form
 * 
 * @author Aliaksandr Belavusau
 */
public class TFormCubeOutputRule implements CubeOutputRule {
    private static final String EMPTY_SPACE = "     ";

    /**
     * {@inheritDoc}
     */
    @Override
    public void out(List<Facet> facets, String filename) throws IOException {
        Facet[] horizontalLineFacets = new Facet[] { facets.get(0), facets.get(1), facets.get(2) };
        Facet[] verticalLineFacets = new Facet[] { facets.get(3), facets.get(4), facets.get(5) };
        Path path = Paths.get(filename);

        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.US_ASCII)) {
            for (int i = 0; i < Side.SIDE_LENGTH; i++) {
                for (Facet facet : horizontalLineFacets) {
                    for (int j = 0; j < Side.SIDE_LENGTH; j++) {
                        bw.write(facet.getSource().get(i).charAt(j));
                    }
                }

                bw.newLine();
            }

            for (Facet facet : verticalLineFacets) {
                for (String line : facet.getSource()) {
                    bw.write(EMPTY_SPACE);
                    bw.write(line);
                    bw.newLine();
                }
            }

            bw.flush();
        }
    }
}
