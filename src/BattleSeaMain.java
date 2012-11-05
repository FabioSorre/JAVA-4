import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//Fabio Sorrentino Matr.147304 Ingegneria del Cinema e dei Mezzi di Comunicazione

public class BattleSeaMain
{

	private static JFrame f;
	public static BattleSeaController sc;
	public static MenuBar mb;
	public static Menu link;
	
	public BattleSeaMain()
	{
		f = new JFrame("Esercitazione 3");
		f.setLocation(50, 50);
		f.setSize(850,500);
		f.setResizable(false);
		
		Color mycolor = new Color(220, 180, 120);
		f.setBackground(mycolor);
		
		
		//Menù Iniziale
		Menu link = new Menu("Avvia");
		link.add(new MenuItem("Nuova Partita"));
		
		Menu link2 = new Menu("Fine");
		link2.add(new MenuItem("Fine"));
		
		MenuBar mb = new MenuBar();
		mb.add(link);
		mb.add(link2);
		f.setMenuBar(mb);
		
		sc = new BattleSeaController();
		
		link.addActionListener(sc);
		link2.addActionListener(sc);
		
		WindowAdapter wa = new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			};
		};
		f.addWindowListener(wa);
		
		f.add(sc.getView());
		
		f.setVisible(true);
		
	}
	
	public static void main(String[] args) 
	{
		Runnable r = new Runnable()
		{
			public void run()
			{
				new BattleSeaMain();
			};
		};
		
		EventQueue.invokeLater(r);
	}
	
	public static BattleSeaController getController()
	{
		return sc;
	}
	
	public static void endDialog()
	{
		int n = JOptionPane.showConfirmDialog(f, "Sicuro di voler terminare?", null, JOptionPane.YES_NO_OPTION);
		
		if (n == JOptionPane.YES_OPTION)
		{
			System.exit(-1);
		}
	}
}
