package ar.edu.ub.si.blockchain.vista;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ar.edu.ub.si.blockchain.interfaces.IAdministrador;

public class AdministradorDeVentana {
	
	
	private static VentanaPrincipal pantallaPrincipal ;
	
	
	public static void generarVentana(IAdministrador admin) {
		
		//se configura el estilo del buscador de archivo para que sea mas interactiva
		configurarEstiloDeBusqueda();
		
		//se crea la ventana que manejara las operaciones del administrador
		crearVentana(admin);
		
		//configuro la ventana para poder visualizarlo
		configurarVentana();
		
	}

	

	private static void configurarEstiloDeBusqueda() {
		
		//SETEAMOS EL ESTILO QUE TENDRA EL BUSCADOR DE ARCHIVOS
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void crearVentana(IAdministrador admin) {
		pantallaPrincipal = new VentanaPrincipal(admin);
	}

	private static void configurarVentana() {
		pantallaPrincipal.setVisible(true);
		//pantallaPrincipal.setBounds(200, 200, 400, 250);
	}

}
