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
