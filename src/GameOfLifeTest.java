import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class GameOfLifeTest {

	@Test
	public void testGenerateDestinationField() {
		GameOfLife gof = new GameOfLife(1,1);
		String zwischenzustand = ".";
		assertArrayEquals("InitializeFieldTest für feldsrc[1,1] fehlgeschlagen.", new String[][] {{zwischenzustand}}, gof.feldsrc);
		assertArrayEquals("InitializeFieldTest für felddest[1,1] fehlgeschlagen.", new String[][] {{zwischenzustand}}, gof.felddest);
		GameOfLife gof1 = new GameOfLife(2,3);
		assertArrayEquals("InitializeFieldTest für feldsrc[2,3] fehlgeschlagen.", new String[][] {{zwischenzustand, zwischenzustand, zwischenzustand}, 
																									{zwischenzustand, zwischenzustand, zwischenzustand}
																								   },
																									gof1.feldsrc);
		assertArrayEquals("InitializeFieldTest für felddest[2,3] fehlgeschlagen.", new String[][] {{zwischenzustand, zwischenzustand, zwischenzustand},
																									{zwischenzustand, zwischenzustand, zwischenzustand}
																								   },
																									gof1.felddest);
	}

	@Test
	public void testGenerateNextGeneration() {
		GameOfLife gof = new GameOfLife(1,1);
		gof.feldsrc[0][0] = "*";
		gof.felddest[0][0] = "x";
		gof.generateNextGeneration();
		assertArrayEquals("Teste die Erstellung des Zielfeldes der nächsten Generation", new String[][]{{"x"}}, gof.felddest);
		GameOfLife gof1 = new GameOfLife(3,3);
		String[][] testfeldSrc = new String[][] {{".", "*", "."}, 
													{"x", "*", "x"}, 
													{".", "*", "."}};
			
        gof1.feldsrc = testfeldSrc;
		String[][] testfeldDest = new String[][] {{".", "x", "."}, 
													 {"*", "*", "*"}, 
													 {".", "x", "."}};
		gof1.generateNextGeneration();
		assertArrayEquals("Teste die Erstellung [3,3] des Zielfeldes der nächsten Generation", testfeldDest, gof1.felddest);	
	}

}
