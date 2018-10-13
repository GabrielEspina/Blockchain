package ar.edu.ub.si.blockchain.table;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import ar.edu.ub.si.blockchain.connectordb.AdministradorDeConexiones;
import ar.edu.ub.si.blockchain.data.Bloque;
import ar.edu.ub.si.blockchain.util.Configuracion;

public class TableExample extends JFrame
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableExample() throws Exception
    {
Configuracion configuracion = new Configuracion("blockchain.properties");
		
		
		// Defino la conexion
		Connection laConexion = AdministradorDeConexiones.obtenerConexion(configuracion);
		
		

			
			String laConsulta = "SELECT * FROM [Blockchain].[dbo].[Hash]";
			Statement stmtConsulta = laConexion.createStatement();
			ResultSet rs = stmtConsulta.executeQuery(laConsulta);
			
			//INFORMO QUE SE ESTA POR HACER LA CONSULTA
			System.out.println(">>SQL: " + laConsulta);
			
			// Armo el array de bloques
			
			ArrayList<Bloque> bloques = new ArrayList();
			
			// muestro los datos
			
			while (rs.next()) {
				
				// armo el bloque con los datos obtenidos
				Bloque bl = new Bloque();
				bl.setHash(rs.getString("Hash"));
				bl.setHashDato(rs.getString("HashDato"));
				bl.setPreviousHash(rs.getString("PreviusHash"));
				bl.setTimeStamp(rs.getDate("TimeStamp"));
				
				// agrego el bloque al array
				bloques.add(bl);
				
			}
			
			// cierro el statement
			stmtConsulta.close();
		laConexion.close();
        

        
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
    }
    

}

