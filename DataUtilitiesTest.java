/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2013, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * ----------------------
 * DataUtilitiesTest.java
 * ----------------------
 * (C) Copyright 2005-2013, by Object Refinery Limited and Contributors.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 03-Mar-2005 : Version 1 (DG);
 * 28-Jan-2009 : Added tests for equal(double[][], double[][]) method (DG);
 * 28-Jan-2009 : Added tests for clone(double[][]) (DG);
 * 04-Feb-2009 : Added tests for new calculateColumnTotal/RowTotal methods (DG);
 *
 */

package org.jfree.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.jmock.Expectations;
import org.jmock.Mockery;

/**
 * Some tests for the {@link DataUtilities} class.
 */
public class DataUtilitiesTest extends DataUtilities {

    /**
     * Tests the createNumberArray2D() method.
     */
    @Test
    public void testCreateNumberArray2D() {
        double[][] d = new double[2][];
        d[0] = new double[] {1.1, 2.2, 3.3, 4.4};
        d[1] = new double[] {1.1, 2.2, 3.3, 4.4, 5.5};
        Number[][] n = DataUtilities.createNumberArray2D(d);
        assertEquals(2, n.length);
        assertEquals(4, n[0].length);
        assertEquals(5, n[1].length);
    }

    private static final double EPSILON = 0.000000001;

    /**
     * Some checks for the calculateColumnTotal() method.
     */
    @Test
    public void testCalculateColumnTotal() {
        DefaultKeyedValues2D table = new DefaultKeyedValues2D();
        table.addValue(new Double(1.0), "R0", "C0");
        table.addValue(new Double(2.0), "R0", "C1");
        table.addValue(new Double(3.0), "R1", "C0");
        table.addValue(new Double(4.0), "R1", "C1");
        assertEquals(4.0, DataUtilities.calculateColumnTotal(table, 0), EPSILON);
        assertEquals(6.0, DataUtilities.calculateColumnTotal(table, 1), EPSILON);
        table.setValue(null, "R1", "C1");
        assertEquals(2.0, DataUtilities.calculateColumnTotal(table, 1), EPSILON);
    }

    /**
     * Some checks for the calculateColumnTotal() method.
     */
    @Test
    public void testCalculateColumnTotal2() {
        DefaultKeyedValues2D table = new DefaultKeyedValues2D();
        table.addValue(new Double(1.0), "R0", "C0");
        table.addValue(new Double(2.0), "R0", "C1");
        table.addValue(new Double(3.0), "R1", "C0");
        table.addValue(new Double(4.0), "R1", "C1");
        assertEquals(4.0, DataUtilities.calculateColumnTotal(table, 0,
                new int[] {0, 1}), EPSILON);
        assertEquals(1.0, DataUtilities.calculateColumnTotal(table, 0,
                new int[] {0}), EPSILON);
        assertEquals(3.0, DataUtilities.calculateColumnTotal(table, 0,
                new int[] {1}), EPSILON);
        assertEquals(0.0, DataUtilities.calculateColumnTotal(table, 0,
                new int[] {}), EPSILON);

        assertEquals(6.0, DataUtilities.calculateColumnTotal(table, 1,
                new int[] {0, 1}), EPSILON);
        assertEquals(2.0, DataUtilities.calculateColumnTotal(table, 1,
                new int[] {0}), EPSILON);
        assertEquals(4.0, DataUtilities.calculateColumnTotal(table, 1,
                new int[] {1}), EPSILON);

        table.setValue(null, "R1", "C1");
        assertEquals(2.0, DataUtilities.calculateColumnTotal(table, 1,
                new int[] {0, 1}), EPSILON);
        assertEquals(0.0, DataUtilities.calculateColumnTotal(table, 1,
                new int[] {1}), EPSILON);
    }

    /**
     * Some checks for the calculateRowTotal() method.
     */
    @Test
    public void testCalculateRowTotal() {
        DefaultKeyedValues2D table = new DefaultKeyedValues2D();
        table.addValue(new Double(1.0), "R0", "C0");
        table.addValue(new Double(2.0), "R0", "C1");
        table.addValue(new Double(3.0), "R1", "C0");
        table.addValue(new Double(4.0), "R1", "C1");
        assertEquals(3.0, DataUtilities.calculateRowTotal(table, 0), EPSILON);
        assertEquals(7.0, DataUtilities.calculateRowTotal(table, 1), EPSILON);
        table.setValue(null, "R1", "C1");
        assertEquals(3.0, DataUtilities.calculateRowTotal(table, 1), EPSILON);
    }

