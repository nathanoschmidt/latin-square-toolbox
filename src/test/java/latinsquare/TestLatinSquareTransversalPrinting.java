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
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * <h1>TestLatinSquareTransversalPrinting</h1>
 * 
 * <p>This class contains the unit tests for transversal lists of Latin squares.</p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public class TestLatinSquareTransversalPrinting 
{
	/**
	 * Unit test for an order-1 Latin square.
	 */
	@Test
	public void testTransversalPrintingOrder1() 
	{
		final int TEST_ORDER = 1;
		final String TARGET_TRANSVERSAL_LIST_STR = "(0,0,0)";
		final long TARGET_TRANSVERSAL_COUNT = 1;

		Square square = new Square(TEST_ORDER);
		square.setCellSymbol(0,0,0);
		
		long actualTransversalCount = square.getTransversalCount();
		String actualTransversalListStr = square.toStringTransversalsOrderedTriple();

		assertEquals(TARGET_TRANSVERSAL_COUNT, actualTransversalCount);
		assertEquals(TARGET_TRANSVERSAL_LIST_STR.trim(), actualTransversalListStr.trim());
	}
	
	/**
	 * Unit test for an order-2 Latin square.
	 */
	@Test
	public void testTransversalPrintingOrder2() 
	{
		final int TEST_ORDER = 2;
		final String TARGET_TRANSVERSAL_LIST_STR = "";
		final long TARGET_TRANSVERSAL_COUNT = 0;
		
		Square square = new Square(TEST_ORDER);
		square.setCellSymbol(0,0,0);
		square.setCellSymbol(0,1,1);
		square.setCellSymbol(1,0,1);
		square.setCellSymbol(1,1,0);
		
		long actualTransversalCount = square.getTransversalCount();
		String actualTransversalListStr = square.toStringTransversalsOrderedTriple();

		assertEquals(TARGET_TRANSVERSAL_COUNT, actualTransversalCount);
		assertEquals(TARGET_TRANSVERSAL_LIST_STR.trim(), actualTransversalListStr.trim());
	}
	
	/**
	 * Unit test for an order-3 Latin square.
	 */
	@Test
	public void testTransversalPrintingOrder3() 
	{
		final int TEST_ORDER = 3;
		final String TARGET_TRANSVERSAL_LIST_STR = "(0,0,0),(1,1,2),(2,2,1)\n" +
												   "(0,1,1),(1,2,0),(2,0,2)\n" +
												   "(0,2,2),(1,0,1),(2,1,0)";
		final long TARGET_TRANSVERSAL_COUNT = 3;
		
		Square square = new Square(TEST_ORDER);
		square.setCellSymbol(0,0,0);
		square.setCellSymbol(0,1,1);
		square.setCellSymbol(0,2,2);
		square.setCellSymbol(1,0,1);
		square.setCellSymbol(1,1,2);
		square.setCellSymbol(1,2,0);
		square.setCellSymbol(2,0,2);
		square.setCellSymbol(2,1,0);
		square.setCellSymbol(2,2,1);
		
		long actualTransversalCount = square.getTransversalCount();
		String actualTransversalListStr = square.toStringTransversalsOrderedTriple();

		assertEquals(TARGET_TRANSVERSAL_COUNT, actualTransversalCount);
		assertEquals(TARGET_TRANSVERSAL_LIST_STR.trim(), actualTransversalListStr.trim());
	}
	
	/**
	 * Unit test for an order-4 Latin square (minimum transversal count).
	 */
	@Test
	public void testTransversalPrintingOrder4_min() 
	{
		final int TEST_ORDER = 4;
		final String TARGET_TRANSVERSAL_LIST_STR = "";
		final long TARGET_TRANSVERSAL_COUNT = 0;
		
		Square square = new Square(TEST_ORDER);
		square.setCellSymbol(0,0,0); 
		square.setCellSymbol(0,1,1);
		square.setCellSymbol(0,2,2);
		square.setCellSymbol(0,3,3);
		
		square.setCellSymbol(1,0,1);
		square.setCellSymbol(1,1,2);
		square.setCellSymbol(1,2,3);
		square.setCellSymbol(1,3,0);
		
		square.setCellSymbol(2,0,2);
		square.setCellSymbol(2,1,3);
		square.setCellSymbol(2,2,0);
		square.setCellSymbol(2,3,1);
		
		square.setCellSymbol(3,0,3);
		square.setCellSymbol(3,1,0);
		square.setCellSymbol(3,2,1);
		square.setCellSymbol(3,3,2);
		
		long actualTransversalCount = square.getTransversalCount();
		String actualTransversalListStr = square.toStringTransversalsOrderedTriple();

		assertEquals(TARGET_TRANSVERSAL_COUNT, actualTransversalCount);
		assertEquals(TARGET_TRANSVERSAL_LIST_STR.trim(), actualTransversalListStr.trim());
	}
	
	/**
	 * Unit test for an order-4 Latin square (maximum transversal count).
	 */
	@Test
	public void testTransversalPrintingOrder4_max() 
	{
		final int TEST_ORDER = 4;
		final String TARGET_TRANSVERSAL_LIST_STR =  "(0,0,0),(1,1,3),(2,2,1),(3,3,2)\n" +
													"(0,0,0),(1,3,1),(2,1,2),(3,2,3)\n" +
													"(0,1,1),(1,0,2),(2,3,0),(3,2,3)\n" +
													"(0,1,1),(1,2,0),(2,0,3),(3,3,2)\n" +
													"(0,2,2),(1,1,3),(2,3,0),(3,0,1)\n" +
													"(0,2,2),(1,3,1),(2,0,3),(3,1,0)\n" +
													"(0,3,3),(1,0,2),(2,2,1),(3,1,0)\n" +
													"(0,3,3),(1,2,0),(2,1,2),(3,0,1)";
		final long TARGET_TRANSVERSAL_COUNT = 8;
		
		Square square = new Square(TEST_ORDER);
		square.setCellSymbol(0,0,0); 
		square.setCellSymbol(0,1,1);
		square.setCellSymbol(0,2,2);
		square.setCellSymbol(0,3,3);
		
		square.setCellSymbol(1,0,2);
		square.setCellSymbol(1,1,3);
		square.setCellSymbol(1,2,0);
		square.setCellSymbol(1,3,1);
		
		square.setCellSymbol(2,0,3);
		square.setCellSymbol(2,1,2);
		square.setCellSymbol(2,2,1);
		square.setCellSymbol(2,3,0);
		
		square.setCellSymbol(3,0,1);
		square.setCellSymbol(3,1,0);
		square.setCellSymbol(3,2,3);
		square.setCellSymbol(3,3,2);
		
		long actualTransversalCount = square.getTransversalCount();
		String actualTransversalListStr = square.toStringTransversalsOrderedTriple();

		assertEquals(TARGET_TRANSVERSAL_COUNT, actualTransversalCount);
		assertEquals(TARGET_TRANSVERSAL_LIST_STR.trim(), actualTransversalListStr.trim());
	}
	
	/**
	 * Unit test for an order-5 Latin square (minimum transversal count).
	 */
	@Test
	public void testTransversalPrintingOrder5_min() 
	{
		final int TEST_ORDER = 5;
		final String TARGET_TRANSVERSAL_LIST_STR =  "(0,1,1),(1,3,4),(2,2,0),(3,0,3),(4,4,2)\n" +
													"(0,3,3),(1,1,2),(2,2,0),(3,4,1),(4,0,4)\n" +
													"(0,4,4),(1,0,1),(2,2,0),(3,3,2),(4,1,3)";
		final long TARGET_TRANSVERSAL_COUNT = 3;
		
		Square square = new Square(TEST_ORDER);
		square.setCellSymbol(0,0,0); 
		square.setCellSymbol(0,1,1);
		square.setCellSymbol(0,2,2);
		square.setCellSymbol(0,3,3);
		square.setCellSymbol(0,4,4);
		
		square.setCellSymbol(1,0,1);
		square.setCellSymbol(1,1,2);
		square.setCellSymbol(1,2,3);
		square.setCellSymbol(1,3,4);
		square.setCellSymbol(1,4,0);
		
		square.setCellSymbol(2,0,2);
		square.setCellSymbol(2,1,4);
		square.setCellSymbol(2,2,0);
		square.setCellSymbol(2,3,1);
		square.setCellSymbol(2,4,3);
		
		square.setCellSymbol(3,0,3);
		square.setCellSymbol(3,1,0);
		square.setCellSymbol(3,2,4);
		square.setCellSymbol(3,3,2);
		square.setCellSymbol(3,4,1);
		
		square.setCellSymbol(4,0,4);
		square.setCellSymbol(4,1,3);
		square.setCellSymbol(4,2,1);
		square.setCellSymbol(4,3,0);
		square.setCellSymbol(4,4,2);
		
		long actualTransversalCount = square.getTransversalCount();
		String actualTransversalListStr = square.toStringTransversalsOrderedTriple();

		assertEquals(TARGET_TRANSVERSAL_COUNT, actualTransversalCount);
		assertEquals(TARGET_TRANSVERSAL_LIST_STR.trim(), actualTransversalListStr.trim());
	}
	
	/**
	 * Unit test for an order-5 Latin square (maximum transversal count).
	 */
	@Test
	public void testTransversalPrintingOrder5_max() 
	{
		final int TEST_ORDER = 5;
		final String TARGET_TRANSVERSAL_LIST_STR =  "(0,0,0),(1,1,2),(2,2,4),(3,3,1),(4,4,3)\n" +
													"(0,0,0),(1,2,3),(2,4,1),(3,1,4),(4,3,2)\n" +
													"(0,0,0),(1,3,4),(2,1,3),(3,4,2),(4,2,1)\n" +
													"(0,1,1),(1,2,3),(2,3,0),(3,4,2),(4,0,4)\n" +
													"(0,1,1),(1,3,4),(2,0,2),(3,2,0),(4,4,3)\n" +
													"(0,1,1),(1,4,0),(2,2,4),(3,0,3),(4,3,2)\n" +
													"(0,2,2),(1,0,1),(2,3,0),(3,1,4),(4,4,3)\n" +
													"(0,2,2),(1,3,4),(2,4,1),(3,0,3),(4,1,0)\n" +
													"(0,2,2),(1,4,0),(2,1,3),(3,3,1),(4,0,4)\n" +
													"(0,3,3),(1,0,1),(2,2,4),(3,4,2),(4,1,0)\n" +
													"(0,3,3),(1,1,2),(2,4,1),(3,2,0),(4,0,4)\n" +
													"(0,3,3),(1,4,0),(2,0,2),(3,1,4),(4,2,1)\n" +
													"(0,4,4),(1,0,1),(2,1,3),(3,2,0),(4,3,2)\n" +
													"(0,4,4),(1,1,2),(2,3,0),(3,0,3),(4,2,1)\n" +
													"(0,4,4),(1,2,3),(2,0,2),(3,3,1),(4,1,0)";
		final long TARGET_TRANSVERSAL_COUNT = 15;
		
		Square square = new Square(TEST_ORDER);
		square.setCellSymbol(0,0,0); 
		square.setCellSymbol(0,1,1);
		square.setCellSymbol(0,2,2);
		square.setCellSymbol(0,3,3);
		square.setCellSymbol(0,4,4);
		
		square.setCellSymbol(1,0,1);
		square.setCellSymbol(1,1,2);
		square.setCellSymbol(1,2,3);
		square.setCellSymbol(1,3,4);
		square.setCellSymbol(1,4,0);
		
		square.setCellSymbol(2,0,2);
		square.setCellSymbol(2,1,3);
		square.setCellSymbol(2,2,4);
		square.setCellSymbol(2,3,0);
		square.setCellSymbol(2,4,1);
		
		square.setCellSymbol(3,0,3);
		square.setCellSymbol(3,1,4);
		square.setCellSymbol(3,2,0);
		square.setCellSymbol(3,3,1);
		square.setCellSymbol(3,4,2);
		
		square.setCellSymbol(4,0,4);
		square.setCellSymbol(4,1,0);
		square.setCellSymbol(4,2,1);
		square.setCellSymbol(4,3,2);
		square.setCellSymbol(4,4,3);
		
		long actualTransversalCount = square.getTransversalCount();
		String actualTransversalListStr = square.toStringTransversalsOrderedTriple();

		assertEquals(TARGET_TRANSVERSAL_COUNT, actualTransversalCount);
		assertEquals(TARGET_TRANSVERSAL_LIST_STR.trim(), actualTransversalListStr.trim());
	}
}
