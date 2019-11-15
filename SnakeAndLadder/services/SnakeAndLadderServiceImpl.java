package LLD.SnakeAndLadder.services;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import LLD.SnakeAndLadder.entities.Board;
import LLD.SnakeAndLadder.entities.Ladder;
import LLD.SnakeAndLadder.entities.Player;
import LLD.SnakeAndLadder.entities.Snake;

public class SnakeAndLadderServiceImpl implements SnakeAndLadderService {

	private Board board;
	private int initialNumberOfPlayers;
	private Queue<Player> players; // Comment: Keeping players in game service as they are specific to this game
									// and not the board. Keeping pieces in the board instead.
	private boolean isGameCompleted;

	private int noOfDices; // Optional Rule 1
	private boolean shouldGameContinueTillLastPlayer; // Optional Rule 3
	private boolean shouldAllowMultipleDiceRollOnSix; // Optional Rule 4

	public SnakeAndLadderServiceImpl() {
		this(DEFAULT_BOARD_SIZE);
	}

	public SnakeAndLadderServiceImpl(int boardSize) {
		this.board = new Board(boardSize);
		this.players = new LinkedList<>();
		this.noOfDices = DEFAULT_NO_OF_DICES;
	}

	public void setNoOfDices(int noOfDices) {
		this.noOfDices = noOfDices;
	}

	public void setShouldGameContinueTillLastPlayer(boolean shouldGameContinueTillLastPlayer) {
		this.shouldGameContinueTillLastPlayer = shouldGameContinueTillLastPlayer;
	}

	public void setShouldAllowMultipleDiceRollOnSix(boolean shouldAllowMultipleDiceRollOnSix) {
		this.shouldAllowMultipleDiceRollOnSix = shouldAllowMultipleDiceRollOnSix;
	}

	@Override
	public void setSnakes(List<Snake> snakes) {
		board.setSnakes(snakes);
	}

	@Override
	public void setLadders(List<Ladder> ladders) {
		board.setLadders(ladders);
	}

	@Override
	public void setPlayers(List<Player> players) {
		this.players = new LinkedList<>();
		this.initialNumberOfPlayers = players.size();
		Map<String, Integer> playerPieces = new HashMap<>();
		for (Player player : players) {
			this.players.add(player);
			playerPieces.put(player.getId(), 0); // Each player has a piece which is initially kept outside the board
													// (i.e., at position 0).
		}
		board.setPlayerPieces(playerPieces); // Add pieces to board
	}

	@Override
	public void startGame() {
		while (!isGameCompleted()) {
			int totalDiceValue = getTotalValueAfterDiceRolls(); // Each player rolls the dice when their turn comes.
			Player currentPlayer = players.poll();
			movePlayer(currentPlayer, totalDiceValue);
			if (hasPlayerWon(currentPlayer)) {
				System.out.println(currentPlayer.getName() + " wins the game");
				board.getPlayerPieces().remove(currentPlayer.getId());
			} else {
				players.add(currentPlayer);
			}
		}
	}

	private boolean isGameCompleted() {
		// Can use shouldGameContinueTillLastPlayer to change the logic of determining
		// if game is completed (Optional requirements)
		int currentNumberOfPlayers = players.size();
		return currentNumberOfPlayers < initialNumberOfPlayers;
	}

	private int getTotalValueAfterDiceRolls() {
		// Can use noOfDices and setShouldAllowMultipleDiceRollOnSix here to get total
		// value (Optional requirements)
		return new DiceServiceImpl().throwDice();
	}

	private void movePlayer(Player player, int positions) {
		int oldPosition = board.getPlayerPieces().get(player.getId());
		int newPosition = oldPosition + positions; // Based on the dice value, the player moves their piece forward that
													// number of cells.

		int boardSize = board.getSize();

		// Can modify this logic to handle side case when there are multiple dices
		// (Optional requirements)
		if (newPosition > boardSize) {
			newPosition = oldPosition; // After the dice roll, if a piece is supposed to move outside position 100, it
										// does not move.
		} else {
			newPosition = getNewPositionAfterGoingThroughSnakesAndLadders(newPosition);
		}

		board.getPlayerPieces().put(player.getId(), newPosition);

		System.out.println(
				player.getName() + " rolled a " + positions + " and moved from " + oldPosition + " to " + newPosition);
	}

	private int getNewPositionAfterGoingThroughSnakesAndLadders(int newPosition) {
		int previousPosition;

		do {
			previousPosition = newPosition;
			for (Snake snake : board.getSnakes()) {
				if (snake.getHead() == newPosition) {
					newPosition = snake.getTail();
				}
			}
			for (Ladder ladder : board.getLadders()) {
				if (ladder.getStart() == newPosition) {
					newPosition = ladder.getEnd();
				}
			}
		} while (newPosition != previousPosition); // There could be another snake/ladder at the tail of the snake or
													// the end position of the ladder and the piece should go up/down
													// accordingly.
		return newPosition;
	}

	private boolean hasPlayerWon(Player player) {
		// Can change the logic a bit to handle special cases when there are more than
		// one dice (Optional requirements)
		int playerPosition = board.getPlayerPieces().get(player.getId());
		int winningPosition = board.getSize();
		return playerPosition == winningPosition; // A player wins if it exactly reaches the position 100 and the game
													// ends there.
	}
}