    /**
     * Some checks for the calculateRowTotal() method.
     */
    @Test
    public void testCalculateRowTotal2() {
        DefaultKeyedValues2D table = new DefaultKeyedValues2D();
        table.addValue(new Double(1.0), "R0", "C0");
        table.addValue(new Double(2.0), "R0", "C1");
        table.addValue(new Double(3.0), "R1", "C0");
        table.addValue(new Double(4.0), "R1", "C1");
        assertEquals(3.0, DataUtilities.calculateRowTotal(table, 0,
                new int[] {0, 1}), EPSILON);
        assertEquals(1.0, DataUtilities.calculateRowTotal(table, 0,
                new int[] {0}), EPSILON);
        assertEquals(2.0, DataUtilities.calculateRowTotal(table, 0,
                new int[] {1}), EPSILON);
        assertEquals(0.0, DataUtilities.calculateRowTotal(table, 0,
                new int[] {}), EPSILON);

        assertEquals(7.0, DataUtilities.calculateRowTotal(table, 1,
                new int[] {0, 1}), EPSILON);
        assertEquals(3.0, DataUtilities.calculateRowTotal(table, 1,
                new int[] {0}), EPSILON);
        assertEquals(4.0, DataUtilities.calculateRowTotal(table, 1,
                new int[] {1}), EPSILON);
        assertEquals(0.0, DataUtilities.calculateRowTotal(table, 1,
                new int[] {}), EPSILON);
        table.setValue(null, "R1", "C1");
        assertEquals(3.0, DataUtilities.calculateRowTotal(table, 1,
                new int[] {0, 1}), EPSILON);
        assertEquals(0.0, DataUtilities.calculateRowTotal(table, 1,
                new int[] {1}), EPSILON);
    }

    /**
     * Some tests for the equal(double[][], double[][]) method.
     */
    @Test
    public void testEqual() {
        assertTrue(DataUtilities.equal(null, null));
        
        double[][] a = new double[5][];
        double[][] b = new double[5][];
        assertTrue(DataUtilities.equal(a, b));

        a = new double[4][];
        assertFalse(DataUtilities.equal(a, b));
        b = new double[4][];
        assertTrue(DataUtilities.equal(a, b));

        a[0] = new double[6];
        assertFalse(DataUtilities.equal(a, b));
        b[0] = new double[6];
        assertTrue(DataUtilities.equal(a, b));

        a[0][0] = 1.0;
        assertFalse(DataUtilities.equal(a, b));
        b[0][0] = 1.0;
        assertTrue(DataUtilities.equal(a, b));

        a[0][1] = Double.NaN;
        assertFalse(DataUtilities.equal(a, b));
        b[0][1] = Double.NaN;
        assertTrue(DataUtilities.equal(a, b));

        a[0][2] = Double.NEGATIVE_INFINITY;
        assertFalse(DataUtilities.equal(a, b));
        b[0][2] = Double.NEGATIVE_INFINITY;
        assertTrue(DataUtilities.equal(a, b));

        a[0][3] = Double.POSITIVE_INFINITY;
        assertFalse(DataUtilities.equal(a, b));
        b[0][3] = Double.POSITIVE_INFINITY;
        assertTrue(DataUtilities.equal(a, b));

        a[0][4] = Double.POSITIVE_INFINITY;
        assertFalse(DataUtilities.equal(a, b));
        b[0][4] = Double.NEGATIVE_INFINITY;
        assertFalse(DataUtilities.equal(a, b));
        b[0][4] = Double.POSITIVE_INFINITY;
        assertTrue(DataUtilities.equal(a, b));
    }

