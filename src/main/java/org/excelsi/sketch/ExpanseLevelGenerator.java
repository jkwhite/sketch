package org.excelsi.sketch;


import org.excelsi.matrix.Matrix;
import org.excelsi.matrix.MatrixMSpace;
import org.excelsi.matrix.NullMatrixMSpace;
import org.excelsi.aether.Ground;


public class ExpanseLevelGenerator implements LevelGenerator {
    @Override public Level generate(final LevelRecipe recipe) {
        final Matrix m = new Matrix(recipe.getWidth(), recipe.getHeight());
        for(int i=0;i<recipe.getWidth();i++) {
            for(int j=0;j<recipe.getHeight();j++) {
                if(true||recipe.getRandom().nextBoolean()) {
                    //MatrixMSpace ms = new NullMatrixMSpace();
                    m.setSpace(new Ground(), i, j);
                }
            }
        }
        return new MatrixLevel(recipe.getName(), recipe.getOrdinal(), m);
    }
}
