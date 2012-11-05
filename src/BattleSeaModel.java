import java.util.Observable;
import java.util.Vector;


//Fabio Sorrentino Matr.147304 Ingegneria del Cinema e dei Mezzi di Comunicazione

public class BattleSeaModel extends Observable
{
	private int righe, colonne, i = -1, k = 0, ctrl1 = 0, ctrl2 = 0;
	private int mx[][];
	public int genius = 0, punteggio = 0;
	public 	int x, y, xx, yy;
	public int z = 0;
	public int g, gg, hh, h, dir, fir = 0;
	
	public Vector<Integer> riPositionPlayer = new Vector<Integer>();
	
	private boolean checkTurn = true;
	public boolean firsthit = true;
	public boolean firsthitPlayer = true;
	public boolean checkstart = false;
	
	
	
	public BattleSeaModel(int riga, int colonna)
	{
		righe = riga;
		colonne = colonna;
		mx = new int[righe][colonne];
		svuotaNavi();
	}
	
	public void svuotaNavi()
	{
		for(int i=0; i<12; i++)
		{
			for(int j=0; j<24; j++)
			{
				mx[i][j]=0;
			}
		}
		setChanged();
		notifyObservers();
	}
	
	public void mescola(int range)
	{
		int n = 0 + range;
		int mrighe = 1;
		int m = 1 + range;
		int mGiocatore;
		int i, j1, k, tamp, tampR, tampC;
		boolean z = true;
		boolean z1 = true;
		int v[] = new int[10];

		//Vettore contenente le informazioni sulle navi
		v[0]=4;
		v[1]=3;
		v[2]=3;
		v[3]=2;
		v[4]=2;
		v[5]=2;
		v[6]=1;
		v[7]=1;
		v[8]=1;
		v[9]=1;
		
		j1=0;
		k=0;
		while(j1<1000)
		{
			for(k=0; k<10; k++)
			{
				while(z)
				{
					if(j1>1000)
						{
						z = false;
						}
					j1++;
	    			int RANGE = m-n+1;
	  				tamp = (int)(RANGE*Math.random());
					switch(tamp)
					{
						case 0: 
									mGiocatore = 10;
									int RANGE0 = mGiocatore-mrighe+1;
									tampR = (int)(RANGE0*Math.random())+mrighe;
									
									mGiocatore = 10 + range - v[k];
									int RANGE1 = mGiocatore-m+1;
									tampC = (int)(RANGE1*Math.random())+m;
									
									z1 = controlloInsertH(tampR, tampC, v[k]);
									
									if(z1)
									{
										for(i=0; i<v[k]; i++)
										{
											mx[tampR][tampC+i]=1;
										}
										SeaAroundNaviH(tampR, tampC, v[k]);
	
										z = false;
									}
									
								
									
								break;
								
						case 1:
									mGiocatore = 10;
									int RANGE2 = mGiocatore + range-m+1;
									tampC = (int)(RANGE2*Math.random())+m;
									
									mGiocatore = 10 - v[k];
									int RANGE3 = mGiocatore-mrighe+1;
									tampR = (int)(RANGE3*Math.random())+mrighe;
									
									z1 = controlloInsertV(tampR, tampC, v[k]);
									
									if(z1)
									{
										for(i=0; i<v[k]; i++)
										{
											mx[tampR+i][tampC]=1;
										}
										SeaAroundNaviV(tampR, tampC, v[k]);
			
										z = false;
									}
								break;
		  			}
	  			}
	  			z = true;
			}
			if(k>9)
				j1=1001;
		}
		if(k<9)
		{
			svuotaNavi();
			mescola(range);
		}
		setChanged();
		notifyObservers();
	}

	private boolean controlloInsertH(int i, int j, int lung)
	{
		for(int k=0; k<lung; k++)
		{
			if(mx[i][j+k]!=0)
			{
				return false;
			}
			if(mx[i-1][j+k]!=0)
			{
				return false;
			}
			if(mx[i+1][j+k]!=0)
			{
				return false;
			}
		}
		return true;
	}
	
	private boolean controlloInsertV(int i, int j, int lung)
	{
		for(int k=0; k<lung; k++)
		{
			if(mx[i+k][j]!=0)
			{
				return false;
			}
			if(mx[i+k][j-1]!=0)
			{
				return false;
			}
			if(mx[i+k][j+1]!=0)
			{
				return false;
			}
		}
		
		return true;
	}
	
	private void SeaAroundNaviH(int i, int j, int lunghezza)
	{
		mx[i][j-1]=2;
		mx[i-1][j-1]=2;
		mx[i+1][j-1]=2;
		mx[i][j+lunghezza]=2;
		mx[i-1][j+lunghezza]=2;
		mx[i+1][j+lunghezza]=2;
		for(int k=0; k<lunghezza; k++)
		{
			mx[i-1][j+k]=2;
			mx[i+1][j+k]=2;
		}
	}
	
	private void SeaAroundNaviV(int i, int j, int lunghezza)
	{
		mx[i-1][j]=2;
		mx[i-1][j-1]=2;
		mx[i-1][j+1]=2;
		mx[i+lunghezza][j]=2;
		mx[i+lunghezza][j-1]=2;
		mx[i+lunghezza][j+1]=2;
		for(int k=0; k<lunghezza; k++)
		{
			mx[i+k][j-1]=2;
			mx[i+k][j+1]=2;
		}
	}
	
