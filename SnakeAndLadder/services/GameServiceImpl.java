package LLD.SnakeAndLadder.services;

import LLD.SnakeAndLadder.entities.Board;
import LLD.SnakeAndLadder.entities.Dice;
import LLD.SnakeAndLadder.entities.Player;

public class GameServiceImpl implements GameService {

	@Override
	public void playGame(Board board, Player player1, Player player2) {
		while (true) {
			int player1Position = player1.getPosition() + Dice.throwDice();
			if (board.getSnakes().containsKey(player1Position))
				player1Position = board.getSnakes().get(player1Position);
			else if (board.getLadders().containsKey(player1Position))
				player1Position = board.getLadders().get(player1Position);
			if (player1Position == Board.getWinPoint()) {
				System.out.println("Player one won");
				break;
			}
			int player2Position = player2.getPosition() + Dice.throwDice();
			if (board.getSnakes().containsKey(player2Position))
				player2Position = board.getSnakes().get(player2Position);
			else if (board.getLadders().containsKey(player2Position))
				player2Position = board.getLadders().get(player2Position);
			if (player2Position == Board.getWinPoint()) {
				System.out.println("Player two won");
				break;
			}
		}
	}

}
