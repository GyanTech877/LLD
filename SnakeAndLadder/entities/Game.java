package LLD.SnakeAndLadder.entities;

import java.util.concurrent.atomic.AtomicInteger;

public class Game {
	private int gameId;
	private Player player1;
	private Player player2;
	private Board board;
	private Player winner;
    private static final AtomicInteger count = new AtomicInteger(0);
	
	public Game() {
		this.gameId=count.getAndIncrement();
	}
	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

}
