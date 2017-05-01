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
 * <h1>TransversalCount</h1>
 * 
 * <p>Keeps track of the number of times that a given transversal count was observed while
 * processing a set of Latin squares.</p>
 * 
 * @author Will Unger
 * @author Nathan O. Schmidt
 * @version 1.10
 */
public class TransversalCount implements Comparable<Object> 
{
	private long transversalCount;
	private long squareCount;
	
	/**
	 * Class constructor.
	 * 
	 * @param transversalCount The representative transversal count.
	 */
	TransversalCount(long transversalCount)
	{
		this.transversalCount = transversalCount;
		squareCount = 1;
	}
	
	/**
	 * Increments the square counter for this transversal count.
	 */
	public void increment()
	{
		squareCount++;
	}

	/**
	 * Returns the value of the square counter for this transversal count.
	 * 
	 * @return long Square counter value.
	 */
	public long getSquareCount()
	{
		return squareCount;
	}
	
	/**
	 * Returns the representative transversal count.
	 * 
	 * @return long The representative transversal count.
	 */
	public long getTransversalCount()
	{
		return transversalCount;
	}
	
	/**
	 * Compares two transversal count objects.
	 * 
	 * @return int Comparison result.
	 */
	@Override
	public int compareTo(Object obj) 
	{
		TransversalCount tempComp = (TransversalCount) obj;
		return (int) (tempComp.getTransversalCount() - this.transversalCount);
	}

	/** 
	 * Returns a string representation of the square counter value for this transversal count.
	 * 
	 * @return String Square counter value representation.
	 */
	@Override
	public String toString()
	{
		return squareCount + " Latin Squares Have " + transversalCount + " Transversals";
	}
}
