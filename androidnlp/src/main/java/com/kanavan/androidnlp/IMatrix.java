package com.kanavan.androidnlp;

public interface IMatrix {
    int row();

    int column();

    String toString();

    IVector get(int row);

    double get(int row, int column);

    void set(int row, IVector that);

    void set(int row, int column, double value);

    double sum();

    IMatrix add(IMatrix that);

    IMatrix multiply(IMatrix that);

    IMatrix subMatrix(int startRow, int startColumn, int endRow, int endColumn);

    IMatrix transpose();

    IVector flatten();

    IMatrix dot(IMatrix that);
}
