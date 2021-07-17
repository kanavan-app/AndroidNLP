package com.kanavan.androidnlp;

public interface IVector {
    int length();

    String toString();

    void set(int position, double value);

    double get(int position);

    IVector add(IVector that);

    double sum();

    IVector multiply(IVector that);

    IVector subVector(int start, int end);

    double dot(IVector that);

    double cosine(IVector that);

    double magnitude();
}
