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
 * <h1>TestLatinSquareFactoryGeneratorSelectionPreload</h1>
 * 
 * <p>This class contains the unit tests for generating Latin square data sets with
 * the preloading selection-based algorithm.</p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public class TestLatinSquareFactoryGeneratorSelectionPreload
{
	private final File resourcesDirectory = new File("src/test/resources");
	private final String inputDirectory = resourcesDirectory.getAbsolutePath() + "/data/target/squares/data_set_preload/";
	private final String outputDirectory = resourcesDirectory.getAbsolutePath() + "/data/actual/";
	
	/**
	 * Unit test for an order-1 Latin square data set.
	 */
	@Test
	public void testGeneratorSelectionOrder1SizeAll() 
	{
		assertEquals(true, testGeneratorSelectionPreload(1, 0));
	}
	
	/**
	 * Unit test for an order-2 Latin square data set.
	 */
	@Test
	public void testGeneratorSelectionOrder2SizeAll() 
	{
		assertEquals(true, testGeneratorSelectionPreload(2, 0));
	}
	
	/**
	 * Unit test for an order-3 Latin square data set.
	 */
	@Test
	public void testGeneratorSelectionOrder3SizeAll() 
	{
		assertEquals(true, testGeneratorSelectionPreload(3, 0));
	}
	
	/**
	 * Unit test for an order-4 Latin square data set.
	 */
	@Test
	public void testGeneratorSelectionOrder4Size10() 
	{
		assertEquals(true, testGeneratorSelectionPreload(4, 10));
	}
	
	/**
	 * Unit test for an order-5 Latin square data set.
	 */
	@Test
	public void testGeneratorSelectionOrder5Size10() 
	{
		assertEquals(true, testGeneratorSelectionPreload(5, 10));
	}
	
	/**
	 * Unit test for an order-6 Latin square data set.
	 */
	@Test
	public void testGeneratorSelectionOrder6Size10() 
	{
		assertEquals(true, testGeneratorSelectionPreload(6, 10));
	}
	
	/**
	 * Unit test for an order-7 Latin square data set.
	 */
	@Test
	public void testGeneratorSelectionOrder7Size10() 
	{
		assertEquals(true, testGeneratorSelectionPreload(7, 10));
	}
	
	/**
	 * Unit test for an order-8 Latin square data set.
	 */
	@Test
	public void testGeneratorSelectionOrder8Size10() 
	{
		assertEquals(true, testGeneratorSelectionPreload(8, 10));
	}
	
	/**
	 * Unit test for an order-9 Latin square data set.
	 */
	@Test
	public void testGeneratorSelectionOrder9Size10() 
	{
		assertEquals(true, testGeneratorSelectionPreload(9, 10));
	}
	
	/**
	 * Unit test method for order-n Latin square data sets (in ordered-triple format).
	 * 
	 * @param testOrder The Latin square order.
	 * @param testDataSetSize The size of the data set.
	 * @return boolean Test result.
	 */
	private boolean testGeneratorSelectionPreload(int testOrder, int testDataSetSize)
	{
		String testOrderStr = Integer.toString(testOrder);
		String testDataSetSizeStr = Integer.toString(testDataSetSize);
		final OutputStream originalOut = System.out;
		
		// generate latin square data set
		String [] args = new String[]{ "-m", "dsp", "-n", testOrderStr, "-s", testDataSetSizeStr };
		
		try
		{
			
			System.setOut(new PrintStream(outputDirectory + "n0" + testOrderStr + "_s" + testDataSetSizeStr + "_preload.txt"));
			LatinSquareToolGenerator.main(args);
			System.setOut(new PrintStream(originalOut));
		}
		catch(Exception e)
		{
			System.setOut(new PrintStream(originalOut));
			System.out.println(e.getMessage());
			return false;
		}
		
		File fileTargetData = new File(inputDirectory + "n0" + testOrderStr + "_s" + testDataSetSizeStr + "_preload.txt");
		File fileActualData = new File(outputDirectory + "n0" + testOrderStr + "_s" + testDataSetSizeStr + "_preload.txt");
		
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
	public void testGeneratorSelectionPreloadOrder1SizeAllHumanReadable() 
	{
		assertEquals(true, testGeneratorSelectionPreloadHumanReadable(1, 0));
	}
	
	/**
	 * Unit test for an order-2 Latin square data set (in human-readable format).
	 */
	@Test
	public void testGeneratorSelectionPreloadOrder2SizeAllHumanReadable() 
	{
		assertEquals(true, testGeneratorSelectionPreloadHumanReadable(2, 0));
	}
	
	/**
	 * Unit test for an order-3 Latin square data set (in human-readable format).
	 */
	@Test
	public void testGeneratorSelectionPreloadOrder3Size5HumanReadable() 
	{
		assertEquals(true, testGeneratorSelectionPreloadHumanReadable(3, 5));
	}
	
	/**
	 * Unit test for an order-4 Latin square data set (in human-readable format).
	 */
	@Test
	public void testGeneratorSelectionPreloadOrder4Size5HumanReadable() 
	{
		assertEquals(true, testGeneratorSelectionPreloadHumanReadable(4, 5));
	}
	
	/**
	 * Unit test for an order-5 Latin square data set (in human-readable format).
	 */
	@Test
	public void testGeneratorSelectionPreloadOrder5Size5HumanReadable() 
	{
		assertEquals(true, testGeneratorSelectionPreloadHumanReadable(5, 5));
	}
	
	/**
	 * Unit test for an order-6 Latin square data set (in human-readable format).
	 */
	@Test
	public void testGeneratorSelectionPreloadOrder6Size5HumanReadable() 
	{
		assertEquals(true, testGeneratorSelectionPreloadHumanReadable(6, 5));
	}
	
	/**
	 * Unit test for an order-7 Latin square data set (in human-readable format).
	 */
	@Test
	public void testGeneratorSelectionPreloadOrder7Size5HumanReadable() 
	{
		assertEquals(true, testGeneratorSelectionPreloadHumanReadable(7, 5));
	}
	
	/**
	 * Unit test for an order-8 Latin square data set (in human-readable format).
	 */
	@Test
	public void testGeneratorSelectionPreloadOrder8Size5HumanReadable() 
	{
		assertEquals(true, testGeneratorSelectionPreloadHumanReadable(8, 5));
	}
	
	/**
	 * Unit test for an order-9 Latin square data set (in human-readable format).
	 */
	@Test
	public void testGeneratorSelectionPreloadOrder9Size5HumanReadable() 
	{
		assertEquals(true, testGeneratorSelectionPreloadHumanReadable(9, 5));
	}
	
	/**
	 * Unit test method for order-n Latin square data sets (in human-readable format).
	 * 
	 * @param testOrder The Latin square order.
	 * @param testDataSetSize The size of the data set.
	 * @return boolean Test result.
	 */
	private boolean testGeneratorSelectionPreloadHumanReadable(int testOrder, int testDataSetSize)
	{
		String testOrderStr = Integer.toString(testOrder);
		String testDataSetSizeStr = Integer.toString(testDataSetSize);
		final OutputStream originalOut = System.out;
		
		// generate latin square data set
		String [] args = new String[]{ "-m", "dsp", "-n", testOrderStr, "-s", testDataSetSizeStr, "-r" };
		
		try
		{
			
			System.setOut(new PrintStream(outputDirectory + "n0" + testOrderStr + "_s" + testDataSetSizeStr + "_preload_human.txt"));
			LatinSquareToolGenerator.main(args);
			System.setOut(new PrintStream(originalOut));
		}
		catch(Exception e)
		{
			System.setOut(new PrintStream(originalOut));
			System.out.println(e.getMessage());
			return false;
		}
		
		File fileTargetData = new File(inputDirectory + "n0" + testOrderStr + "_s" + testDataSetSizeStr + "_preload_human.txt");
		File fileActualData = new File(outputDirectory + "n0" + testOrderStr + "_s" + testDataSetSizeStr + "_preload_human.txt");
		
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
