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
 * <h1>LatinSquareJobType</h1>
 * 
 * <p>This enum represents the job types for processing Latin squares.</p>
 * 
 * @author Nathan O. Schmidt
 * @author Will Unger
 * @version 1.10
 */
public enum LatinSquareJobType 
{
	GENERATE_DATASET,						// generate Latin square data set with selection-based algorithm
	GENERATE_SUPERSYMMETRIC,				// generate a single super-symmetric (or cyclic) Latin square with lifting-and-merging algorithm
	PARSE_INPUT_FILE_COUNT_TRANSVERSALS,	// count transversals of Latin squares stored in input file
	PARSE_INPUT_FILE_PROPERTY_CHECK,		// check if squares satisfy the Latin square property
	INVALID 								// invalid job type
}
