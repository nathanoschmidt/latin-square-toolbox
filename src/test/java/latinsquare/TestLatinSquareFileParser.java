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
 * <h1>TestLatinSquareFileParser</h1>
 * 
 * <p>This class contains the unit tests for parsing and loading Latin squares that
 * are encoded and stored in an ordered-triple format input file.</p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public class TestLatinSquareFileParser 
{
    private final File resourcesDirectory = new File("src/test/resources");
	private final String inputDirectory = resourcesDirectory.getAbsolutePath() + "/data/target/squares/data_set/";
	
	private LatinSquareFileParser squareFileParser = null;
	private Square squareA = null;
	private Square squareB = null;
	private int testOrder = 0;
	
	/**
	 * Unit test for order-1 Latin square file parsing.
	 */
	@Test
	public void testSingleSquareFileParsingOrder1() 
	{
		// test order-1 latin square file size 1 parsing
		testOrder = 1;
		squareA = new Square(testOrder);
		squareA.setCellSymbol(0,0,0);
		
		squareFileParser = new LatinSquareFileParser(testOrder, inputDirectory + "n01_s1.txt");
		assertEquals(true, squareFileParser.hasNext());
		squareB = squareFileParser.next();
		assertEquals(true, squareA.equals(squareB));
	}
	
	/**
	 * Unit test for order-2 Latin square file parsing.
	 */
	@Test
	public void testSingleSquareFileParsingOrder2()
	{
		// test order-2 latin square file size 1 parsing
		testOrder = 2;
		squareA = new Square(testOrder);
		squareA.setCellSymbol(0,0,0);
		squareA.setCellSymbol(0,1,1);
		squareA.setCellSymbol(1,0,1);
		squareA.setCellSymbol(1,1,0);
		
		squareFileParser = new LatinSquareFileParser(testOrder, inputDirectory + "n02_s1.txt");
		assertEquals(true, squareFileParser.hasNext());
		squareB = squareFileParser.next();
		assertEquals(true, squareA.equals(squareB));
	}
	
	/**
	 * Unit test for order-3 Latin square file parsing.
	 */
	@Test
	public void testSingleSquareFileParsingOrder3()
	{
		// test order-3 latin square file size 1 parsing
		testOrder = 3;
		squareA = new Square(testOrder);
		squareA.setCellSymbol(0,0,0);
		squareA.setCellSymbol(0,1,1);
		squareA.setCellSymbol(0,2,2);
		squareA.setCellSymbol(1,0,1);
		squareA.setCellSymbol(1,1,2);
		squareA.setCellSymbol(1,2,0);
		squareA.setCellSymbol(2,0,2);
		squareA.setCellSymbol(2,1,0);
		squareA.setCellSymbol(2,2,1);
		
		squareFileParser = new LatinSquareFileParser(testOrder, inputDirectory + "n03_s1.txt");
		assertEquals(true, squareFileParser.hasNext());
		squareB = squareFileParser.next();
		assertEquals(true, squareA.equals(squareB));
	}
	
	/**
	 * Unit test for order-4 Latin square file parsing.
	 */
	@Test
	public void testSingleSquareFileParsingOrder4()
	{
		// test order-4 latin square file size 1 parsing
		testOrder = 4;
		squareA = new Square(testOrder);
		squareA.setCellSymbol(0,0,0);
		squareA.setCellSymbol(0,1,1);
		squareA.setCellSymbol(0,2,2);
		squareA.setCellSymbol(0,3,3);
		squareA.setCellSymbol(1,0,1);
		squareA.setCellSymbol(1,1,0);
		squareA.setCellSymbol(1,2,3);
		squareA.setCellSymbol(1,3,2);
		squareA.setCellSymbol(2,0,2);
		squareA.setCellSymbol(2,1,3);
		squareA.setCellSymbol(2,2,0);
		squareA.setCellSymbol(2,3,1);
		squareA.setCellSymbol(3,0,3);
		squareA.setCellSymbol(3,1,2);
		squareA.setCellSymbol(3,2,1);
		squareA.setCellSymbol(3,3,0);
		
		squareFileParser = new LatinSquareFileParser(testOrder, inputDirectory + "n04_s1.txt");
		assertEquals(true, squareFileParser.hasNext());
		squareB = squareFileParser.next();
		assertEquals(true, squareA.equals(squareB));
	}
	
	/**
	 * Unit test for order-4 Latin square file parsing (multiple squares).
	 */
	@Test
	public void testMultiSquareFileParsingOrder3()
	{
		// test order-3 latin square file size 4 parsing - square 0
		testOrder = 3;
		squareA = new Square(testOrder);
		squareA.setCellSymbol(0,0,0);
		squareA.setCellSymbol(0,1,1);
		squareA.setCellSymbol(0,2,2);
		squareA.setCellSymbol(1,0,1);
		squareA.setCellSymbol(1,1,2);
		squareA.setCellSymbol(1,2,0);
		squareA.setCellSymbol(2,0,2);
		squareA.setCellSymbol(2,1,0);
		squareA.setCellSymbol(2,2,1);
		
		squareFileParser = new LatinSquareFileParser(testOrder, inputDirectory + "n03_s4.txt");
		assertEquals(true, squareFileParser.hasNext());
		squareB = squareFileParser.next();
		assertEquals(true, squareA.equals(squareB));
		
		// test order-3 latin square file size 4 parsing - square 1
		squareA = new Square(testOrder);
		squareA.setCellSymbol(0,0,0);
		squareA.setCellSymbol(0,1,1);
		squareA.setCellSymbol(0,2,2);
		squareA.setCellSymbol(1,0,2);
		squareA.setCellSymbol(1,1,0);
		squareA.setCellSymbol(1,2,1);
		squareA.setCellSymbol(2,0,1);
		squareA.setCellSymbol(2,1,2);
		squareA.setCellSymbol(2,2,0);
		
		assertEquals(true, squareFileParser.hasNext());
		squareB = squareFileParser.next();
		assertEquals(true, squareA.equals(squareB));
		
		// test order-3 latin square file size 4 parsing - square 2
		squareA = new Square(testOrder);
		squareA.setCellSymbol(0,0,0);
		squareA.setCellSymbol(0,1,2);
		squareA.setCellSymbol(0,2,1);
		squareA.setCellSymbol(1,0,1);
		squareA.setCellSymbol(1,1,0);
		squareA.setCellSymbol(1,2,2);
		squareA.setCellSymbol(2,0,2);
		squareA.setCellSymbol(2,1,1);
		squareA.setCellSymbol(2,2,0);
		
		assertEquals(true, squareFileParser.hasNext());
		squareB = squareFileParser.next();
		assertEquals(true, squareA.equals(squareB));
		
		// test order-3 latin square file size 4 parsing - square 3
		squareA = new Square(testOrder);
		squareA.setCellSymbol(0,0,0);
		squareA.setCellSymbol(0,1,2);
		squareA.setCellSymbol(0,2,1);
		squareA.setCellSymbol(1,0,2);
		squareA.setCellSymbol(1,1,1);
		squareA.setCellSymbol(1,2,0);
		squareA.setCellSymbol(2,0,1);
		squareA.setCellSymbol(2,1,0);
		squareA.setCellSymbol(2,2,2);
		
		assertEquals(true, squareFileParser.hasNext());
		squareB = squareFileParser.next();
		assertEquals(true, squareA.equals(squareB));
	}
}
