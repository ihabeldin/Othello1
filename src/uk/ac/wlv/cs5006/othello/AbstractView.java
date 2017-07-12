package uk.ac.wlv.cs5006.othello;


public abstract class AbstractView
{
	/**
	 * The Controller.
	 */
	protected Controller controller;

	/**
	 * Constructor.
	 * @param controller The Controller.
	 */
	public AbstractView(Controller controller)
	{
		this.controller = controller;
	}

	/**
	 * Plays the game.
	 */
	public abstract void play();

	/**
	 * Starts a new game.
	 */
	public abstract void newGame();

	/**
	 * Gets the GamePiece in the row and column specified.
	 * @param row The row number.
	 * @param col The column number.
	 * @return The GamePiece.
	 */
	public GamePiece getGamePiece(int row, int col)
	{
		return controller.getGamePiece(row, col);
	}
}
