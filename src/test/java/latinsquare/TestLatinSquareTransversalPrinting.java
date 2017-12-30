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