    /**
     * Some tests for the clone() method.
     */
    @Test
    public void testClone() {
        double[][] a = new double[1][];
        double[][] b = DataUtilities.clone(a);
        assertTrue(DataUtilities.equal(a, b));
        a[0] = new double[] { 3.0, 4.0 };
        assertFalse(DataUtilities.equal(a, b));
        b[0] = new double[] { 3.0, 4.0 };
        assertTrue(DataUtilities.equal(a, b));

        a = new double[2][3];
        a[0][0] = 1.23;
        a[1][1] = Double.NaN;
        b = DataUtilities.clone(a);
        assertTrue(DataUtilities.equal(a, b));

        a[0][0] = 99.9;
        assertFalse(DataUtilities.equal(a, b));
        b[0][0] = 99.9;
        assertTrue(DataUtilities.equal(a, b));
    }
    
 // createNumberArray
 	@Test
 	public void testCreateNumberArrayNormalCase() {
 	 double[] data = {1.0, 2.0, 3.0};
 	    Number[] result = DataUtilities.createNumberArray(data);
 	    System.out.println(result);
 	    assertArrayEquals("Array should contain Number equivalents of double values",
 	                         new Number[]{1.0, 2.0, 3.0}, result);
 	}
 	
 	@Test
 	public void testCreateNumberArrayEmptyArray() {
 	    double[] data = {};
 	    Number[] result = DataUtilities.createNumberArray(data);
 	    assertEquals("Resulting array should be empty", 0, result.length);
 	}
 	
 	@Test
	public void testCreateNumberArraySingleElement() {
	    double[] data = {42.0};
	    Number[] result = DataUtilities.createNumberArray(data);
	    assertArrayEquals("Array should contain a single Number equivalent of double value",
	                      new Number[]{42.0}, result);
	}
 	
