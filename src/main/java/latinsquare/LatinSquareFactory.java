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
