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
