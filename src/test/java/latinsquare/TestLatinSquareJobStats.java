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
 * <h1>TestLatinSquareJobStats</h1>
 * 
 * <p>This class contains the unit tests for transversal count job statistics.</p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public class TestLatinSquareJobStats 
{
	/**
	 * Unit test for an odd number of transversal counts.
	 */
	@Test
	public void testTransversalCountJobStatsOdd() 
	{
		final long TARGET_MIN = 10;
		final long TARGET_MAX = 100;
		final double TARGET_MEAN = 52.73;
		final double TARGET_MEDIAN = 50.0;
		final long TARGET_MODE = 30;
		final long NUM_TARGET_MODES = 1;
		final int NUM_TRANSVERSAL_COUNTS = 11;
		
		String [] args = { "-m", "ds", "-n", "5", "-s", "0", "-T", "-j" }; // only -T and -j matter here
		LatinSquareJob job = new LatinSquareJob(new LatinSquareJobConfigGenerator(args));
		
		job.submitTransversalCount(30);
		job.submitTransversalCount(20);
		job.submitTransversalCount(40);
		job.submitTransversalCount(60);
		job.submitTransversalCount(50);
		job.submitTransversalCount(10);
		job.submitTransversalCount(70);
		job.submitTransversalCount(100);
		job.submitTransversalCount(80);
		job.submitTransversalCount(90);
		job.submitTransversalCount(30);
		
		for(int i = 0; i < NUM_TRANSVERSAL_COUNTS; i++) { job.incrementNumSquaresProcessed(); }
		
		assertEquals(TARGET_MIN, job.getTransversalCountMin());
		assertEquals(TARGET_MAX, job.getTransversalCountMax());
		assertEquals(TARGET_MEAN, job.getTransversalCountMean(), 0.1);
		assertEquals(TARGET_MEDIAN, job.getTransversalCountMedian(), 0.1);
		assertEquals(TARGET_MODE, job.getTransversalCountMode()[0]);
		assertEquals(NUM_TARGET_MODES, job.getTransversalCountMode().length);
	}
	
	/**
	 * Unit test for an even number of transversal counts.
	 */
	@Test
	public void testTransversalCountJobStatsEven() 
	{
		final long TARGET_MIN = 10;
		final long TARGET_MAX = 90;
		final double TARGET_MEAN = 48.0;
		final double TARGET_MEDIAN = 45.0;
		final long TARGET_MODE = 30;
		final long NUM_TARGET_MODES = 1;
		final int NUM_TRANSVERSAL_COUNTS = 10;
		
		String [] args = { "-m", "ds", "-n", "5", "-s", "0", "-T", "-j" }; // only -T and -j matter here
		LatinSquareJob job = new LatinSquareJob(new LatinSquareJobConfigGenerator(args));
		
		job.submitTransversalCount(10);
		job.submitTransversalCount(20);
		job.submitTransversalCount(30);
		job.submitTransversalCount(30);
		job.submitTransversalCount(40);
		job.submitTransversalCount(50);
		job.submitTransversalCount(60);
		job.submitTransversalCount(70);
		job.submitTransversalCount(80);
		job.submitTransversalCount(90);
		
		for(int i = 0; i < NUM_TRANSVERSAL_COUNTS; i++) { job.incrementNumSquaresProcessed(); }
		
		assertEquals(TARGET_MIN, job.getTransversalCountMin());
		assertEquals(TARGET_MAX, job.getTransversalCountMax());
		assertEquals(TARGET_MEAN, job.getTransversalCountMean(), 0.1);
		assertEquals(TARGET_MEDIAN, job.getTransversalCountMedian(), 0.1);
		assertEquals(TARGET_MODE, job.getTransversalCountMode()[0]);
		assertEquals(NUM_TARGET_MODES, job.getTransversalCountMode().length);
	}

	/**
	 * Unit test for a single transversal count.
	 */
	@Test
	public void testTransversalCountJobStatsSingle() 
	{
		final long TARGET_MIN = 30;
		final long TARGET_MAX = 30;
		final double TARGET_MEAN = 30;
		final double TARGET_MEDIAN = 30;
		final long TARGET_MODE = 30;
		final long NUM_TARGET_MODES = 1;
		final int NUM_TRANSVERSAL_COUNTS = 1;
		
		String [] args = { "-m", "ds", "-n", "5", "-s", "0", "-T", "-j" }; // only -T and -j matter here
		LatinSquareJob job = new LatinSquareJob(new LatinSquareJobConfigGenerator(args));
		
		job.submitTransversalCount(30);
		
		for(int i = 0; i < NUM_TRANSVERSAL_COUNTS; i++) { job.incrementNumSquaresProcessed(); }
		
		assertEquals(TARGET_MIN, job.getTransversalCountMin());
		assertEquals(TARGET_MAX, job.getTransversalCountMax());
		assertEquals(TARGET_MEAN, job.getTransversalCountMean(), 0.1);
		assertEquals(TARGET_MEDIAN, job.getTransversalCountMedian(), 0.1);
		assertEquals(TARGET_MODE, job.getTransversalCountMode()[0]);
		assertEquals(NUM_TARGET_MODES, job.getTransversalCountMode().length);
	}
	
	/**
	 * Unit test for transversal counts with multiple modes.
	 */
	@Test
	public void testTransversalCountJobStatsMultiModes() 
	{
		final long TARGET_MIN = 30;
		final long TARGET_MAX = 80;
		final double TARGET_MEAN = 54.6;
		final double TARGET_MEDIAN = 50;
		final long [] TARGET_MODE = { 30, 50, 80 };
		final int NUM_TRANSVERSAL_COUNTS = 13;
		
		String [] args = { "-m", "ds", "-n", "5", "-s", "0", "-T", "-j" }; // only -T and -j matter here
		LatinSquareJob job = new LatinSquareJob(new LatinSquareJobConfigGenerator(args));
		
		job.submitTransversalCount(80);
		job.submitTransversalCount(80);
		job.submitTransversalCount(80);
		job.submitTransversalCount(30);
		job.submitTransversalCount(60);
		job.submitTransversalCount(30);
		job.submitTransversalCount(40);
		job.submitTransversalCount(50);
		job.submitTransversalCount(50);
		job.submitTransversalCount(30);
		job.submitTransversalCount(50);
		job.submitTransversalCount(60);
		job.submitTransversalCount(70);

		for(int i = 0; i < NUM_TRANSVERSAL_COUNTS; i++) { job.incrementNumSquaresProcessed(); }
		
		long [] actualModes = job.getTransversalCountMode();
		
		assertEquals(TARGET_MIN, job.getTransversalCountMin());
		assertEquals(TARGET_MAX, job.getTransversalCountMax());
		assertEquals(TARGET_MEAN, job.getTransversalCountMean(), 0.1);
		assertEquals(TARGET_MEDIAN, job.getTransversalCountMedian(), 0.1);
		assertEquals(TARGET_MODE[0], actualModes[0]);
		assertEquals(TARGET_MODE[1], actualModes[1]);
		assertEquals(TARGET_MODE[2], actualModes[2]);
	}
}
