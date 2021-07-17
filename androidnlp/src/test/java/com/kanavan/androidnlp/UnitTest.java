package com.kanavan.androidnlp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
    @Test
    public void vector_create() {
        IVector vector = new Vector(1);
        assertEquals("[0.0]", vector.toString());
    }

    @Test
    public void vector_create_with_default_value() {
        IVector vector = new Vector(1, 0.0d);
        assertEquals("[0.0]", vector.toString());
    }

    @Test
    public void vector_create_with_data() {
        IVector vector = new Vector(1, new double[]{1.0d});
        assertEquals("[1.0]", vector.toString());
    }

    @Test
    public void vector_dot() {
        IVector vector1 = new Vector(6, new double[]{3.0d, 8.0d, 7.0d, 5.0d, 2.0d, 9.0d});
        IVector vector2 = new Vector(6, new double[]{10.0d, 8.0d, 6.0d, 6.0d, 4.0d, 5.0d});
        assertEquals(219.0d, vector1.dot(vector2), 0.0d);
    }

    @Test
    public void vector_cosine() {
        IVector vector1 = new Vector(6, new double[]{3.0d, 8.0d, 7.0d, 5.0d, 2.0d, 9.0d});
        IVector vector2 = new Vector(6, new double[]{10.0d, 8.0d, 6.0d, 6.0d, 4.0d, 5.0d});
        assertEquals(0.8638935626791596d, vector1.cosine(vector2), 0.0d);
    }

    @Test
    public void matrix_create() {
        IMatrix matrix = new Matrix(2, 2);
        assertEquals("[[0.0,0.0],[0.0,0.0]]", matrix.toString());
    }

    @Test
    public void matrix_create_with_default_value() {
        IMatrix matrix = new Matrix(2, 2, 0.0d);
        assertEquals("[[0.0,0.0],[0.0,0.0]]", matrix.toString());
    }

    @Test
    public void matrix_create_with_data() {
        IMatrix matrix = new Matrix(2, 2, new double[][]{{1.0d, 2.0d}, {3.0d, 4.0d}});
        assertEquals("[[1.0,2.0],[3.0,4.0]]", matrix.toString());
    }

    @Test
    public void matrix_dot() {
        IMatrix matrix1 = new Matrix(3, 1, new double[][]{{3.0d, 4.0d, 2.0d}});
        IMatrix matrix2 = new Matrix(4, 3, new double[][]{{13.0d, 9.0d, 7.0d, 15.0d}, {8.0d, 7.0d, 4.0d, 6.0d}, {6.0d, 4.0d, 0.0d, 3.0d}});
        assertEquals("[[83.0,63.0,37.0,75.0]]", matrix1.dot(matrix2).toString());
    }

    @Test
    public void matrix_add() {
        IMatrix matrix1 = new Matrix(2, 2, new double[][]{{1.0d, 2.0d}, {3.0d, 4.0d}});
        IMatrix matrix2 = new Matrix(2, 2, new double[][]{{5.0d, 6.0d}, {7.0d, 8.0d}});
        assertEquals("[[6.0,8.0],[10.0,12.0]]", matrix1.add(matrix2).toString());
    }

    @Test
    public void matrix_sub_matrix() {
        IMatrix matrix = new Matrix(4, 3, new double[][]{{13.0d, 9.0d, 7.0d, 15.0d}, {8.0d, 7.0d, 4.0d, 6.0d}, {6.0d, 4.0d, 0.0d, 3.0d}});
        assertEquals("[[4.0,6.0],[0.0,3.0]]", matrix.subMatrix(1, 2, 2, 3).toString());
    }

    @Test
    public void matrix_flatten() {
        IMatrix matrix = new Matrix(4, 3, new double[][]{{13.0d, 9.0d, 7.0d, 15.0d}, {8.0d, 7.0d, 4.0d, 6.0d}, {6.0d, 4.0d, 0.0d, 3.0d}});
        assertEquals("[13.0,9.0,7.0,15.0,8.0,7.0,4.0,6.0,6.0,4.0,0.0,3.0]", matrix.flatten().toString());
    }

    @Test
    public void nearest_neighbor_interpolation() {
        IMatrix matrix = new Matrix(3, 3, new double[][]{{1.0d, 2.0d, 3.0d}, {4.0d, 5.0d, 6.0d}, {7.0d, 8.0d, 9.0d}});
        assertEquals("[[1.0,2.0,3.0,3.0],[4.0,5.0,6.0,6.0],[7.0,8.0,9.0,9.0],[7.0,8.0,9.0,9.0]]", NearestNeighborInterpolation.interpolate(matrix, 4, 4).toString());
    }

    @Test
    public void convolution() {
        IMatrix matrix1 = new Matrix(7, 7, new double[][]{{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, {0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d}, {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, {0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d}, {0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d}, {0.0d, 0.0d, 1.0d, 1.0d, 1.0d, 0.0d, 0.0d}, {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}});
        IMatrix matrix2 = new Matrix(3, 3, new double[][]{{0.0d, 0.0d, 1.0d}, {1.0d, 0.0d, 0.0d}, {0.0d, 1.0d, 1.0d}});
        assertEquals("[[0.0,1.0,0.0,0.0,0.0],[0.0,1.0,1.0,1.0,0.0],[1.0,0.0,0.0,2.0,1.0],[1.0,4.0,2.0,1.0,0.0],[0.0,0.0,1.0,2.0,1.0]]", Convolution.convolve(matrix1, matrix2, 1).toString());
    }
}