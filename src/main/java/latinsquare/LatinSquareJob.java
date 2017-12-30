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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * <h1>LatinSquareJob</h1>
 * 
 * <p>This class represents a user-specified job for processing Latin squares. It keeps track
 * of the user-specified configuration and various statistics for the job. It has the
 * capability of printing a job summary report upon completion of the job.</p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public class LatinSquareJob 
{
	private LatinSquareJobConfig config;
	private long numSquaresProcessed;
	private ArrayList<TransversalCount> transversalCountList;
	private boolean transversalCountListSorted;
	private long propertySatisfiedCount;

	/**
	 * Class constructor that accepts a user-specified job configuration 
	 * for generating a super-symmetric (or cyclic) Latin square.
	 * 
	 * @param config The user-specified job configuration for generating a super-symmetric Latin square.
	 */
	public LatinSquareJob(LatinSquareJobConfig config)
	{
		this.config = config;
		numSquaresProcessed = 0;
		propertySatisfiedCount = 0;
		transversalCountList = new ArrayList<TransversalCount>();
		transversalCountListSorted = true;
	}
	
	/**
	 * Increments the number of Latin squares with a specific transversal count.
	 * Used to keep track of the number of Latin squares that have a specific transversal count
	 * as the job is executing.
	 * 
	 * @param transversalCount The transversal count for a Latin square.
	 */
	public void submitTransversalCount(long transversalCount)
	{
		if(isCountingTransversals() || isPrintingTransversals() || isPrintingHeatMap())
		{
			boolean added = false;
			for(int i = 0; i < transversalCountList.size();i++)
			{
				if(transversalCountList.get(i).getTransversalCount() == transversalCount)
				{
					transversalCountList.get(i).increment();
					added = true;
					transversalCountListSorted = false;
				}
			}
			if(!added) 
			{ 
				transversalCountList.add(new TransversalCount(transversalCount)); 
				transversalCountListSorted = false;
			}
		}
	}
	
	/**
	 * Increments the number of Latin squares that have been processed.
	 */
	public void incrementNumSquaresProcessed() { ++numSquaresProcessed; }
	
	/**
	 * Returns the number of Latin squares that have been processed.
	 * 
	 * @return long The number of processed Latin squares.
	 */
	public long getNumSquaresProcessed() { return numSquaresProcessed; }
	
	/**
	 * Returns the order-n of the Latin square(s) that will be (or have been) processed.
	 * 
	 * @return int The Latin square order.
	 */
	public int getOrder() { return config.getOrder(); }
	
	/**
	 * Returns the number of the Latin squares in the data set that will be generated
	 * (this is zero if all Latin squares of the given order will be generated).
	 * Note: This is typically used by the selection-based generation algorithms.
	 * 
	 * @return long The size of the data set.
	 */
	public long getDataSetSize() { return ((LatinSquareJobConfigGenerator)config).getDataSetSize(); }
	
	/**
	 * Returns the prime base p of the prime power order-p^d super-symmetric Latin square
	 * that will be generated.
	 * Note: This is typically used by the super-symmetric lifting-and-merging generation algorithm.
	 * 
	 * @return int The prime base p of the order-p^d.
	 */
	public int getOrderBase() { return ((LatinSquareJobConfigGenerator)config).getOrderBase(); }
	
	/**
	 * Returns the power d of the prime power order-p^d super-symmetric Latin square
	 * that will be generated.
	 * 
	 * @return int The power d of the order p^d.
	 */
	public int getOrderPower() { return ((LatinSquareJobConfigGenerator)config).getOrderPower(); }
	
	/**
	 * Returns true if the square(s) are being checked for the Latin Square Property.
	 * 
	 * @return boolean The property checking flag.
	 */
	public boolean isPropertyChecking() { return config.isPropertyChecking(); }
	
	/**
	 * Returns true if the Latin square(s) will be printed in the human-readable format. 
	 * If this is not set, then they will be printed in the
	 * ordered-triple format that can be parsed for post-processing (ex. transversal counting).
	 * 
	 * @return boolean The human-readable print format flag.
	 */
	public boolean isPrintingHumanReadable() { return config.isPrintingHumanReadable(); }
	
	/**
	 * Returns true if the number of transversals will be counted for each Latin square.
	 * 
	 * @return boolean The transversal count flag.
	 */
	public boolean isCountingTransversals() { return config.isCountingTransversals(); }
	
	/**
	 * Returns true if the list of all transversals will be printed for each Latin square.
	 * 
	 * @return boolean The transversal print flag.
	 */
	public boolean isPrintingTransversals() { return config.isPrintingTransversals(); }
	
	/**
	 * Returns true if the transversal heat map will be generated and printed for each Latin square.
	 * 
	 * @return boolean The heat map print flag.
	 */
	public boolean isPrintingHeatMap() { return config.isPrintingHeatMap(); }
	
	/**
	 * Returns true if the job report summary will be printed upon completion.
	 * 
	 * @return boolean The job report summary print flag.
	 */
	public boolean isPrintingReport() { return config.isPrintingReport(); }
	
	/**
	 * Returns true if the Latin square generation mode uses the preloading feature.
	 * Note: this applies to the selection-based generation algorithm.
	 * 
	 * @return boolean The preloading generation mode flag.
	 */
	public boolean isPreloading() { return ((LatinSquareJobConfigGenerator)config).isPreloading(); }
	
	/**
	 * Returns true if the quiet flag is set (meaning that the each individual square and its transversal counts,
	 * heat map, etc. will not be printed as the processing occurs).
	 * 
	 * @return boolean The quiet flag.
	 */
	public boolean isBeingQuiet() { return ((LatinSquareJobConfigFromFileTransversalCounter)config).isBeingQuiet(); }
	
	/**
	 * Returns the type of the Latin square job that will be (or has been) executed.
	 * 
	 * @return LatinSquareJobType The Latin square job type.
	 */
	public LatinSquareJobType getJobType() { return config.getJobType(); }
	
	/**
	 * Computes and returns the arithmetic mean/average transversal count for the processed Latin squares.
	 * (Assuming that all Latin square transversals have been counted.)
	 * Returns null of the transversal count list is empty.
	 * 
	 * @return double The mean of the transversal count.
	 */
	public double getTransversalCountMean()
	{
		if((transversalCountList != null) && !transversalCountList.isEmpty())
		{
			long sumTransversalCount = 0;
			long sumSquareCount = 0;
			
			for(int i = 0; i < transversalCountList.size(); i++)
			{
				sumTransversalCount += transversalCountList.get(i).getSquareCount() * 
									   transversalCountList.get(i).getTransversalCount();
				sumSquareCount += transversalCountList.get(i).getSquareCount();
			}
			
			return ((double)sumTransversalCount) / sumSquareCount; 
		}
		
		else { return 0.0; }
	}
	
	/**
	 * Computes and returns the transversal count mode(s) for the processed Latin squares.
	 * (Assuming that all Latin square transversals have been counted.)
	 * Returns null of the transversal count list is empty.
	 * 
	 * @return long [] The mode(s) of the transversal count.
	 */
	public long [] getTransversalCountMode()
	{
		if((transversalCountList != null) && !transversalCountList.isEmpty())
		{
			sortTransversalCountList(); // sort first
	
			// find the largest number of squares with a specific transversal count (max mode value)
			long maxSquareCountWithTransversals = 0;
			for(int i = 0; i < transversalCountList.size(); i++)
			{
				if(maxSquareCountWithTransversals < transversalCountList.get(i).getSquareCount())
				{
					maxSquareCountWithTransversals = transversalCountList.get(i).getSquareCount();
				}
			}
			
			// since there may be multiple modes, then find them all
			ArrayList<Long> modesList = new ArrayList<Long>();
			for(int i = 0; i < transversalCountList.size(); i++)
			{
				if(maxSquareCountWithTransversals == transversalCountList.get(i).getSquareCount())
				{
					modesList.add(transversalCountList.get(i).getTransversalCount());
				}
			}
			long [] modes = new long[modesList.size()];
			for(int i = 0; i < modesList.size(); i++) { modes[i] = modesList.get(i); }
			
			return modes;
		}
		
		else { return null; }
	}
	
	/**
	 * Computes and returns the minimum transversal count for the processed Latin squares.
	 * (Assuming that all Latin square transversals have been counted.)
	 * Returns null of the transversal count list is empty.
	 * 
	 * @return long The minimum transversal count.
	 */
	public long getTransversalCountMin()
	{
		if((transversalCountList != null) && !transversalCountList.isEmpty())
		{
			sortTransversalCountList(); // sort first
			return transversalCountList.get(0).getTransversalCount();
		}
		else { return 0; }
	}
	
	/**
	 * Computes and returns the maximum transversal count for the processed Latin squares.
	 * (Assuming that all Latin square transversals have been counted.)
	 * Returns null of the transversal count list is empty.
	 * 
	 * @return long The maximum transversal count.
	 */
	public long getTransversalCountMax()
	{
		if((transversalCountList != null) && !transversalCountList.isEmpty())
		{
			sortTransversalCountList(); // sort first
			return transversalCountList.get(transversalCountList.size() - 1).getTransversalCount();
		}
		else { return 0; }
	}
	
	/**
	 * Computes and returns the transversal count median for the processed Latin squares.
	 * (Assuming that all Latin square transversals have been counted.)
	 * Returns null of the transversal count list is empty.
	 * 
	 * @return double The median of the transversal count.
	 */
	public double getTransversalCountMedian()
	{
		if((transversalCountList != null) && !transversalCountList.isEmpty())
		{
			sortTransversalCountList(); // short first
			
			// copy counts into single array so we can compute the median
			// note: not sure about this narrowing of long to int...
			long [] sortedTransversalCountListIndividual = new long[(int)numSquaresProcessed];
			for(int i = 0, index = 0; i < transversalCountList.size(); i++)
			{
				for(int j = 0; j < transversalCountList.get(i).getSquareCount(); j++, index++)
				{
					sortedTransversalCountListIndividual[index] = transversalCountList.get(i).getTransversalCount();
				}
			}

			// handle median for short length cases
			if(sortedTransversalCountListIndividual.length == 1) 
			{ 
				return sortedTransversalCountListIndividual[0]; 
			}
			else if (sortedTransversalCountListIndividual.length == 2)
			{
				return (sortedTransversalCountListIndividual[0] + sortedTransversalCountListIndividual[1]) / 2;
			}
			
			// handle median for larger length cases
			else
			{
				if(sortedTransversalCountListIndividual.length % 2 == 1)
				{
					int index = sortedTransversalCountListIndividual.length / 2;
					return (double)sortedTransversalCountListIndividual[index];
				}
				else
				{
					int index = (sortedTransversalCountListIndividual.length / 2) - 1;
					double median = (sortedTransversalCountListIndividual[index] + sortedTransversalCountListIndividual[index + 1]) / 2;
					return median;
				}
			}
		}
		else { return 0.0; }
	}
	
	/**
	 * Increments the counter for the number of squares that satisfied the Latin Square Property.
	 */
	public void incrementPropertySatisfiedCount()
	{
		++propertySatisfiedCount;
	}
	
	/**
	 * Returns the number of squares that satisfied the Latin Square Property.
	 * (Assuming that all squares have been processed.)
	 * 
	 * @return long The number of squares that satisfied the Latin Square Property.
	 */
	public long getPropertySatisfiedCount()
	{
		return propertySatisfiedCount;
	}
	
	/**
	 * Sorts the list of transversal count objects by the transversal count itself.
	 */
	private void sortTransversalCountList()
	{
		if(!transversalCountListSorted)
		{
			Collections.sort(transversalCountList, new TransversalCountComparator());
			transversalCountListSorted = true;
		}
	}
	
	/**
	 * <h1>TransversalCountComparator</h1>
	 * 
	 * This class represents the comparator that is used to sort TransversalCount objects.
	 * 
	 * @author Nathan O. Schmidt
	 * @author Will Unger
	 */
	private class TransversalCountComparator implements Comparator<TransversalCount> 
	{
	    @Override
	    public int compare(TransversalCount a, TransversalCount b) 
	    {
	        return (int)(a.getTransversalCount() - b.getTransversalCount());
	    }
	}

	/**
	 * Prints the final job report summary to standard output.
	 */
	public void printReport()
	{
		System.out.println("****************************************************************");
		System.out.println(CoolTitles.coolTitleline1 + CoolTitles.coolTitleline2);
		System.out.println("****************************************************************");
		System.out.println("********************** Job Summary Report **********************");
		System.out.println("****************************************************************\n");
		
		if(!isPropertyChecking()) { printReportDefault(); }
		else { printReportPropertyChecking(); }
		
		System.out.println("****************************************************************");
	}
	
	/**
	 * Prints the final job report summary to standard output (default).
	 */
	private void printReportDefault()
	{
		// print user-specified job configuration 
		System.out.println("[Configuration]");
		System.out.print("                      Job Type: ");
		switch(config.jobType)
		{
			case GENERATE_DATASET:
				System.out.print("Data Set Generation");
				if(((LatinSquareJobConfigGenerator)config).getGenerationMode() == GenerationMode.SELECTION_PRELOAD) 
				{ 
					System.out.println(" (Preloading)"); 
				}
				else { System.out.println(); }
				break;
			case GENERATE_SUPERSYMMETRIC:
				System.out.println("Super-Symmetric Generation");
				break;
			case PARSE_INPUT_FILE_COUNT_TRANSVERSALS:
				System.out.println("Transversal Counting");
				break;
			default:
				// illegal argument exception should already be printed
				System.exit(1);
		}
		
		System.out.println("            Latin Square Order: " + getOrder());
		System.out.println("     # Latin Squares Processed: " + numSquaresProcessed);
		System.out.println();
		
		System.out.print("Ordered-Triple Format Printing: ");
		if(!isPrintingHumanReadable()) { System.out.println("On"); }
		else { System.out.println("Off"); }
		
		System.out.print("          Transversal Counting: ");
		if(isCountingTransversals() || isPrintingTransversals() || isPrintingHeatMap()) { System.out.println("On"); }
		else { System.out.println("Off"); }
		
		System.out.print("    Transversal Count Printing: ");
		if(isCountingTransversals())    { System.out.println("On"); }
		else { System.out.println("Off"); }
		
		System.out.print("     Transversal List Printing: ");
		if(isPrintingTransversals())    { System.out.println("On"); }
		else { System.out.println("Off"); }
		
		System.out.print(" Transversal Heat Map Printing: ");
		if(isPrintingHeatMap()) { System.out.println("On"); }
		else { System.out.println("Off"); }
		
		System.out.println();
		
		// print observed transversal results
		if((isCountingTransversals() || isPrintingTransversals()) && (transversalCountList.size() > 0))
		{
			System.out.println("[Transversal Count Statistics]");
			System.out.println("                       Minimum: " + getTransversalCountMin());
			System.out.println("                       Maximum: " + getTransversalCountMax());
			System.out.println("                          Mean: " + getTransversalCountMean());
			System.out.println("                        Median: " + getTransversalCountMedian());
			System.out.print("                       Mode(s): ");
			long [] modes = getTransversalCountMode();
			for(int i = 0; i < modes.length; i++)
			{
				System.out.print(modes[i]);
				if(i < (modes.length - 1)) { System.out.print(","); }
			}
			System.out.println("\n");
			
			System.out.println("   Specific Transversal Counts: ");
			for(int i = 0; i < transversalCountList.size(); i++)
			{
				System.out.println("             " + transversalCountList.get(i));
			}
			System.out.println();
		}
	}
	
	/**
	 * Prints the final job report summary to standard output (default).
	 */
	private void printReportPropertyChecking()
	{
		// print user-specified job configuration 
		System.out.println("[Configuration]");
		System.out.print("                      Job Type: ");
		switch(config.jobType)
		{
			case PARSE_INPUT_FILE_PROPERTY_CHECK:
				System.out.println("Latin Square Property Checking");
				break;
			default:
				// illegal argument exception should already be printed
				System.exit(1);
		}
		
		System.out.println("            Latin Square Order: " + getOrder());
		System.out.println("     # Latin Squares Processed: " + numSquaresProcessed);
		System.out.print("Ordered-Triple Format Printing: ");
		if(!isPrintingHumanReadable()) { System.out.println("On"); }
		else { System.out.println("Off"); }
		
		System.out.println();
		
		// print observed property checking results
		System.out.println("[Latin Square Property Verification Results]");
		System.out.println("           # Squares Satisfied: " + getPropertySatisfiedCount());
		System.out.println("       # Squares Not Satisfied: " + (numSquaresProcessed - getPropertySatisfiedCount()));
		System.out.println();
	}
}
