/**
 * ******************************************************************************
 * ************************ LATIN SQUARE TOOLBOX ********************************
 * ******************************************************************************
 * Copyright (c) 2017 Nathan O. Schmidt <c0ldc4lcul4ti0n@gmail.com>
 * Copyright (c) 2017 Will Unger <zomborg1@gmail.com>
 * ******************************************************************************
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * ******************************************************************************
 */

import latinsquare.*;
import java.io.File;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * <h1>TestLatinSquarePropertyChecking</h1>
 * 
 * <p>This class contains the unit tests for checking if squares satisfy the
 * Latin Square Property.</p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public class TestLatinSquarePropertyChecking
{
	private final File resourcesDirectory = new File("src/test/resources");
	private final String inputDirectory = resourcesDirectory.getAbsolutePath() + "/data/target/squares/data_set/";
	
	/**
	 * Unit test for an order-1 Latin square data set.
	 */
	@Test
	public void testLatinSquarePropertyCheckOrder1() 
	{
		assertEquals(true, testLatinSquarePropertyChecking(1, 1, 1, 0));
	}
	
	/**
	 * Unit test for an order-2 Latin square data set.
	 */
	@Test
	public void testLatinSquarePropertyCheckOrder2() 
	{
		assertEquals(true, testLatinSquarePropertyChecking(2, 2, 1, 1));
	}
	
	/**
	 * Unit test for an order-3 Latin square data set.
	 */
	@Test
	public void testLatinSquarePropertyCheckOrder3() 
	{
		assertEquals(true, testLatinSquarePropertyChecking(3, 10, 7, 3));
	}
	
	/**
	 * Unit test for an order-4 Latin square data set.
	 */
	@Test
	public void testLatinSquarePropertyCheckOrder4() 
	{
		assertEquals(true, testLatinSquarePropertyChecking(4, 10, 5, 5));
	}
	
	/**
	 * Unit test for an order-5 Latin square data set.
	 */
	@Test
	public void testLatinSquarePropertyCheckOrder5() 
	{
		assertEquals(true, testLatinSquarePropertyChecking(5, 10, 5, 5));
	}
	
	/**
	 * Unit test for an order-6 Latin square data set.
	 */
	@Test
	public void testLatinSquarePropertyCheckOrder6() 
	{
		assertEquals(true, testLatinSquarePropertyChecking(6, 10, 9, 1));
	}
	
	/**
	 * Unit test for an order-7 Latin square data set.
	 */
	@Test
	public void testLatinSquarePropertyCheckOrder7() 
	{
		assertEquals(true, testLatinSquarePropertyChecking(7, 10, 9, 1));
	}
	
	/**
	 * Unit test for an order-8 Latin square data set.
	 */
	@Test
	public void testLatinSquarePropertyCheckOrder8() 
	{
		assertEquals(true, testLatinSquarePropertyChecking(8, 10, 9, 1));
	}
	
	/**
	 * Unit test for an order-9 Latin square data set.
	 */
	@Test
	public void testLatinSquarePropertyCheckOrder9() 
	{
		assertEquals(true, testLatinSquarePropertyChecking(9, 10, 9, 1));
	}
	
	/**
	 * Unit test method for order-n Latin square data sets.
	 * 
	 * @param order The Latin square order.
	 * @param dataSetSize The size of the data set.
	 * @param expectedSuccess The expected number of squares that satisfy the Latin Square Property.
	 * @param expectedFail The expected number of squares that do not satisfy the Latin Square Property.
	 * @return boolean Test result.
	 */
	public boolean testLatinSquarePropertyChecking(int order, int dataSetSize, int expectedSuccess, int expectedFail) 
	{
		String inputFileName = inputDirectory + "some_invalid_n0" + order + "_s" + dataSetSize + ".txt";
		LatinSquareFileParser squareFileParser = new LatinSquareFileParser(order, inputFileName);
		int i, actualSuccess = 0, actualFail = 0;
		
		for(i = 0; squareFileParser.hasNext(); i++)
		{
			Square square = squareFileParser.next();
			if(square.latinSquarePropertyHolds()) { ++actualSuccess; }
			else { ++actualFail; }
		}
		
		return (i == dataSetSize) && (actualSuccess == expectedSuccess) && (actualFail == expectedFail);
	}
}
