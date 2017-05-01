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
 * <h1>Square Coordinate 2D</h1>
 * 
 * <p>This class represents a 2D position in a square. It stores data
 * for a row and column; this represents a 2D coordinate. For an order-n 
 * Latin square we assume that the available values are from 0 to (n - 1)
 * and this order is also stored. Note: a square doesn't necessarily need to 
 * satisfy the Latin Square Property.</p>
 * 
 * @author Will Unger
 * @author Nathan O. Schmidt
 * @version 1.10
 */
public class SquareCoordinate2D 
{
	protected int row; 
	protected int col; 
	protected int latinSquareOrder; 
	
	/**
	 * Class constructor for a 2D coordinate that encodes the location of a cell in a square.
	 * 
	 * @param order The order of the square in which the 2D coordinate encodes a location.
	 * @param row The row coordinate for a cell's 2D location in a square.
	 * @param col The column coordinate for a cell's 2D location in a square.
	 */
	SquareCoordinate2D(int latinSquareOrder, int row, int col)
	{
		this.latinSquareOrder = latinSquareOrder;
		this.row = row;
		this.col = col;
	}
	
	/**
	 * Class constructor for a 2D coordinate that encodes the location of a cell in a square.
	 * 
	 * @param sourceCoordinate The 2D coordinate of a cell.
	 */
	SquareCoordinate2D(SquareCoordinate2D sourceCoordinate)
	{
		latinSquareOrder = sourceCoordinate.getOrder();
		row = sourceCoordinate.getRow();
		col = sourceCoordinate.getColumn();
	}
	
	/**
	 * Sets the row coordinate of the cell's 2D location in a square.
	 * 
	 * @param row The row coordinate for the cell.
	 */
	public void setRow(int row)
	{
		this.row = row;
	}
	
	/**
	 * Sets the column coordinate of the cell's 2D location in a square.
	 * 
	 * @param col The column coordinate for the cell.
	 */
	public void setColumn(int col)
	{
		this.col = col;
	}
	
	/**
	 * Returns the row coordinate of the cell's 2D location in a square.
	 * 
	 * @return int The row coordinate for the cell.
	 */
	public int getRow()
	{
		return row;
	}
	
	/**
	 * Returns the column coordinate of the cell's 2D location in a square.
	 * 
	 * @return int The column coordinate for the cell.
	 */
	public int getColumn()
	{
		return col;
	}
	
	/**
	 * Returns the order of the square in which the 2D coordinate encodes a location.
	 * 
	 * @return int The order of the square.
	 */
	public int getOrder()
	{
		return latinSquareOrder;
	}
	
	/**
	 * Advances this 2D cell to the next 2D cell in the square row-by-row by taking 
	 * into account the order and stops when the cell's row equals the order.
	 */
	public void increment()
	{
		if(row == latinSquareOrder) { return; }
		else if(col == (latinSquareOrder - 1))
		{
			row = row + 1;
			col = 0;
		}
		else { col = col + 1; }
	}
	
	/**
	 * Advances the 2D cell to the next 2D in the square with the column equal to 0 by taking 
	 * into account the order and stops when the cell's row equals the order.
	 */
	public void nextRow()
	{
		if(row != latinSquareOrder)
		{
			row = row + 1;
			col = 0;
		}
	}
	
	/**
	 * Returns true if the 2D cell's row equals the order of the square.
	 * 
	 * @return boolean The cell's row-order equality status.
	 */
	public boolean atEnd()
	{
		return (row == latinSquareOrder);
	}

	/** 
	 * Returns the 2D coordinate representation of the cell in the form: (row,column).
	 * 
	 * @return String The 2D coordinate representation of the cell.
	 */
	@Override
	public String toString()
	{
		return "(" + row + "," + col + ")";
	}
}
