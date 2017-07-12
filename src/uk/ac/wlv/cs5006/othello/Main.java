package uk.ac.wlv.cs5006.othello;


public class Main
{
	private static final int ROWS = 8;
	private static final int COLS = 8;

	public static void main(String[] args)
	{
		// create the model
		OthelloModel model = new OthelloModel(ROWS, COLS);

		// create the controller
		Controller controller = new Controller(model);

		// create the view
		AbstractView view= new OthelloView(controller);

		// play the game
		view.play();
	}
}
