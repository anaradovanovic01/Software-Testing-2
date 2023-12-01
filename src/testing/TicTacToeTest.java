package testing;
import tictactoe.TicTacToeAI;
import tictactoe.TicTacToeMain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JButton;

import org.junit.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.easymock.EasyMock.*;
import org.junit.*;

public class TicTacToeTest {
	  public static TicTacToeAI tictactoe = new TicTacToeAI();
	  public static TicTacToeMain window = new TicTacToeMain();
	  
	  @Before
	  public void clear() {
		  for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
					tictactoe.setBoardValue(i, j, TicTacToeAI.EMPTY); 
	  }

	  @ParameterizedTest
	  @CsvFileSource(resources = {"./testData.csv"})
	  public void getBoardValue(int i, int j) { 
		  assertEquals(TicTacToeAI.EMPTY, tictactoe.getBoardValue(i, j));
	  }
	  
	  @ParameterizedTest
	  @CsvFileSource(resources = {"./testData.csv"}, numLinesToSkip = 1)
	  public void setBoardValue1(int i, int j) {
		  tictactoe.setBoardValue(i, j, TicTacToeAI.ONE);
		  assertEquals(TicTacToeAI.EMPTY, tictactoe.getBoardValue(i, j));
	  }
	  
	  @Test
	  public void setBoardValue2() {
		  tictactoe.setBoardValue(0, 2, TicTacToeAI.TWO);
		  assertEquals(TicTacToeAI.TWO, tictactoe.getBoardValue(0, 2));
	  }
	  
	  @ParameterizedTest
	  @CsvFileSource(resources = {"./testData2.csv"})
	  public void inverse(int expected, int actual) {
		  assertEquals(expected, tictactoe.inverse(actual));
	  }
	  
	  @Test
	  public void isWin1() {
		  tictactoe.setBoardValue(0, 0, TicTacToeAI.ONE);
		  tictactoe.setBoardValue(1, 0, TicTacToeAI.TWO);
		  tictactoe.setBoardValue(1, 1, TicTacToeAI.ONE);
		  tictactoe.setBoardValue(0, 1, TicTacToeAI.TWO);
		  tictactoe.setBoardValue(2, 2, TicTacToeAI.ONE);
		  assertEquals(true, tictactoe.isWin(TicTacToeAI.ONE));
	  }
	  
	  @Test
	  public void isWin2() {
		  tictactoe.setBoardValue(0, 0, TicTacToeAI.ONE);
		  tictactoe.setBoardValue(1, 0, TicTacToeAI.TWO);
		  tictactoe.setBoardValue(1, 1, TicTacToeAI.ONE);
		  tictactoe.setBoardValue(0, 1, TicTacToeAI.TWO);
		  assertEquals(false, tictactoe.isWin(TicTacToeAI.ONE));
	  }
	  
	  @Test
	  public void nextWinningMove1() { 
		  tictactoe.setBoardValue(0, 0, TicTacToeAI.ONE);
		  tictactoe.setBoardValue(1, 0, TicTacToeAI.TWO);
		  tictactoe.setBoardValue(1, 1, TicTacToeAI.ONE);
		  tictactoe.setBoardValue(0, 1, TicTacToeAI.TWO);
		  int arr[] = tictactoe.nextWinningMove(TicTacToeAI.ONE);
		  assertEquals(2, arr[0]);
		  assertEquals(2, arr[1]);
	  }
	  
	  @Test
	  public void nextWinningMove2() {
		  assertEquals(null, tictactoe.nextWinningMove(TicTacToeAI.ONE));
	  }
	  
	  @Test
	  public void nextMove1() {
		  tictactoe.setBoardValue(1, 1, TicTacToeAI.EMPTY);
		  int arr[] = tictactoe.nextMove(TicTacToeAI.ONE);
		  assertEquals(1, arr[0]);
		  assertEquals(1, arr[1]);
	  }
	  
	  @Test
	  public void nextMove2() {
		  tictactoe.setBoardValue(0, 0, TicTacToeAI.ONE);
		  tictactoe.setBoardValue(1, 0, TicTacToeAI.TWO);
		  tictactoe.setBoardValue(1, 1, TicTacToeAI.ONE);
		  tictactoe.setBoardValue(0, 1, TicTacToeAI.TWO);
		  int arr[] = tictactoe.nextMove(TicTacToeAI.ONE);
		  assertEquals(2, arr[0]);
		  assertEquals(2, arr[1]);
	  }
	  
	  @Test
	  public void nextMove3() {
		  tictactoe.setBoardValue(1, 1, TicTacToeAI.ONE);
		  tictactoe.setBoardValue(0, 0, TicTacToeAI.TWO);
		  tictactoe.setBoardValue(0, 2, TicTacToeAI.ONE);
		  int arr[] = tictactoe.nextMove(TicTacToeAI.TWO);
		  assertEquals(2, arr[0]);
		  assertEquals(0, arr[1]);
	  }
	  
	  @Test
	  public void nextMove4() {
		  tictactoe.setBoardValue(1, 1, TicTacToeAI.ONE);
		  tictactoe.setBoardValue(0, 0, TicTacToeAI.TWO);
		  tictactoe.setBoardValue(2, 2, TicTacToeAI.ONE);
		  tictactoe.setBoardValue(0, 1, TicTacToeAI.TWO);
		  tictactoe.setBoardValue(0, 2, TicTacToeAI.ONE);
		  int arr[] = tictactoe.nextMove(TicTacToeAI.TWO);
		  assertEquals(1, arr[0]);
		  assertEquals(0, arr[1]);
	  }
	  
