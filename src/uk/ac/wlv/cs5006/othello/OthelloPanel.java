package uk.ac.wlv.cs5006.othello;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OthelloPanel extends JPanel
{
	/**
	 * The BasicGUIView.
	 */
	private final OthelloView view;

	/**
	 * The number of rows on the GameMatrix. This information comes from the model.
	 */
	private int rows;

	/**
	 * The number of cols on the GameMatrix. This information comes from the model.
	 */
	private int cols;

	/**
	 * Constructor.
	 * @param view_ The BasicGUIView view.
	 */
	public OthelloPanel(OthelloView view_, int rows, int cols)
	{
		this.view = view_;

		this.rows = rows;
		this.cols = cols;

		addMouseListener(new MouseAdapter()
		{
		    public void mouseClicked(MouseEvent e)
		    {
		    	view.nextPlay(e.getY()/OthelloView.cellWidth, e.getX()/OthelloView.cellHeight);
		    }
		});

		setBackground(Color.GREEN);

		setPreferredSize(new Dimension(OthelloView.cellWidth * cols +1,OthelloView.cellHeight * rows +1));
	}

	/**
	 * Paints the game into the panel.
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// paint the GameMatrix
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(2));

		for (int i = 0; i < rows +1; i++)
		{
			g2d.drawLine(0, i*OthelloView.cellHeight, OthelloView.cellWidth * cols, i*OthelloView.cellHeight);

			for (int j = 0; j < cols+1; j++)
			{
				g2d.drawLine(j*OthelloView.cellWidth, 0, j* OthelloView.cellWidth, OthelloView.cellHeight * rows);
			}
		}

		int wShift = (OthelloView.cellWidth - OthelloView.tokenDiameter)/2;
		int hShift = (OthelloView.cellHeight - OthelloView.tokenDiameter)/2;


		// paint the GamePieces
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < cols; j++)
			{
				GamePiece tok = view.getGamePiece(i, j);

				if (tok != null && tok.getValue().equals("WHITE"))
				{
					g2d.setColor(Color.WHITE);

					g2d.fillOval((j*OthelloView.cellWidth) + wShift, (i*OthelloView.cellHeight) +hShift, OthelloView.tokenDiameter, OthelloView.tokenDiameter);
				}

				else if (tok != null && tok.getValue().equals("BLACK"))
				{
					g2d.setColor(Color.BLACK);

					g2d.fillOval((j*OthelloView.cellWidth) + wShift, (i*OthelloView.cellHeight) + hShift, OthelloView.tokenDiameter, OthelloView.tokenDiameter);
				}
			}
		}
	}
}
