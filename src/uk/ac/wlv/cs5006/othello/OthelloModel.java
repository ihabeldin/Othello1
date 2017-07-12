package uk.ac.wlv.cs5006.othello;


/**
 * The OthelloModel class.
 * 
 * @author ianskenny.
 * @since 14/15.
 */

public class OthelloModel extends AbstractModel {
    /**
     * DefaultConstructor. Initialises the game GameMatrix.
     */
    public OthelloModel(int rows, int cols) {
        super(rows, cols);
    }

    /**
     * Clears the GameMatrix and sets the initial configuration.
     */
    public void setInitialState() {
        grid.clear();
        grid.setGamePieceValue((grid.getNumRows() / 2) - 1, (grid.getNumColumns() / 2) - 1, "BLACK");
        grid.setGamePieceValue((grid.getNumRows() / 2) - 1, (grid.getNumColumns() / 2), "WHITE");
        grid.setGamePieceValue((grid.getNumRows() / 2), (grid.getNumColumns() / 2) - 1, "WHITE");
        grid.setGamePieceValue((grid.getNumRows() / 2), (grid.getNumColumns() / 2), "BLACK");
    }

    /**
     * Gets the token at the position given.
     * 
     * @param row the row number.
     * @param col the column number.
     * @return the GamePiece at the position given.
     */
    public GamePiece getGamePiece(int row, int col) {
        return grid.getGamePiece(row, col);
    }

    /**
     * Attempts to play the GamePiece at the location specified. If the move is legal then the play
     * will be made. If the move is not legal it won't be made. Thus if the move isn't legal the
     * GameMatrix will be left in the same state it was in prior to the method call.
     * 
     * @param row the row number.
     * @param col the column number.
     * @param token the GamePiece to attempt to play.
     * @return true if the play was legal, false otherwise.
     */
    public boolean attemptPlay(int row, int col, GamePiece token) {
        // {horizontal right, horizontal left, vertical down, vertical up,
        // diagonal right down, diagonal left up, diagonal right up, diagonal
        // left down}
        int[] rowInc = new int[] {0, 0, 1, -1, 1, -1, 1, -1};
        int[] colInc = new int[] {1, -1, 0, 0, 1, -1, -1, 1};

        boolean successfulPlay = false;

        // cannot play a null GamePiece
        if (token == null)
            return successfulPlay;

        // is the attempted play location on the GameMatrix?
        if (!grid.isOnGameMatrix(row, col))
            return successfulPlay;

        // does the location already have a GamePiece?
        if (!(grid.getGamePiece(row, col).getValue().equals("EMPTY")))
            return successfulPlay;

        // save the GamePiece at the play location in case the move is illegal
        String savedVal = grid.getGamePiece(row, col).getValue();

        // play the GamePiece first, then check it
        grid.setGamePieceValue(row, col, token.getValue());

        for (int i = 0; i < rows; i++) {
            // calculate the starting cell for checking for a legal play
            GameMatrixLocation startLocation =
                new GameMatrixLocation(row + rowInc[i], col + colInc[i]);

            // check whether the cells contain a legal move
            CheckResult result = checkCells(startLocation, rowInc[i], colInc[i], token);

            // if there are any tokens to flip (i.e. a legal move) ...
            if (result.numGamePiecesToFlip > 0) {
                // flip the tokens to this player's colour
                flipGamePieces(result.start, rowInc[i], colInc[i], result.numGamePiecesToFlip,
                    token);
                successfulPlay = true;
            }
        }

        // if we didn't find a successful play, revert the token
        if (!successfulPlay)
            grid.setGamePieceValue(row, col, savedVal);

        return successfulPlay;
    }

    /**
     * Flips the given tokens to the opposite colour.
     * 
     * @param start The location to start flipping from.
     * @param rowInc The row increment (direction).
     * @param colInc The column increment (direction).
     * @param numToFlip The number of tokens to flip.
     * @param token The desired colour of the GamePiece.
     */
    private void flipGamePieces(GameMatrixLocation start, int rowInc, int colInc, int numToFlip,
        GamePiece token) {
        int row = start.getRow();
        int col = start.getCol();

        for (int i = 0; i < numToFlip; i++) // assumes square GameMatrix!
        {
            grid.setGamePieceValue(row, col, token.getValue());
            row += rowInc;
            col += colInc;
        }
    }

