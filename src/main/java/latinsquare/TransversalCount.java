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
