/**
 * **********************************************************************
 * ************************ LATIN SQUARE TOOLBOX ************************
 * **********************************************************************
 * Copyright (C) 2017 Nathan O. Schmidt <c0ldc4lcul4ti0n@gmail.com>
 * Copyright (C) 2017 Will Unger <zomborg1@gmail.com>
 * **********************************************************************
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *  
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * **********************************************************************
 */

import latinsquare.*;
import java.io.File;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * <h1>TestLatinSquareTransversalCounting</h1>
 * 
 * <p>This class contains the unit tests for counting the number of transversals
 * in Latin squares.</p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public class TestLatinSquareTransversalCounting 
{
	private final File resourcesDirectory = new File("src/test/resources");
	private final String inputDirectorySS = resourcesDirectory.getAbsolutePath() + "/data/target/squares/super_symmetric/";
	private final String inputDirectoryDSP = resourcesDirectory.getAbsolutePath() + "/data/target/squares/data_set_preload/";
	
	/**
	 * Unit test for an order-2 cyclic Latin square.
	 */
	@Test
	public void testTransversalCountingCyclicPrimeOrder2() 
	{
		assertEquals(true, testTransversalCounterSuperSymmetric(2, 1, 0));
	}
	
	/**
	 * Unit test for an order-2^2 super-symmetric Latin square.
	 */
	@Test
	public void testTransversalCountingSuperSymmetricPrimePowerOrder2_2() 
	{
		assertEquals(true, testTransversalCounterSuperSymmetric(2, 2, 8));
	}
	
	/**
	 * Unit test for an order-2^3 super-symmetric Latin square.
	 */
	@Test
	public void testTransversalCountingSuperSymmetricPrimePowerOrder2_3() 
	{
		assertEquals(true, testTransversalCounterSuperSymmetric(2, 3, 384));
	}
	
	/**
	 * Unit test for an order-3 cyclic Latin square.
	 */
	@Test
	public void testTransversalCountingCyclicPrimeOrder3() 
	{
		assertEquals(true, testTransversalCounterSuperSymmetric(3, 1, 3));
	}
	
	/**
	 * Unit test for an order-3^2 super-symmetric Latin square.
	 */
	@Test
	public void testTransversalCountingSuperSymmetricPrimePowerOrder3_2() 
	{
		assertEquals(true, testTransversalCounterSuperSymmetric(3, 2, 2241));
	}
	
	/**
	 * Unit test for an order-5 cyclic Latin square.
	 */
	@Test
	public void testTransversalCountingCyclicPrimeOrder5() 
	{
		assertEquals(true, testTransversalCounterSuperSymmetric(5, 1, 15));
	}
	
	/**
	 * Unit test for an order-7 cyclic Latin square.
	 */
	@Test
	public void testTransversalCountingCyclicPrimeOrder7() 
	{
		assertEquals(true, testTransversalCounterSuperSymmetric(7, 1, 133));
	}
	
	/**
	 * Unit test method for order-p^d super-symmetric (or cyclic) Latin squares.
	 * 
	 * @param orderBase The base p of the Latin square's order.
	 * @param orderPower The power d of the Latin square's order.
	 * @param expectedTransversalCount The expected transversal count.
	 * @return boolean Test result.
	 */
	public boolean testTransversalCounterSuperSymmetric(int orderBase, int orderPower, long expectedTransversalCount) 
	{
		String inputFileName = inputDirectorySS + "p" + orderBase + "_d" + orderPower + "_supersym.txt";
		int order = (int)Math.pow(orderBase, orderPower);
		LatinSquareFileParser squareFileParser = new LatinSquareFileParser(order, inputFileName);
		
		assertEquals(true, squareFileParser.hasNext());
		Square square = squareFileParser.next();
		return (expectedTransversalCount == square.getTransversalCount());
	}
	
	/**
	 * Unit test for an order-1 Latin square data set.
	 */
	@Test
	public void testTransversalCountingDataSetSize0Order1() 
	{
		final long [] expectedTransversalCounts = { 1 };
		
		assertEquals(true, testTransversalCounterDataSet(1, 0, expectedTransversalCounts));
	}
	
	/**
	 * Unit test for an order-2 Latin square data set.
	 */
	@Test
	public void testTransversalCountingDataSetSize0Order2() 
	{
		final long [] expectedTransversalCounts = { 0, 0 };
		
		assertEquals(true, testTransversalCounterDataSet(2, 0, expectedTransversalCounts));
	}
	
	/**
	 * Unit test for an order-3 Latin square data set.
	 */
	@Test
	public void testTransversalCountingDataSetSize0Order3() 
	{
		final long [] expectedTransversalCounts = { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3 };
		
		assertEquals(true, testTransversalCounterDataSet(3, 0, expectedTransversalCounts));
	}
	
	/**
	 * Unit test for an order-4 Latin square data set.
	 */
	@Test
	public void testTransversalCountingDataSetSize10Order4() 
	{
		final long [] expectedTransversalCounts = { 0, 0, 0, 0, 0, 0, 8, 0, 0, 8 };
		
		assertEquals(true, testTransversalCounterDataSet(4, 10, expectedTransversalCounts));
	}
	
	/**
	 * Unit test for an order-5 Latin square data set.
	 */
	@Test
	public void testTransversalCountingDataSetSize10Order5() 
	{
		final long [] expectedTransversalCounts = { 15, 15, 3, 3, 3, 3, 3, 3, 3, 3 };
		
		assertEquals(true, testTransversalCounterDataSet(5, 10, expectedTransversalCounts));
	}
	
	/**
	 * Unit test for an order-6 Latin square data set.
	 */
	@Test
	public void testTransversalCountingDataSetSize10Order6() 
	{
		final long [] expectedTransversalCounts = { 0, 0, 8, 8, 8, 8, 8, 8, 0, 8 };
		
		assertEquals(true, testTransversalCounterDataSet(6, 10, expectedTransversalCounts));
	}
	
	/**
	 * Unit test for an order-7 Latin square data set.
	 */
	@Test
	public void testTransversalCountingDataSetSize10Order7() 
	{
		final long [] expectedTransversalCounts = { 133, 133, 23, 23, 41, 41, 41, 41, 23, 23 };
		
		assertEquals(true, testTransversalCounterDataSet(7, 10, expectedTransversalCounts));
	}
	
	/**
	 * Unit test for an order-8 Latin square data set.
	 */
	@Test
	public void testTransversalCountingDataSetSize10Order8() 
	{
		final long [] expectedTransversalCounts = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		
		assertEquals(true, testTransversalCounterDataSet(8, 10, expectedTransversalCounts));
	}
	
	/**
	 * Unit test for an order-9 Latin square data set.
	 */
	@Test
	public void testTransversalCountingDataSetSize10Order9() 
	{
		final long [] expectedTransversalCounts = { 2025, 2025, 437, 437, 425, 425, 425, 425, 379, 379 };
		
		assertEquals(true, testTransversalCounterDataSet(9, 10, expectedTransversalCounts));
	}
	
	/**
	 * Unit test method for order-n Latin square data sets.
	 * 
	 * @param order The Latin square order.
	 * @param dataSetSize The size of the data set.
	 * @param expectedTransversalCount The array of expected transversal counts.
	 * @return boolean Test result.
	 */
	public boolean testTransversalCounterDataSet(int order, int dataSetSize, long [] expectedTransversalCount) 
	{
		String inputFileName = inputDirectoryDSP + "n0" + order + "_s" + dataSetSize + "_preload.txt";
		LatinSquareFileParser squareFileParser = new LatinSquareFileParser(order, inputFileName);
		
		for(int i = 0; squareFileParser.hasNext(); i++)
		{
			Square square = squareFileParser.next();
			if(expectedTransversalCount[i] != square.getTransversalCount()) { return false; }
		}
		
		return true;
	}
}
