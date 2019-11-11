package LLD.SnakeAndLadder.services;

import java.util.Map;

import LLD.SnakeAndLadder.entities.Board;

public class BoardServiceImpl implements BoardService{

	@Override
	public Board makeBoard(Map<Integer,Integer> snakes,Map<Integer,Integer> ladders) {
		Board board= new Board();
		board.getSnakes().putAll(snakes);
		board.getLadders().putAll(ladders);
		return board;
	}

	

}
