package uk.ac.wlv.cs5006.othello;


/**
 * The GameMatrixLocation class. This class represents a single location on the GameMatrix.
 *
 * @author ianskenny.
 * @since 14/15.
 *
 */

public class GameMatrixLocation
{
	/**
	 * The row number of this location.
	 */
	private int row;

	/**
	 * The column number of this location.
	 */
	private int col;

	/**
	 * Default constructor. Initialises the location to an invalid location.
	 */
	public GameMatrixLocation()
	{
		setInvalid();
	}

	/**
	 * Constructor that enables the initialisation of the location.
	 * @param row the row number.
	 * @param col the column number.
	 */
	public GameMatrixLocation(int row, int col)
	{
		this.row = row;
		this.col = col;
	}

	/**
	 * Sets the location to an invalid location.
	 */
	public void setInvalid()
	{
		row = -1;
		col = -1;
	}

	/**
	 * Tests if the location is invalid
	 * @return true if the location is invalid, false otherwise.
	 */
	public boolean isInvalid()
	{
		if (row == -1 || col == -1)
			return true;
		return false;
	}

	/**
	 * Returns the row number for this location.
	 * @return the row number in the range 0:number_of_rows-1.
	 */
	public int getRow()
	{
		return row;
	}

	/**
	 * Returns the column number for this location.
	 * @return the column number in the range 0:number_of_columns-1.
	 */
	public int getCol()
	{
		return col;
	}

	/**
	 * Sets the row number.
	 * @param row the row number.
	 */
	public void setRow(int row)
	{
		this.row = row;
	}

	/**
	 * Sets the column number.
	 * @param col the column number.
	 */
	public void setCol(int col)
	{
		this.col = col;
	}
}
