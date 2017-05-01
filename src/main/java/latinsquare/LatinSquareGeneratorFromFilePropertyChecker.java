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
