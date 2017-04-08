public class GameOfLife {
	private 
		String lebend = "*";
		String gestorben = "x";
		String  zwischenzustand = ".";
		String[][] feldsrc, felddest;
		int neighbours = 0;
		int dimx, dimy;


	GameOfLife(int dimx, int dimy)
	{
		this.dimx = dimx;
		this.dimy = dimy;
		initializeField();
	}
	
	void noNeighbours() {
		neighbours = 0;
	}
	
	void generateDestinationField(int zeile, int spalte) 
	{
		if ((feldsrc[zeile][spalte] == lebend) && (neighbours < 2 ))
		{
			felddest[zeile][spalte] = gestorben;
		}
		
		//lebt -> Überlebt
		if ((feldsrc[zeile][spalte] == lebend) && ((neighbours == 3 ) || (neighbours == 4 )))
		{
			felddest[zeile][spalte] = lebend;	
		}
		
		//lebt -> stirbt an übervölkerung
		if ((feldsrc[zeile][spalte] == lebend) && (neighbours > 4 ))
		{
			felddest[zeile][spalte] = gestorben;	
		}
		//tot -> wird wiederbelebt
		if ((feldsrc[zeile][spalte] == gestorben) && (neighbours == 3 ))
		{
			felddest[zeile][spalte] = lebend;	
		}
	}
	
	void calculateNeighbours(int zeile, int spalte) 
	{
		felddest[zeile][spalte] = feldsrc[zeile][spalte] ;
		//teste links				
			if ((spalte > 0) && (feldsrc[zeile][spalte-1]) == lebend)
			{
				neighbours++;
			}
		//teste rechts
			if ((spalte < dimx-1) && (feldsrc[zeile][spalte+1]) == lebend)
			{
				neighbours++;	
			}
		//teste oben
			if ((zeile > 0) && (feldsrc[zeile-1][spalte]) == lebend)
			{
				neighbours++;
			}
		//teste unten
			if ((zeile < dimy-1) && (feldsrc[zeile+1][spalte]) == lebend)
			{
				neighbours++;
			}					
		//teste linksOben
			if ((spalte > 0) && (zeile > 0) && (feldsrc[zeile-1][spalte-1]) == lebend)
			{
				neighbours++;
			}		
		//teste rechtsOben
			if ((spalte < dimy-1) && (zeile > 0) && (feldsrc[zeile-1][spalte+1]) == lebend)
			{
				neighbours++;
			}						
		//teste linksUnten
			if ((spalte > 0) && (zeile < dimy-1) && (feldsrc[zeile+1][spalte-1]) == lebend)
			{
				neighbours++;
			}
		//teste rechtsUnten
			if ((spalte < dimx-1) && (zeile < dimy-1) && (feldsrc[zeile+1][spalte+1]) == lebend)
			{
				neighbours++;
			}			
	}
	
	void makeNextGenerationCurrentGeneration() 
	{
		feldsrc = felddest;
	}
	
	void generateNextGeneration() 
	{
		initializeDestinationField();
		for (int zeile = 0; zeile < dimy ; zeile++) 
		{
			for (int spalte = 0; spalte < dimx; spalte++)
			{
				noNeighbours();
				calculateNeighbours(zeile, spalte);
				generateDestinationField(zeile, spalte);
			}
		}
		makeNextGenerationCurrentGeneration();
	}
	
	void initializeSourceField()
	{
		feldsrc = new String[dimx][dimy];
		for (int i = 0; i < dimy; i++) {
			for (int j = 0; j < dimx; j++) {
				feldsrc[j][i] = ".";
			}
		}
	}
	
	void initializeDestinationField()
	{
		felddest = new String[dimx][dimy];
		for (int i = 0; i < dimy; i++) {
			for (int j = 0; j < dimx; j++) {
				felddest[j][i] = ".";
			}
		}
	}

	void initializeField() {
		initializeSourceField();
		initializeDestinationField();	
	}
	
	void printToconsole()
	{
		for (int i = 0; i < dimy; i++) {
			for (int j = 0; j < dimx; j++) {
				System.out.print(felddest[i][j]);
			}	
			System.out.print('\n');
		}
		System.out.print('\n');		
	}

	public static void main(String[] args) 
	{
		GameOfLife gol = new GameOfLife(3,3);
		String[][] testfeldSrc = new String[][]{{".", "*", "."}, {"x", "*", "x"},{".", "*", "."}};
		gol.feldsrc = testfeldSrc;
		int count = 0;
		while(count < 10)
		{
			gol.generateNextGeneration();		
			gol.printToconsole();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
		}
	}
}
