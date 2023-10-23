package com.fidelity.bowling;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {
	
	Game game;
	
	@BeforeEach
	void setUp() throws Exception {
		game = new Game();
	}

	@AfterEach
	void tearDown() throws Exception {
		game = null;
	}
	
	@Test
	void negativePins() {
		assertThrows(IllegalArgumentException.class, ()-> {
			rolls(-1);
			game.getScore();
		});	
	}
	
	@Test
	void extraPins() {
		assertThrows(IllegalArgumentException.class, ()-> {
			rolls(11);
			game.getScore();
		});	
	}

	@Test
	void allZeroThrows() {
		rollPins(20, 0);
		int actualScore = game.getScore();
		assertEquals(actualScore, 0);
	}
	
	@Test
	void allOnesThrows() {
		rollPins(20, 1);
		int actualScore = game.getScore();
		assertEquals(20, actualScore);
	}
	
	@Test
	void canScoreSpare() {
		rolls(5,5,3);
		rollPins(17, 0);
		int actualScore = game.getScore();
		assertEquals(16, actualScore);
	}
	
	@Test
	void canScoreAllStrikes() {
		rollPins(12, 10);
		int actualScore = game.getScore();
		assertEquals(300, actualScore);
	}
	
	private void rolls(int...pinsArray) {
		for(int pins : pinsArray) {
			game.rolls(pins);
		}
	}
	
	private void rollPins(int numberOfRolls, int pinValue) {
		for(int i = 0; i < numberOfRolls; i++) {
			game.rolls(pinValue);
		}
	}
}
