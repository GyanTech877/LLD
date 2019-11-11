package LLD.SnakeAndLadder.services;

import java.util.Map;

import LLD.SnakeAndLadder.entities.Board;

public interface BoardService {
	public Board makeBoard(Map<Integer,Integer> snakes,Map<Integer,Integer> ladders);
}
