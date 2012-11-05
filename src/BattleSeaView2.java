import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;


//Fabio Sorrentino Matr.147304 Ingegneria del Cinema e dei Mezzi di Comunicazione

public class BattleSeaView2  extends Panel implements ActionListener
{
	private static final long serialVersionUID = -4553817256864460415L;
	
	private JLabel l1;
	private JButton b1;
	
	public BattleSeaView2(BattleSeaController c)
	{
		l1 = new JLabel("<html><br/><br/><p align = 'center'><u>Istruzioni</u><br/>La battaglia Navale!!!<br/><br/><br/><br/><br/>Creato da <i>Fabio Sorrentino</i><br/>Ingegneria del Cinema e dei Mezzi di comunicazione<br/>Corso di Programmazione e realizzazione di Interfacce grafiche.</p></html>");
		l1.setForeground(Color.BLACK);
		
		b1 = new JButton("Torna Indietro");
		
		setLayout(new FlowLayout());
		
		add(l1, JLabel.CENTER);
		add(b1, JButton.CENTER);
		
		setVisible(true);
		
	}

	public void actionPerformed(ActionEvent ae) 
	{
		String cmd = ae.getActionCommand();
		if("Torna Indietro".equals(cmd))
		{
			(BattleSeaMain.getController()).getView();
		}
	}
}