	private void SeaAroundNaviH1(int i, int j, int lunghezza)
	{
		mx[i][j-1]=8;
		mx[i-1][j-1]=8;
		mx[i+1][j-1]=8;
		mx[i][j+lunghezza]=8;
		mx[i-1][j+lunghezza]=8;
		mx[i+1][j+lunghezza]=8;
		for(int k=0; k<lunghezza; k++)
		{
			mx[i-1][j+k]=8;
			mx[i+1][j+k]=8;
		}
		//setChanged();
		//notifyObservers();
	}
	
	private void SeaAroundNaviV1(int i, int j, int lunghezza)
	{
		mx[i-1][j]=8;
		mx[i-1][j-1]=8;
		mx[i-1][j+1]=8;
		mx[i+lunghezza][j]=8;
		mx[i+lunghezza][j-1]=8;
		mx[i+lunghezza][j+1]=8;
		for(int k=0; k<lunghezza; k++)
		{
			mx[i+k][j-1]=8;
			mx[i+k][j+1]=8;
		}
		//setChanged();
		//notifyObservers();
	}
	
	private void SeaAroundNaviH2(int i, int j, int lunghezza)
	{
		//Riempio anche il vettore di mare cosi non lo puoi ricliccare
		mx[i][j-1]=6;
		riPositionPlayer.addElement(i);
		riPositionPlayer.addElement(j-1);
		mx[i-1][j-1]=6;
		riPositionPlayer.addElement(i-1);
		riPositionPlayer.addElement(j-1);
		mx[i+1][j-1]=6;
		riPositionPlayer.addElement(i+1);
		riPositionPlayer.addElement(j-1);
		mx[i][j+lunghezza]=6;
		riPositionPlayer.addElement(i);
		riPositionPlayer.addElement(j+lunghezza);
		mx[i-1][j+lunghezza]=6;
		riPositionPlayer.addElement(i-1);
		riPositionPlayer.addElement(j+lunghezza);
		mx[i+1][j+lunghezza]=6;
		riPositionPlayer.addElement(i+1);
		riPositionPlayer.addElement(j+lunghezza);
		for(int k=0; k<lunghezza; k++)
		{
			mx[i-1][j+k]=6;
			riPositionPlayer.addElement(i-1);
			riPositionPlayer.addElement(j+k);
			mx[i+1][j+k]=6;
			riPositionPlayer.addElement(i+1);
			riPositionPlayer.addElement(j+k);
		}
		setChanged();
		notifyObservers();
	}
	
	private void SeaAroundNaviV2(int i, int j, int lunghezza)
	{
		mx[i-1][j]=6;
		riPositionPlayer.addElement(i-1);
		riPositionPlayer.addElement(j);
		mx[i-1][j-1]=6;
		riPositionPlayer.addElement(i-1);
		riPositionPlayer.addElement(j-1);
		mx[i-1][j+1]=6;
		riPositionPlayer.addElement(i-1);
		riPositionPlayer.addElement(j+1);
		mx[i+lunghezza][j]=6;
		riPositionPlayer.addElement(i+lunghezza);
		riPositionPlayer.addElement(j);
		mx[i+lunghezza][j-1]=6;
		riPositionPlayer.addElement(i+lunghezza);
		riPositionPlayer.addElement(j-1);
		mx[i+lunghezza][j+1]=6;
		riPositionPlayer.addElement(i+lunghezza);
		riPositionPlayer.addElement(j+1);
		for(int k=0; k<lunghezza; k++)
		{
			mx[i+k][j-1]=6;
			riPositionPlayer.addElement(i+k);
			riPositionPlayer.addElement(j-1);
			mx[i+k][j+1]=6;
			riPositionPlayer.addElement(i+k);
			riPositionPlayer.addElement(j+1);
		}
		setChanged();
		notifyObservers();
	}
	
	public void inserisciMare(int Val)
	{
		if(Val==1)
		{
			for(int i=0; i<12; i++)
			{
				for(int j=0; j<12; j++)
				{
					if(mx[i][j]==0)
					{
						mx[i][j]=2;
					}
				}
			}
			setChanged();
			notifyObservers();
		}
		else if(Val==2)
		{
			for(int i=0; i<12; i++)
			{
				for(int j=12; j<24; j++)
				{
					if(mx[i][j]==0)
					{
						mx[i][j]=2;
					}
				}
			}
			setChanged();
			notifyObservers();
		}
		
	}

	public void start()
	{
		checkstart = true;
		
		if(checkTurn(checkTurn))
		{
			generAndHit();
		}
		setChanged();
		notifyObservers();
	}
	
	public void stop()
	{
		checkstart = false;
		ctrl1 = ctrl2 = 0;
		
		removeVect();
		setChanged();
		notifyObservers();
	}
	
	public int getValMatrix(int i, int j)
	{
		return mx[i][j];
	}
	
