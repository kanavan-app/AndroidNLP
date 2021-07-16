package com.kanavan.androidnlp;

import java.util.Arrays;

public class Vector implements IVector {
    public final int size;
    private final double[] data;

    public Vector(int size) {
        this.size = size;
        this.data = new double[this.size];
    }

    public Vector(int size, double defaultValue) {
        this.size = size;
        this.data = new double[this.size];
        Arrays.fill(this.data, defaultValue);
    }

    public Vector(int size, double[] data) {
        this.size = size;
        this.data = data.clone();
    }

    @Override
    public String toString() {
        return Arrays.toString(this.data);
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
    public IVector add(Vector that) {
        Vector vector = new Vector(this.size);
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
        Vector vector = new Vector(end - start + 1);
        for (int i = 0; i < vector.size; i++) {
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
