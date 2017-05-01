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

import java.util.ArrayList;

/**
 * <h1>Square</h1>
 * 
 * <p>This class represents an n-by-n array of symbols (which may or may not
 * be a Latin square depending on whether the Latin Square Property holds). 
 * It stores a 2D array of symbols where each represents a cell of the square. For an 
 * order-n square we assume that the available values are from 0 to 
 * (n - 1) and this order is also stored.</p>
 * 
 * @author Will Unger
 * @author Nathan O. Schmidt
 * @version 1.10
 */
public class Square 
{
	private int[][] cells;
	private int order;
	private long transversalCount;
	private long heatValue;
	private ArrayList<int []> transversals;
	private boolean[] transversalCol;
	private boolean[] transversalSym;
	private int[] transversalForHeat;
	private long[][] heatMap;
	
	/**
	 * Class constructor for an order-n square where the default value
	 * of the symbol inscribed in each cell is zero.
	 * (To make this a Latin square, the symbols have to be inscribed in 
	 * the cells in such a way that the square satisfies the Latin Square 
	 * Property for quasi-groups.)
	 * 
	 * @param order The order of the square.
	 */
	public Square(int order)
	{
		this.order = order;
		cells = new int[this.order][this.order];
		heatMap = new long[this.order][this.order];
		for(int i = 0; i < this.order; i++)
		{
			for(int j = 0; j < this.order; j++) { heatMap[i][j] = cells[i][j] = 0; }
		}
		
		transversalCount = 0;
		heatValue = -1;
		transversals = new ArrayList<int[]>();
		transversalForHeat = new int[order];
		transversalSym = new boolean[order];
		transversalCol = new boolean[order];
	}
	
	/**
	 * Returns the order of the square.
	 * 
	 * @return int The square's order.
	 */
	public int getOrder()
	{
		return order;
	}
	
	/**
	 * Sets the symbol coordinate inscribed in the cell at location (row, col) of the square.
	 * 
	 * @param row The row coordinate for a cell's 2D location in the square.
	 * @param col The column coordinate for a cell's 2D location in the square.
	 * @param sym The symbol coordinate inscribed in the cell at location (row, col) in the square.
	 */
	public void setCellSymbol(int row, int col, int sym)
	{
		cells[row][col] = sym;
	}
	
	/**
	 * Sets the symbol coordinate inscribed in the cell at location of the source cell of the square.
	 * 
	 * @param sourceCell The source cell from which to copy the coordinate.
	 */
	public void setCellSymbol(SquareCoordinate3D sourceCell)
	{
		cells[sourceCell.getRow()][sourceCell.getColumn()] = sourceCell.getSymbol();
	}
	
	/**
	 * Sets all of the symbols for a specific destination row of the square.
	 * 
	 * @param destRowIndex The destination row index.
	 * @param sourceRowSymbols The source row symbols.
	 */
	public void setRowSymbols(int destRowIndex, ArrayList<Integer> sourceRowSymbols)
	{
		for (int i = 0; i < order; i++) { cells[destRowIndex][i] = sourceRowSymbols.get(i); }
	}
	
	/**
	 * Returns the symbol coordinate inscribed in the cell at location (row, col) of the square.
	 * 
	 * @param row The row coordinate for a cell's 2D location in the square.
	 * @param col The column coordinate for a cell's 2D location in the square.
	 * @return int The symbol coordinate inscribed in the cell at location (row, col) in the square.
	 */
	public int getCellSymbol(int row, int col)
	{
		return cells[row][col];
	}
	
	/**
	 * Returns the symbol coordinate inscribed in the cell at location (row, col) of the square.
	 * 
	 * @param sourceCell A cell coordinate in the square.
	 * @return int The symbol coordinate inscribed in the cell at location (row, col) in the square.
	 */
	public int getCellSymbol(SquareCoordinate2D sourceCell)
	{
		return cells[sourceCell.getRow()][sourceCell.getColumn()];
	}
	
	/**
	 * Returns true if the squares are equal (meaning that they have the same order and each of
	 * the corresponding cells have equal symbols).
	 * 
	 * @param square The second square to test for equality.
	 * @return boolean Equality flag.
	 */
	public boolean equals(Square square)
	{
		if((square == null) || (order != square.getOrder())) { return false; }
		
		for(int i = 0; i < order; i++)
		{
			for(int j = 0; j < order; j++)
			{
				if(getCellSymbol(i, j) != square.getCellSymbol(i, j)) { return false; }
			}
		}
		return true;
	}
	
	/**
	 * Computes and returns the number of transversals that are present in the square.
	 * 
	 * @return long The transversal count.
	 */
	public long getTransversalCount()
	{
		// reset transversal and heat map values
		transversalCount = 0;
		transversals.clear();
		
		for(int i = 0; i < this.order; i++)
		{
			transversalSym[i] = transversalCol[i] = true;
			for(int j = 0; j < this.order; j++) { heatMap[i][j] = 0; }
		}
		
		// begin recursive counting
		countTransversals(0);
		
		return transversalCount;
	}
	
