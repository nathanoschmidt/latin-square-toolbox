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
 * <h1>TestLatinSquareTransversalHeatMap</h1>
 * 
 * <p>This class contains the unit tests for generating heat maps of Latin squares.</p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public class TestLatinSquareTransversalHeatMap 
{
	private final File resourcesDirectory = new File("src/test/resources");
	private final String inputDirectorySS = resourcesDirectory.getAbsolutePath() + "/data/target/squares/super_symmetric/";
	private final String inputDirectoryDS = resourcesDirectory.getAbsolutePath() + "/data/target/squares/data_set/";
	
	/**
	 * Unit test for an order-2 cyclic Latin square.
	 */
	@Test
	public void testHeatMapCyclicPrimeOrder2() 
	{
		final String heatMapStr = "0   0   \n" +
								  "0   0";
		
		assertEquals(true, testHeatMapSuperSymmetric(2, 1, heatMapStr));
	}
	
	/**
	 * Unit test for an order-2^2 super-symmetric Latin square.
	 */
	@Test
	public void testHeatMapSuperSymmetricPrimePowerOrder2_2() 
	{
		final String heatMapStr = "2   2   2   2   \n" +
								  "2   2   2   2   \n" +
								  "2   2   2   2   \n" +
								  "2   2   2   2";
		
		assertEquals(true, testHeatMapSuperSymmetric(2, 2, heatMapStr));
	}
	
	/**
	 * Unit test for an order-2^3 super-symmetric Latin square.
	 */
	@Test
	public void testHeatMapSuperSymmetricPrimePowerOrder2_3() 
	{
		final String heatMapStr = "48  48  48  48  48  48  48  48  \n" +
								  "48  48  48  48  48  48  48  48  \n" +
								  "48  48  48  48  48  48  48  48  \n" +
								  "48  48  48  48  48  48  48  48  \n" +
								  "48  48  48  48  48  48  48  48  \n" +
								  "48  48  48  48  48  48  48  48  \n" +
								  "48  48  48  48  48  48  48  48  \n" +
								  "48  48  48  48  48  48  48  48";
		
		assertEquals(true, testHeatMapSuperSymmetric(2, 3, heatMapStr));
	}
	
	/**
	 * Unit test for an order-3 cyclic Latin square.
	 */
	@Test
	public void testHeatMapCyclicPrimeOrder3() 
	{
		final String heatMapStr = "1   1   1   \n" +
				  				  "1   1   1   \n" +
				  				  "1   1   1";
		assertEquals(true, testHeatMapSuperSymmetric(3, 1, heatMapStr));
	}
	
	/**
	 * Unit test for an order-3^2 super-symmetric Latin square.
	 */
	@Test
	public void testHeatMapSuperSymmetricPrimePowerOrder3_2() 
	{
		final String heatMapStr = "249  249  249  249  249  249  249  249  249  \n" +
								  "249  249  249  249  249  249  249  249  249  \n" +
								  "249  249  249  249  249  249  249  249  249  \n" +
								  "249  249  249  249  249  249  249  249  249  \n" +
								  "249  249  249  249  249  249  249  249  249  \n" +
								  "249  249  249  249  249  249  249  249  249  \n" +
								  "249  249  249  249  249  249  249  249  249  \n" +
								  "249  249  249  249  249  249  249  249  249  \n" +
								  "249  249  249  249  249  249  249  249  249";
		
		assertEquals(true, testHeatMapSuperSymmetric(3, 2, heatMapStr));
	}
	
	/**
	 * Unit test for an order-5 cyclic Latin square.
	 */
	@Test
	public void testHeatMapCyclicPrimeOrder5() 
	{
		final String heatMapStr = "3   3   3   3   3   \n" +
								  "3   3   3   3   3   \n" +
								  "3   3   3   3   3   \n" +
								  "3   3   3   3   3   \n" +
								  "3   3   3   3   3";
		
		assertEquals(true, testHeatMapSuperSymmetric(5, 1, heatMapStr));
	}
	
	/**
	 * Unit test for an order-7 cyclic Latin square.
	 */
	@Test
	public void testHeatMapCyclicPrimeOrder7() 
	{
		final String heatMapStr = "19  19  19  19  19  19  19  \n" +
								  "19  19  19  19  19  19  19  \n" +
								  "19  19  19  19  19  19  19  \n" +
								  "19  19  19  19  19  19  19  \n" +
								  "19  19  19  19  19  19  19  \n" +
								  "19  19  19  19  19  19  19  \n" +
								  "19  19  19  19  19  19  19";
		
		assertEquals(true, testHeatMapSuperSymmetric(7, 1, heatMapStr));
	}
	
	/**
	 * Unit test method for order-p^d super-symmetric (or cyclic) Latin squares 
	 * with uniform heat values (in human-readable format).
	 * 
	 * @param orderBase The prime base p of the order-p^d of the super-symmetric Latin square.
	 * @param orderPower The power d of the order-p^d of the super-symmetric Latin square.
	 * @param expectedHeatMapStr The expected value of the heat map string.
	 * @return boolean Test result.
	 */
	public boolean testHeatMapSuperSymmetric(int orderBase, int orderPower, String expectedHeatMapStr) 
	{
		String inputFileName = inputDirectorySS + "p" + orderBase + "_d" + orderPower + "_supersym.txt";
		int order = (int)Math.pow(orderBase, orderPower);
		LatinSquareFileParser squareFileParser = new LatinSquareFileParser(order, inputFileName);
		
		assertEquals(true, squareFileParser.hasNext());
		Square square = squareFileParser.next();
		square.getTransversalCount();
		
		return expectedHeatMapStr.equals(square.toStringTransversalHeatMapHumanReadable().trim()) && 
				(square.getHeatValue() >= 0);
	}
	
	/**
	 * Unit test for an order-7 Latin square data set.
	 */
	@Test
	public void testHeatMapDataSetSize1Order7() 
	{
		final String heatMapStr = "0   0   0   0   1   1   1   \n" +
								  "0   0   0   0   1   1   1   \n" +
								  "0   0   0   0   1   1   1   \n" +
								  "3   0   0   0   0   0   0   \n" +
								  "0   3   0   0   0   0   0   \n" +
								  "0   0   3   0   0   0   0   \n" +
								  "0   0   0   3   0   0   0";
		
		assertEquals(true, testHeatMapNonUniformEntries(7, 1, heatMapStr));
	}
	
	/**
	 * Unit test for an order-9 Latin square data set.
	 */
	@Test
	public void testHeatMapDataSetSize1Order9() 
	{
		final String heatMapStr = "22  27  20  14  12  13  21  12  9   \n" +
								  "19  15  24  19  10  18  10  15  20  \n" +
								  "26  24  25  20  18  15  7   11  4   \n" +
								  "23  15  12  23  21  12  20  10  14  \n" +
								  "12  10  15  13  42  16  15  10  17  \n" +
								  "10  17  16  15  11  33  18  9   21  \n" +
								  "9   10  11  20  12  14  11  57  6   \n" +
								  "11  21  13  13  13  14  32  16  17  \n" +
								  "18  11  14  13  11  15  16  10  42";
		
		assertEquals(true, testHeatMapNonUniformEntries(9, 1, heatMapStr));
	}
	
	/**
	 * Unit test method for order-n Latin squares with non-uniform heat values (in human-readable format).
	 * 
	 * @param order The Latin square order.
	 * @param dataSetSize The size of the Latin square data set.
	 * @param expectedHeatMapStr The expected value of the heat map string.
	 * @return boolean The unit test result.
	 */
	public boolean testHeatMapNonUniformEntries(int order, int dataSetSize, String expectedHeatMapStr) 
	{
		String inputFileName = inputDirectoryDS + "n0" + order + "_s" + dataSetSize + ".txt";
		LatinSquareFileParser squareFileParser = new LatinSquareFileParser(order, inputFileName);
		
		assertEquals(true, squareFileParser.hasNext());
		Square square = squareFileParser.next();
		square.getTransversalCount();
		
		return expectedHeatMapStr.equals(square.toStringTransversalHeatMapHumanReadable().trim()) && 
			   (square.getHeatValue() < 0);
	}
}