	public String getLabelString() 
	{
		String s = new String();
		if(checkstart)
		{
			if(i==(-1))
			{
				i++;
			}
			if(i==0)
			{
				s = " Gioco avviato! Tocca a te... ";
				i++;
			}
			else
			{
				if(i%2!=0)
				{
					s = " Tocca a te... ";
					i=i+1;
		
				}
				else
				{
					if(k==1)
					{
						s = " Colpito! Tocca a te... ";
						i=i+2;
					}
					else if(k==2)
					{
						s = " ah ah Colpito e Affondato! Tocca a te... ";
						i=i+2;
					}
					else if(k==3)
					{
						s = " Mare! Tocca a te... ";
						i=i+2;
					}
					else if(k==7)
					{
						s = " Mossa già effettuata... ";
						i=i+2;
					}
				}
			}
		}
		else
		{
			s = " Setup Navi ";
		}

		return s;
	}
	
	public String getPunteggio()
	{
		String s = new String();
		
		if(checkstart)
		{
			s = "Punti: " + calcolaPunteggio();
		}
		else
		{
			punteggio = 0;
		}
		
		return s;
	}
	
	public int calcolaPunteggio()
	{
		return punteggio;
	}

	public boolean getCordinateLabel(String s)
	{
		int i, j;
		
		String[] split = s.split(" ");
		
		i = Integer.parseInt(split[0]);
		j = Integer.parseInt(split[1]);
		
		if(firsthitPlayer)
		{	
			riPositionPlayer.add(i);
			riPositionPlayer.add(j);
			checkPosition(i, j, 2);
			firsthitPlayer=false;

			return false;
		}
		else
		{
			riPositionPlayer.add(i);
			riPositionPlayer.add(j);
			for(int k = riPositionPlayer.size()-4; k>=0; k=k-2)
			{
				if(riPositionPlayer.elementAt(k) == i)
				{
					if(riPositionPlayer.elementAt(k+1) == j)
					{
						riPositionPlayer.removeElementAt(riPositionPlayer.size()-1);
						riPositionPlayer.removeElementAt(riPositionPlayer.size()-1);
						k=7;
						return true;
					}
				}
			}
		}
		checkPosition(i,j,2);
		return false;
	}
	
