package com.kanavan.androidnlp;

public class Vector implements IVector {
    private final int size;
    private final double[] data;

    public Vector(int size) {
        this.size = size;
        this.data = new double[this.size];
    }

    public Vector(int size, double value) {
        this.size = size;
        this.data = this.fill(size, value);
    }

    public Vector(int size, double[] source) {
        this.size = size;
        this.data = this.copy(size, source);
    }

    public int length() {
        return this.size;
    }

    private double[] fill(int size, double value) {
        double[] filledData = new double[size];
        for (int i = 0; i < size; i++) {
            filledData[i] = value;
        }
        return filledData;
    }

    private double[] copy(int size, double[] source) {
        double[] copiedData = new double[size];
        for (int i = 0; i < size; i++) {
            copiedData[i] = source[i];
        }
        return copiedData;
    }

    public static IVector copy(int size, IVector source) {
        IVector copiedData = new Vector(size);
        for (int i = 0; i < size; i++) {
            copiedData.set(i, source.get(i));
        }
        return copiedData;
    }

    private String join(int size, double[] source, char delimiter) {
        StringBuilder joinedString = new StringBuilder();
        for (int i = 0; i < size; i++) {
            joinedString.append(source[i]);
            if (i < size - 1) {
                joinedString.append(delimiter);
            }
        }
        return joinedString.toString();
    }

    @Override
    public String toString() {
        return '[' + this.join(this.size, this.data, ',') + ']';
    }

    @Override
    public void set(int position, double value) {
        this.data[position] = value;
    }

    @Override
    public double get(int position) {
        return this.data[position];
    }

    @Override
    public IVector add(IVector that) {
        IVector vector = new Vector(this.size);
        for (int i = 0; i < this.size; i++) {
            vector.set(i, this.data[i] + that.get(i));
        }
        return vector;
    }

    @Override
    public double sum() {
        double value = 0.0d;
        for (int i = 0; i < this.size; i++) {
            value = value + this.data[i];
        }
        return value;
    }

    @Override
    public IVector multiply(IVector that) {
        IVector vector = new Vector(this.size);
        for (int i = 0; i < this.size; i++) {
            vector.set(i, this.data[i] * that.get(i));
        }
        return vector;
    }

    @Override
    public IVector subVector(int start, int end) {
        IVector vector = new Vector(end - start + 1);
        for (int i = 0; i < vector.length(); i++) {
            vector.set(i, this.data[start + i]);
        }
        return vector;
    }

    @Override
    public double dot(IVector that) {
        return this.multiply(that).sum();
    }

    @Override
    public double cosine(IVector that) {
        return this.dot(that) / (this.magnitude() * that.magnitude());
    }

    @Override
    public double magnitude() {
        return Math.sqrt(this.dot(this));
    }
}
