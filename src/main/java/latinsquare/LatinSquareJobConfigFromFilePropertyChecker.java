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
 * <h1>LatinSquareJobConfigFromFilePropertyChecker</h1>
 * 
 * <p>This class represents the user-specified configuration the Latin Square Property
 * Checker Tool; it parses, validates, and stores the user-specified command line arguments.</p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public class LatinSquareJobConfigFromFilePropertyChecker extends LatinSquareJobConfig
{
	private final int MIN_NUM_VALID_ARGS = 4;
	private final int MAX_NUM_VALID_ARGS = 6;
	private String inputFile;
	
	/**
	 * Class constructor for a user-specified job configuration 
	 * that checks if a set of square's satisfy the Latin square property.
	 * 
	 * @param args The user-specified command line arguments.
	 */
	public LatinSquareJobConfigFromFilePropertyChecker(String [] args)
	{
		super();
		minNumValidArgs = MIN_NUM_VALID_ARGS;
		maxNumValidArgs = MAX_NUM_VALID_ARGS;
		jobType = LatinSquareJobType.PARSE_INPUT_FILE_PROPERTY_CHECK;
		inputFile = null;
		propertyCheck = true;
		
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
			if(args[i].equals("-r")) { printHumanReadable = true; }
			else if(args[i].equals("-j")) { printReport = true; }
		}
	}
	
	/**
	 * Returns the name of the input file containing the data set of Latin squares stored 
	 * in ordered-triple format.
	 * 
	 * @return String The Latin square data set input filename.
	 */
	public String getInputFile() { return inputFile; }
	
	/**
	 * Prints the command-line usage for the Latin Square Property Checker Tool's
	 * driver class main method. 
	 */
	@Override
	public void printUsage()
	{
		System.out.println("****************************************************************");
		System.out.println("*********** Latin Square Property Checker Tool v1.10 ***********");
		System.out.println("****************************************************************");
		System.out.println("Usage: ./lspc -f <file> -n <order> [optional args]");
		System.out.println("The required arguments are:");
		System.out.println("\t-f <file>  \t# The input file containing a set of order-n squares in ordered-triple format");
		System.out.println("\t-n <order> \t# The square order (a positive integer that must match the input file squares)");
		System.out.println("The optional arguments are:");
		System.out.println("\t-r         \t# Print each Latin square in human-readable (non-ordered-triple) form");
		System.out.println("\t-j         \t# Print the job report summary upon completion");
		System.out.println("****************************************************************");
		System.out.println("Definition: A square of order-n is an n-by-n array over a set of n symbols.");
		System.out.println("Definition: If every symbol in a square appears exactly once in each row and each column, then");
		System.out.println("            the square satisfies the Latin Square Property; such a square is a Latin square.");
		System.out.println("    Remark: The Latin Square Property is the defining property of a quasi-group.");
		System.out.println("    Remark: An order-n Latin square encodes the Cayley table of an order-n quasi-group.");
		System.out.println("    Remark: A Latin square encodes features of a quasi-group.");
		System.out.println("****************************************************************");
	}
}