	public void checkPosition(int i, int j, int matrix)
	{
		if(matrix==1)
		{
			int checkctrl = ctrl1;
			if(getValMatrix(i, j)==1)
			{
				mx[i][j]=3;
				k=1;

				//Horizontal
				if((getValMatrix(i-1, j) == 2 && getValMatrix(i+1, j) == 2) || (getValMatrix(i-1, j) == 8 && getValMatrix(i+1, j) == 8) || (getValMatrix(i-1, j) == 8 && getValMatrix(i+1, j) == 2) || (getValMatrix(i-1, j) == 2 && getValMatrix(i+1, j) == 8))
				{
					if((getValMatrix(i, j-1) == 2 && getValMatrix(i, j+1) == 2) || (getValMatrix(i, j-1) == 8 && getValMatrix(i, j+1) == 8) || (getValMatrix(i, j-1) == 2 && getValMatrix(i, j+1) == 8) || (getValMatrix(i, j-1) == 8 && getValMatrix(i, j+1) == 2))
					{
						//Nave da 1
						SeaAroundNaviH1(i,j,1);
						mx[i][j]=4;
						
						k=2;
						genius = 0;
						ctrl1++;
					}
					else if((getValMatrix(i, j-1) == 2 && getValMatrix(i, j+1) == 3) || (getValMatrix(i, j-1) == 8 && getValMatrix(i, j+1) == 3))
					{
						if((getValMatrix(i, j+2) == 2) || (getValMatrix(i, j+2) == 8))
						{
							//Nave da 2 orizzontale S/D
							SeaAroundNaviH1(i,j,2);
							mx[i][j]=4;
							mx[i][j+1]=4;
							
							k=2;
							genius = 0;
							ctrl1++;
						}
						else if(((getValMatrix(i, j+2) == 3 && getValMatrix(i, j+3) == 2)) || ((getValMatrix(i, j+2) == 3 && getValMatrix(i, j+3) == 8)))
						{
							//Nave da 3 orizzontale S/D
							SeaAroundNaviH1(i,j,3);
							mx[i][j]=4;
							mx[i][j+1]=4;
							mx[i][j+2]=4;
							
							k=2;
							genius = 0;
							ctrl1++;
						}
						else if(getValMatrix(i, j+2) == 3 && getValMatrix(i, j+3) == 3)
						{
							//Nave da 4 orizzontale S/D
							SeaAroundNaviH1(i,j,4);
							mx[i][j]=4;
							mx[i][j+1]=4;
							mx[i][j+2]=4;
							mx[i][j+3]=4;
							
							k=2;
							genius = 0;
							ctrl1++;
						}
					}
					else if((getValMatrix(i, j-1) == 3 && getValMatrix(i, j+1) == 2) || (getValMatrix(i, j-1) == 3 && getValMatrix(i, j+1) == 8))
					{
						if((getValMatrix(i, j-2) == 2) || (getValMatrix(i, j-2) == 8))
						{
							//Nave da 2 orizzontale D/S
							SeaAroundNaviH1(i,j-1,2);
							mx[i][j]=4;
							mx[i][j-1]=4;
							
							k=2;
							genius = 0;
							ctrl1++;
						}
						else if((getValMatrix(i, j-2) == 3 && getValMatrix(i, j-3) == 2) || (getValMatrix(i, j-2) == 3 && getValMatrix(i, j-3) == 8))
						{ 
							//Nave da 3 orizzontale D/S
							SeaAroundNaviH1(i,j-2,3);
							mx[i][j]=4;
							mx[i][j-1]=4;
							mx[i][j-2]=4;
							
							k=2;
							genius = 0;
							ctrl1++;
						}
						else if(getValMatrix(i, j-2) == 3 && getValMatrix(i, j-3) == 3)
						{
							//Nave da 4 orizzontale D/S
							SeaAroundNaviH1(i,j-3,4);
							mx[i][j]=4;
							mx[i][j-1]=4;
							mx[i][j-2]=4;
							mx[i][j-3]=4;
							
							k=2;
							genius = 0;
							ctrl1++;
						}
					}
					else if((getValMatrix(i, j-1) == 3 && getValMatrix(i, j+1) == 3))
					{
						//Nave da 3 orizzontale C
						if(((getValMatrix(i, j-2) == 2) && (getValMatrix(i, j+2)) == 2) || ((getValMatrix(i, j-2) == 8) && (getValMatrix(i, j+2)) == 8) || ((getValMatrix(i, j-2) == 2) && (getValMatrix(i, j+2)) == 8) || ((getValMatrix(i, j-2) == 8) && (getValMatrix(i, j+2)) == 2))
						{
							SeaAroundNaviH1(i,j-1,3);
							mx[i][j]=4;
							mx[i][j-1]=4;
							mx[i][j+1]=4;
							
							k=2;
							genius = 0;
							ctrl1++;
						}
						else if(((getValMatrix(i, j-2) == 2) && (getValMatrix(i, j+2)) == 3) || ((getValMatrix(i, j-2) == 8) && (getValMatrix(i, j+2)) == 3))
						{
							//Nave da 4 orizzontale C1
							SeaAroundNaviH1(i,j-1,4);
							mx[i][j]=4;
							mx[i][j-1]=4;
							mx[i][j+1]=4;
							mx[i][j+2]=4;
							
							k=2;
							genius = 0;
							ctrl1++;
						}
						else if(((getValMatrix(i, j-2) == 3) && (getValMatrix(i, j+2)) == 2) || ((getValMatrix(i, j-2) == 3) && (getValMatrix(i, j+2)) == 8))
						{
							//Nave da 4 orizzontale C2
							SeaAroundNaviH1(i,j-2,4);
							mx[i][j]=4;
							mx[i][j-1]=4;
							mx[i][j-2]=4;
							mx[i][j+1]=4;
							
							k=2;
							genius = 0;
							ctrl1++;
						}
					}	
				}
				//Vertical
				else
				{
					if((getValMatrix(i-1, j) == 8 && getValMatrix(i+1, j) == 3) || (getValMatrix(i-1, j) == 2 && getValMatrix(i+1, j) == 3))
					{
						if((getValMatrix(i+2, j) == 8) || (getValMatrix(i+2, j) == 2))
						{
							//Nave da 2 Verticale A/B
							SeaAroundNaviV1(i,j,2);
							mx[i][j]=4;
							mx[i+1][j]=4;
							
							k=2;
							genius = 0;
							ctrl1++;
						}
						else if((getValMatrix(i+2, j) == 3 && getValMatrix(i+3, j) == 2) || (getValMatrix(i+2, j) == 3 && getValMatrix(i+3, j) == 8))
						{
							//Nave da 3 Verticale A/B
							SeaAroundNaviV1(i,j,3);
							mx[i][j]=4;
							mx[i+1][j]=4;
							mx[i+2][j]=4;
							
							k=2;
							genius = 0;
							ctrl1++;
						}
						else if(getValMatrix(i+2, j) == 3 && getValMatrix(i+3, j) == 3)
						{
							//Nave da 4 Verticale A/B
							SeaAroundNaviV1(i,j,4);
							mx[i][j]=4;
							mx[i+1][j]=4;
							mx[i+2][j]=4;
							mx[i+3][j]=4;
							
							k=2;
							genius = 0;
							ctrl1++;
						}
					}
					else if((getValMatrix(i-1, j) == 3 && getValMatrix(i+1, j) == 2) || (getValMatrix(i-1, j) == 3 && getValMatrix(i+1, j) == 8))
					{
						if((getValMatrix(i-2, j) == 2) || (getValMatrix(i-2, j) == 8))
						{
							//Nave da 2 Verticale B/A
							SeaAroundNaviV1(i-1,j,2);
							mx[i][j]=4;
							mx[i-1][j]=4;
							
							k=2;
							genius = 0;
							ctrl1++;
						}
						else if((getValMatrix(i-2, j) == 3 && getValMatrix(i-3, j) == 2) || (getValMatrix(i-2, j) == 3 && getValMatrix(i-3, j) == 8))
						{
							//Nave da 3 Verticale B/A
							SeaAroundNaviV1(i-2,j,3);
							mx[i][j]=4;
							mx[i-1][j]=4;
							mx[i-2][j]=4;
							
							k=2;
							genius = 0;
							ctrl1++;
						}
						else if(getValMatrix(i-2, j) == 3 && getValMatrix(i-3, j) == 3)
						{
							//Nave da 4 Verticale B/A
							SeaAroundNaviV1(i-3,j,4);
							mx[i][j]=4;
							mx[i-1][j]=4;
							mx[i-2][j]=4;
							mx[i-3][j]=4;
							
							k=2;
							genius = 0;
							ctrl1++;
						}
					}
					else if((getValMatrix(i-1, j) == 3 && getValMatrix(i+1, j) == 3))
					{
						//Nave da 3 Verticale C
						if(((getValMatrix(i-2, j) == 2) && (getValMatrix(i+2, j)) == 2) || ((getValMatrix(i-2, j) == 8) && (getValMatrix(i+2, j)) == 8) || ((getValMatrix(i-2, j) == 8) && (getValMatrix(i+2, j)) == 2) || ((getValMatrix(i-2, j) == 2) && (getValMatrix(i+2, j)) == 8))
						{
							SeaAroundNaviV1(i-1,j,3);
							mx[i][j]=4;
							mx[i-1][j]=4;
							mx[i+1][j]=4;
							
							k=2;
							genius = 0;
							ctrl1++;
						}
						else if(((getValMatrix(i-2, j) == 2) && (getValMatrix(i+2, j)) == 3) || ((getValMatrix(i-2, j) == 8) && (getValMatrix(i+2, j)) == 3))
						{
							//Nave da 4 Verticale C1
							SeaAroundNaviV1(i-1,j,4);
							mx[i][j]=4;
							mx[i-1][j]=4;
							mx[i+1][j]=4;
							mx[i+2][j]=4;
							
							k=2;
							genius = 0;
							ctrl1++;
						}
						else if(((getValMatrix(i-2, j) == 3) && (getValMatrix(i+2, j)) == 2) || ((getValMatrix(i-2, j) == 3) && (getValMatrix(i+2, j)) == 8))
						{
							//Nave da 4 Verticale C2
							SeaAroundNaviV1(i-2,j,4);
							mx[i][j]=4;
							mx[i-1][j]=4;
							mx[i-2][j]=4;
							mx[i+1][j]=4;
							
							k=2;
							genius = 0;
							ctrl1++;
						}
					}	
				}
				if(checkctrl == ctrl1)
				{
					//Vuol dire che ha colpito ma non affondato
					g = i;
					h = j;
					genius++;
					if(genius == 1)
					{
						xx = i;
						yy = j;
					}
				}
				
				setChanged();
			}
			else if(getValMatrix(i, j)==2)
			{
				mx[i][j]=8;
				k = 3;
				setChanged();
			}
			
		}
		//Matrice in cui colpisce il giocatore
		else if(matrix==2)
		{
			if((getValMatrix(i, j)==1) || (getValMatrix(i, j)==5))
			{
				mx[i][j]=5;
				riPositionPlayer.add(i);
				riPositionPlayer.add(j);
				punteggio = punteggio + 50;
				//Horizontal
				if((getValMatrix(i-1, j) == 6 && getValMatrix(i+1, j) == 6) || (getValMatrix(i-1, j) == 2 && getValMatrix(i+1, j) == 2) || (getValMatrix(i-1, j) == 2 && getValMatrix(i+1, j) == 6) || (getValMatrix(i-1, j) == 6 && getValMatrix(i+1, j) == 2))
				{
					if((getValMatrix(i, j-1) == 6 && getValMatrix(i, j+1) == 6) || (getValMatrix(i, j-1) == 2 && getValMatrix(i, j+1) == 2) || (getValMatrix(i, j-1) == 2 && getValMatrix(i, j+1) == 6) || (getValMatrix(i, j-1) == 6 && getValMatrix(i, j+1) == 2))
					{
						//Nave da 1
						SeaAroundNaviH2(i,j,1);
						mx[i][j]=7;
						ctrl2++;
						punteggio = punteggio + 300;
					}
					else if((getValMatrix(i, j-1) == 6 && getValMatrix(i, j+1) == 5) || (getValMatrix(i, j-1) == 2 && getValMatrix(i, j+1) == 5))
					{
						if((getValMatrix(i, j+2) == 6) || (getValMatrix(i, j+2) == 2))
						{
							//Nave da 2 orizzontale S/D
							SeaAroundNaviH2(i,j,2);
							mx[i][j]=7;
							mx[i][j+1]=7;
							ctrl2++;
							punteggio = punteggio + 250;
						}
						else if((getValMatrix(i, j+2) == 5 && getValMatrix(i, j+3) == 6) || (getValMatrix(i, j+2) == 5 && getValMatrix(i, j+3) == 2))
						{
							//Nave da 3 orizzontale S/D
							SeaAroundNaviH2(i,j,3);
							mx[i][j]=7;
							mx[i][j+1]=7;
							mx[i][j+2]=7;
							ctrl2++;
							punteggio = punteggio + 200;
						}
						else if(getValMatrix(i, j+2) == 5 && getValMatrix(i, j+3) == 5)
						{
							//Nave da 4 orizzontale S/D
							SeaAroundNaviH2(i,j,4);
							mx[i][j]=7;
							mx[i][j+1]=7;
							mx[i][j+2]=7;
							mx[i][j+3]=7;
							ctrl2++;
							punteggio = punteggio + 150;
						}
					}
					else if((getValMatrix(i, j-1) == 5 && getValMatrix(i, j+1) == 6) || (getValMatrix(i, j-1) == 5 && getValMatrix(i, j+1) == 2))
					{
						if((getValMatrix(i, j-2) == 6) || (getValMatrix(i, j-2) == 2))
						{
							//Nave da 2 orizzontale D/S
							SeaAroundNaviH2(i,j-1,2);
							mx[i][j]=7;
							mx[i][j-1]=7;
							ctrl2++;
							punteggio = punteggio + 250;
						}
						else if((getValMatrix(i, j-2) == 5 && getValMatrix(i, j-3) == 6) || (getValMatrix(i, j-2) == 5 && getValMatrix(i, j-3) == 2))
						{
							//Nave da 3 orizzontale D/S
							SeaAroundNaviH2(i,j-2,3);
							mx[i][j]=7;
							mx[i][j-1]=7;
							mx[i][j-2]=7;
							ctrl2++;
							punteggio = punteggio + 200;
						}
						else if(getValMatrix(i, j-2) == 5 && getValMatrix(i, j-3) == 5)
						{
							//Nave da 4 orizzontale D/S
							SeaAroundNaviH2(i,j-3,4);
							mx[i][j]=7;
							mx[i][j-1]=7;
							mx[i][j-2]=7;
							mx[i][j-3]=7;
							ctrl2++;
							punteggio = punteggio + 150;
						}
					}
					else if((getValMatrix(i, j-1) == 5 && getValMatrix(i, j+1) == 5))
					{
						//Nave da 3 orizzontale C
						if(((getValMatrix(i, j-2) == 6) && (getValMatrix(i, j+2)) == 6) || ((getValMatrix(i, j-2) == 2) && (getValMatrix(i, j+2)) == 2) || ((getValMatrix(i, j-2) == 2) && (getValMatrix(i, j+2)) == 6) || ((getValMatrix(i, j-2) == 6) && (getValMatrix(i, j+2)) == 2))
						{
							SeaAroundNaviH2(i,j-1,3);
							mx[i][j]=7;
							mx[i][j-1]=7;
							mx[i][j+1]=7;
							ctrl2++;
							punteggio = punteggio + 200;
						}
						else if(((getValMatrix(i, j-2) == 6) && (getValMatrix(i, j+2)) == 5) || ((getValMatrix(i, j-2) == 2) && (getValMatrix(i, j+2)) == 5))
						{
							//Nave da 4 orizzontale C1
							SeaAroundNaviH2(i,j-1,4);
							mx[i][j]=7;
							mx[i][j-1]=7;
							mx[i][j+1]=7;
							mx[i][j+2]=7;
							ctrl2++;
							punteggio = punteggio + 150;
						}
						else if(((getValMatrix(i, j-2) == 5) && (getValMatrix(i, j+2)) == 6) || ((getValMatrix(i, j-2) == 5) && (getValMatrix(i, j+2)) == 2))
						{
							//Nave da 4 orizzontale C2
							SeaAroundNaviH2(i,j-2,4);
							mx[i][j]=7;
							mx[i][j-1]=7;
							mx[i][j-2]=7;
							mx[i][j+1]=7;
							ctrl2++;
							punteggio = punteggio + 150;
						}
					}	
				}
				//Vertical
				else
				{
					if((getValMatrix(i-1, j) == 6 && getValMatrix(i+1, j) == 5) || (getValMatrix(i-1, j) == 2 && getValMatrix(i+1, j) == 5))
					{
						if((getValMatrix(i+2, j) == 6) || (getValMatrix(i+2, j) == 2))
						{
							//Nave da 2 Verticale A/B
							SeaAroundNaviV2(i,j,2);
							mx[i][j]=7;
							mx[i+1][j]=7;
							ctrl2++;
							punteggio = punteggio + 250;
						}
						else if((getValMatrix(i+2, j) == 5 && getValMatrix(i+3, j) == 6) || (getValMatrix(i+2, j) == 5 && getValMatrix(i+3, j) == 2))
						{
							//Nave da 3 Verticale A/B
							SeaAroundNaviV2(i,j,3);
							mx[i][j]=7;
							mx[i+1][j]=7;
							mx[i+2][j]=7;
							ctrl2++;
							punteggio = punteggio + 200;
						}
						else if(getValMatrix(i+2, j) == 5 && getValMatrix(i+3, j) == 5)
						{
							//Nave da 4 Verticale A/B
							SeaAroundNaviV2(i,j,4);
							mx[i][j]=7;
							mx[i+1][j]=7;
							mx[i+2][j]=7;
							mx[i+3][j]=7;
							ctrl2++;
							punteggio = punteggio + 150;
						}
					}
					else if((getValMatrix(i-1, j) == 5 && getValMatrix(i+1, j) == 6) || (getValMatrix(i-1, j) == 5 && getValMatrix(i+1, j) == 2))
					{
						if((getValMatrix(i-2, j) == 6) || (getValMatrix(i-2, j) == 2))
						{
							//Nave da 2 Verticale B/A
							SeaAroundNaviV2(i-1,j,2);
							mx[i][j]=7;
							mx[i-1][j]=7;
							ctrl2++;
							punteggio = punteggio + 250;
						}
						else if((getValMatrix(i-2, j) == 5 && getValMatrix(i-3, j) == 6) || (getValMatrix(i-2, j) == 5 && getValMatrix(i-3, j) == 2))
						{
							//Nave da 3 Verticale B/A
							SeaAroundNaviV2(i-2,j,3);
							mx[i][j]=7;
							mx[i-1][j]=7;
							mx[i-2][j]=7;
							ctrl2++;
							punteggio = punteggio + 200;
						}
						else if(getValMatrix(i-2, j) == 5 && getValMatrix(i-3, j) == 5)
						{
							//Nave da 4 Verticale B/A
							SeaAroundNaviV2(i-3,j,4);
							mx[i][j]=7;
							mx[i-1][j]=7;
							mx[i-2][j]=7;
							mx[i-3][j]=7;
							ctrl2++;
							punteggio = punteggio + 150;
						}
					}
					else if((getValMatrix(i-1, j) == 5 && getValMatrix(i+1, j) == 5))
					{
						//Nave da 3 Verticale C
						if(((getValMatrix(i-2, j) == 6) && (getValMatrix(i+2, j)) == 6) || ((getValMatrix(i-2, j) == 2) && (getValMatrix(i+2, j)) == 2) || ((getValMatrix(i-2, j) == 6) && (getValMatrix(i+2, j)) == 2) || ((getValMatrix(i-2, j) == 2) && (getValMatrix(i+2, j)) == 6))
						{
							SeaAroundNaviV2(i-1,j,3);
							mx[i][j]=7;
							mx[i-1][j]=7;
							mx[i+1][j]=7;
							ctrl2++;
							punteggio = punteggio + 200;
						}
						else if(((getValMatrix(i-2, j) == 6) && (getValMatrix(i+2, j)) == 5) || ((getValMatrix(i-2, j) == 2) && (getValMatrix(i+2, j)) == 5))
						{
							//Nave da 4 Verticale C1
							SeaAroundNaviV2(i-1,j,4);
							mx[i][j]=7;
							mx[i-1][j]=7;
							mx[i+1][j]=7;
							mx[i+2][j]=7;
							ctrl2++;
							punteggio = punteggio + 150;
						}
						else if(((getValMatrix(i-2, j) == 5) && (getValMatrix(i+2, j)) == 6) || ((getValMatrix(i-2, j) == 5) && (getValMatrix(i+2, j)) == 2))
						{
							//Nave da 4 Verticale C2
							SeaAroundNaviV2(i-2,j,4);
							mx[i][j]=7;
							mx[i-1][j]=7;
							mx[i-2][j]=7;
							mx[i+1][j]=7;
							ctrl2++;
							punteggio = punteggio + 150;
						}
					}	
				}
				
				setChanged();
			}
			else if((getValMatrix(i, j)==2) || (getValMatrix(i, j)==6))
			{
				mx[i][j]=6;
				punteggio = punteggio - 20;
				setChanged();
			}
		}
		
		notifyObservers();
	}
	
