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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * <h1>LatinSquareFileParser</h1>
 * 
 * <p>This class represents a Latin Square File Parser that that iterates over a set of
 * squares (represented in an n-by-n array of ordered-triples: row, column, symbol)
 * that are stored in a given data set input file. Each square in the input file must have the
 * same order matching the user-specified order, and each square must be
 * represented in the ordered-triple format of the Latin Square Generator Tool, and
 * the file must not contain any other meta-data (ex. transversal counts, heat maps,
 * etc.). Each square in the input file must be separated by a blank line. Moreover,
 * due to speed and efficiency requirements, the Latin Square property is not checked
 * for each square, so squares that do not satisfy this property will still be parsed
 * and processed.
 * (Note: this class will parse and store squares that may not satisfy
 * the Latin Square Property.)</p>
 * 
 * @author Will Unger
 * @author Nathan O. Schmidt
 * @version 1.10
 */
public class LatinSquareFileParser
{
	private final String DELIMITERS = "(), ";
	private File file;
	private Scanner fileScanner;
	private int order;
	private int orderSquared;
	private Square currentSquare;
	private boolean returned;
	
	/**
	 * Class constructor that accepts a user-specified order and data set input file containing
	 * (Latin) square(s) that are stored in the (non-human-readable) ordered-triple format.
	 * 
	 * @param order The order of the square(s) in the input file.
	 * @param inputFileName The input file.
	 */
	public LatinSquareFileParser(int order, String inputFileName)
	{
		this.order = order;
		this.orderSquared = this.order * this.order;
		this.returned = false;
		
		try
		{
			// attempt to open the input file containing Latin squares
			file = new File(inputFileName);
			if(file.exists() && file.isFile())
			{
				// only process a non-empty input file
				if(file.length() > 0)
				{
					// attempt to scan the input file
					try 
					{
						fileScanner = new Scanner(file);
						returned = true;
					} 
					
					// handle input file not found exception
					catch (FileNotFoundException e) 
					{
						System.err.println("[File Not Found] Could not open the Latin square input file \"" 
										   + inputFileName + "\" for scanning!");
						System.exit(1);
					}
				}
				
				// handle empty input file case
				else
				{
					System.err.println("[Empty File] The Latin square input file \"" + inputFileName + "\" is empty!");
					System.exit(1);
				}
			}
			
			// handle input file not found case
			else
			{
				System.err.println("[File Not Found] Could not find the Latin square input file \"" + inputFileName + "\"!");
				System.exit(1);
			}
		}
		
		// handle null input file exception
		catch(NullPointerException e)
		{
			System.err.println("[File Path Null] Could not open Latin square input file with null path or filename!");
			System.exit(1);
		}
	}
	
	/**
	 * Returns true if one or more (Latin) squares remain in the input file and
	 * also parses and loads the next square.
	 * Note: invoking this method more than once will attempt to parse and store
	 * multiple Latin squares, so this also behaves as the next method; this was
	 * done for speed and efficiency purposes.
	 * 
	 * @return boolean The squares remaining flag. 
	 */
	public boolean hasNext()
	{
		if (!returned) { return true; }
		
		else
		{
			// construct a new blank square and keep track of the number 
			// of cells stored for completing the square
			currentSquare = new Square(order);
			int cellsProcessed = 0;
			
			// continue to parse and store the cells in the square
			// until the square is complete
			while(cellsProcessed < orderSquared)
			{
				try
				{
					// when no more squares remain in input file, stop parsing
					if(!fileScanner.hasNext()) 
					{ 
						fileScanner.close();
						return false; 
					}
					
					// parse and store the next square
					else
					{
						// load and parse the next line
						String currentLine = fileScanner.next();
						
						try
						{
							// parse each of n cells on the current line
							int numCellsParsed = 0;
							StringTokenizer st = new StringTokenizer(currentLine, DELIMITERS);
							
							while (st.hasMoreTokens()) 
							{
								currentSquare.setCellSymbol(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 
										 					 Integer.parseInt(st.nextToken()));
								++cellsProcessed;
								++numCellsParsed;
							}
							
							if(numCellsParsed != currentSquare.getOrder()) { throw new ArrayIndexOutOfBoundsException(); }
						}
						
						// handle tokenizer invalid latin square ordered-triple format exception
						catch(NumberFormatException e)
						{
							System.err.println("[Invalid Ordered-Triple Format] The line value \"" + currentLine + "\""
												+ " in the Latin square input file \"" + file + "\""
												+ " contains an invalid row, column, or symbol value! "
												+ "These must be integers from 0 to " + (order - 1) + " for order-" 
												+ order + " Latin squares.");
							System.exit(1);
						}
						
						// handle tokenizer invalid symbol latin square ordered-triple format exception
						catch(ArrayIndexOutOfBoundsException e)
						{
							System.err.println("[Invalid Data Set Order] Each Latin square in the input file " 
									+ "\"" + file + "\" must have the same order " + order + "! "
									+ "The ordered-triple values must each be integers from 0 to " + (order - 1) + " for order-" 
									+ order + " Latin squares.");
							System.exit(1);
						}
						
						// handle tokenizer no such element exception
						catch(NoSuchElementException e)
						{
							System.err.println("[Invalid Ordered-Triple Format] The line value \"" + currentLine + "\""
									+ " in the Latin square input file \"" + file + "\""
									+ " contains an invalid row, column, or symbol value! "
									+ "These must be integers from 0 to " + (order - 1) + " for order-" 
									+ order + " Latin squares.");
							System.exit(1);
						}
					}
				}
				
				// handle scanner illegal state exception
				catch(IllegalStateException e)
				{
					System.err.println("[Illegal Scanner State] While scanning the Latin square input file "
							+ "\"" + file + "\"");
					System.exit(1);
				}
				
				// handle scanner no such element exception
				catch(NoSuchElementException e)
				{
					System.err.println("[No Such Element] While scanning the Latin square input file "
							+ "\"" + file + "\"");
					System.exit(1);
				}
			}
			
			returned = false;
			
			return true;
		}
	}
	
	/**
	 * Returns the next square in the input file. If there are no more squares remaining,
	 * then null is returned.
	 * Note: this actually invokes the hasNext method; this was done for speed and
	 * efficiency purposes.
	 * 
	 * @return Square The next square.
	 */
	public Square next()
	{
		if(!hasNext()) { return null; }
		else
		{
			returned = true;
			return currentSquare;
		}
	}
}