	/**
	 * Counts and stores the number of transversals that are present in the square. Uses a recursive
	 * algorithm that accepts a starting row as input by making recursive calls for each row.
	 * 
	 * @param row The starting row.
	 */
	private void countTransversals(int row)
	{
		for(int i = 0; i < order; i++)
		{
			if(transversalSym[cells[row][i]] && transversalCol[i])
			{
				transversalSym[cells[row][i]] = false;
				transversalCol[i] = false;
				if(row == (order - 1))
				{
					transversalForHeat[row] = i;
					transversalCount++;
					submitTransversal(transversalForHeat);
				}
				else
				{
					transversalForHeat[row] = i;
					countTransversals(row + 1);
				}
				transversalSym[cells[row][i]] = true;
				transversalCol[i] = true;
			}
		}
	}
	
	/**
	 * Updates the list of transversals and their heat map values given a transversal of the square.
	 * 
	 * @param transversal An array that encodes a transversal.
	 */
	private void submitTransversal(int [] transversal)
	{
		for(int i = 0; i < transversal.length; i++) { ++heatMap[i][transversal[i]]; }
		transversals.add(transversal.clone());
	}
	
	/**
	 * Computes and returns the square's heat value that is non-negative if all the heat values are uniform. 
	 * If the heat values are non-uniform, then this value will be -1. 
	 * (Assumes that all transversals have been counted.)
	 * 
	 * @return long The square's heat value.
	 */
	public long getHeatValue()
	{
		if((heatMap == null) || (heatMap.length == 0) || (heatMap[0].length == 0)) { return (heatValue = -1); }
		
		heatValue = heatMap[0][0];
		for(int i = 0; i < heatMap.length; i++)
		{
			for(int j = 1; j < heatMap[i].length; j++)
			{
				if(heatValue != heatMap[i][j]) { return (heatValue = -1); }
			}
		}
		return heatValue;
	}
	
	/**
	 * Returns an ArrayList of 3D coordinates that represent all the cells in the square.
	 * 
	 * @return ArrayList The list of all the square's cells.
	 */
	public ArrayList<SquareCoordinate3D> outputCells()
	{
		ArrayList<SquareCoordinate3D> list = new ArrayList<SquareCoordinate3D>(order);
		
		for(int i = 0; i < order; i++)
		{
			for(int j = 0; j < order; j++) { list.add(new SquareCoordinate3D(order, i, j, cells[i][j])); }
		}
		
		return list;
	}
	
	/**
	 * Returns true if the square is indeed a Latin square 
	 * (satisfying the Latin Square Property for quasi-groups).
	 * 
	 * @return boolean The Latin Square Property flag.
	 */
	public boolean latinSquarePropertyHolds()
	{
		ArrayList<Integer> observed = new ArrayList<Integer>(order);
		
		// test the square row-by-row
		for(int i = 0; i < order; i++)
		{
			for(int j = 0; j < order; j++)
			{
				// if symbol repeat observed in current row, then not a Latin square
				if(observed.contains(cells[i][j])) { return false; }
				
				// add symbol to keep track
				observed.add(cells[i][j]);
			}
			observed.clear();
		}
		
		// test the square column-by-column
		for(int i = 0; i < order; i++)
		{
			for(int j = 0;j < order; j++)
			{
				// if symbol repeat observed in current column, then not a Latin square
				if(observed.contains(cells[j][i])) { return false; }
				
				// add symbol to keep track
				observed.add(cells[j][i]);
			}
			observed.clear();
		}
		
		// all good
		return true;
	}
	
	/**
	 * Rotates the square clock-wise by 90 degrees.
	 */
	public void rotate()
	{
		int[][] newCells = new int[order][order];
		
		for(int i = 0; i < order; i++)
		{
			for(int j = 0; j < order; j++) { newCells[i][j] = cells[order - j - 1][i]; }
		}
		
		cells = newCells;
	}
	
	/**
	 * Constructs a sub-square that is identical to the current square except that
	 * one row and one column are removed.
	 * 
	 * @param row The row to exclude from the current square.
	 * @param col The column to exclude from the current square.
	 * @return Square A sub-square with one row and one column removed.
	 */
	public Square makeSubSquare(int row, int col)
	{
		Square subSquare = new Square(order - 1);
		
		for(int i = 0; i < order; i++)
		{
			for(int j = 0; j < order; j++)
			{
				if((i != row) && (j != col)) 
				{
					int tempRow = i;
					if(tempRow > row) { tempRow--; }
					
					int tempCol = j;
					if(tempCol > col) { tempCol--; }
					
					subSquare.setCellSymbol(tempRow, tempCol, cells[i][j]);
				}
			}
		}
		
		return subSquare;
	}
		
