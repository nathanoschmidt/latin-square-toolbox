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
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.File;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * <h1>TestLatinSquareFactoryGeneratorSelection</h1>
 * 
 * <p>This class contains the unit tests for generating Latin square data sets with
 * the selection-based algorithm.</p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public class TestLatinSquareFactoryGeneratorSelection 
{
	private final File resourcesDirectory = new File("src/test/resources");
	private final String inputDirectory = resourcesDirectory.getAbsolutePath() + "/data/target/squares/data_set/";
	private final String outputDirectory = resourcesDirectory.getAbsolutePath() + "/data/actual/";
	
	/**
	 * Unit test for an order-1 Latin square data set.
	 */
	@Test
	public void testGeneratorSelectionOrder1SizeAll() 
	{
		assertEquals(true, testGeneratorSelection(1, 0));
	}
	
	/**
	 * Unit test for an order-2 Latin square data set.
	 */
	@Test
	public void testGeneratorSelectionOrder2SizeAll() 
	{
		assertEquals(true, testGeneratorSelection(2, 0));
	}
	
	/**
	 * Unit test for an order-3 Latin square data set.
	 */
	@Test
	public void testGeneratorSelectionOrder3SizeAll() 
	{
		assertEquals(true, testGeneratorSelection(3, 0));
	}
	
	/**
	 * Unit test for an order-4 Latin square data set.
	 */
	@Test
	public void testGeneratorSelectionOrder4Size10() 
	{
		assertEquals(true, testGeneratorSelection(4, 10));
	}
	
	/**
	 * Unit test for an order-5 Latin square data set.
	 */
	@Test
	public void testGeneratorSelectionOrder5Size10() 
	{
		assertEquals(true, testGeneratorSelection(5, 10));
	}
	
	/**
	 * Unit test for an order-6 Latin square data set.
	 */
	@Test
	public void testGeneratorSelectionOrder6Size10() 
	{
		assertEquals(true, testGeneratorSelection(6, 10));
	}
	
	/**
	 * Unit test for an order-7 Latin square data set.
	 */
	@Test
	public void testGeneratorSelectionOrder7Size10() 
	{
		assertEquals(true, testGeneratorSelection(7, 10));
	}
	
	/**
	 * Unit test for an order-8 Latin square data set.
	 */
	@Test
	public void testGeneratorSelectionOrder8Size10() 
	{
		assertEquals(true, testGeneratorSelection(8, 10));
	}
	
	/**
	 * Unit test for an order-9 Latin square data set.
	 */
	@Test
	public void testGeneratorSelectionOrder9Size10() 
	{
		assertEquals(true, testGeneratorSelection(9, 10));
	}
	
	/**
	 * Unit test method for order-n Latin square data sets (in ordered-triple format).
	 * 
	 * @param testOrder The Latin square order.
	 * @param testDataSetSize The size of the data set.
	 * @return boolean Test result.
	 */
	private boolean testGeneratorSelection(int testOrder, int testDataSetSize)
	{
		String testOrderStr = Integer.toString(testOrder);
		String testDataSetSizeStr = Integer.toString(testDataSetSize);
		final OutputStream originalOut = System.out;
		
		// generate latin square data set
		String [] args = new String[]{ "-m", "ds", "-n", testOrderStr, "-s", testDataSetSizeStr };
		
		try
		{
			
			System.setOut(new PrintStream(outputDirectory + "n0" + testOrderStr + "_s" + testDataSetSizeStr + ".txt"));
			LatinSquareToolGenerator.main(args);
			System.setOut(new PrintStream(originalOut));
		}
		catch(Exception e)
		{
			System.setOut(new PrintStream(originalOut));
			System.out.println(e.getMessage());
			return false;
		}
		
		File fileTargetData = new File(inputDirectory + "n0" + testOrderStr + "_s" + testDataSetSizeStr + ".txt");
		File fileActualData = new File(outputDirectory + "n0" + testOrderStr + "_s" + testDataSetSizeStr + ".txt");
		
		if(fileTargetData.exists() && fileTargetData.isFile() && fileActualData.exists() && fileActualData.isFile())
		{
			try
			{
				Scanner fileTargetScanner = new Scanner(fileTargetData);
				Scanner fileActualScanner = new Scanner(fileActualData);
				
				while(fileTargetScanner.hasNext() && fileActualScanner.hasNext())
				{
					String targetLine = fileTargetScanner.next();
					String actualLine = fileActualScanner.next();
					
					if(!targetLine.equals(actualLine)) { return false; }
				}
				
				fileTargetScanner.close();
				fileActualScanner.close();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				return false;
			}
		}
		else
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * Unit test for an order-1 Latin square data set (in human-readable format).
	 */
	@Test
	public void testGeneratorSelectionOrder1SizeAllHumanReadable() 
	{
		assertEquals(true, testGeneratorSelectionHumanReadable(1, 0));
	}
	
	/**
	 * Unit test for an order-2 Latin square data set (in human-readable format).
	 */
	@Test
	public void testGeneratorSelectionOrder2SizeAllHumanReadable() 
	{
		assertEquals(true, testGeneratorSelectionHumanReadable(2, 0));
	}
	
	/**
	 * Unit test for an order-3 Latin square data set (in human-readable format).
	 */
	@Test
	public void testGeneratorSelectionOrder3Size5HumanReadable() 
	{
		assertEquals(true, testGeneratorSelectionHumanReadable(3, 5));
	}
	
	/**
	 * Unit test for an order-4 Latin square data set (in human-readable format).
	 */
	@Test
	public void testGeneratorSelectionOrder4Size5HumanReadable() 
	{
		assertEquals(true, testGeneratorSelectionHumanReadable(4, 5));
	}
	
	/**
	 * Unit test for an order-5 Latin square data set (in human-readable format).
	 */
	@Test
	public void testGeneratorSelectionOrder5Size5HumanReadable() 
	{
		assertEquals(true, testGeneratorSelectionHumanReadable(5, 5));
	}
	
	/**
	 * Unit test for an order-6 Latin square data set (in human-readable format).
	 */
	@Test
	public void testGeneratorSelectionOrder6Size5HumanReadable() 
	{
		assertEquals(true, testGeneratorSelectionHumanReadable(6, 5));
	}
	
	/**
	 * Unit test for an order-7 Latin square data set (in human-readable format).
	 */
	@Test
	public void testGeneratorSelectionOrder7Size5HumanReadable() 
	{
		assertEquals(true, testGeneratorSelectionHumanReadable(7, 5));
	}
	
	/**
	 * Unit test for an order-8 Latin square data set (in human-readable format).
	 */
	@Test
	public void testGeneratorSelectionOrder8Size5HumanReadable() 
	{
		assertEquals(true, testGeneratorSelectionHumanReadable(8, 5));
	}
	
	/**
	 * Unit test for an order-9 Latin square data set (in human-readable format).
	 */
	@Test
	public void testGeneratorSelectionOrder9Size5HumanReadable() 
	{
		assertEquals(true, testGeneratorSelectionHumanReadable(9, 5));
	}
	
	/**
	 * Unit test method for order-n Latin square data sets (in human-readable format).
	 * 
	 * @param testOrder The Latin square order.
	 * @param testDataSetSize The size of the data set.
	 * @return boolean Test result.
	 */
	private boolean testGeneratorSelectionHumanReadable(int testOrder, int testDataSetSize)
	{
		String testOrderStr = Integer.toString(testOrder);
		String testDataSetSizeStr = Integer.toString(testDataSetSize);
		final OutputStream originalOut = System.out;
		
		// generate latin square data set
		String [] args = new String[]{ "-m", "ds", "-n", testOrderStr, "-s", testDataSetSizeStr, "-r" };
		
		try
		{
			
			System.setOut(new PrintStream(outputDirectory + "n0" + testOrderStr + "_s" + testDataSetSizeStr + "_human.txt"));
			LatinSquareToolGenerator.main(args);
			System.setOut(new PrintStream(originalOut));
		}
		catch(Exception e)
		{
			System.setOut(new PrintStream(originalOut));
			System.out.println(e.getMessage());
			return false;
		}
		
		File fileTargetData = new File(inputDirectory + "n0" + testOrderStr + "_s" + testDataSetSizeStr + "_human.txt");
		File fileActualData = new File(outputDirectory + "n0" + testOrderStr + "_s" + testDataSetSizeStr + "_human.txt");
		
		if(fileTargetData.exists() && fileTargetData.isFile() && fileActualData.exists() && fileActualData.isFile())
		{
			try
			{
				Scanner fileTargetScanner = new Scanner(fileTargetData);
				Scanner fileActualScanner = new Scanner(fileActualData);
				
				while(fileTargetScanner.hasNext() && fileActualScanner.hasNext())
				{
					String targetLine = fileTargetScanner.next();
					String actualLine = fileActualScanner.next();
					
					if(!targetLine.equals(actualLine)) { return false; }
				}
				
				fileTargetScanner.close();
				fileActualScanner.close();
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				return false;
			}
		}
		else
		{
			return false;
		}
		
		return true;
	}
}