    /**
     * Check for a legal play in the direction given.
     * 
     * @param start The location to start checking from.
     * @param rowInc The row increment (direction).
     * @param colInc The column increment (direction).
     * @param token The GamePiece that has been played.
     * @return The CheckResult result.
     */
    private CheckResult checkCells(GameMatrixLocation start, int rowInc, int colInc, GamePiece token) {
        CheckResult result = new CheckResult();
        result.start = start;

        boolean done = false;
        boolean opponentAdjacent = true;

        int tokenCount = 0;

        int row = start.getRow();
        int col = start.getCol();

        for (/*  */; row < grid.getNumRows() && row >= 0 && col < grid.getNumColumns() && col >= 0
            && !done && opponentAdjacent; row += rowInc, col += colInc) 
        {
            GamePiece tok = grid.getGamePiece(row, col);

            if (tok.getValue().equals("EMPTY"))
                done = false;

            if (tok.getValue() == getOpponent(token.getValue()))
                tokenCount++;

            else if (tok.getValue().equals(token.getValue())) {
                result.end.setRow(row);
                result.end.setCol(col);
                done = true;
            }
        }

        if (result.end.isInvalid()) {
            result.numGamePiecesToFlip = 0;
        } else if (opponentAdjacent == false) {
            result.numGamePiecesToFlip = 0;
        } else
            result.numGamePiecesToFlip = tokenCount;

        return result;
    }

    /**
     * Checks if the GameMatrix is full.
     * 
     * @return true if the GameMatrix is full, false otherwise (i.e. one or more EMPTY locations).
     */
    public boolean gameMatrixIsFull() {
        return grid.gameMatrixIsFull();
    }

    /**
     * Checks if the GameMatrix contains only one colour of GamePiece.
     * 
     * @return true if the GameMatrix has only one colour of GamePiece, false otherwise.
     */
    public boolean gameMatrixHasSingleValue() {
        return grid.gameMatrixHasSingleValue();
    }

    /**
     * Gets the opponent of the GamePiece.
     * 
     * @param player the GamePiece to find the opponent of.
     * @return the opponent of the GamePiece passed in.
     */
    public String getOpponent(String player) {
        if (player.equals("BLACK"))
            return "WHITE";

        if (player.equals("WHITE"))
            return "BLACK";

        else
            return "EMPTY";
    }

    /**
     * Gets the winning GamePiece.
     * 
     * @return the winning GamePiece. EMPTY if a draw.
     */
    public String getWinner() {
        int blackCount = 0;
        int whiteCount = 0;

        for (int i = 0; i < grid.getNumRows(); i++) {
            for (int j = 0; j < grid.getNumColumns(); j++) {
                GamePiece tok = getGamePiece(i, j);
                if (tok.getValue().equals("WHITE"))
                    whiteCount++;

                else if (tok.getValue().equals("BLACK"))
                    blackCount++;
            }
        }

        if (blackCount > whiteCount)
            return "BLACK";
        if (whiteCount > blackCount)
            return "WHITE";

        return "NONE"; // draw
    }

    /**
     * The CheckResult class. A private class.
     * 
     * @author ianskenny
     * @since 14/15
     */
    private class CheckResult {
        /**
         * The start location.
         */
        public GameMatrixLocation start;

        /**
         * The end location.
         */
        public GameMatrixLocation end;

        /**
         * The number of GamePieces to flip.
         */
        public int numGamePiecesToFlip;

        /**
         * Constructor.
         */
        public CheckResult() {
            start = new GameMatrixLocation();
            end = new GameMatrixLocation();

            clear();
        }

        /**
         * Clears the result.
         */
        public void clear() {
            start.setInvalid();
            end.setInvalid();
            numGamePiecesToFlip = 0;
        }
    }
}