	/**
	 * Returns the ordered-triple (non-human-readable) representation of the square.
	 * 
	 * @return String The ordered-triple square representation.
	 */
	@Override 
	public String toString()
	{
		StringBuilder buffer = new StringBuilder("");
		
		for(int i = 0; i < order; i ++)
		{
			for(int j = 0; j < order;j++)
			{
				buffer.append("(" + i + "," + j + "," + cells[i][j] + ")");
			}
			buffer.append("\n");
		}
		
		return buffer.toString();		
	}
	
	/**
	 * Returns the human-readable representation of the square.
	 * 
	 * @return String The human-readable square representation.
	 */
	public String toStringHumanReadable()
	{
		StringBuilder buffer = new StringBuilder("");

		for(int i = 0; i < order; i ++)
		{
			for(int j = 0; j < order;j++)
			{
				buffer.append(cells[i][j] + "  ");
				if(cells[i][j] < 10) { buffer.append(" "); }
			}
			buffer.append("\n");
		}
		
		return buffer.toString();
	}
	
	/**
	 * Returns a string representation of the square's heat map in human-readable form. 
	 * (Assuming that all transversals have been counted.)
	 * 
	 * @return String The human-readable transversal heat map representation.
	 */
	public String toStringTransversalHeatMapHumanReadable()
	{
		StringBuilder buffer = new StringBuilder("");
		
		for(int i = 0; i < order; i++)
		{
			for(int j = 0; j < order; j++)
			{
				buffer.append(heatMap[i][j] + "  ");
				if(heatMap[i][j] < 10) { buffer.append(" "); }
			}
			buffer.append("\n");
		}
		
		return buffer.toString();
	}
	
	/**
	 * Returns a string representation of the square's heat map in ordered-triple form. 
	 * (Assuming that all transversals have been counted.)
	 * 
	 * @return String The ordered-triple transversal heat map representation.
	 */
	public String toStringTransversalHeatMapOrderedTriple()
	{
		StringBuilder buffer = new StringBuilder("");
		
		for(int i = 0; i < order; i++)
		{
			for(int j = 0; j < order; j++) { buffer.append("(" + i + "," + j + "," + heatMap[i][j] + ")"); }
			buffer.append("\n");
		}
		
		return buffer.toString();
	}
	
	/**
	 * Returns a string representation of a formula relating the square's (uniform) heat value,
	 * transversal count, and order if the heat value is uniform and positive.
	 * If the square's heat value is non-uniform or negative, then this it returns the empty string.
	 * (Assuming that all transversals have been counted.)
	 * 
	 * @return String The square's transversal formula.
	 */
	public String toStringTransversalFormula()
	{
		StringBuilder buffer = new StringBuilder("");
		
		if(getHeatValue() > 0)
		{
			buffer.append("[!] Positive Uniform Heat Value Detected: " + heatValue + "\n");
			buffer.append("[!] We Have The Transversal Formula: ");
			buffer.append("(Transversal Count) = (Order) x (Uniform Heat Value) = " + transversalCount + " = " + 
						  order + " x " + heatValue);
		}
		
		return buffer.toString();
	}
	
	/**
	 * Returns a string representation of the square's list of transversals in ordered-triple form. 
	 * (Assuming that all transversals have been counted.)
	 * 
	 * @return String The ordered-triple transversal list representation.
	 */
	public String toStringTransversalsOrderedTriple()
	{
		StringBuilder buffer = new StringBuilder("");
		
		for(int i = 0; i < transversals.size(); i++)
		{	
			int [] transversal = transversals.get(i);
			for(int j = 0; j < transversal.length; j++)
			{
				buffer.append("(" + j + "," + transversal[j] + "," + cells[j][transversal[j]] + ")");
				if(j < (transversal.length - 1)) { buffer.append(","); }
			}
			buffer.append("\n");
		}
		
		return buffer.toString();
	}
	
	/**
	 * Returns true if the square's main diagonal contains all zeros.
	 * 
	 * @return boolean The zero state of the main diagonal.
	 */
	public boolean hasZeroMainDiagonal()
	{
		boolean returnVal = true;
		
		for(int i = 0; i < order;i++)
		{
			if(cells[i][i] != 0) { return returnVal = false; }
		}
		
		return returnVal;
	}
	
	/**
	 * Prints out the "Delta Lemma" value for each cell of the square.
	 * Note: this is not currently used in this implementation.
	 */
	public void printDelta()
	{
		int[][] returnArr = new int[order][order];
		
		for(int i = 0; i < order; i++)
		{
			for(int j = 0; j < order; j++)
			{
				returnArr[i][j] = cells[i][j] - i - j;
				while (returnArr[i][j] < 0) { returnArr[i][j] = returnArr[i][j] + order; }
			}
		}
		
		for(int i = 0; i < order; i++)
		{
			for(int j = 0; j < order; j++) { System.out.print(returnArr[i][j] + " "); }
			System.out.println(" ");
		}
		System.out.println(" ");
	}
}
