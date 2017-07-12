package uk.ac.wlv.cs5006.othello;


/**
 * The GamePiece class.
 * @author ianskenny.
 * @since 15/16
 */
public class GamePiece
{
    /**
     * The value of this GamePiece.
     */
	protected String value;

	/**
	 * Constructor.
	 * @param val the value to initialise this GamePiece to.
	 */
	public GamePiece(String val)
	{
		value = val;
	}

	/**
	 * Sets the value of the GamePiece.
	 * @param val the value to set the GamePiece to.
	 */
	public void setValue(String val)
	{
		value = val;
	}

	/**
	 * Gets the value of this GamePiece.
	 * @return the value of this GamePiece.
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Tests if another GamePiece has the same value as this GamePiece.
	 * @param other the GamePiece to test against this GamePiece.
	 * @return true if the GamePieces have the same value, false otherwise.
	 */
	public boolean equals(GamePiece other)
	{
		return this.value.equals(other.value);
	}
}
