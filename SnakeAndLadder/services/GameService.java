package LLD.SnakeAndLadder.services;

import LLD.SnakeAndLadder.entities.Board;
import LLD.SnakeAndLadder.entities.Player;

public interface GameService {
	public void playGame(Board board,Player player1,Player player2);
}
