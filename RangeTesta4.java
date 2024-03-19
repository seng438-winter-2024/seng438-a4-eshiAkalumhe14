package org.jfree.data;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {
    private Range posnegrange;
    private Range posrange;
    private Range negrange;
    
    @BeforeClass 
    public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { 
        posnegrange = new Range(-1, 1);
        posrange = new Range(5, 10);
        negrange = new Range(-10, -5);
    }


    @Test
    public void centralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0", 0, posnegrange.getCentralValue(), .000000001d);
    }
    
    @Test
    public void centralValueShouldBeSevenPointFive() {
        assertEquals("The central value of 5 and 10 should be 7.5", 7.5, posrange.getCentralValue(), .000000001d);
    }
    
    @Test
    public void centralValueShouldBeNegSevenPointFive() {
        assertEquals("The central value of -10 and -5 should be -7.5", -7.5, negrange.getCentralValue(), .000000001d);
    }
    
    
    @Test
    public void lowerValueShouldBeNegOne() {
        assertEquals("The lower bound of -1 and 1 should be -1", -1, posnegrange.getLowerBound(), 0.0001);
    }
    
    @Test
    public void lowerValueShouldBeFive() {
        assertEquals("The lower bound of 5 and 10 should be 5", 5, posrange.getLowerBound(), 0.0001);
    }
    
    @Test
    public void lowerValueShouldBeNegTen() {
        assertEquals("The lower bound of -10 and -5 should be -10", -10, negrange.getLowerBound(), 0.0001);
    }
    
    
    
    
    @Test
    public void upperValueShouldBeOne() {
        assertEquals("The upper bound of -1 and 1 should be 1", 1, posnegrange.getUpperBound(), 0.0001);
    }
    
    @Test
    public void upperValueShouldBeTen() {
        assertEquals("The upper bound of 5 and 10 should be 10", 10, posrange.getUpperBound(), 0.0001);
    }
    
    @Test
    public void upperValueShouldBeNegFive() {
        assertEquals("The upper bound of -10 and -5 should be -5", -5, negrange.getUpperBound(), 0.0001);
    }
    
    
    @Test
    public void lengthShouldBeTwo() {
        assertEquals("The length of -1 and 1 should be 2", 2, posnegrange.getLength(), 0.0001);
    }
    
    @Test
    public void lengthShouldBeFive() {
        assertEquals("The length of 5 and 10 should be 5", 5, posrange.getLength(), 0.0001);
        assertEquals("The length of -10 and -5 should be 5", 5, negrange.getLength(), 0.0001);
    }
    
    
    @Test
    public void posnegrangeContainsZeroShouldReturnTrue() {
        assertTrue("The range -1 to 1 should contain 0", posnegrange.contains(0));
    }
    
    @Test
    public void posrangecontainsSevenShouldReturnTrue() {
        assertTrue("The range 5 to 10 should contain 7", posrange.contains(7));
    }
    
    @Test
    public void negrangecontainsNegSevenShouldReturnTrue() {
        assertTrue("The range -10 to -5 should contain -7", negrange.contains(-7));
    }
    
    // New test cases for increased coverage
    
    @Test
    public void posnegrangecontainsValueOutsideRangeShouldReturnFalse() {
        assertFalse("The range -1 to 1 should not contain 2", posnegrange.contains(2));
    }
    
    @Test
    public void posrangecontainsNegativeValueOutsideRangeShouldReturnFalse() {
        assertFalse("The range 5 to 10 should not contain -1", posrange.contains(-1));
    }
    
    @Test
    public void posnegrangecontainsValueEqualToUpperBound() {
        assertTrue("The range -1 to 1 should contain 1", posnegrange.contains(1));
    }
    
    @Test
    public void posnegrangecontainsValueEqualToLowerBound() {
        assertTrue("The range -1 to 1 should contain -1", posnegrange.contains(-1));
    }
    
    // Test cases for intersects method
    
    @Test
    public void intersectsShouldReturnTrueForOverlappingRanges() {
        Range overlapRange = new Range(-2, 0);
        assertTrue("Ranges (-1, 1) and (-2, 0) should overlap", posnegrange.intersects(overlapRange));
    }
    
    @Test
    public void intersectsShouldReturnFalseForNonOverlappingRanges() {
        Range nonOverlapRange = new Range(2, 4);
        assertFalse("Ranges (-1, 1) and (2, 4) should not overlap", posnegrange.intersects(nonOverlapRange));
    }
    
    @Test
    public void intersectsShouldReturnTrueForContainedRange() {
        Range containedRange = new Range(-0.5, 0.5);
        assertTrue("Range (-0.5, 0.5) should be contained within (-1, 1)", posnegrange.intersects(containedRange));
    }
    
    @Test
    public void intersectsShouldReturnTrueForSameRange() {
        assertTrue("Range (-1, 1) should intersect with itself", posnegrange.intersects(posnegrange));
    }
    
    @Test
    public void intersectsShouldReturnFalseForNullRange() {
        assertFalse("Range (-1, 1) should not intersect with null range", posnegrange.intersects(null));
    }
    
    @Test
    public void combineNonNullRangesShouldEqualCorrctRange() {
    	// Test case with two non-null ranges
        Range range1 = new Range(0, 5);
        Range range2 = new Range(3, 8);
        Range combinedRange = Range.combine(range1, range2);
        assertEquals(new Range(0, 8), combinedRange);
    }
    
    public void combinationWithOneNullRangeShouldReturnCorrectRange() {
    	// Test case with one null range
    	Range range1 = new Range(0, 5);
        Range range3 = null;
        Range combinedRange2 = Range.combine(range1, range3);
        assertEquals(range1, combinedRange2);
    }
    
    public void combineTwoNullRangesShouldReturnNull() {
    	Range combinedRange3 = Range.combine(null, null);
        assertNull(combinedRange3);
    }
    
    public void testExpandToIncludeForValueOutsideRange() {
    	// Test case with a range and a value outside the range
        Range range1 = new Range(0, 5);
        Range expandedRange = Range.expandToInclude(range1, 10);
        assertEquals(new Range(0, 10), expandedRange);
    }
    
    public void expandToIncludeForValueInsideRangeShouldReturnSameRange() {
    	 // Test case with a range and a value within the range
        Range expandedRange2 = Range.expandToInclude(range1, 3);
        assertEquals(range1, expandedRange2);
    }
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}