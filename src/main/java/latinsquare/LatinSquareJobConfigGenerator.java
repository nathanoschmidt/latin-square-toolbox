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
 * <h1>LatinSquareJobConfigGenerator</h1>
 * 
 * <p>This class represents the user-specified configuration for the Latin Square Generation Tool.</p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public class LatinSquareJobConfigGenerator extends LatinSquareJobConfig
{
	private final int MIN_NUM_VALID_ARGS = 6;
	private final int MAX_NUM_VALID_ARGS = 11;
	private GenerationMode mode; 
	private long dataSetSize;
	private int superSymmetricPrimeBase;
	private int superSymmetricPower;
	
	/**
	 * Class constructor for a user-specified job configuration 
	 * that generates Latin squares.
	 * 
	 * @param args The user-specified command line arguments.
	 */
	public LatinSquareJobConfigGenerator(String [] args)
	{
		super();
		minNumValidArgs = MIN_NUM_VALID_ARGS;
		maxNumValidArgs = MAX_NUM_VALID_ARGS;
		mode = GenerationMode.INVALID; 
		dataSetSize = superSymmetricPrimeBase = superSymmetricPower = 0;
		propertyCheck = false;
		
		if((args != null) && (args.length > 0))
		{
			parseRequiredArgs(args);
			parseOptionalArgs(args);
			
			if(superSymmetricPrimeBase > 0) 
			{ 
				jobType = LatinSquareJobType.GENERATE_SUPERSYMMETRIC; 
				order = (int)Math.pow(superSymmetricPrimeBase, superSymmetricPower);
			}
			else { jobType = LatinSquareJobType.GENERATE_DATASET; }
		}
		else { validArguments = false; }
	}
	
	/**
	 * Parses, validates, and stores the required parameters for the user-specified 
	 * job configuration given command line arguments.
	 * 
	 * @param args The user-specified command line arguments.
	 */
	@Override
	public void parseRequiredArgs(String [] args)
	{
		checkArgCount(args);
		parseGenerationMode(args);
		parseGenerationModeSpecific(args);
	}
	
	/**
	 * Parses, validates, and stores the required user-specified Latin square generation mode parameter.
	 * 
	 * @param args The user-specified command line arguments.
	 */
	private void parseGenerationMode(String [] args)
	{
		for(int i = 0; i < (args.length - 1); i++)
		{
			if(args[i].equals("-m"))
			{
				if(args[i + 1].equals("ds")) { mode = GenerationMode.SELECTION; }
				else if(args[i + 1].equals("dsp")) { mode = GenerationMode.SELECTION_PRELOAD; }
				else if(args[i + 1].equals("ss")) { mode = GenerationMode.SUPERSYMMETRIC; }
				else 
				{
					System.err.println("[Illegal Argument] Invalid generation mode!");
					validArguments = false;
				}
			}
		}
	}
	
	/**
	 * Parses, validates, and stores the required user-specified parameters for
	 * the generation mode.
	 * 
	 * @param args The user-specified command line arguments.
	 */
	private void parseGenerationModeSpecific(String [] args)
	{
		switch(mode)
		{
			case SELECTION:
			case SELECTION_PRELOAD:
				parseOrderAndDataSetSize(args);
				break;
			case SUPERSYMMETRIC:
				parseSuperSymmetricPrimeBaseAndPower(args);
				break;
			default:
				validArguments = false;
				break;
		}
	}
	
	/**
	 * Parses, validates, and stores the required user-specified parameters for the
	 * Latin square order and size for the data set to generate.
	 * This applies to the selection-based generation algorithms.
	 * 
	 * @param args The user-specified command line arguments.
	 */
	private void parseOrderAndDataSetSize(String [] args)
	{
		boolean foundOrder = false, foundSize = false;
		String orderArg = null, sizeArg = null;
		
		for(int i = 0; i < (args.length - 1); i++)
		{
			if(args[i].equals("-n"))
			{
				orderArg = args[i + 1];
				foundOrder = true;
			}
			else if(args[i].equals("-s"))
			{
				sizeArg = args[i + 1];
				foundSize = true;
			}
		}
		
		// parse and store the latin square order
		try
		{
			if(!foundOrder) { throw new NumberFormatException(); }
			
			order = Integer.parseInt(orderArg);
			if(order < 1)
			{
				System.err.println("[Illegal Argument] The value of n must be a positive integer!");
				validArguments = false;
			}
		}
		catch(NumberFormatException nfe)
		{
			System.err.println("[Illegal Argument] Unable to identify the value of n; it must be a positive integer!");
			validArguments = false;
		}
			
		// parse and store the number of latin squares to generate
		try
		{
			if(!foundSize) { throw new NumberFormatException(); }
			
			dataSetSize = Integer.parseInt(sizeArg);
			if(dataSetSize < 0)
			{
				System.err.println("[Illegal Argument] The value of s must be a non-negative integer!");
				printUsage();
				System.exit(1);
			}
		}
		catch(NumberFormatException nfe)
		{
			System.err.println("[Illegal Argument] Unable to identify the value of s; it must be a non-negative integer!");
			validArguments = false;
		}
	}
	
	/**
	 * Determines if a given number is prime.
	 * 
	 * @param p The number to test.
	 * @return boolean The primality state of p.
	 */
	public boolean isPrimeNumber(int p) 
	{ 
		if((p == 2) || (p == 3)) { return true; } 
		if((p == 1) || (p % 2 == 0)) { return false; } 
		
		int sqrt = (int) Math.sqrt(p) + 1; 
		for (int i = 3; i < sqrt; i += 2) 
		{ 
			if (p % i == 0) { return false; } 
		} 
		
		return true; 
	}
	
	/**
	 * Parses, validates, and stores the required user-specified parameters for the
	 * prime base p and power d of the order-p^d super-symmetric (or cyclic) Latin square 
	 * to generate. 
	 * This applies to the lifting-and-merging generation algorithm.
	 * 
	 * @param args The user-specified command line arguments.
	 */
	private void parseSuperSymmetricPrimeBaseAndPower(String [] args)
	{
		// verify the existence of the order's prime base arg and the order's power arg
		boolean foundBase = false, foundPower = false;
		String baseArg = null, powerArg = null;
		
		for(int i = 0; i < (args.length - 1); i++)
		{
			if(args[i].equals("-p"))
			{
				baseArg = args[i + 1];
				foundBase = true;
			}
			else if(args[i].equals("-d"))
			{
				powerArg = args[i + 1];
				foundPower = true;
			}
		}
		
		if(!foundBase || !foundPower)
		{
			System.err.println("[Illegal Arguments] Invalid generation mode arguments!");
			validArguments = false;
		}
		
		// parse and store the prime base for the super-symmetric latin square order
		try
		{
			superSymmetricPrimeBase = Integer.parseInt(baseArg);
			if(superSymmetricPrimeBase < 1)
			{
				System.err.println("[Illegal Argument] The value of p must be a positive prime integer!");
				validArguments = false;
			}
			
			if(!isPrimeNumber(superSymmetricPrimeBase))
			{
				System.out.println("[Warning] The value of p is not prime!");
			}
		}
		catch(NumberFormatException nfe)
		{
			System.err.println("[Illegal Argument] Unable to identify the value of p; it must be a positive prime integer!");
			validArguments = false;
		}
		
		// parse and store the power for the super-symmetric latin square order
		try
		{
			superSymmetricPower = Integer.parseInt(powerArg);
			if(superSymmetricPower < 1)
			{
				System.err.println("[Illegal Argument] The value of d must be a positive integer");
				validArguments = false;
			}
		}
		catch(NumberFormatException nfe)
		{
			System.err.println("[Illegal Argument] Unable to identify the value of d; it must be a positive integer!");
			validArguments = false;
		}
	}
	
	/**
	 * Parses, validates, and stores the optional parameters for the user-specified 
	 * job configuration given command line arguments.
	 * 
	 * @param args The user-specified command line arguments.
	 */
	@Override
	protected void parseOptionalArgs(String [] args)
	{
		for(int i = 0; i < args.length; i++)
		{
			if(args[i].equals("-t")) { countTransversals = true; }
			else if(args[i].equals("-T")) { printTransversals = countTransversals = true; }
			else if(args[i].equals("-r")) { printHumanReadable = true; }
			else if(args[i].equals("-h")) { printTransversalHeatMap = true; }
			else if(args[i].equals("-j")) { printReport = true; }
		}
	}

	/**
	 * Returns the size of the Latin square data set to generate.
	 * This is zero if the set of all Latin squares will be generated for the given order.
	 * 
	 * @return long The data set size.
	 */
	public long getDataSetSize() { return dataSetSize; }
	
	/**
	 * Returns the prime base p of the prime power order-p^d super-symmetric Latin square
	 * that will be generated.
	 * 
	 * @return int The prime base.
	 */
	public int getOrderBase() { return superSymmetricPrimeBase; }
	
	/**
	 * Returns the power d of the prime power order-p^d super-symmetric Latin square
	 * that will be generated.
	 * If this is 1, then a cyclic Latin square will be generated.
	 * 
	 * @return int The power.
	 */
	public int getOrderPower() { return superSymmetricPower; }
	
	/**
	 * Returns the Latin square generation mode for the user-specified job.
	 * 
	 * @return GenerationMode The Latin square generation mode.
	 */
	public GenerationMode getGenerationMode()
	{
		return mode;
	}
	
	/**
	 * Returns true if the preloading generation algorithm will be used to
	 * generate the Latin square data set.
	 * 
	 * @return boolean The preloading flag.
	 */
	public boolean isPreloading() { return (mode == GenerationMode.SELECTION_PRELOAD); }
	
	/**
	 * Prints the command-line usage for the Latin Square Generator Tool's
	 * driver class main method. 
	 */
	@Override
	public void printUsage()
	{
		System.out.println("****************************************************************");
		System.out.println("************** Latin Square Generator Tool v1.10 ***************");
		System.out.println("****************************************************************");
		System.out.println("Usage: ./lsg -m <mode> <mode specific arg 1> <mode specific arg 2> [optional args]");
		System.out.println("Select one of the following generation mode algorithms for \"-m <mode>\":");
		System.out.println("\t-m ds   \t# Generate an order-n Latin square data set of size s");
		System.out.println("\t-m dsp  \t# Generate an order-n Latin square data set of size s with preloading");
		System.out.println("\t-m ss   \t# Generate one order-p^d super-symmetric Latin square");
		System.out.println("The specifically required arguments for the data set generation modes \"-m ds\" and \"-m dsp\" are:");
		System.out.println("\t-n <order> \t# The Latin square order-n (a positive integer)");
		System.out.println("\t-s <size>  \t# The data set size s (a non-negative integer); \"-s 0\" generates all");
		System.out.println("The specifically required arguments for the super-symmetric generation mode \"-m ss\" are:");
		System.out.println("\t-p <base>  \t# The base p of the order-p^d super-symmetric Latin square (a prime integer)");
		System.out.println("\t-d <power> \t# The power d of the order-p^d super-symmetric Latin square (a positive integer)");
		System.out.println("The optional arguments for any mode are:");
		System.out.println("\t-t         \t# Count and print the number of transversals for each Latin square");
		System.out.println("\t-T         \t# Print the transversals for each Latin square (includes \"-t\")");
		System.out.println("\t-h         \t# Print the transversal heat map for each Latin square (also counts transversals)");
		System.out.println("\t-r         \t# Print each Latin square in human-readable (non-ordered-triple) form");
		System.out.println("\t-j         \t# Print the job report summary upon completion");
		System.out.println("****************************************************************");
		System.out.println("Definition: A Latin square of order-n is an n-by-n array over a set of n symbols, where every symbol");
		System.out.println("            appears exactly once in each row and each column.");
		System.out.println("    Remark: An order-n Latin square encodes the Cayley table of an order-n quasi-group.");
		System.out.println("    Remark: The addition Cayley table of a prime order-p finite field is an order-p cyclic Latin square.");
		System.out.println("    Remark: The addition Cayley table of a prime power order-p^d finite field is an order-p^d");
		System.out.println("            super-symmetric Latin square.");
		System.out.println("    Remark: A prime power order-p^d super-symmetric Latin square has a self-similar structure.");
		System.out.println("    Remark: A prime power order-p^d super-symmetric Latin square can be constructed from order-p cyclic");
		System.out.println("            Latin square \"building blocks\".");
		System.out.println("****************************************************************");
	}
}
