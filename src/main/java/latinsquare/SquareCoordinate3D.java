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
