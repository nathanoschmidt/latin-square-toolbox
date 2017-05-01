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
 * <h1>LatinSquareToolTransversalCounter</h1>
 * 
 * <p>This driver class represents a tool that counts the number of transversals 
 * in one or more Latin squares encoded in the ordered-triple input file format.</p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public class LatinSquareToolTransversalCounter
{
	/**
	 * The main method for the Latin Square Transversal Counter Tool.
	 * 
	 * @param args The user-specified command line arguments.
	 */
	public static void main(String[] args) 
	{
		// parse, validate, and store the user-specified command line parameters for transversal counting job
		LatinSquareJobConfigFromFileTransversalCounter config = new LatinSquareJobConfigFromFileTransversalCounter(args);
		
		if(config.isValid())
		{
			// construct the factory
			LatinSquareFactory factory = new LatinSquareFactory(config);
			
			// count some transversals!
			factory.go();
		}
		
		else { config.printUsage(); }
	}
}