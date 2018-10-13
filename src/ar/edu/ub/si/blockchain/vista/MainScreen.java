package ar.edu.ub.si.blockchain.vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import ar.edu.ub.si.blockchain.interfaces.IAdministradorBlockchain;


public class MainScreen extends JFrame 
{

	private static final long serialVersionUID = 1L;
	
	private JPanel jPanel;
	private JLabel texto;
	private JButton boton;
	private JFileChooser selectorArchivo;
	private File archivo;
	private String ruta;
	
	private IAdministradorBlockchain admin;
	public MainScreen(IAdministradorBlockchain admin) 
	{
		setAdmin(admin);
		this.startWindow();
		this.addElements();
		//setActionListeners();		
	}

	private void startWindow() {
		this.setLocation(300, 300);
		this.setSize(400, 400);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
	}


	private void addElements() 
	{
		
		JMenuBar menuBar = new JMenuBar();
		
		
		menuBar.add(this.createMenuFile());
		menuBar.add(this.createMenuBd());
		
		this.setJMenuBar(menuBar);
		
		/*
		jPanel = new JPanel();
		
		texto = new JLabel();
		boton = new JButton();
		
		selectorArchivo = new JFileChooser();
		selectorArchivo.setFileSelectionMode(0);
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("PDF", "pdf");
        selectorArchivo.setFileFilter(extensionFilter);
        
        selectorArchivo.setDialogTitle("Abrir Archivo");
        
		this.add(jPanel);
		jPanel.setBackground(Color.white);
		jPanel.add(texto);
		jPanel.add(boton);
		
		boton.setText("Buscar Archivo");
		*/
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
		
		String texto = archivo.getName();
		
		ruta = file.getSelectedFile().toString();
		
		try {
			if(archivo.exists()) {
                System.out.println(ruta);
            	
                getAdmin().crearDato(archivo);
                
                getAdmin().generarBlockchain();
                
                getAdmin().mostrarBlockChain();
			}else {
            	
                texto = "El archivo no existe";
                
            }
		}catch (Exception ex) {
            
            System.out.println(ex.getMessage());
        }
/*
        
        if (selectorArchivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        	

        	
        	
        
            archivo = selectorArchivo.getSelectedFile();
            
            texto.setText(archivo.getName());
            
            ruta = selectorArchivo.getSelectedFile().toString();
            
            try {
            
                if (archivo.exists()) {
                    System.out.println(ruta);
                	
                    getAdmin().crearDato(archivo);
                    
                    getAdmin().generarBlockchain();
                    
                    getAdmin().mostrarBlockChain();
                    
                } else {
                	
                    texto.setText("El archivo no existe");
                    
                }
                
            } catch (Exception ex) {
                
                System.out.println(ex.getMessage());
            }
        }
        
        */
    }

	public IAdministradorBlockchain getAdmin() {
		return admin;
	}

	public void setAdmin(IAdministradorBlockchain admin) {
		this.admin = admin;
	}

}