 	@Test
	public void testCreateNumberArraySpecialValues() {
	    double[] data = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};
	    Number[] result = DataUtilities.createNumberArray(data);
	    Number[] expected = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};
	    for (int i = 0; i < expected.length; i++) {
	        assertEquals("Special value should be equal",
	            expected[i].doubleValue(), result[i].doubleValue(), 0.0);
	    }
	}
 	
 	// createNumberArray2D() 
 	@Test
     public void testCreateNumberArray2DNormalCase() {
         double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
         Number[][] result = DataUtilities.createNumberArray2D(data);
         assertArrayEquals("Array should contain Number equivalents of double values",
                           new Number[][]{{1.0, 2.0}, {3.0, 4.0}}, result);
     }

     @Test
     public void testCreateNumberArray2DEmptyArray() {
         double[][] data = {};
         Number[][] result = DataUtilities.createNumberArray2D(data);
         assertEquals("Resulting array should be empty", 0, result.length);
     }

     @Test
     public void testCreateNumberArray2DSubArrayEmpty() {
         double[][] data = {{}, {1.0, 2.0}};
         Number[][] result = DataUtilities.createNumberArray2D(data);
         assertEquals("First sub-array should be empty", 0, result[0].length);
         assertArrayEquals("Second sub-array should contain Numbers",
                           new Number[]{1.0, 2.0}, result[1]);
     }
     
     @Test
     public void testCreateNumberArray2DSingleElement() {
         double[][] data = {{42.0}};
         Number[][] result = DataUtilities.createNumberArray2D(data);
         assertArrayEquals("Array should contain a single sub-array with a single Number",
                           new Number[][]{{42.0}}, result);
     }

     @Test
     public void testCreateNumberArray2DVariedLength() {
         double[][] data = {{1.0}, {2.0, 3.0}};
         Number[][] result = DataUtilities.createNumberArray2D(data);
         assertArrayEquals("First sub-array should contain a single Number",
                           new Number[]{1.0}, result[0]);
         assertArrayEquals("Second sub-array should contain two Numbers",
                           new Number[]{2.0, 3.0}, result[1]);
     }

     @Test
     public void testCreateNumberArray2DSpecialValues() {
         double[][] data = {{Double.NaN, Double.POSITIVE_INFINITY}, {Double.NEGATIVE_INFINITY}};
         Number[][] result = DataUtilities.createNumberArray2D(data);
         assertEquals("Special value NaN should be equal",
                      Double.NaN, result[0][0].doubleValue(), 0.0);
         assertEquals("Special value POSITIVE_INFINITY should be equal",
                      Double.POSITIVE_INFINITY, result[0][1].doubleValue(), 0.0);
         assertEquals("Special value NEGATIVE_INFINITY should be equal",
                      Double.NEGATIVE_INFINITY, result[1][0].doubleValue(), 0.0);
     }


     //This test tests the createNumberArray() method from the data utilities class
     //It test the scenario where the values used to create the array include both negative and positive floats 
     
     //we expect the test to pass and create the array with the values given
     @Test
     public void testCreateNumberArrayWithMixedValues() {
         double[] data = {1.0, -2.0, 3.5, -4.2};
         Number[] result = DataUtilities.createNumberArray(data);
         assertArrayEquals("Array should contain Number equivalents of double values",
                 new Number[]{1.0, -2.0, 3.5, -4.2}, result);
     }

   
     //This test tests the createNumberArray2D() method from the data utilities class
     //It test the scenario where the values used to create the 2D array include both negative and positive floats
     
     //we expect the test to pass and create the 2D array with the values given
     @Test
     public void testCreateNumberArray2DWithMixedValues() {
         double[][] data = {{1.0, -2.0}, {3.5, -4.2}};
         Number[][] result = DataUtilities.createNumberArray2D(data);
         assertArrayEquals("Array should contain Number equivalents of double values",
                 new Number[][]{{1.0, -2.0}, {3.5, -4.2}}, result);
     }
     
     @Test
     public void testEqualForTwoPositiveArraysThatAreEqual() {
         double[][] arr1 = {
                {12, 21, 11},
                {34, 43, 54},
                {67, 68, 69}
         };
         
         double[][] arr2 = {
                {12, 21, 11},
                {34, 43, 54},
                {67, 68, 69}
         };
         
        boolean result = DataUtilities.equal(arr1, arr2);
        assertEquals(true, result);
     }

     @Test
     public void testEqualForTwoPositiveArraysThatAreNotEqual() {
         double[][] arr1 = {
                {12, 21, 11},
                {34, 43, 54},
                {67, 68, 69}
         };
         
         double[][] arr2 = {
                {123, 245, 75},
                {4, 5, 85},
                {35, 185, 142}
         };
         
        boolean result = DataUtilities.equal(arr1, arr2);
        assertEquals(false, result);
     }
     
     @Test
     public void testEqualForWhenArrayBIsNull() {
         double[][] arr1 = {
                {12, 21, 11},
                {34, 43, 54},
                {67, 68, 69}
         };
         
         double[][] arr2 = null;
         
        boolean result = DataUtilities.equal(arr1, arr2);
        assertEquals(false, result);
     }

     
     //This test tests the equal() method from method from the data utilities class,
     //In this scenario, one of the arrays is left null and compared to the other fully populated array
     
     //we expect the test to pass as we're expecting a false.
     @Test
     public void testEqualForWhenArrayAIsNull() {
         double[][] arr1 = null;
         
         double[][] arr2 = {
                {12, 21, 11},
                {34, 43, 54},
                {67, 68, 69}
         };
         
        boolean result = DataUtilities.equal(arr1, arr2);
        assertEquals(false, result);
     }

     //This test tests the equal() method from method from the data utilities class,
     //In this scenario, one of the array is a different legnth than the other.
     
     //we expect this test to pass as we're expecting a false
     @Test
     public void testEqualForWhenArraysAreDifferentLengths() {
         double[][] arr1 = {
                 {2, 6, 8}
         };
         
         double[][] arr2 = {
                {12, 21, 11},
                {34, 43, 54},
                {67, 68, 69}
         };
         
        boolean result = DataUtilities.equal(arr1, arr2);
        assertEquals(false, result);
     }
     
     @Test
     public void testClonePositiveArray() {
         double[][] source = {
                {12, 21, 11},
                {34, 43, 54},
                {67, 68, 69}
         };
         
        double[][] result = DataUtilities.clone(source);
        assertEquals(source.length, result.length);
        assertArrayEquals(source[0], result[0], 0.001);
     }
     
     @Test
     public void testCreateNumberArrayWithInfinityAndNaN() {
         double[] data = {Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NaN};
         Number[] result = DataUtilities.createNumberArray(data);
         // Expecting the array to handle Infinity and NaN correctly
         assertArrayEquals(new Number[]{Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NaN}, result);
     }
     
     @Test
     public void testCalculateColumnTotalWithEmptyTable() {
         DefaultKeyedValues2D table = new DefaultKeyedValues2D();
         assertEquals(0.0, DataUtilities.calculateColumnTotal(table, 0), EPSILON);
     }
     
     @Test
     public void testEqualWithDifferentNaNValues() {
         double[][] a = new double[1][1];
         a[0][0] = Double.longBitsToDouble(0x7ff8000000000000L); // One form of NaN
         double[][] b = new double[1][1];
         b[0][0] = Double.NaN; // Standard NaN
         assertTrue(DataUtilities.equal(a, b));
     }
     
     @Test
     public void testCloneWithEmptyArray() {
         double[][] source = {};
         double[][] result = DataUtilities.clone(source);
         assertArrayEquals(source, result);
     }

     @Test
     public void testCloneWithNullElements() {
         double[][] source = new double[2][];
         source[0] = null;
         source[1] = new double[]{1.0, 2.0};
         double[][] result = DataUtilities.clone(source);
         assertNull(result[0]);
         assertArrayEquals(new double[]{1.0, 2.0}, result[1], EPSILON);
     }
     
     @Test
     public void testCalculateColumnTotalWithNegativeValues() {
         DefaultKeyedValues2D table = new DefaultKeyedValues2D();
         table.addValue(-1.0, "R0", "C0");
         table.addValue(-2.0, "R1", "C0");
         double result = DataUtilities.calculateColumnTotal(table, 0);
         assertEquals("Total should account for negative values", -3.0, result, EPSILON);
     }

     @Test
     public void testCalculateRowTotalWithMixedValues() {
         DefaultKeyedValues2D table = new DefaultKeyedValues2D();
         table.addValue(5.0, "R0", "C0");
         table.addValue(-2.0, "R0", "C1");
         double result = DataUtilities.calculateRowTotal(table, 0);
         assertEquals("Total should account for mix of positive and negative values", 3.0, result, EPSILON);
     }

     @Test
     public void testCreateNumberArrayWithZeroLength() {
         double[] data = new double[0];
         Number[] result = DataUtilities.createNumberArray(data);
         assertEquals("Array should be empty", 0, result.length);
     }
     
     @Test
     public void testCreateNumberArray2DOuterArrayEmpty() {
         double[][] data = new double[0][];
         Number[][] result = DataUtilities.createNumberArray2D(data);
         assertEquals("Outer array should be empty", 0, result.length);
     }

     @Test
     public void testCreateNumberArray2DInnerArraysDifferentLengths() {
         double[][] data = {{1.0, 2.0}, {3.0}};
         Number[][] result = DataUtilities.createNumberArray2D(data);
         assertEquals("First inner array length", 2, result[0].length);
         assertEquals("Second inner array length", 1, result[1].length);
     }
     
     @Test
     public void testGetCumulativePercentagesWithZeroTotal() {
         DefaultKeyedValues data = new DefaultKeyedValues();
         data.addValue("Key1", 0);
         data.addValue("Key2", 0);
         KeyedValues result = DataUtilities.getCumulativePercentages(data);
         assertEquals("Cumulative percentage for zero total should be NaN", Double.NaN, result.getValue(0).doubleValue(), EPSILON);
         assertEquals("Cumulative percentage for zero total should be NaN", Double.NaN, result.getValue(1).doubleValue(), EPSILON);
     }
     
     @Test
     public void testGetCumulativePercentagesWithNegativeValues() {
         DefaultKeyedValues data = new DefaultKeyedValues();
         data.addValue("Key1", -1.0);
         data.addValue("Key2", -2.0);
         KeyedValues result = DataUtilities.getCumulativePercentages(data);
         assertEquals("Cumulative percentage with negative values should be calculated correctly", 1.0 / 3.0, result.getValue(0).doubleValue(), EPSILON);
         assertEquals("Cumulative percentage with negative values should be calculated correctly", 1.0, result.getValue(1).doubleValue(), EPSILON);
     }


}
