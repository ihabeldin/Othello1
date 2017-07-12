package uk.ac.wlv.cs5006.othello;


/**
 * This class represents the Controller.
 * @author ianskenny
 * @since 14/15
 */

public class Controller extends AbstractController
{
	/**
	 * Constructor.
	 * @param model the Model.
	 */
	public Controller(OthelloModel model)
	{
		super(model);
	}

	/**
	 * Attempts to play the GamePiece at the location specified. If the move is
	 * legal then the play will be made. If the move is not legal it won't be made.
	 * Thus if the move isn't legal the board will be left in the same state
	 * it was in prior to the method call.
	 *
	 * @param row the row number.
	 * @param col the column number.
	 * @param token the GamePiece to attempt to play.
	 * @return true if the play was legal, false otherwise.
	 */

	public boolean attemptPlay(int row, int col, GamePiece token)
	{
		return ((OthelloModel)model).attemptPlay(row, col, token);
	}

	/**
	 * Gets the opponent of the token.
	 * @param player the token to find the opponent of.
	 * @return the opponent of the token passed in.
	 */
	public String getOpponent(String player)
	{
		return ((OthelloModel)model).getOpponent(player);
	}

	/**
	 * Gets the winning GamePiece.
	 * @return the winning GamePiece. EMPTY if a draw.
	 */
	public String getWinner()
	{
		return ((OthelloModel)model).getWinner();
	}
}
