package ar.edu.ub.si.blockchain.vista;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.*;

import ar.edu.ub.si.blockchain.interfaces.IAdministrador;
import ar.edu.ub.si.blockchain.table.EjemploDeTabla;
import ar.edu.ub.si.blockchain.util.Configuracion;


public class VentanaPrincipal extends JFrame 
{

	private static final long serialVersionUID = 1L;
	
	private String ruta;
	private Configuracion configuracion;
	private IAdministrador admin;
	
	
	public VentanaPrincipal(IAdministrador admin) 
	{
		setAdmin(admin);
		
		this.startWindow();
		this.addElements();
	}

	private void startWindow() {
		this.setLocation(300, 200);
		this.setSize(500, 300);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	
		this.setTitle("Blockchain");
		this.setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
		
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
		
		menuItem = new JMenuItem("See all records");
		menuItem.addActionListener(this::onClickDatabaseSeeAll);
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

	public void onClickDatabaseSeeAll(ActionEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
            
            public void run() {
                try {
					new EjemploDeTabla();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
	}
	
	public void onClickDatabaseDelete (ActionEvent e) {
		try {
			getAdmin().eliminarTodosLosRegistros();
			showMessage("Base de datos Truncada!");
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public void onClickMenuUpload(ActionEvent e) {
		leerFichero();
	}
	
	public void onClickMenuExit(ActionEvent e) {
		getAdmin().closeConnection();
		this.dispose();
	}
	

	
	public void leerFichero() {
		
		JFileChooser file = new JFileChooser();
		file.showOpenDialog(this);
		File archivo = file.getSelectedFile(); 
		
		
		try {
			if(archivo.exists()) {
				ruta = file.getSelectedFile().toString();
                System.out.println(ruta);
                showMessage(getAdmin().validarArchivo(archivo));
                
			}else {
                showMessage("El archivo no existe");
            }
		}catch (Exception ex) {
            
            System.out.println(ex.getMessage());
        }

    }

	private void showMessage(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}

	public IAdministrador getAdmin() {
		return admin;
	}

	public void setAdmin(IAdministrador admin) {
		this.admin = admin;
	}

	public Configuracion getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(Configuracion configuracion) {
		this.configuracion = configuracion;
	}

}
