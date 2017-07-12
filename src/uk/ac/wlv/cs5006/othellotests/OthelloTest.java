package uk.ac.wlv.cs5006.othellotests;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.wlv.cs5006.othello.Controller;
import uk.ac.wlv.cs5006.othello.GameMatrix;
import uk.ac.wlv.cs5006.othello.GamePiece;
import uk.ac.wlv.cs5006.othello.OthelloModel;
import uk.ac.wlv.cs5006.othello.GameMatrixCell;
import uk.ac.wlv.cs5006.othello.GameMatrixLocation;


public class OthelloTest {

	@Test
	public void ControllerTest() {
	
		GamePiece piece = new GamePiece("WHITE");
		OthelloModel model = new OthelloModel(5, 10);
		Controller cont = new Controller(model);
		assertTrue(cont.attemptPlay(2, 9, piece));
		GamePiece piece2 = new GamePiece("BLACK");
		OthelloModel model2 = new OthelloModel(5, 10);
		Controller cont2 = new Controller(model);
		assertEquals("WHITE", cont2.getOpponent("BLACK"));
		assertEquals("WHITE", cont2.getWinner());
		
	}

	@Test
	public void GameMatrixCellTest() {
		
		//Test the constructor with both "WHITE" and "BLACK" and different locations.
		GamePiece game_piece = new GamePiece("WHITE");
		GameMatrixCell cell = new GameMatrixCell(0,0, game_piece);
		GamePiece game_piece2 = new GamePiece("");
		game_piece2.setValue("BLACK");
		GameMatrixCell cell2 = new GameMatrixCell(1,10, game_piece2);
		
		GameMatrixLocation location = new GameMatrixLocation(1,10);
		GameMatrixLocation location2 = new GameMatrixLocation();
		assertTrue(location2.isInvalid());
		
		location2.setRow(20);
		location2.setCol(40);
		
		assertFalse(location.isInvalid());
		assertEquals(game_piece,cell.getGamePiece());
	
		assertEquals(game_piece2,cell2.getGamePiece());
		
		cell.setGamePiece(game_piece2);
		assertEquals(game_piece2,cell.getGamePiece());
		
		cell.setValue("WHITE");
		assertEquals("WHITE",cell.getGamePiece().getValue());
		
		
		GameMatrixLocation location3 = cell2.getLocation();
		assertEquals(location.getCol(),location3.getCol());
		assertEquals(location.getRow(),location3.getRow());
		game_piece.equals(game_piece);
		
		
	}


	@Test
	public void GameMatrixLocationTest() {
		
	 	//Test that the default constructor works and that it sets the location to an invalid location
	 	GameMatrixLocation location = new GameMatrixLocation();
		assertTrue(location.isInvalid());
		assertEquals(-1, location.getRow());
		assertEquals(-1, location.getCol());

		//Test that the parameterised constructor works with a variety of values
		GameMatrixLocation location1 = new GameMatrixLocation(0,0);
		assertFalse(location1.isInvalid());
		//Test all of the other getters and setters.
		assertEquals(0, location1.getRow());
		assertEquals(0, location1.getCol());
		location1.setRow(5);
		location1.setCol(10);
		assertEquals(5, location1.getRow());
		assertEquals(10, location1.getCol());
		//Test that setInvalid() sets a location to an invalid value
		location1.setInvalid();
		assertEquals(-1, location1.getRow());
		assertEquals(-1, location1.getCol());
		}

	@Test
	public void GameMatrixTest() {
	
	
	GameMatrix matrix = new GameMatrix(5,10);
	
	assertEquals(5, matrix.getNumRows());
	assertEquals(10, matrix.getNumColumns());
	
	assertTrue(matrix.setGamePieceValue(2, 6, "WHITE"));
	assertTrue(matrix.setGamePieceValue(3, 4, "BLACK"));
	assertFalse(matrix.setGamePieceValue(22, 6, "BLACK"));
	
	assertNotNull(matrix.getGamePiece(2, 6));
	assertNull(matrix.getGamePiece(22, 6));
	
	assertFalse(matrix.gameMatrixIsFull());
	
	GameMatrix matrix2 = new GameMatrix(1,1);
	assertTrue(matrix2.setGamePieceValue(0, 0, "WHITE"));
	assertTrue(matrix2.gameMatrixIsFull());
	
	assertTrue(matrix2.gameMatrixHasSingleValue());
	assertFalse(matrix.gameMatrixHasSingleValue());
	
	}

	@Test
	public void GamePieceTest() {
		//Constructor call to set 
		GamePiece game_piece = new GamePiece("WHITE");
		//Two values are the same
		assertEquals(game_piece.getValue(), "WHITE");
		//Constructor call to set 
		GamePiece game_piece2 = new GamePiece(null);
		//The object is null.
		assertNull(null, game_piece2.getValue());
		//using set method to set the value of object
		game_piece2.setValue("WHITE");
		//The object is not null.
		assertNotNull("Now the object is not null", game_piece2.getValue());
		//Editing previous value of object
		game_piece2.setValue("BLACK");
		assertEquals(game_piece2.getValue(), "BLACK");
		//Both variables refer to the same object
		game_piece.equals(game_piece2);
		//Boolean condition is False
		assertFalse(game_piece.equals(game_piece2));
		//Constructor call to set 
		GamePiece game_piece3 = new GamePiece("BLACK");
		//Boolean condition is True
		assertTrue(game_piece3.equals(game_piece2));
		//Two values are the same
		assertEquals(game_piece3.getValue(), "BLACK");
		//Both variables refer to the same object
		game_piece3.equals(game_piece2);
	}

	@Test
	public void OthelloModelTest() {
		
		
		OthelloModel model = new OthelloModel(6,6);
		model.setInitialState();
	
		assertNotNull(model.getGamePiece(3, 5));
		GamePiece token = new GamePiece("WHITE");
		assertFalse(model.attemptPlay(10, 10,token));
		assertFalse(model.attemptPlay(5, 5,token));
		assertTrue(model.attemptPlay(4, 3,token));
		
		GamePiece token2 = new GamePiece("BLACK");
		token2.equals(token);
		assertFalse(model.attemptPlay(10, 10,null));
		
		
		
		assertFalse(model.gameMatrixIsFull());
		
		assertFalse(model.gameMatrixHasSingleValue());
		
		assertEquals("BLACK", model.getOpponent("WHITE"));
		assertEquals("WHITE", model.getOpponent("BLACK"));
		assertEquals("EMPTY", model.getOpponent(""));
		
		
		model.attemptPlay(4, 3, token);
	    assertEquals(model.getWinner(), "WHITE");
	    
	    GamePiece token3 = new GamePiece("BLACK");
	   
	    assertTrue(model.attemptPlay(4, 2,token3));
	    model.attemptPlay(2, 5, token3);
	    assertEquals(model.getWinner(), "BLACK");
	    
	    OthelloModel model2 = new OthelloModel(5,5);
	    assertEquals(model2.getWinner(), "NONE");
	    
	    
	    GameMatrixCell last_cell = new GameMatrixCell(5, 5, token3);
	    last_cell.setGamePiece(token2);
	    assertNotNull(last_cell.getLocation());
		
		
		
	}


}




