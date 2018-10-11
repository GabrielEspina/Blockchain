package ar.edu.ub.si.blockchain.vista;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ar.edu.ub.si.blockchain.interfaces.IAdministradorBlockchain;

public class AdminScreen {
	
	
	private static MainScreen pantallaPrincipal ;
	
	
	public static void generateScreen(IAdministradorBlockchain admin) {
		
		configFileChooserStyle();
		
		createScreen(admin);
		
		configScreen();
		
	}

	

	private static void configFileChooserStyle() {
		
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
	
	private static void createScreen(IAdministradorBlockchain admin) {
		pantallaPrincipal = new MainScreen(admin);
	}

	private static void configScreen() {
		pantallaPrincipal.setVisible(true);
		pantallaPrincipal.setBounds(200, 200, 400, 250);
	}

}
