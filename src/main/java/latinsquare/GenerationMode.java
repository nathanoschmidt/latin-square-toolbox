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
 * <h1>GenerationMode</h1>
 * 
 * <p>This enum represents the Latin square generation modes that are currently supported
 * by the Latin Square Generator Tool.</p>
 * 
 * @author Will Unger
 * @author Nathan O. Schmidt
 * @version 1.10
 */
public enum GenerationMode 
{
	SELECTION, 				// generate Latin square data set with selection-based algorithm
	SELECTION_PRELOAD, 		// generate Latin square data set with preloading selection-based algorithm
	SUPERSYMMETRIC, 		// generate a single super-symmetric (or cyclic) Latin square with lifting-and-merging algorithm
	INVALID					// invalid generation mode
};