	public void generCoordinate(int o, int p, int r , int k) 
	{
		int i = o;
		int j = p;
		int directions = r;
		int RangePos = directions-1+1;
		int pos;
		pos = (int)(RangePos*Math.random())+1;

		if(k==1)
		{
			switch(pos)
			{
				case 1:
					g = i+1;
							h = j;
							break;
				case 2:
					g = i;
					h = j+1;
							break;
			}
		}
		else if(k==2)
		{
			switch(pos)
			{
				case 1:
					g = i;
					h = j-1;
							break;
				case 2:
					g = i+1;
					h = j;	
							break;
			}
		}
		else if(k==3)
		{
			switch(pos)
			{
				case 1: 
					g = i-1;
					h = j;
							break;
				case 2:
					g = i;
					h = j+1;
							break;
			}
		}
		else if(k==4)
		{
			switch(pos)
			{
				case 1: 
					g = i-1;
					h = j;
							break;
				case 2:
					g = i;
					h = j-1;
							break;
			}
		}
		else if(k==5)
		{
			switch(pos)
			{
				case 1:
					g = i+1;
					h = j;	
							break;
				case 2:
							g = i;
							h = j-1;
							break;
				case 3:
							g = i;
							h = j+1;
							break;
			}
		}
		else if(k==6)
		{
			switch(pos)
			{
				case 1: 
							g = i-1;
							h = j;
							break;
				case 2:
							g = i+1;
							h = j;	
							break;
				case 3:
							g = i;
							h = j+1;
							break;
			}
		}
		else if(k==7)
		{
			switch(pos)
			{
				case 1: 
							g = i-1;
							h = j;
							break;
				case 2:
							g = i;
							h = j-1;
							break;
				case 3:
							g = i;
							h = j+1;
							break;
			}
		}
		else if(k==8)
		{
			switch(pos)
			{
				case 1: 
							g = i-1;
							h = j;
							break;
				case 2:
							g = i+1;
							h = j;	
							break;
				case 3:
							g = i;	
							h = j-1;
							break;
			}
		}
		else if(k==9)
		{
			switch(pos)
			{
				case 1: 
							g = i-1;
							h = j;
							break;
				case 2:
							g = i+1;
							h = j;
							break;
				case 3:
							g = i;
							h = j-1;
							break;
				case 4:
							g = i;
							h = j+1;
							break;
			}
		}
		else if(k==10)
		{
			if(g == xx)
			{
				if(h>yy)
				{
					gg = i;
					hh = j+1;
					dir=1;
				}
				else
				{
					gg = i;
					hh = j-1;
					dir=2;
				}
			}
			else
			{
				if(g>xx)
				{
					gg = i+1;
					hh = j;
					dir=3;
				}
				else
				{
					gg = i-1;
					hh = j;
					dir=4;
				}
			}
		}
	}

