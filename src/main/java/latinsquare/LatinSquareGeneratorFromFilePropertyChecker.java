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
 * <h1>LatinSquareGeneratorFromFilePropertyChecker</h1>
 * 
 * <p>This class represents a "generator" that reads existing Latin squares 
 * that are encoded in an ordered-triple format input file. Although this class
 * does not actually generate Latin squares, it behaves in a very similar way
 * to those that do generate Latin squares.
 * This is used for the Latin Square Property Checker Tool.
 * </p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public class LatinSquareGeneratorFromFilePropertyChecker extends LatinSquareGenerator
{
	private LatinSquareFileParser fileParser;
	
	/**
	 * Class constructor that accepts a user-specified job configuration and
	 * instantiates a new job for reading in Latin squares from an ordered-triple 
	 * format input file. 
	 * 
	 * @param config The user-specified job configuration for counting transversals.
	 */
	public LatinSquareGeneratorFromFilePropertyChecker(LatinSquareJobConfigFromFilePropertyChecker config)
	{
		super(config);
		fileParser = new LatinSquareFileParser(config.getOrder(), config.getInputFile());
	}
	
	/**
	 * Iterates over each squares from the input file and determines if the 
	 * Latin Square Property is satisfied.
	 */
	public void go()
	{
		// process each square by checking if the Latin Square Property is satisfied
		while(fileParser.hasNext())
		{
			square = fileParser.next();
			
			// process the square
			this.processSquare(square);
		}
		
		if(job.isPrintingReport()) { job.printReport(); }
	}
	
	/**
	 * Processes a square by determining if the Latin Square Property is satisfied
	 * and prints the result.
	 * 
	 * @param square The square to process.
	 */
	@Override
	protected void processSquare(Square square)
	{
		long squareCount = job.getNumSquaresProcessed() + 1;
		
		System.out.println("Square #" + squareCount + ": ");
			
		// print square in either ordered-triple or human-readable format
		if(!isPrintingHumanReadable()) { System.out.println(square); }
		else { System.out.println(square.toStringHumanReadable()); }
		
		// determine if the Latin Square Property is satisfied and print result
		if(square.latinSquarePropertyHolds())
		{
			System.out.println("Square #" + squareCount + " encodes the Cayley table of a quasi-group!");
			
			// if keeping track of total job stats, then keep track of property satisfied count
			if(isPrintingReport()) { job.incrementPropertySatisfiedCount(); }
			
		}
		else
		{
			System.out.println("Square #" + squareCount + " does NOT encode the Cayley table of a quasi-group");
		}
		System.out.println();
		
		// Increment # of Latin squares generated
		job.incrementNumSquaresProcessed();
	}
}
