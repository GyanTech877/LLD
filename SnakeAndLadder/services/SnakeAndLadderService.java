package LLD.SnakeAndLadder.services;

import java.util.List;

import LLD.SnakeAndLadder.entities.Ladder;
import LLD.SnakeAndLadder.entities.Player;
import LLD.SnakeAndLadder.entities.Snake;

public interface SnakeAndLadderService {
    static final int DEFAULT_BOARD_SIZE = 100; //The board will have 100 cells numbered from 1 to 100.
    static final int DEFAULT_NO_OF_DICES = 1;
	void startGame();
	void setPlayers(List<Player> players);
	void setSnakes(List<Snake> snakes);
	void setLadders(List<Ladder> ladders);
}
