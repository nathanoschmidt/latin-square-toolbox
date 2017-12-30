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
 * <h1>Square Coordinate 3D</h1>
 * 
 * <p>This class represents a cell of a square. It stores data
 * for a row, column, and symbol; this represents a 3D coordinate.
 * For an order-n Latin square we assume that the available values 
 * are from 0 to (n - 1) and this order is also stored.
 * Note: a square doesn't necessarily need to satisfy the Latin Square Property.</p>
 * 
 * @author Will Unger
 * @author Nathan O. Schmidt
 * @version 1.10
 */
public class SquareCoordinate3D extends SquareCoordinate2D
{
	private int sym;
	
	/**
	 * Class constructor for a 3D coordinate that encodes the location of a cell in a square.
	 * 
	 * @param order The order of the square in which the 3D coordinate encodes a location.
	 * @param row The row coordinate for a cell's 3D location in a square.
	 * @param col The column coordinate for a cell's 3D location in a square.
	 */
	public SquareCoordinate3D(int order, int row, int col)
	{
		super(order, row, col);
	}
	
	/**
	 * Class constructor for a 3D coordinate that encodes the location of a cell in a square.
	 * 
	 * @param order The order of the square in which the 3D coordinate encodes a location.
	 * @param row The row coordinate for a cell's 3D location in a square.
	 * @param col The column coordinate for a cell's 3D location in a square.
	 * @param sym The symbol coordinate inscribed in the cell at location (row, col) in a square.
	 */
	public SquareCoordinate3D(int order, int row, int col, int sym)
	{
		super(order, row, col);
		this.sym = sym;
	}
	
	/**
	 * Class constructor for a 3D coordinate that encodes the location of a cell in a square.
	 * 
	 * @param sourceCoordinate The 3D coordinate of a cell.
	 */
	SquareCoordinate3D(SquareCoordinate3D sourceCoordinate)
	{
		super(sourceCoordinate.getOrder(), sourceCoordinate.getRow(), sourceCoordinate.getColumn());
		this.sym = sourceCoordinate.getSymbol();
	}
	
	/**
	 * Sets the symbol coordinate inscribed in the cell at location (row, col) in a square.
	 * 
	 * @param sym The symbol coordinate inscribed in the cell.
	 */
	public void setSymbol(int sym)
	{
		this.sym = sym;
	}
	
	/**
	 * Returns the symbol coordinate inscribed in the cell at location (row, col) in a square.
	 * 
	 * @return int The symbol coordinate inscribed in the cell.
	 */
	public int getSymbol()
	{
		return sym;
	}
	
	/**
	 * Returns the 3D coordinate representation of the cell in the form: (row,column,symbol).
	 * 
	 * @return String The 3D coordinate representation of the cell.
	 */
	@Override 
	public String toString()
	{
		return "(" + row + "," + col + "," + sym + ")";
	}
}
