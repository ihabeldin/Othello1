package uk.ac.wlv.cs5006.othello;


/**
 * This class represents the game GameMatrix.
 * @author ianskenny
 * @since 14/15
 */

public class GameMatrix
{
	/**
	 * The number of rows in the GameMatrix.
	 */
	private int rows;

	/**
	 * The number of columns in the GameMatrix.
	 */
	private int cols;

	/**
	 * The GameMatrix as a 2d array of GameMatrixCell objects.
	 */
	private GameMatrixCell[][] cells;


	/**
	 * Constructor
	 * @param nrows the number of rows on this GameMatrix.
	 * @param ncols the number of columns on this GameMatrix.
	 */
  	public GameMatrix(int nrows, int ncols)
	{
  		rows = nrows;
  		cols = ncols;
  		cells = new GameMatrixCell[rows][cols];
  		clear();
	}

  	/**
  	 * Gets the number of rows on this GameMatrix.
  	 * @return the number of rows on this GameMatrix.
  	 */
  	public int getNumRows()
  	{
  		return rows;
  	}

  	/**
  	 * Gets the number of columns on this GameMatrix.
  	 * @return the number of columns on this GameMatrix.
  	 */
  	public int getNumColumns()
  	{
  		return cols;
  	}


	/**
	 * Clears the GameMatrix and sets all tokens to EMPTY
	 */
	public void clear()
	{
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				cells[i][j] = new GameMatrixCell(i, j, new GamePiece("EMPTY"));
			}
		}
	}

	/**
	 * Sets the given cell to the GamePiece value.
	 * @param row the row number.
	 * @param col the column number.
	 * @param value the GamePiece value.
	 */
	public boolean setGamePieceValue(int row, int col, String value)
	{
		 if (isOnGameMatrix(row, col))
		 {
			 cells[row][col].setValue(value);
			 return true;
		 }

		 return false;
	}

	/**
	 * Gets the GamePiece at the given cell.
	 * @param row the row number.
	 * @param col the column number.
	 * @return the GamePiece at the given cell.
	 */
	public GamePiece getGamePiece(int row, int col)
	{
		 if (isOnGameMatrix(row, col))
		 {
			 return cells[row][col].getGamePiece();
		 }

		 return null;
	}

	/**
	 * Tests if the cell represented by (row, col) is on the GameMatrix.
	 * @param row the row number.
	 * @param col the column number.
	 * @return true if the cell is on the GameMatrix, false otherwise.
	 */
	public boolean isOnGameMatrix(int row, int col)
	{
		 if (row >= 0 && row < rows && col >= 0 && col < cols)
		 {
			 return true;
		 }

		 else return false;
	}

	/**
	 * Checks if the GameMatrix is full.
	 * @return true if the GameMatrix is full, false otherwise (i.e. one or more EMPTY locations).
	 */
	public boolean gameMatrixIsFull()
	{
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				if (getGamePiece(i, j).getValue().equals("EMPTY"))
					return false;
			}
		}

		return true;
	}

	/**
	 * Checks if the GameMatrix contains only one colour.
	 * @return true if the GameMatrix contains a single colour, false otherwise.
	 */
	public boolean gameMatrixHasSingleValue()
	{
	    // find the first token
	    GamePiece checkGamePiece = null;
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                if (!(getGamePiece(i, j).getValue().equals("EMPTY")))
                    checkGamePiece = getGamePiece(i, j);
            }
        }

		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				if (!getGamePiece(i, j).getValue().equals("EMPTY") && (!getGamePiece(i, j).getValue().equals(checkGamePiece.getValue())))
					return false;
			}
		}

		return true;
	}
}
