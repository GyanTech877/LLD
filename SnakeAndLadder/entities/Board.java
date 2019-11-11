package LLD.SnakeAndLadder.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Board {
	private int boardId;
	private final static int WIN_POINT = 100;
	private Map<Integer,Integer> snakes;
	private Map<Integer,Integer> ladders;
	private static final AtomicInteger count = new AtomicInteger(0);
	
	public Board() {
		this.boardId=count.getAndIncrement();
		this.snakes=new HashMap<>();
		this.ladders=new HashMap<>();
	}
	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	
	public Map<Integer, Integer> getSnakes() {
		return snakes;
	}
	
	public Map<Integer, Integer> getLadders() {
		return ladders;
	}
	
	public static AtomicInteger getCount() {
		return count;
	}
	public static int getWinPoint() {
		return WIN_POINT;
	}

}
