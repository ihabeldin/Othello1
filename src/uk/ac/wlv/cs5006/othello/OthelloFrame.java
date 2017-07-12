package uk.ac.wlv.cs5006.othello;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OthelloFrame extends JFrame
{
	/**
	 * Order of components:
	 *
	 * --------------------
	 * Othello panel
	 * --------------------
	 * Message panel
	 * --------------------
	 * Controls panel
	 * --------------------
	 */

	/**
	 * The panel for the Othello board.
	 */
	private OthelloPanel gamePanel;

	/**
	 * The message panel (for the labels).
	 */
	private JPanel messagePanel;

	/**
	 * The controls panel (for the buttons).
	 */
	private JPanel controlsPanel;

	/**
	 * The player message JLabel.
	 */
	private JLabel playerMsg;

	/**
	 * The status message JLabel
	 */
	private JLabel statusMsg;

	/**
	 * The String for the "New Game" button.
	 */
	private static final String newGameButtonMsg = "New Game";

	/**
	 * The String for the "Cede" button.
	 */
	private static final String cedeButtonMsg = "Cede";

	/**
	 * The BasicCUIView.
	 */
	private OthelloView view;

	/**
	 * Constructor.
	 * @param view_ the BasicGUIView.
	 */
	public OthelloFrame(OthelloView view_, int rows, int cols)
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Othello");
		setVisible(true);

		view = view_;

		gamePanel = new OthelloPanel(view, rows, cols);
		add(gamePanel, BorderLayout.NORTH);

		messagePanel = new JPanel(new FlowLayout());

		playerMsg = new JLabel(" ", JLabel.CENTER);
		messagePanel.add(playerMsg/*, BorderLayout.SOUTH*/);

		statusMsg = new JLabel (" ", JLabel.CENTER);
		messagePanel.add(statusMsg/*, BorderLayout.CENTER*/);

		add(messagePanel, BorderLayout.CENTER);

		controlsPanel = new JPanel(new FlowLayout());

		JButton newGameButton = new JButton(newGameButtonMsg);

		newGameButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getActionCommand().equals(newGameButtonMsg))
				{
					view.newGame();
				}
			}
		});

		controlsPanel.add(newGameButton);

		JButton cedeButton = new JButton(cedeButtonMsg);

		cedeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getActionCommand().equals(cedeButtonMsg))
				{
					view.cede();
				}
			}
		});

		controlsPanel.add(cedeButton);

		add(controlsPanel, BorderLayout.SOUTH);

		pack();
	}

	/**
	 * Sets the player message, i.e. the message showing which player is to play now.
	 * @param player The player as a String.
	 */
	public void setPlayerMessage(String player)
	{
		playerMsg.setText(player);
	}

	/**
	 * Clears the player message.
	 */
	public void clearPlayerMessage()
	{
		playerMsg.setText(" ");
	}

	/**
	 * Sets the status message, e.g. "game over", "illegal move", etc.
	 * @param msg the status message as a String.
	 */
	public void setStatusMessage(String msg)
	{
		statusMsg.setText(msg);
	}

	/**
	 * Clears the status message.
	 */
	public void clearStatusMessage()
	{
		statusMsg.setText(" ");
	}
}
