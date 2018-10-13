package ar.edu.ub.si.blockchain.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import ar.edu.ub.si.blockchain.interfaces.IAdministradorBlockchain;
import ar.edu.ub.si.blockchain.util.Configuracion;


public class MainScreen extends JFrame 
{

	private static final long serialVersionUID = 1L;
	
	private String ruta;
	private Configuracion configuracion;
	
	private IAdministradorBlockchain admin;
	public MainScreen(IAdministradorBlockchain admin) 
	{
		setAdmin(admin);
		this.startWindow();
		this.addElements();
	}

	private void startWindow() {
		this.setLocation(300, 300);
		this.setSize(100, 100);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
	}


	private void addElements() 
	{
		
		JMenuBar menuBar = new JMenuBar();
		
		
		menuBar.add(this.createMenuFile());
		menuBar.add(this.createMenuBd());
		
		this.setJMenuBar(menuBar);
		
	}

	private JMenu createMenuBd() {
		JMenu menu = new JMenu("DataBase");
		JMenuItem menuItem = new JMenuItem("Delete all records");
		menuItem.addActionListener(this::onClickDatabaseDelete);
		menu.add(menuItem);
		
		return menu;
	}

	private JMenu createMenuFile() {
		JMenu menu = new JMenu("File");
		JMenuItem menuItem = new JMenuItem("Upload pdf");
		menuItem.addActionListener(this::onClickMenuUpload);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this::onClickMenuExit);
		menu.add(menuItem);
		
		return menu;
	}

	public void onClickDatabaseDelete (ActionEvent e) {
		admin.eliminarTodosLosRegistros();
	}
	public void onClickMenuUpload(ActionEvent e) {
		leerFichero();
	}
	
	public void onClickMenuExit(ActionEvent e) {
		this.dispose();
	}
	

	
	public void leerFichero() {
		
		JFileChooser file = new JFileChooser();
		file.showOpenDialog(this);
		File archivo = file.getSelectedFile(); 
		String texto;
		
		
		try {
			if(archivo.exists()) {
				ruta = file.getSelectedFile().toString();
                System.out.println(ruta);
                texto = archivo.getName();
            	
                //getAdmin().crearDato(archivo);
                
                //getAdmin().generarBlockchain();
                
                getAdmin().mostrarBlockChain();
                
                showMessage(getAdmin().validarArchivo(archivo));
                
              
			}else {
                texto = "El archivo no existe"; 
            }
		}catch (Exception ex) {
            
            System.out.println(ex.getMessage());
        }

    }

	private void showMessage(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

	public IAdministradorBlockchain getAdmin() {
		return admin;
	}

	public void setAdmin(IAdministradorBlockchain admin) {
		this.admin = admin;
	}

	public Configuracion getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(Configuracion configuracion) {
		this.configuracion = configuracion;
	}

}