	  @Test
	  public void nextMove5() {
		  tictactoe.setBoardValue(1, 1, TicTacToeAI.ONE);
		  tictactoe.setBoardValue(0, 0, TicTacToeAI.TWO);
		  tictactoe.setBoardValue(0, 2, TicTacToeAI.ONE);
		  tictactoe.setBoardValue(2, 0, TicTacToeAI.TWO);
		  tictactoe.setBoardValue(1, 0, TicTacToeAI.ONE);
		  tictactoe.setBoardValue(1, 2, TicTacToeAI.TWO);
		  tictactoe.setBoardValue(0, 1, TicTacToeAI.ONE);
		  tictactoe.setBoardValue(2, 1, TicTacToeAI.TWO);
		  tictactoe.setBoardValue(2, 2, TicTacToeAI.ONE);
		  assertEquals(null, tictactoe.nextMove(TicTacToeAI.TWO));
	  }

	  @Test
	  public void play() {
		  JButton playButton = window.getPlayButton();
		  playButton.doClick();
		  assertEquals(TicTacToeAI.ONE, window.getHumanValue());
		  assertEquals(TicTacToeAI.TWO, window.getComputerValue());
		  assertEquals(true, window.isPlay());
		  assertEquals("Your Turn", window.getStatusLabel().getText());
		  for(int i=0; i<3; i++)
			  for(int j=0; j<3; j++) 
				  assertEquals(true, window.getButton(i, j).isEnabled());
	  }
	  
	  @Test
	  public void click1() {
		  window.getPlayButton().doClick();
		  JButton buttonHuman = window.getButton(1,1);
		  JButton buttonComputer = window.getButton(0,0);
		  buttonHuman.doClick();
		  assertEquals(window.getChars(window.getHumanValue()), buttonHuman.getText());
		  assertEquals(window.getChars(window.getComputerValue()), buttonComputer.getText());
		  assertEquals("Your Turn", window.getStatusLabel().getText());
	  }
	  
	  @Test
	  public void click2() {
		  window.getPlayButton().doClick();
		  window.getGame().setBoardValue(1, 1, 2);
		  window.getButton(1, 1).setText(window.getChars(2));
		  JButton button = window.getButton(1,1);
		  button.doClick();
		  assertEquals(window.getChars(2), button.getText());
	  }
	  
	  @Test
	  public void click3() {
		  window.getPlayButton().doClick();
		  TicTacToeAI mock = createNiceMock(TicTacToeAI.class);
		  expect(mock.getBoardValue(1,1)).andReturn(TicTacToeAI.EMPTY);
		  expect(mock.isWin(1)).andReturn(true);
		  expect(mock.isWin(2)).andReturn(false);
		  expect(mock.nextMove(1)).andReturn(new int[] {0,0});
		  expect(mock.nextMove(2)).andReturn(new int[] {0,0});
		  replay(mock);
		  window.setGame(mock);
		  JButton buttonHuman = window.getButton(1,1);
		  buttonHuman.doClick();
		  assertEquals(window.getChars(window.getHumanValue()), buttonHuman.getText());
		  assertEquals("Congratulations, You've Won!", window.getStatusLabel().getText());
		  assertEquals(false, window.isPlay());
		  for(int i=0; i<3; i++)
			  for(int j=0; j<3; j++) 
				  assertEquals(false, window.getButton(i, j).isEnabled());
	  }
	  
	  @Test
	  public void click4() {
		  window.getPlayButton().doClick();
		  TicTacToeAI mock = createNiceMock(TicTacToeAI.class);
		  expect(mock.getBoardValue(1,1)).andReturn(TicTacToeAI.EMPTY);
		  expect(mock.isWin(1)).andReturn(false);
		  expect(mock.isWin(2)).andReturn(true);
		  expect(mock.nextMove(1)).andReturn(new int[] {0,0});
		  expect(mock.nextMove(2)).andReturn(new int[] {0,0});
		  replay(mock);
		  window.setGame(mock);
		  JButton buttonComputer = window.getButton(2,0);
		  buttonComputer.doClick();
		  assertEquals(window.getChars(window.getHumanValue()), buttonComputer.getText());
		  assertEquals("Sorry, You Lose!", window.getStatusLabel().getText());
		  assertEquals(false, window.isPlay());
		  for(int i=0; i<3; i++)
			  for(int j=0; j<3; j++) 
				  assertEquals(false, window.getButton(i, j).isEnabled());
	  }
	  
	  @Test
	  public void click5() {
		  window.getPlayButton().doClick();
		  TicTacToeAI mock = createNiceMock(TicTacToeAI.class);
		  expect(mock.getBoardValue(1,1)).andReturn(TicTacToeAI.EMPTY);
		  expect(mock.isWin(1)).andReturn(false);
		  expect(mock.isWin(2)).andReturn(false);
		  expect(mock.nextMove(1)).andReturn(null);
		  expect(mock.nextMove(2)).andReturn(null);
		  replay(mock);
		  window.setGame(mock);
		  JButton buttonComputer = window.getButton(2,2);
		  buttonComputer.doClick();
		  assertEquals(window.getChars(window.getHumanValue()), buttonComputer.getText());
		  assertEquals("Draw, Click 'Play' For Rematch!", window.getStatusLabel().getText());
		  assertEquals(false, window.isPlay());
		  for(int i=0; i<3; i++)
			  for(int j=0; j<3; j++) 
				  assertEquals(false, window.getButton(i, j).isEnabled());
	  }
	  
}
