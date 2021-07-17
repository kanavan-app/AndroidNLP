package com.kanavan.androidnlp;

public class Convolution {
    public static IMatrix convolve(IMatrix source, IMatrix kernel, int stride) {
        int convolvedHeight = (source.row() - kernel.row()) / stride + 1;
        int convolvedWidth = (source.column() - kernel.column()) / stride + 1;
        IMatrix convolvedMatrix = new Matrix(convolvedWidth, convolvedHeight);
        for (int i = 0; i < convolvedHeight; i++) {
            for (int j = 0; j < convolvedWidth; j++) {
                convolvedMatrix.set(i, j, source.subMatrix(i * stride, j * stride, i * stride + kernel.row() - 1, j * stride + kernel.column() - 1).multiply(kernel).sum());
            }
        }
        return convolvedMatrix;
    }
}
