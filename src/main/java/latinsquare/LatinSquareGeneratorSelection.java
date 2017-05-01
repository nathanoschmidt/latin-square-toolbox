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

package latinsquare;

/**
 * <h1>LatinSquareGeneratorSelection</h1>
 * 
 * <p>This class generates a Latin square data set with a recursive
 * selection-based algorithm.
 * This is used for the Latin Square Generation Tool.
 * </p>
 * 
 * @author Will Unger
 * @author Nathan O. Schmidt
 * @version 1.10
 */
public class LatinSquareGeneratorSelection extends LatinSquareGenerator 
{
	private int order;
	private long dataSetSize;
	private boolean preLoadMode;
	private boolean preLoadCurrent;
	private boolean jobComplete;
	
	/**
	 * Class constructor that accepts a user-specified job configuration and
	 * instantiates a new job for generating a data set in Latin squares.
	 * 
	 * @param config The user-specified job configuration for generating Latin squares.
	 */
	public LatinSquareGeneratorSelection(LatinSquareJobConfig config)
	{
		super(config);
		order = job.getOrder();
		dataSetSize = job.getDataSetSize();
		preLoadMode = preLoadCurrent = job.isPreloading();
		jobComplete = false;
	}
	
	/**
	 * Returns true if the preloading generation algorithm will be used to
	 * generate the Latin square data set.
	 * 
	 * @return boolean The preloading flag.
	 */
	public boolean isPreloading()
	{
		return job.isPreloading();
	}
	
	/**
	 * Returns the size of the Latin square data set to generate.
	 * This is zero if the set of all Latin squares will be generated for the given order.
	 * 
	 * @return long The data set size.
	 */
	public long getDataSetSize()
	{
		return job.getDataSetSize();
	}
	
	/**
	 * Returns the number of Latin squares in the data set that have been generated.
	 * If the go() method has not been already invoked, then this will return zero.
	 * After the data set has been generated with the go() method, then the job is
	 * complete so the value returned by this method should be equal to the value 
	 * returned by the getDataSetSize() method.
	 * 
	 * @return long The Latin square generation count.
	 */
	public long getGeneratedCount()
	{
		return job.getNumSquaresProcessed();
	}
	
	/**
	 * Generates and prints the Latin square data set to standard output by invoking
	 * the generateAndPrintDataSet() method with a recursive selection-based algorithm.
	 */
	@Override
	public void go()
	{
		// begin recursion at the top-left cell of the empty Latin square
		generateAndPrintDataSet(0, 0);
	}
	
	/**
	 * Uses a selection-based algorithm to recursively generate and print the Latin square data set 
	 * to standard output.
	 * 
	 * @param rowIndex The index of the starting row of the next Latin Square to be generated.
	 * @param colIndex The index of the starting column of the next Latin Square to be generated.
	 */
	private void generateAndPrintDataSet(int rowIndex, int colIndex)
	{
		for(int i = 0; (i < order) && !jobComplete; i++)
		{
			// if preloading mode is active, then compute the preloaded index
			if(preLoadMode && preLoadCurrent) { i = (rowIndex + colIndex) % order; }
			
			// the current cell does not yet have a symbol, then add symbol
			if(row[rowIndex][i] && col[colIndex][i])
			{
				// set cell symbol and flag symbol as occupied
				row[rowIndex][i] = col[colIndex][i] = false;
				square.setCellSymbol(rowIndex, colIndex, i);
				
				// if we're at the last cell of the square
				if((rowIndex == colIndex) && (colIndex == (order - 1)))
				{
					// if the Latin Square Property holds, then square is complete
					if(square.latinSquarePropertyHolds())
					{
						// if preloading mode is active for the current square, 
						// then stop recomputing additional preloaded indices
						if(preLoadMode && preLoadCurrent) { preLoadCurrent = false; }
						
						// process the square according to user-specified job
						processSquare(square);
						
						// if we're finished generating the data set
						if((dataSetSize != 0) && (job.getNumSquaresProcessed() == dataSetSize)) 
						{ 
							if(job.isPrintingReport()) { job.printReport(); }
							jobComplete = true;
							break;
						}
					}
				}
				
				// if we're at the end of a row
				else if(colIndex == (order - 1))
				{
					generateAndPrintDataSet(rowIndex + 1, 0);
				}
				
				// if we're not at the end of a row or all done
				else 
				{
					generateAndPrintDataSet(rowIndex, colIndex + 1);
				}
				
				// flag symbol as occupied
				row[rowIndex][i] = col[colIndex][i] = true;
			}
		}
	}
}
