package uk.ac.wlv.cs5006.othello;


/**
 * This class represents a single cell on the GameMatrix. It has a location and a token value.
 * @author ianskenny
 * @since 14/15
 */
public class GameMatrixCell
{
	/**
	 * The GamePiece at this GameMatrixCell.
	 */
	private GamePiece token;

	/**
	 * The CellLocation of this GameMatrixCell.
	 */
	private GameMatrixLocation location;

	/**
	 * Constructor.
	 * @param row the row number.
	 * @param col the column number.
	 * @param token the GamePiece value.
	 */
	public GameMatrixCell(int row, int col, GamePiece token)
	{
		this.token = token;
		location = new GameMatrixLocation(row, col);
	}

	/**
	 * Sets the GamePiece value.
	 * @param token the GamePiece value.
	 */
	public void setGamePiece(GamePiece token)
	{
		this.token = token;
	}

	/**
	 * Set the value of the GamePiece in this GameMatrixCell.
	 * @param val the value of the GamePiece.
	 */
	public void setValue(String val)
	{
		this.token.setValue(val);
	}

	/**
	 * Gets the GamePiece value.
	 * @return the GamePiece at this GameMatrixCell.
	 */
	public GamePiece getGamePiece()
	{
		return token;
	}

	/**
	 * Get the GameMatrixLocation for this GameMatrixCell.
	 * @return the GameMatrixLocation for this GameMatrixCell.
	 */
	public GameMatrixLocation getLocation()
	{
		return location;
	}
}
