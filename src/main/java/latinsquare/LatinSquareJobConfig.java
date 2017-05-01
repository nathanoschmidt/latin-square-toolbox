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
 * <h1>LatinSquareJobConfig</h1>
 * 
 * <p>This class represents the user-specified configuration for a tool that
 * processes Latin squares; it parses, validates, and stores 
 * the user-specified command line arguments.</p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public abstract class LatinSquareJobConfig 
{
	protected LatinSquareJobType jobType;
	protected int minNumValidArgs;
	protected int maxNumValidArgs;
	protected int order;
	protected boolean validArguments;
	protected boolean countTransversals;
	protected boolean printTransversals;
	protected boolean printHumanReadable;
	protected boolean printTransversalHeatMap;
	protected boolean printReport;
	protected boolean propertyCheck;
	
	/**
	 * Class constructor for a user-specified job configuration 
	 * that processing Latin squares.
	 */
	public LatinSquareJobConfig()
	{
		jobType = LatinSquareJobType.INVALID;
		minNumValidArgs = maxNumValidArgs = order = 0;
		validArguments = true;
		countTransversals = printHumanReadable = printTransversalHeatMap = printReport = propertyCheck = false;
	}
	
	/**
	 * Parses, validates, and stores the required parameters for the user-specified 
	 * job configuration given command line arguments.
	 * 
	 * @param args The user-specified command line arguments.
	 */
	protected abstract void parseRequiredArgs(String [] args);
	
	/**
	 * Parses, validates, and stores the optional parameters for the user-specified 
	 * job configuration given command line arguments.
	 * 
	 * @param args The user-specified command line arguments.
	 */
	protected abstract void parseOptionalArgs(String [] args);
	
	/**
	 * Verifies that the number of user-specified command line arguments
	 * falls within the accepted range.
	 * 
	 * @param args The user-specified command line arguments.
	 */
	protected void checkArgCount(String [] args)
	{
		if((args == null) || (args.length == 0))
		{
			printUsage();
			System.exit(0);
		}
		else if((args.length < minNumValidArgs) || (args.length > maxNumValidArgs))
		{
			System.err.println("[Illegal Arguments] Invalid number of arguments!");
			validArguments = false;
		}
	}
	
	/**
	 * Returns true if the user-specified job configuration parameters are valid.
	 * 
	 * @return boolean The validity of the job configuration parameters.
	 */
	public boolean isValid() { return validArguments; }
	
	/**
	 * Returns the order of the Latin square(s) to process.
	 * 
	 * @return int The Latin square order.
	 */
	public int getOrder() { return order; }
	
	/**
	 * Returns true if the list of all transversals will be printed for each Latin square.
	 * 
	 * @return boolean The transversal print flag.
	 */
	public boolean isPrintingTransversals() { return printTransversals; }
	
	/**
	 * Returns true if the Latin square(s) will be printed in the human-readable format. 
	 * If this is not set, then they will be printed in the
	 * ordered-triple format that can be parsed for post-processing (ex. transversal counting).
	 * 
	 * @return boolean The human-readable print format flag.
	 */
	public boolean isPrintingHumanReadable() { return printHumanReadable; }
	
	/**
	 * Returns true if the transversal heat map will be generated and printed for each Latin square.
	 * 
	 * @return boolean The heat map print flag.
	 */
	public boolean isPrintingHeatMap() { return printTransversalHeatMap; }
	
	/**
	 * Returns true if the job report summary will be printed upon completion.
	 * 
	 * @return boolean The job report summary print flag.
	 */
	public boolean isPrintingReport() { return printReport; }
	
	/**
	 * Returns true if the number of transversals will be counted for each Latin square.
	 * 
	 * @return boolean The transversal count flag.
	 */
	public boolean isCountingTransversals() { return countTransversals; }
	
	/**
	 * Returns true if the square(s) are being checked for the Latin Square Property.
	 * 
	 * @return boolean The property checking flag.
	 */
	public boolean isPropertyChecking() { return propertyCheck; }
	
	/**
	 * Returns the type of the Latin square job that will be executed.
	 * 
	 * @return LatinSquareJobType The Latin square job type.
	 */
	public LatinSquareJobType getJobType() { return jobType; }
	
	/**
	 * Prints the command-line usage for a Latin square tool's main method.
	 */
	public abstract void printUsage();
}
