package ar.edu.ub.si.blockchain.table;



import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;

import ar.edu.ub.si.blockchain.data.Bloque;
import ar.edu.ub.si.blockchain.interfaces.IAdministrador;

public class EjemploDeTabla extends JFrame
{
	
	private Timer timerVentana;
	
    /**
	 * 
	 */
	
	IAdministrador admin;
	private static final long serialVersionUID = 1L;

	public EjemploDeTabla(IAdministrador admin) throws Exception
    {
		this.admin = admin;
		
		
		ArrayList<Bloque> bloques;
		bloques = this.admin.getBloques();
        
        //create the model
        BloqueTableModel model = new BloqueTableModel(bloques);
        //create the table
        JTable table = new JTable(model);
        
        //add the table to the frame
        this.add(new JScrollPane(table));
        
        this.setTitle("Bloques En la Base de Datos");
        this.setSize(900, 300);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
       // this.pack();
        this.setVisible(true);
        
		//setTimerVentana(new Timer(1000,this::refrescarVentana));
		//this.getTimerVentana().start();
    }
	
	public void refrescarVentana(ActionEvent e) {
		this.revalidate();
		this.repaint();
	}
	
	
	// temporal
	

	public Timer getTimerVentana() {
		return timerVentana;
	}

	public void setTimerVentana(Timer timerVentana) {
		this.timerVentana = timerVentana;
	}
    

}

