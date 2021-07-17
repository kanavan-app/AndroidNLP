package com.kanavan.androidnlp;

public class Matrix implements IMatrix {
    private final int width;
    private final int height;
    private IVector[] data;

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new IVector[height];
        this.data = this.fill(width, height, 0.0d);
    }

    public Matrix(int width, int height, double value) {
        this.width = width;
        this.height = height;
        this.data = this.fill(width, height, value);
    }

    public Matrix(int width, int height, double[][] sourceArrays) {
        this.width = width;
        this.height = height;
        this.data = this.copy(width, height, sourceArrays);
    }

    public int row() {
        return this.height;
    }

    public int column() {
        return this.width;
    }

    private IVector[] fill(int width, int height, double value) {
        IVector[] filledData = new IVector[height];
        for (int i = 0; i < height; i++) {
            filledData[i] = new Vector(width, value);
        }
        return filledData;
    }

    private IVector[] copy(int width, int height, double[][] source) {
        IVector[] copiedData = new IVector[height];
        for (int i = 0; i < height; i++) {
            copiedData[i] = new Vector(width, source[i]);
        }
        return copiedData;
    }

    private String join(int height, IVector[] source, char delimiter) {
        StringBuilder joinedString = new StringBuilder();
        for (int i = 0; i < height; i++) {
            joinedString.append(source[i].toString());
            if (i < height - 1) {
                joinedString.append(delimiter);
            }
        }
        return joinedString.toString();
    }

    @Override
    public void set(int row, int column, double value) {
        this.data[row].set(column, value);
    }

    @Override
    public double get(int row, int column) {
        return this.data[row].get(column);
    }

    @Override
    public void set(int row, IVector that) {
        this.data[row] = Vector.copy(that.length(), that);
    }

    @Override
    public IVector get(int row) {
        return this.data[row];
    }

    @Override
    public double sum() {
        double value = 0.0d;
        for (int i = 0; i < this.height; i++) {
            value = value + this.data[i].sum();
        }
        return value;
    }

    @Override
    public IMatrix add(IMatrix that) {
        IMatrix matrix = new Matrix(this.width, this.height);
        for (int i = 0; i < this.height; i++) {
            matrix.set(i, this.data[i].add(that.get(i)));
        }
        return matrix;
    }

    @Override
    public IMatrix multiply(IMatrix that) {
        IMatrix matrix = new Matrix(this.width, this.height);
        for (int i = 0; i < this.height; i++) {
            matrix.set(i, this.data[i].multiply(that.get(i)));
        }
        return matrix;
    }

    @Override
    public IMatrix transpose() {
        int height = this.width;
        int width = this.height;
        IMatrix matrix = new Matrix(width, height);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix.set(i, j, this.data[j].get(i));
            }
        }
        return matrix;
    }

    @Override
    public IMatrix subMatrix(int startRow, int startColumn, int endRow, int endColumn) {
        IMatrix matrix = new Matrix(endColumn - startColumn + 1, endRow - startRow + 1);
        for (int i = 0; i < matrix.row(); i++) {
            matrix.set(i, this.data[startRow + i].subVector(startColumn, endColumn));
        }
        return matrix;
    }

    @Override
    public IVector flatten() {
        IVector vector = new Vector(this.width * this.height);
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                vector.set(i * this.width + j, this.data[i].get(j));
            }
        }
        return vector;
    }

    @Override
    public IMatrix dot(IMatrix that) {
        IMatrix transposedMatrix = that.transpose();
        IMatrix matrix = new Matrix(that.column(), this.height);
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < that.column(); j++) {
                matrix.set(i, j, this.data[i].dot(transposedMatrix.get(j)));
            }
        }
        return matrix;
    }

    @Override
    public String toString() {
        return '[' + this.join(this.height, this.data, ',') + ']';
    }
}
