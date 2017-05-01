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
