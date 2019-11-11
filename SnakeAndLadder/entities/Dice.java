package LLD.SnakeAndLadder.entities;

import java.util.Random;

public class Dice {

	public static int throwDice() {
		Random random = new Random();
		int rand = 0;
		while (true) {
			rand = random.nextInt(7);
			if (rand != 0)
				break;
		}
		return rand;
	}
}
