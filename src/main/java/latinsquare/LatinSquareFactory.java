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
 * <h1>LatinSquareFactory</h1>
 * 
 * <p>This class represents a factory that either generates new Latin squares or
 * "generates" and parses existing Latin squares that are stored in an 
 * ordered-triple formatted input file.</p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public class LatinSquareFactory 
{
	private LatinSquareGenerator generator;
	
	/**
	 * Class constructor that accepts a user-specified job configuration and
	 * instantiates the generator that is appropriate for the job.
	 * 
	 * @param config The user-specified configuration.
	 */
	public LatinSquareFactory(LatinSquareJobConfig config)
	{
		// generate a latin square data set via selection-based algorithm (with or without preloading)
		if(config.getJobType() == LatinSquareJobType.GENERATE_DATASET)
		{
			generator = new LatinSquareGeneratorSelection((LatinSquareJobConfigGenerator)config);
		}
		
		// generate a single super-symmetric (or cyclic) latin square via lifting-and-merging algorithm
		else if(config.getJobType() == LatinSquareJobType.GENERATE_SUPERSYMMETRIC)
		{
			generator = new LatinSquareGeneratorSuperSymmetric((LatinSquareJobConfigGenerator)config);
		}
		
		// "generate"/read a latin square data set by reading them from an existing input file for transversal counting
		else if(config.getJobType() == LatinSquareJobType.PARSE_INPUT_FILE_COUNT_TRANSVERSALS)
		{
			generator = new LatinSquareGeneratorFromFileTransversalCounter((LatinSquareJobConfigFromFileTransversalCounter)config);
		}
		
		// "generate"/read a latin square data set by reading them from an existing input file for property checking
		else if(config.getJobType() == LatinSquareJobType.PARSE_INPUT_FILE_PROPERTY_CHECK)
		{
			generator = new LatinSquareGeneratorFromFilePropertyChecker((LatinSquareJobConfigFromFilePropertyChecker)config);
		}
		
		// handle invalid argument, print usage, and terminate
		else
		{
			System.err.println("[Illegal Argument] Invalid Latin square job type!");
			config.printUsage();
			System.exit(1);
		}
	}
	
	/**
	 * Invokes the generator and executes the user-specified job.
	 */
	public void go()
	{
		generator.go();
	}
}
