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
 * <h1>LatinSquareToolPropertyChecker</h1>
 * 
 * <p>This driver class represents a tool that determines if a square
 * satisfies the Latin square property.</p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public class LatinSquareToolPropertyChecker
{
	/**
	 * The main method for the Latin Square Property Checker Tool.
	 * 
	 * @param args The user-specified command line arguments.
	 */
	public static void main(String[] args) 
	{
		// parse, validate, and store the user-specified command line parameters for property checking job
		LatinSquareJobConfigFromFilePropertyChecker config = new LatinSquareJobConfigFromFilePropertyChecker(args);
		
		if(config.isValid())
		{
			// construct the factory
			LatinSquareFactory factory = new LatinSquareFactory(config);
			
			// check the property!
			factory.go();
		}
		
		else { config.printUsage(); }
	}
}