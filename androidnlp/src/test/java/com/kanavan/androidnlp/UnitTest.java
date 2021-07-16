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
        Vector vector = new Vector(1);
        assertEquals("[0.0]", vector.toString());
    }

    @Test
    public void vector_create_with_default_value() {
        Vector vector = new Vector(1, 0.0d);
        assertEquals("[0.0]", vector.toString());
    }

    @Test
    public void vector_create_with_data() {
        Vector vector = new Vector(1, new double[]{0.0d});
        assertEquals("[0.0]", vector.toString());
    }

    @Test
    public void vector_dot() {
        Vector vector1 = new Vector(6, new double[]{3.0d, 8.0d, 7.0d, 5.0d, 2.0d, 9.0d});
        Vector vector2 = new Vector(6, new double[]{10.0d, 8.0d, 6.0d, 6.0d, 4.0d, 5.0d});
        assertEquals(219.0d, vector1.dot(vector2), 0.0d);
    }

    @Test
    public void vector_cosine() {
        Vector vector1 = new Vector(6, new double[]{3.0d, 8.0d, 7.0d, 5.0d, 2.0d, 9.0d});
        Vector vector2 = new Vector(6, new double[]{10.0d, 8.0d, 6.0d, 6.0d, 4.0d, 5.0d});
        assertEquals(0.8638935626791596d, vector1.cosine(vector2), 0.0d);
    }
}