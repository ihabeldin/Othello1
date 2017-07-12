package uk.ac.wlv.cs5006.othello;


/**
 *
 * @author ianskenny
 * @since 14/15
 */

public abstract class AbstractModel
{
	/**
	 * The GameMatrix of cells representing the board.
	 */
	protected GameMatrix grid;

	/**
	 * The number of rows on the GameMatrix.
	 */
	protected int rows;

	/**
	 * The number of columns on the GameMatrix.
	 */
	protected int cols;

	/**
	 * Constructor.
	 * @param rows number of rows on the GameMatrix.
	 * @param cols number of columns on the GameMatrix.
	 */
	public AbstractModel(int rows, int cols)
	{
		this.rows = rows;
		this.cols = cols;

		grid = new GameMatrix(rows, cols);

		setInitialState();
	}

	/**
	 * Get the number of rows on the GameMatrix.
	 * @return the number of rows on the GameMatrix.
	 */
	public int getNumRows()
	{
		return rows;
	}

	/**
	 * Get the number of cols on the GameMatrix.
	 * @return the number of cols on the GameMatrix.
	 */
	public int getNumColumns()
	{
		return cols;
	}

	/**
	 * Resets the model for a new game.
	 */
	public void newGame()
	{
		grid.clear();
		setInitialState();
	}

	/**
	 * Clears the board of all non-EMPTY tokens and sets the initial configuration.
	 */
	public abstract void setInitialState();

	/**
	 * Gets the token at the position given.
	 * @param row the row number.
	 * @param col the column number.
	 * @return the GamePiece at the position given.
	 */
	public GamePiece getGamePiece(int row, int col)
	{
		return grid.getGamePiece(row, col);
	}

	/**
	 * Checks if the board is full.
	 * @return true if the board is full, false otherwise (i.e. one or more EMPTY locations).
	 */
	public boolean gameMatrixIsFull()
	{
		return grid.gameMatrixIsFull();
	}

	/**
	 * Checks if the board contains only one colour of GamePiece.
	 * @return true if the board has only one colour of GamePiece, false otherwise.
	 */
	public boolean gameMatrixHasSingleValue()
	{
		return grid.gameMatrixHasSingleValue();
	}
}
