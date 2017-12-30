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

package latinsquare;

/**
 * <h1>LatinSquareGenerator</h1>
 * 
 * <p>This abstract class represents a generator that either constructs new Latin squares
 * or "generates"/reads existing Latin squares that are encoded in an ordered-triple format input file.
 * This represents the base class for various generation algorithms and also the input file parsing
 * algorithm for post-processing (ex. transversal counting).
 * </p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public abstract class LatinSquareGenerator 
{
	protected LatinSquareJob job;
	protected boolean[][] row;
	protected boolean[][] col;
	protected Square square;
	
	/**
	 * 
	 * Class constructor that accepts a user-specified job configuration and
	 * instantiates a new job.
	 * 
	 * @param config The user-specified job configuration.
	 */
	public LatinSquareGenerator(LatinSquareJobConfig config)
	{
		job = new LatinSquareJob(config);
		initializeSquareTemplate();
	}

	/**
	 * Initializes the generator's attributes based on the user-specified job configuration.
	 */
	protected void initializeSquareTemplate()
	{
		int order = job.getOrder();
		row = new boolean[order][order];
		col = new boolean[order][order];
		square = new Square(order);
		
		for(int i = 0; i < order; i++)
		{
			for(int j = 0; j < order; j++) { row[i][j] = col[i][j] = true; }
		}
	}
	
	/**
	 * Returns the order of the Latin square(s) in the data set.
	 * 
	 * @return int The order of the Latin square(s).
	 */
	public int getOrder() { return job.getOrder(); }
	
	/**
	 * Returns true if the Latin square(s) will be printed in the human-readable format. 
	 * If this is not set, then they will be printed in the
	 * ordered-triple format that can be parsed for post-processing (ex. transversal counting).
	 * 
	 * @return boolean The human-readable print format flag.
	 */
	public boolean isPrintingHumanReadable() { return job.isPrintingHumanReadable(); }
	
	/**
	 * Returns true if the number of transversals will be counted for each Latin square.
	 * 
	 * @return boolean The transversal count flag.
	 */
	public boolean isCountingTransversals() { return job.isCountingTransversals(); }
	
	/**
	 * Returns true if the list of all transversals will be printed for each Latin square.
	 * 
	 * @return boolean The transversal print flag.
	 */
	public boolean isPrintingTransversals() { return job.isPrintingTransversals(); }
	
	/**
	 * Returns true if the transversal heat map will be generated and printed for each Latin square.
	 * 
	 * @return boolean The heat map print flag.
	 */
	public boolean isPrintingHeatMap() { return job.isPrintingHeatMap(); }
	
	/**
	 * Returns true if the job report summary will be printed upon completion.
	 * 
	 * @return boolean The job report summary print flag.
	 */
	public boolean isPrintingReport() { return job.isPrintingReport(); }
	
	/**
	 * Processes a Latin square by computing the user-specified transversal characteristics.
	 * 
	 * @param square The Latin square to process.
	 */
	protected void processSquare(Square square)
	{
		long squareCount = job.getNumSquaresProcessed() + 1;
		long transversalCount = 0;
		
		// if counting transversals, then do it!
		if(isCountingTransversals() || isPrintingHeatMap() || isPrintingTransversals())
		{
			System.out.println("Latin Square #" + squareCount + ": ");
			transversalCount = square.getTransversalCount(); 
			
			// if keeping track of total job stats, then keep track of transversal count
			if(isPrintingReport()) { job.submitTransversalCount(transversalCount); }
		}

		// print the user-specified transversal characteristics
		printSquareStuff(square, squareCount, transversalCount);
		
		// increment # of Latin squares generated/read
		job.incrementNumSquaresProcessed();
	}
	
	/**
	 * Prints the user-specified transversal characteristics for a Latin square being processed.
	 * 
	 * @param square The current square.
	 * @param squareCount The number of squares that have already been processed.
	 * @param transversalCount The transversal count for the current square.
	 */
	protected void printSquareStuff(Square square, long squareCount, long transversalCount)
	{
		// print square in either ordered-triple or human-readable format
		if(!isPrintingHumanReadable()) { System.out.println(square); }
		else { System.out.println(square.toStringHumanReadable()); }
		
		// if counting transversals
		if(isCountingTransversals()) 
		{ 
			System.out.println("Latin Square #" + squareCount +" Transversal Count: " + transversalCount + "\n"); 
		}
		
		// if printing the list of all transversals
		if(isPrintingTransversals()) 
		{ 
			System.out.println("Latin Square #" + squareCount + " Transversal List: \n" + square.toStringTransversalsOrderedTriple()); 
		}
		
		// if printing the transversal heat map
		if(isPrintingHeatMap()) 
		{ 
			System.out.println("Latin Square #" + squareCount + " Transversal Heat Map: ");
			if(!isPrintingHumanReadable()) { System.out.println(square.toStringTransversalHeatMapOrderedTriple()); }
			else { System.out.println(square.toStringTransversalHeatMapHumanReadable()); }
			
			// if the heat map has a uniform/constant heat value, then print the formula
			String transversalFormulaStr = square.toStringTransversalFormula();
			if(!transversalFormulaStr.equals("")) { System.out.println(transversalFormulaStr + "\n"); }
		}
	}
	
	/**
	 * Generates or reads in a data set of Latin squares according to some algorithm.
	 */
	protected abstract void go();
}
