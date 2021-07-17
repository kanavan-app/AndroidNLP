package com.kanavan.androidnlp;

public class NearestNeighborInterpolation {
    public static IMatrix interpolate(IMatrix source, int scaleWidth, int scaleHeight) {
        IMatrix scaledMatrix = new Matrix(scaleWidth, scaleHeight);
        double widthRatio = source.column() * 1.0d / scaleWidth;
        double heightRatio = source.row() * 1.0d / scaleHeight;
        for (int i = 0; i < scaleHeight; i++) {
            for (int j = 0; j < scaleWidth; j++) {
                scaledMatrix.set(i, j, source.get((int) Math.round(i * heightRatio), (int) Math.round(j * widthRatio)));
            }
        }
        return scaledMatrix;
    }
}
