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
