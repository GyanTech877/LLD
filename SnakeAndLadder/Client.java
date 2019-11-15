package LLD.SnakeAndLadder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import LLD.SnakeAndLadder.entities.Ladder;
import LLD.SnakeAndLadder.entities.Player;
import LLD.SnakeAndLadder.entities.Snake;
import LLD.SnakeAndLadder.services.SnakeAndLadderService;
import LLD.SnakeAndLadder.services.SnakeAndLadderServiceImpl;

public class Client {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int noOfSnakes = scanner.nextInt();
		List<Snake> snakes = new ArrayList<>();
		for (int i = 0; i < noOfSnakes; i++) {
			snakes.add(new Snake(scanner.nextInt(), scanner.nextInt()));
		}

		int noOfLadders = scanner.nextInt();
		List<Ladder> ladders = new ArrayList<>();
		for (int i = 0; i < noOfLadders; i++) {
			ladders.add(new Ladder(scanner.nextInt(), scanner.nextInt()));
		}

		int noOfPlayers = scanner.nextInt();
		List<Player> players = new ArrayList<>();
		for (int i = 0; i < noOfPlayers; i++) {
			players.add(new Player(scanner.next()));
		}

		SnakeAndLadderService snakeAndLadderService = new SnakeAndLadderServiceImpl();
		snakeAndLadderService.setPlayers(players);
		snakeAndLadderService.setSnakes(snakes);
		snakeAndLadderService.setLadders(ladders);

		snakeAndLadderService.startGame();
		scanner.close();
	}
}
