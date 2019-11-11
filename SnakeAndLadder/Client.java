package LLD.SnakeAndLadder;

import java.util.HashMap;
import java.util.Map;

import LLD.SnakeAndLadder.entities.Address;
import LLD.SnakeAndLadder.entities.Board;
import LLD.SnakeAndLadder.entities.Player;
import LLD.SnakeAndLadder.entities.User;
import LLD.SnakeAndLadder.services.BoardService;
import LLD.SnakeAndLadder.services.BoardServiceImpl;
import LLD.SnakeAndLadder.services.GameService;
import LLD.SnakeAndLadder.services.GameServiceImpl;

public class Client {
	public static void main(String[] args) {
		
		Address address1=new Address("street1", "city1", "state1", "zip1");
		Address address2=new Address("street2", "city2", "state2", "zip2");
		
		Player player1=new Player(new User("userName1", address1));
		Player player2=new Player(new User("userName2", address2));
		
		Map<Integer,Integer> snakes=new HashMap<>();
		Map<Integer,Integer> ladders=new HashMap<>();
		
		BoardService boardService=new BoardServiceImpl();
		Board board = boardService.makeBoard(snakes, ladders);
		
		GameService gameService =new GameServiceImpl();
		gameService.playGame(board,player1,player2);
		
	}
}
