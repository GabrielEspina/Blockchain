package ar.edu.ub.si.blockchain.vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import ar.edu.ub.si.blockchain.interfaces.IAdministradorBlockchain;


public class MainScreen extends JFrame implements ActionListener
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
		addElements();
		setActionListeners();
	
		
	}

	private void setActionListeners() 
	{
		boton.addActionListener(this);
		
	}

	private void addElements() 
	{
		
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
		
	}

	@Override
	public void actionPerformed(ActionEvent aEvent) {
		
		if(aEvent.getSource().equals(boton)) {
			//JOptionPane.showMessageDialog(null,"BOTON APRETADO");
			leerFichero();
		}

		
	}
	
	public void leerFichero() {

		
        
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
    }

	public IAdministradorBlockchain getAdmin() {
		return admin;
	}

	public void setAdmin(IAdministradorBlockchain admin) {
		this.admin = admin;
	}

}
