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
 * <h1>LatinSquareJobConfigFromFileTransversalCounter</h1>
 * 
 * <p>This class represents the user-specified configuration the Latin Square Transversal
 * Counter Tool; it parses, validates, and stores the user-specified command line arguments.</p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public class LatinSquareJobConfigFromFileTransversalCounter extends LatinSquareJobConfig
{
	private final int MIN_NUM_VALID_ARGS = 4;
	private final int MAX_NUM_VALID_ARGS = 9;
	private String inputFile;
	private boolean beQuiet;
	
	/**
	 * Class constructor for a user-specified job configuration 
	 * that counts Latin square transversals.
	 * 
	 * @param args The user-specified command line arguments.
	 */
	public LatinSquareJobConfigFromFileTransversalCounter(String [] args)
	{
		super();
		minNumValidArgs = MIN_NUM_VALID_ARGS;
		maxNumValidArgs = MAX_NUM_VALID_ARGS;
		jobType = LatinSquareJobType.PARSE_INPUT_FILE_COUNT_TRANSVERSALS;
		beQuiet = false;
		inputFile = null;
		countTransversals = true;
		propertyCheck = false;
		
		if((args != null) && (args.length > 0))
		{
			parseRequiredArgs(args);
			parseOptionalArgs(args);
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
		parseOrderAndInputFile(args);
	}
	
	/**
	 * Parses, validates, and stores the user-specified Latin square order parameter 
	 * (matching the order of the Latin squares in the input file)
	 * and the name of the input file parameter (consisting of Latin squares in the ordered-triple format). 
	 * 
	 * @param args The user-specified command line arguments.
	 */
	private void parseOrderAndInputFile(String [] args)
	{
		boolean foundOrder = false, foundInputFile = false;
		String orderArg = null;
		
		for(int i = 0; i < (args.length - 1); i++)
		{
			if(args[i].equals("-n"))
			{
				orderArg = args[i + 1];
				foundOrder = true;
			}
			else if(args[i].equals("-f"))
			{
				inputFile = args[i + 1];
				foundInputFile = true;
			}
		}
		
		if(!foundInputFile)
		{
			System.err.println("[Missing Argument] Invalid Latin square input file!");
			validArguments = false;
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
			if(args[i].equals("-q")) { beQuiet = true; }
			else if(args[i].equals("-T")) { printTransversals = true; }
			else if(args[i].equals("-r")) { printHumanReadable = true; }
			else if(args[i].equals("-h")) { printTransversalHeatMap = true; }
			else if(args[i].equals("-j")) { printReport = true; }
		}
	}
	
	/**
	 * Returns true if the quiet flag is set (meaning that the each individual square and 
	 * its transversal characteristics, etc. will not be printed as the job executes).
	 * 
	 * @return boolean The quiet flag.
	 */
	public boolean isBeingQuiet() { return beQuiet; }
	
	/**
	 * Returns the name of the input file containing the data set of Latin squares stored 
	 * in ordered-triple format.
	 * 
	 * @return String The Latin square data set input filename.
	 */
	public String getInputFile() { return inputFile; }
	
	/**
	 * Prints the command-line usage for the Latin Square Transversal Counter Tool's
	 * driver class main method. 
	 */
	@Override
	public void printUsage()
	{
		System.out.println("****************************************************************");
		System.out.println("********* Latin Square Transversal Counter Tool v1.10 **********");
		System.out.println("****************************************************************");
		System.out.println("Usage: ./lstc -f <file> -n <order> [optional args]");
		System.out.println("The required arguments are:");
		System.out.println("\t-f <file>  \t# The input file containing a set of order-n Latin squares in ordered-triple format");
		System.out.println("\t-n <order> \t# The Latin square order (a positive integer that must match the input file squares)");
		System.out.println("The optional arguments are:");
		System.out.println("\t-q         \t# Be quiet! (Don't print anything during the job)");
		System.out.println("\t-T         \t# Print the transversals for each Latin square");
		System.out.println("\t-h         \t# Print the transversal heat map for each Latin square (also counts transversals)");
		System.out.println("\t-r         \t# Print each Latin square in human-readable (non-ordered-triple) form");
		System.out.println("\t-j         \t# Print the job report summary upon completion");
		System.out.println("****************************************************************");
		System.out.println("    Remark: Certain types of quasi-groups, such as finite fields, can be used to construct");
		System.out.println("            cryptographic systems; certain features can impact the security of such systems.");
		System.out.println("Definition: A transversal of a Latin square is a set of entries which includes exactly one entry from");
		System.out.println("            each row and column, and one of each symbol.");
		System.out.println("    Remark: The transversal count is an important feature of a Latin square and its quasi-group.");
		System.out.println("    Remark: Counting the pairs of permutations over a finite field whose point-wise sum is also a");
		System.out.println("            permutation is equivalent to counting the transversals of a Latin square that encodes");
		System.out.println("            the addition group of the finite field.");
		System.out.println("    Remark: To build strong cryptographic systems, it is desirable to use Latin squares with maximum ");
		System.out.println("            transversal counts.");
		System.out.println("    Remark: Prime order-p cyclic Latin squares have confirmed maximum transversal counts up to order-9.");
		System.out.println("Conjecture: Prime order-p cyclic Latin squares may have maximum transversal counts.");
		System.out.println("    Remark: Prime power order-p^d super-symmetric Latin squares have confirmed maximum transversal ");
		System.out.println("            counts up to order-9.");
		System.out.println("Conjecture: Prime power order-p^d super-symmetric Latin squares may have maximum transversal counts.");
		System.out.println("****************************************************************");
	}
}
