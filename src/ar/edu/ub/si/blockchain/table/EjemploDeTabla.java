package ar.edu.ub.si.blockchain.table;



import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;

import ar.edu.ub.si.blockchain.config.ConectorBaseDeDatos;
import ar.edu.ub.si.blockchain.data.Bloque;
import ar.edu.ub.si.blockchain.util.Configuracion;

public class EjemploDeTabla extends JFrame
{
	
	private Timer timerVentana;
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EjemploDeTabla() throws Exception
    {
		
		ArrayList<Bloque> bloques;
		bloques = obtenerBloques();
        
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
	private ArrayList<Bloque> obtenerBloques () throws Exception{
		
		Configuracion configuracion = new Configuracion("blockchain.properties");
		// Defino la conexion
				Connection laConexion = ConectorBaseDeDatos.obtenerConexion(configuracion);
				
					String laConsulta = "SELECT * FROM [Blockchain].[dbo].[Hash]";
					Statement stmtConsulta = laConexion.createStatement();
					ResultSet rs = stmtConsulta.executeQuery(laConsulta);

					
					// Armo el array de bloques
					
					ArrayList<Bloque> bloques = new ArrayList<Bloque>();
					
					// muestro los datos
					
					while (rs.next()) {
						
						// armo el bloque con los datos obtenidos
						Bloque bl = new Bloque();
						bl.setHash(rs.getString("Hash"));
						bl.setHashDato(rs.getString("HashDato"));
						bl.setPreviousHash(rs.getString("PreviusHash"));
						bl.setTsbd(rs.getString("TimeStamp"));
						
						// agrego el bloque al array
						bloques.add(bl);
						
					}
					
					// cierro el statement
					stmtConsulta.close();
				laConexion.close();
				
				return bloques;
	}

	public Timer getTimerVentana() {
		return timerVentana;
	}

	public void setTimerVentana(Timer timerVentana) {
		this.timerVentana = timerVentana;
	}
    

}

