import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;


//Fabio Sorrentino Matr.147304 Ingegneria del Cinema e dei Mezzi di Comunicazione

public class BattleSeaView extends Panel implements Observer, MouseListener
{
	private static final long serialVersionUID = 4050252965019909373L;
	
	private BattleSeaModel mView;
	private JButton b1, b2;
	private JLabel l, l1, l2;
	public String s;
	
	public BattleSeaView(BattleSeaModel m, BattleSeaController bsc1) 
	{
		mView = m;
		mView.addObserver(this);
		
		Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);

		s = new String(" ");
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbcMain = new GridBagConstraints();

		gbcMain.gridwidth = 1;
		gbcMain.gridheight = 1;
		gbcMain.weighty = 1.0;
		gbcMain.fill = GridBagConstraints.BOTH;
		gbcMain.anchor = GridBagConstraints.CENTER;
		gbcMain.insets.set(5, 5, 0, 0);
	
		for (int i=1; i<11; i++)
		{
			gbcMain.gridy = i-1;
			
			for(int j=1; j<11; j++)
			{
				l = new JLabel(i + " " +j);
				l.setPreferredSize(new Dimension(30,30));
				l.setOpaque(true);
				l.setBackground(Color.WHITE);
				l.setForeground(Color.WHITE);
				l.setBorder(raisedetched);
				
				
				if(i-1==0)
				{
					if(i-1==0 && j-1==0)
					{
						gbcMain.insets.set(15, 15, 0, 0);
					}
					else
					{
						gbcMain.insets.set(15, 5, 0, 0);
					}
				}
				
				if(j-1==0 && i-1!=0)
				{
					gbcMain.insets.set(5, 15, 0, 0);
				}
				
				if(i-1==0 && j-1==9)
				{
					gbcMain.insets.set(15, 5, 0, 25);
				}
				else if(j-1==9)
				{
					gbcMain.insets.set(5, 5, 0, 25);
				}
				
				gbcMain.gridx = j-1;
				add(l, gbcMain);
				
				gbcMain.insets.set(5, 5, 0, 0);
			}
			
			for(int j=13; j<23; j++)
			{
				l = new JLabel(i + " " +j);
				l.setPreferredSize(new Dimension(30,30));
				l.setOpaque(true);
				l.setBackground(Color.WHITE);
				l.setForeground(Color.WHITE);
				l.setBorder(raisedetched);
				
				
				if(i-1==0)
				{
					if(i-1==0 && j-1==12)
					{
						gbcMain.insets.set(15, 15, 0, 0);
					}
					else
					{
						gbcMain.insets.set(15, 5, 0, 0);
					}
				}
				
				if(j-1==12 && i-1!=0)
				{
					gbcMain.insets.set(5, 15, 0, 0);
				}
				
				if(i-1==0 && j-1==22)
				{
					gbcMain.insets.set(15, 5, 0, 25);
				}
				else if(j-1==22)
				{
					gbcMain.insets.set(5, 5, 0, 25);
				}
				
				gbcMain.gridx = j-1;
				add(l, gbcMain);
				
				gbcMain.insets.set(5, 5, 0, 0);
			}
		}
		
		b1 = new JButton("Mescola");
		gbcMain.insets.set(15, 5, 15, 5);
		gbcMain.gridy = 20;
		gbcMain.gridx = 1;
		gbcMain.gridwidth = 4;
		add(b1, gbcMain);
		
		b2 = new JButton("Start");
		gbcMain.insets.set(15, 5, 15, 5);
		gbcMain.gridy = 20;
		gbcMain.gridx = 6;
		gbcMain.gridwidth = 3;
		add(b2, gbcMain);

		l1 = new JLabel(s);
		gbcMain.insets.set(15, 5, 15, 5);
		gbcMain.gridy = 20;
		gbcMain.gridx = 12;
		gbcMain.gridwidth = 7;
		add(l1, gbcMain);
		
		l2 = new JLabel("    ");
		gbcMain.insets.set(15, 5, 15, 5);
		gbcMain.gridy = 20;
		gbcMain.gridx = 19;
		gbcMain.gridwidth = 3;
		add(l2, gbcMain);
		
