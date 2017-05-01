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
 * <h1>TestLatinSquareFactoryGeneratorSuperSymmetric</h1>
 * 
 * <p>This class contains the unit tests for generating super-symmetric (or cyclic) Latin squares
 * with the lifting-and-merging algorithm.</p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public class TestLatinSquareFactoryGeneratorSuperSymmetric 
{
    private final File resourcesDirectory = new File("src/test/resources");
    private final String inputDirectory = resourcesDirectory.getAbsolutePath() + "/data/target/squares/super_symmetric/";
    private final String outputDirectory = resourcesDirectory.getAbsolutePath() + "/data/actual/";
	
	/**
	 * Unit test for an order-2 cyclic Latin square.
	 */
	@Test
	public void testGeneratorCyclicPrimeOrder2() 
	{
		assertEquals(true, testGeneratorSuperSymmetric(2, 1));
	}
	
	/**
	 * Unit test for an order-3 cyclic Latin square.
	 */
	@Test
	public void testGeneratorCyclicPrimeOrder3() 
	{
		assertEquals(true, testGeneratorSuperSymmetric(3, 1));
	}
	
	/**
	 * Unit test for an order-5 cyclic Latin square.
	 */
	@Test
	public void testGeneratorCyclicPrimeOrder5() 
	{
		assertEquals(true, testGeneratorSuperSymmetric(5, 1));
	}
	
	/**
	 * Unit test for an order-7 cyclic Latin square.
	 */
	@Test
	public void testGeneratorCyclicPrimeOrder7() 
	{
		assertEquals(true, testGeneratorSuperSymmetric(7, 1));
	}
	
	/**
	 * Unit test for an order-2^2 super-symmetric Latin square.
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder2_2() 
	{
		assertEquals(true, testGeneratorSuperSymmetric(2, 2));
	}
	
	/**
	 * Unit test for an order-2^3 super-symmetric Latin square.
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder2_3() 
	{
		assertEquals(true, testGeneratorSuperSymmetric(2, 3));
	}
	
	/**
	 * Unit test for an order-2^4 super-symmetric Latin square.
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder2_4() 
	{
		assertEquals(true, testGeneratorSuperSymmetric(2, 4));
	}

	/**
	 * Unit test for an order-2^5 super-symmetric Latin square.
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder2_5() 
	{
		assertEquals(true, testGeneratorSuperSymmetric(2, 5));
	}
	
	/**
	 * Unit test for an order-2^6 super-symmetric Latin square.
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder2_6() 
	{
		assertEquals(true, testGeneratorSuperSymmetric(2, 6));
	}
	
	/**
	 * Unit test for an order-3^2 super-symmetric Latin square.
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder3_2() 
	{
		assertEquals(true, testGeneratorSuperSymmetric(3, 2));
	}
	
	/**
	 * Unit test for an order-3^3 super-symmetric Latin square.
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder3_3() 
	{
		assertEquals(true, testGeneratorSuperSymmetric(3, 3));
	}
	
	/**
	 * Unit test for an order-3^4 super-symmetric Latin square.
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder3_4() 
	{
		assertEquals(true, testGeneratorSuperSymmetric(3, 4));
	}
	
	/**
	 * Unit test for an order-5^2 super-symmetric Latin square.
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder5_2() 
	{
		assertEquals(true, testGeneratorSuperSymmetric(5, 2));
	}
	
	/**
	 * Unit test for an order-5^3 super-symmetric Latin square.
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder5_3() 
	{
		assertEquals(true, testGeneratorSuperSymmetric(5, 3));
	}
	
	/**
	 * Unit test for an order-7^2 super-symmetric Latin square.
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder7_2() 
	{
		assertEquals(true, testGeneratorSuperSymmetric(7, 2));
	}
	
	/**
	 * Unit test method for order-p^d super-symmetric (or cyclic) Latin squares (in ordered-triple format).
	 * 
	 * @param testOrder The Latin square order.
	 * @param testDataSetSize The size of the data set.
	 * @return boolean Test result.
	 */
	private boolean testGeneratorSuperSymmetric(int orderBase, int orderPower)
	{
		String testOrderBaseStr = Integer.toString(orderBase);
		String testOrderPowerStr = Integer.toString(orderPower);
		final OutputStream originalOut = System.out;
		
		// generate latin square data set
		String [] args = new String[]{ "-m", "ss", "-p", testOrderBaseStr, "-d", testOrderPowerStr };
		try
		{
			System.setOut(new PrintStream(outputDirectory + "p" + testOrderBaseStr + "_d" + testOrderPowerStr + "_supersym.txt"));
			LatinSquareToolGenerator.main(args);
			System.setOut(new PrintStream(originalOut));
		}
		catch(Exception e)
		{
			System.setOut(new PrintStream(originalOut));
			System.out.println(e.getMessage());
			return false;
		}
		
		File fileTargetData = new File(inputDirectory + "p" + testOrderBaseStr + "_d" + testOrderPowerStr + "_supersym.txt");
		File fileActualData = new File(outputDirectory + "p" + testOrderBaseStr + "_d" + testOrderPowerStr + "_supersym.txt");
		
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
	 * Unit test for an order-2 cyclic Latin square (human-readable format).
	 */
	@Test
	public void testGeneratorCyclicPrimeOrder2HumanReadable() 
	{
		assertEquals(true, testGeneratorSuperSymmetricHumanReadable(2, 1));
	}
	
	/**
	 * Unit test for an order-3 cyclic Latin square (human-readable format).
	 */
	@Test
	public void testGeneratorCyclicPrimeOrder3HumanReadable() 
	{
		assertEquals(true, testGeneratorSuperSymmetricHumanReadable(3, 1));
	}
	
	/**
	 * Unit test for an order-5 cyclic Latin square (human-readable format).
	 */
	@Test
	public void testGeneratorCyclicPrimeOrder5HumanReadable() 
	{
		assertEquals(true, testGeneratorSuperSymmetricHumanReadable(5, 1));
	}
	
	/**
	 * Unit test for an order-7 cyclic Latin square (human-readable format).
	 */
	@Test
	public void testGeneratorCyclicPrimeOrder7HumanReadable() 
	{
		assertEquals(true, testGeneratorSuperSymmetricHumanReadable(7, 1));
	}
	
	/**
	 * Unit test for an order-2^2 super-symmetric Latin square (human-readable format).
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder2_2HumanReadable() 
	{
		assertEquals(true, testGeneratorSuperSymmetricHumanReadable(2, 2));
	}
	
	/**
	 * Unit test for an order-2^3 super-symmetric Latin square (human-readable format).
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder2_3HumanReadable() 
	{
		assertEquals(true, testGeneratorSuperSymmetricHumanReadable(2, 3));
	}
	
	/**
	 * Unit test for an order-2^4 super-symmetric Latin square (human-readable format).
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder2_4HumanReadable() 
	{
		assertEquals(true, testGeneratorSuperSymmetricHumanReadable(2, 4));
	}
	
	/**
	 * Unit test for an order-2^5 super-symmetric Latin square (human-readable format).
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder2_5HumanReadable() 
	{
		assertEquals(true, testGeneratorSuperSymmetricHumanReadable(2, 5));
	}
	
	/**
	 * Unit test for an order-2^6 super-symmetric Latin square (human-readable format).
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder2_6HumanReadable() 
	{
		assertEquals(true, testGeneratorSuperSymmetricHumanReadable(2, 6));
	}
	
	/**
	 * Unit test for an order-3^2 super-symmetric Latin square (human-readable format).
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder3_2HumanReadable() 
	{
		assertEquals(true, testGeneratorSuperSymmetricHumanReadable(3, 2));
	}
	
	/**
	 * Unit test for an order-3^3 super-symmetric Latin square (human-readable format).
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder3_3HumanReadable() 
	{
		assertEquals(true, testGeneratorSuperSymmetricHumanReadable(3, 3));
	}
	
	/**
	 * Unit test for an order-3^4 super-symmetric Latin square (human-readable format).
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder3_4HumanReadable() 
	{
		assertEquals(true, testGeneratorSuperSymmetricHumanReadable(3, 4));
	}
	
	/**
	 * Unit test for an order-5^2 super-symmetric Latin square (human-readable format).
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder5_2HumanReadable() 
	{
		assertEquals(true, testGeneratorSuperSymmetricHumanReadable(5, 2));
	}
	
	/**
	 * Unit test for an order-5^3 super-symmetric Latin square (human-readable format).
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder5_3HumanReadable() 
	{
		assertEquals(true, testGeneratorSuperSymmetricHumanReadable(5, 3));
	}
	
	/**
	 * Unit test for an order-7^2 super-symmetric Latin square (human-readable format).
	 */
	@Test
	public void testGeneratorSuperSymmetricPrimePowerOrder7_2HumanReadable() 
	{
		assertEquals(true, testGeneratorSuperSymmetricHumanReadable(7, 2));
	}
	
	/**
	 * Unit test method for order-p^d super-symmetric (or cyclic) Latin squares (in human-readable format).
	 * 
	 * @param testOrder The Latin square order.
	 * @param testDataSetSize The size of the data set.
	 * @return boolean Test result.
	 */
	private boolean testGeneratorSuperSymmetricHumanReadable(int orderBase, int orderPower)
	{
		String testOrderBaseStr = Integer.toString(orderBase);
		String testOrderPowerStr = Integer.toString(orderPower);
		final OutputStream originalOut = System.out;
		
		// generate latin square data set
		String [] args = new String[]{ "-m", "ss", "-p", testOrderBaseStr, "-d", testOrderPowerStr, "-r" };
		try
		{
			System.setOut(new PrintStream(outputDirectory + "p" + testOrderBaseStr + "_d" + testOrderPowerStr + "_supersym_human.txt"));
			LatinSquareToolGenerator.main(args);
			System.setOut(new PrintStream(originalOut));
		}
		catch(Exception e)
		{
			System.setOut(new PrintStream(originalOut));
			System.out.println(e.getMessage());
			return false;
		}
		
		File fileTargetData = new File(inputDirectory + "p" + testOrderBaseStr + "_d" + testOrderPowerStr + "_supersym_human.txt");
		File fileActualData = new File(outputDirectory + "p" + testOrderBaseStr + "_d" + testOrderPowerStr + "_supersym_human.txt");
		
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
