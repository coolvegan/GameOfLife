
public class GameOfLife {

	public static void main(String[] args) 
	{
		String lebend = "*";
		String gestorben = "x";
		
		String[][] feld = {
						   		{".", "*", "."},
						   		{"x", "*", "x"},
						   		{".", "*", "."},
						   };
		String[][] ziel;
		int count = 0;
		while(count < 10)
		{
			ziel = new String[3][3];
			for (int zeile = 0; zeile < feld.length ; zeile++) 
			{
				for (int spalte = 0; spalte < feld[zeile].length ; spalte++)
				{
					int neighbours = 0;
					//teste links
						ziel[zeile][spalte] = feld[zeile][spalte] ;
						
						if ((spalte > 0) && (feld[zeile][spalte-1]) == lebend)
						{
							neighbours++;
						}
					//teste rechts
						if ((spalte < feld[zeile].length-1) && (feld[zeile][spalte+1]) == lebend)
						{
							neighbours++;	
						}
					//teste oben
						if ((zeile > 0) && (feld[zeile-1][spalte]) == lebend)
						{
							neighbours++;
						}
					//teste unten
						if ((zeile < feld.length-1) && (feld[zeile+1][spalte]) == lebend)
						{
							neighbours++;
						}					
					//teste linksOben
						if ((spalte > 0) && (zeile > 0) && (feld[zeile-1][spalte-1]) == lebend)
						{
							neighbours++;
						}		
					//teste rechtsOben
						if ((spalte < feld[zeile].length-1) && (zeile > 0) && (feld[zeile-1][spalte+1]) == lebend)
						{
							neighbours++;
						}						
					//teste linksUnten
						if ((spalte > 0) && (zeile < feld.length-1) && (feld[zeile+1][spalte-1]) == lebend)
						{
							neighbours++;
						}

					//teste rechtsUnten
						if ((spalte < feld[zeile].length-1) && (zeile < feld.length-1) && (feld[zeile+1][spalte+1]) == lebend)
						{
							neighbours++;
						}					
						
						if ((feld[zeile][spalte] == lebend) && (neighbours < 2 ))
						{
							ziel[zeile][spalte] = gestorben;
						}
						
						//lebt -> Überlebt
						if ((feld[zeile][spalte] == lebend) && ((neighbours == 3 ) || (neighbours == 4 )))
						{
							ziel[zeile][spalte] = lebend;	
						}
						
						//lebt -> stirbt an übervölkerung
						if ((feld[zeile][spalte] == lebend) && (neighbours > 4 ))
						{
							ziel[zeile][spalte] = gestorben;	
						}
						//tot -> wird wiederbelebt
						if ((feld[zeile][spalte] == gestorben) && (neighbours == 3 ))
						{
							ziel[zeile][spalte] = lebend;	
						}
				}
				
			}
			for (String[] element : ziel) {
		
				for (String element2 : element) {
					System.out.print(element2);	
				}
				System.out.print('\n');
			}
			feld = ziel;
			System.out.print('\n');
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
		}
	}

}