	public boolean checkTurn(boolean check)
	{
		if(check)
		{
			checkTurn=false;
			return false;
		}
		else
		{
			checkTurn=true;
			return true;
		}
	}
	
	public void generAndHit()
	{
		int mGiocatore, m, tampR, tampC;
		m = 1;
		mGiocatore = 10;
		
		if(firsthit)
		{	
			int RANGE0 = mGiocatore-m+1;
			tampR = (int)(RANGE0*Math.random())+m;
	
			int RANGE1 = mGiocatore-m+1;
			tampC = (int)(RANGE1*Math.random())+m;

			checkPosition(tampR, tampC, 1);
			firsthit=false;
		}
		else
		{
			if(genius>0)
			{
				if(genius == 1)
				{
					
					//genera due coordinate possibili
					boolean entry = true;
					z = 0;
					while(entry)
					{
						
							if(xx==1)
							{
								if(yy==1)
								{
									generCoordinate(xx, yy, 2, 1);
								}
								else if(yy==10)
								{
									generCoordinate(xx, yy, 2, 2);
								}
								else
								{
									generCoordinate(xx, yy, 3, 5);
								}
							}
							else if(xx==10)
							{
								if(yy==1)
								{
									generCoordinate(xx, yy, 2, 3);
								}
								else if(yy==10)
								{
									generCoordinate(xx, yy, 2, 4);
								}
								else
								{
									generCoordinate(xx, yy, 3, 7);
								}
							}
							else
							{
								if(yy==1)
								{
									generCoordinate(xx, yy, 3, 6);
								}
								else if(yy==10)
								{
									generCoordinate(xx, yy, 3, 8);
								}
								else
								{
									generCoordinate(xx, yy, 4, 9);
								}
							}
				
						
						if((getValMatrix(g, h)==0) || (getValMatrix(g, h)==2) || (getValMatrix(g, h)==1))
						{
							entry = false;
						}
					}
					if(!entry)
					{
						checkPosition(g, h, 1);
					}
				}
				else if(genius>1)
				{
					boolean entry = true;
					int z = 0;
					fir=0;
					while(entry)
					{
						if(z>25)
						{
							if(dir==1)
							{
								gg = xx;
								hh = yy-1;

								z = 0;
							}
							else if(dir==2)
							{
								gg = xx;
								hh = yy+1;

								z = 0;
							}
							else if(dir==3)
							{
								gg = xx-1;
								hh = yy;

								z = 0;
							}
							else if(dir==4)
							{
								gg = xx+1;
								hh = yy;

								z = 0;
							}
							fir=1;
						}
						
						if(fir==0)
						{
							generCoordinate(g, h, 1, 10);
							z++;
							
						}
						
						if((getValMatrix(gg, hh)==0) || (getValMatrix(gg, hh)==2) || (getValMatrix(gg, hh)==1))
						{
							entry = false;
						}
					}
					if(!entry)
					{
						checkPosition(gg, hh, 1);	
					}
				}
			}
			else
			{
				boolean entry = true;
				
				
				int tampR1 = 0, tampC1 = 0;
				
				while(entry)
				{	
					mGiocatore = 10;
					int RANGE0 = mGiocatore-m+1;
					tampR1 = (int)(RANGE0*Math.random())+m;
					
					int RANGE1 = mGiocatore-m+1;
					tampC1 = (int)(RANGE1*Math.random())+m;
					
					if((getValMatrix(tampR1, tampC1)==0) || (getValMatrix(tampR1, tampC1)==2) || (getValMatrix(tampR1, tampC1)==1))
					{
						entry = false;
					}
				}
				if(!entry)
				{
					checkPosition(tampR1, tampC1, 1);
				}
			}
		}
	}

	public void resetI()
	{
		i = -1;
		setChanged();
		notifyObservers();
	}
	
	public int getI()
	{
		return i;
	}
	
	public int getCTRL1()
	{
		if(ctrl1==10)
		{
			setChanged();
			notifyObservers();
		}
		return ctrl1;
	}
	
	public int getCTRL2()
	{
		if(ctrl2==10)
		{
			setChanged();
			notifyObservers();
		}
		return ctrl2;
	}
	
	public void removeVect()
	{
		riPositionPlayer.removeAllElements();
		genius = 0;
	}
}
