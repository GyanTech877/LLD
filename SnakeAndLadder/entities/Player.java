package LLD.SnakeAndLadder.entities;

import java.util.List;

public class Player {
	private User user;
	private int rank;
	private List<Game> gameHistory;
	int position;
	
	public Player(User user) {
		super();
		this.user = user;
		this.position=0;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public List<Game> getGameHistory() {
		return gameHistory;
	}

	public void setGameHistory(List<Game> gameHistory) {
		this.gameHistory = gameHistory;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Player [user=" + user + ", rank=" + rank + ", gameHistory=" + gameHistory + ", position=" + position
				+ "]";
	}

	

}
