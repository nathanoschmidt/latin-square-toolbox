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
 * <h1>LatinSquareGeneratorSuperSymmetric</h1>
 * 
 * <p>This class generates a super-symmetric (or cyclic) Latin square of prime power order-p^d
 * with a recursive lifting-and-merging algorithm. If d is greater than 1, then the Latin square will be
 * super-symmetric. Otherwise, if d equals 0, then the Latin square will be cyclic.
 * This is used for the Latin Square Generation Tool.
 * </p>
 * 
 * @author Will Unger
 * @author Nathan O. Schmidt
 * @version 1.10
 */
public class LatinSquareGeneratorSuperSymmetric extends LatinSquareGenerator
{
	private int primeBase;
	private int power;
	
	/**
	 * Class constructor that accepts a user-specified job configuration and
	 * instantiates a new job for generating a super-symmetric (or cyclic) Latin square.
	 * 
	 * @param config The user-specified job configuration for generating a super-symmetric Latin square.
	 */
	public LatinSquareGeneratorSuperSymmetric(LatinSquareJobConfigGenerator config)
	{
		super(config);
		this.primeBase = job.getOrderBase();
		this.power = job.getOrderPower();
	}
	
	/**
	 * Generates and prints the prime power order-p^d super-symmetric Latin square to standard 
	 * output by invoking the generateAndPrint() method with the recursive lifting-and-merging
	 * algorithm.
	 */
	@Override
	public void go()
	{
		generateAndPrint();
		
		if(job.isPrintingReport()) { job.printReport(); }
	}
	
	/**
	 * Uses the lifting-and-merging algorithm to recursively generate and print the 
	 * prime power order-p^d super-symmetric Latin square Latin to standard output.
	 */
	private void generateAndPrint()
	{
		// construct the initial prime order cyclic Latin square
		square = generateBaseSquare(primeBase);
		
		// construct the prime power order-p^d super-symmetric Latin Square
		for(int i = 1; i < power; i++) { square = liftToPrimePowerSquare(square, primeBase); }
		
		// if printing job summary report, then print if the square is cyclic or super-symmetric
		if(job.isPrintingReport())
		{
			// if super-symmetric
			if(power > 1)
			{
				System.out.print("The super-symmetric Latin square of prime power order-");
				System.out.println(primeBase + "^" + power + " is: \n");
			}
			
			// if cyclic
			else 
			{ 
				System.out.println("The cyclic Latin square of prime order-" + primeBase + " is: \n"); 
			}
		}
		
		// process the square according to user-specified job
		processSquare(square);
	}
	
	/**
	 * Generates a prime order-p cyclic Latin square, which is the initial "base square".
	 * 
	 * @param p The prime order-p of the cyclic base square to generate.
	 * @return Square The generated cyclic base square.
	 */
	public Square generateBaseSquare(int p)
	{
		Square cyclicSquare = new Square(p);
		
		for(int i = 0; i < p; i++)
		{
			for(int j = 0; j < p; j++) { cyclicSquare.setCellSymbol(i, j, (i + j) % p); }
		}
		
		return cyclicSquare;
	}
	
	/**
	 * Generates a prime power order-p^(k+1) super-symmetric "lifted Latin square" from
	 * a preceding order-p^k "base Latin square". The symmetry of the given base square is
	 * applied to the new lifted square.
	 * 
	 * @param baseSquare The order-p^k base Latin square.
	 * @param p The prime base of the order of the original base Latin square.
	 * @return Square The order-p^(k+1) syper-symmetric Latin square.
	 */
	public Square liftToPrimePowerSquare(Square baseSquare, int p)
	{
		Square liftedSquare =  new Square(primeBase * baseSquare.getOrder());
		
		for(int i = 0; i < p; i++)
		{
			for(int j = 0; j < p; j++) { mergeBaseSquare(liftedSquare, i, j, baseSquare, (i + j) % p); }
		}
		
		return liftedSquare;
	}
	
	/**
	 * Merges the order-p^k "base Latin square" into the order-p^(k+1) super-symmetric "lifted Latin square",
	 * where the top left cell of the base square will be at the lifted square's cell 
	 * with the coordinate (row * baseSquare.getOrder(), col * baseSquare.getOrder()). 
	 * 
	 * @param liftedSquare The super-symmetric Latin square that will contain the base Latin square as a sub-square.
	 * @param row The row of the super-symmetric Latin square in which to start merging the base Latin square.
	 * @param col The column of the super-symmetric Latin square in which to start merging the base sub-square.
	 * @param baseSquare The base Latin square that will become a sub-square of the super-symmetric Latin square.
	 * @param offsetScalar The scalar used to construct the uniform offset that is added to each symbol.
	 */
	public void mergeBaseSquare(Square liftedSquare, int row, int col, Square baseSquare, int offsetScalar)
	{
		for(int i = (row * baseSquare.getOrder()); i < ((row + 1) * (baseSquare.getOrder())); i++)
		{
			for(int j = (col * baseSquare.getOrder()); j < ((col + 1) * (baseSquare.getOrder())); j++)
			{
				liftedSquare.setCellSymbol(i, j, baseSquare.getCellSymbol(i % baseSquare.getOrder(), j % baseSquare.getOrder()));
				liftedSquare.setCellSymbol(i, j, liftedSquare.getCellSymbol(i, j) + (baseSquare.getOrder() * offsetScalar));
			}
		}
	}
}