		l1.addMouseListener(this);
		
	}

	public void update(Observable o, Object data) 
	{
		removeAll();

		Border blackline = BorderFactory.createLineBorder(Color.black);
		Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbcMain = new GridBagConstraints();

		gbcMain.gridwidth = 1;
		gbcMain.gridheight = 1;
		gbcMain.weighty = 1.0;
		gbcMain.fill = GridBagConstraints.BOTH;
		gbcMain.anchor = GridBagConstraints.CENTER;
		gbcMain.insets.set(5, 5, 0, 0);
		
		l1 = new JLabel(mView.getLabelString());
		l2 = new JLabel(mView.getPunteggio());
		
		for (int i=1; i<11; i++)
		{
			gbcMain.gridy = i-1;
			
			for(int j=1; j<11; j++)
			{
				l = new JLabel(i + " " +j);
				l.setPreferredSize(new Dimension(30,30));
				if(mView.getValMatrix(i, j)==0 || mView.getValMatrix(i, j)==2)
				{
					l.setOpaque(true);
					l.setBackground(Color.WHITE);
					l.setForeground(Color.WHITE);
					l.setBorder(raisedetched);
				}
				else if((mView.getValMatrix(i, j)==1))
				{
					l.setOpaque(true);
					l.setBackground(Color.GRAY);
					l.setForeground(Color.GRAY);
					l.setBorder(blackline);
				}
				else if((mView.getValMatrix(i, j)==3))
				{
					l.setOpaque(true);
					l.setBackground(Color.YELLOW);
					l.setForeground(Color.YELLOW);
					l.setBorder(raisedetched);
				}
				else if((mView.getValMatrix(i, j)==4))
				{
					l.setOpaque(true);
					l.setBackground(Color.RED);
					l.setForeground(Color.RED);
					l.setBorder(raisedetched);
				}
				else if((mView.getValMatrix(i, j)==8))
				{
					l.setOpaque(true);
					l.setBackground(Color.CYAN);
					l.setForeground(Color.CYAN);
					l.setBorder(raisedetched);
				}
				
				
				
				if(i-1==0)
				{
					if(i-1==0 && j-1==0)
					{
						gbcMain.insets.set(15, 15, 0, 0);
					}
					else
					{
						gbcMain.insets.set(15, 5, 0, 0);
					}
				}
				
				if(j-1==0 && i-1!=0)
				{
					gbcMain.insets.set(5, 15, 0, 0);
				}
				
				if(i-1==0 && j-1==9)
				{
					gbcMain.insets.set(15, 5, 0, 25);
				}
				else if(j-1==9)
				{
					gbcMain.insets.set(5, 5, 0, 25);
				}
				
				gbcMain.gridx = j-1;
				add(l, gbcMain);

				
				gbcMain.insets.set(5, 5, 0, 0);
			}
			
			for(int j=13; j<23; j++)
			{
				l = new JLabel(i + " " +j);
				l.setPreferredSize(new Dimension(30,30));
				if(mView.getValMatrix(i, j)==0)
				{
					l.setOpaque(true);
					l.setBackground(Color.WHITE);
					l.setForeground(Color.WHITE);
				}
				if((mView.getValMatrix(i, j)==1) || (mView.getValMatrix(i, j)==2))
				{
					l.setOpaque(true);
					l.setBackground(Color.WHITE);
					l.setForeground(Color.WHITE);
				}
				else if((mView.getValMatrix(i, j)==5))
				{
					l.setOpaque(true);
					l.setBackground(Color.YELLOW);
					l.setForeground(Color.YELLOW);
				}
				else if((mView.getValMatrix(i, j)==6))
				{
					l.setOpaque(true);
					l.setBackground(Color.CYAN);
					l.setForeground(Color.CYAN);
				}
				else if((mView.getValMatrix(i, j)==7))
				{
					l.setOpaque(true);
					l.setBackground(Color.RED);
					l.setForeground(Color.RED);
				}
				l.setBorder(raisedetched);
				
				
				if(i-1==0)
				{
					if(i-1==0 && j-1==12)
					{
						gbcMain.insets.set(15, 15, 0, 0);
					}
					else
					{
						gbcMain.insets.set(15, 5, 0, 0);
					}
				}
				
				if(j-1==12 && i-1!=0)
				{
					gbcMain.insets.set(5, 15, 0, 0);
				}
				
				if(i-1==0 && j-1==22)
				{
					gbcMain.insets.set(15, 5, 0, 25);
				}
				else if(j-1==22)
				{
					gbcMain.insets.set(5, 5, 0, 25);
				}
				
				gbcMain.gridx = j-1;
				add(l, gbcMain);
				
				if((l1.getText().equals(" Gioco avviato! Tocca a te... ")) || (l1.getText().equals(" Tocca a te... ")) || (l1.getText().equals(" Colpito! Tocca a te... ")) || (l1.getText().equals(" ah ah Colpito e Affondato! Tocca a te... ")) || (l1.getText().equals(" Mare! Tocca a te... "))|| (l1.getText().equals(" Mossa già effettuata... ")))
				{
					l.addMouseListener(this);
				}

				
				gbcMain.insets.set(5, 5, 0, 0);
			}
		}
		
		b1 = new JButton("Mescola");
		gbcMain.insets.set(15, 5, 15, 5);
		gbcMain.gridy = 20;
		gbcMain.gridx = 1;
		gbcMain.gridwidth = 4;
		add(b1, gbcMain);
		
		b2 = new JButton("Start");
		gbcMain.insets.set(15, 5, 15, 5);
		gbcMain.gridy = 20;
		gbcMain.gridx = 6;
		gbcMain.gridwidth = 3;
		add(b2, gbcMain);

		//Creazione etichette in cima all'update
		gbcMain.insets.set(15, 5, 15, 5);
		gbcMain.gridy = 20;
		gbcMain.gridx = 12;
		gbcMain.gridwidth = 7;
		add(l1, gbcMain);
		
		gbcMain.insets.set(15, 5, 15, 5);
		gbcMain.gridy = 20;
		gbcMain.gridx = 19;
		gbcMain.gridwidth = 3;
		
		b1.addActionListener(BattleSeaMain.getController());
		b2.addActionListener(BattleSeaMain.getController());
		
		if((l1.getText().equals(" Gioco avviato! Tocca a te... ")) || (l1.getText().equals(" Tocca a te... ")) || (l1.getText().equals(" Colpito! Tocca a te... ")) || (l1.getText().equals(" ah ah Colpito e Affondato! Tocca a te... ")) || (l1.getText().equals(" Mare! Tocca a te... ")))
		{
			b1.removeActionListener(BattleSeaMain.getController());
			b2.removeActionListener(BattleSeaMain.getController());
			add(l2, gbcMain);
		}
		
		validate();
	}

	@Override
	public void mouseClicked(MouseEvent me) 
	{
		boolean z = true;
		
		l = new JLabel();
		l = (JLabel) me.getComponent();
		
		
		z = mView.getCordinateLabel(l.getText());
		System.out.println("zeta "+ z);
		if(!z)	
		{
			controllo();
			mView.generAndHit();
			controllo();
		}
		else
		{
			l1.setText(" Mossa già effettuata... ");
		}
	}
	
	private void controllo()
	{
		if(mView.getCTRL2()==10)
		{
			if(mView.getCTRL1()==10)
			{
				JOptionPane.showMessageDialog(this, "Pareggio!");
				mView.stop();
				mView.svuotaNavi();
				l1.setText(" ");
				b1.removeActionListener(BattleSeaMain.getController());
				b2.removeActionListener(BattleSeaMain.getController());
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Hai vinto!");
				mView.stop();
				mView.svuotaNavi();
				l1.setText(" ");
				b1.removeActionListener(BattleSeaMain.getController());
				b2.removeActionListener(BattleSeaMain.getController());
			}
		}
		else if(mView.getCTRL1()==10)
		{
			JOptionPane.showMessageDialog(this, "Hai perso...");
			mView.stop();
			mView.svuotaNavi();
			l1.setText(" ");
			b1.removeActionListener(BattleSeaMain.getController());
			b2.removeActionListener(BattleSeaMain.getController());
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}