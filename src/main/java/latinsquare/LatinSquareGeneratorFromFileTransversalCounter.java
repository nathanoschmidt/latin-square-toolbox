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
 * <h1>LatinSquareGeneratorFromFileTransversalCounter</h1>
 * 
 * <p>This class represents a "generator" that reads existing Latin squares 
 * that are encoded in an ordered-triple format input file. Although this class
 * does not actually generate Latin squares, it behaves in a very similar way
 * to those that do generate Latin squares.
 * This is used for the Latin Square Transversal Counting Tool.
 * </p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public class LatinSquareGeneratorFromFileTransversalCounter extends LatinSquareGenerator
{
	private LatinSquareFileParser fileParser;
	
	/**
	 * Class constructor that accepts a user-specified job configuration and
	 * instantiates a new job for reading in Latin squares from an ordered-triple 
	 * format input file. 
	 * Note: This is used for post-processing Latin squares that
	 * have already been generated, such as transversal counting.
	 * 
	 * @param config The user-specified job configuration for counting transversals.
	 */
	public LatinSquareGeneratorFromFileTransversalCounter(LatinSquareJobConfigFromFileTransversalCounter config)
	{
		super(config);
		fileParser = new LatinSquareFileParser(config.getOrder(), config.getInputFile());
	}
	
	/**
	 * Iterates over each Latin squares from the input file, counts the transversals for each,
	 * and then prints the results to standard output.
	 */
	public void go()
	{
		// process each square by counting transversals, etc.
		while(fileParser.hasNext())
		{
			square = fileParser.next();
			
			// process the square according to user-specified job
			processSquare(square);
		}
		
		if(job.isPrintingReport()) { job.printReport(); }
	}
	
	/**
	 * Processes a Latin square by computing the user-specified transversal characteristics.
	 * 
	 * @param square The Latin square to process.
	 */
	@Override
	protected void processSquare(Square square)
	{
		long squareCount = job.getNumSquaresProcessed() + 1;
		
		// count the number of transversals in the square
		long transversalCount = square.getTransversalCount();
		
		// if keeping track of total job stats, then keep track of transversal count
		if(isPrintingReport()) { job.submitTransversalCount(transversalCount); }
		
		// if printing square-by-square stats, then do it
		if(!job.isBeingQuiet())
		{
			System.out.println("Latin Square #" + squareCount + ": ");
			
			// print the user-specified transversal characteristics
			printSquareStuff(square, squareCount, transversalCount);
		}
		
		// Increment # of Latin squares generated
		job.incrementNumSquaresProcessed();
	}
}
