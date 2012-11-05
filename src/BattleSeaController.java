import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//Fabio Sorrentino Matr.147304 Ingegneria del Cinema e dei Mezzi di Comunicazione

public class BattleSeaController implements ActionListener 
{
	private BattleSeaModel m;
	private BattleSeaView v;
	@SuppressWarnings("unused")
	private BattleSeaView2 v2;
	
	public BattleSeaController()
	{
		m = new BattleSeaModel(12, 24);
		v = new BattleSeaView(m, this);
		v2 = new BattleSeaView2(this);
	}
	
	public void actionPerformed(ActionEvent ae) 
	{
		String cmd = ae.getActionCommand();
		if("Nuova Partita".equals(cmd))
		{
				m.stop();
				m.svuotaNavi();
				m.mescola(0);
				m.inserisciMare(1);
			
		}
		else if("Mescola".equals(cmd))
		{
			m.svuotaNavi();
			m.mescola(0);
			m.inserisciMare(1);
		}
		else if("Start".equals(cmd))
		{
			m.mescola(12);
			m.inserisciMare(2);
			m.start();
			m.resetI();
		}
		else if("Fine".equals(cmd))
		{
			BattleSeaMain.endDialog();
		}
	}

	public Component getView() 
	{
		return v;
	}
}
