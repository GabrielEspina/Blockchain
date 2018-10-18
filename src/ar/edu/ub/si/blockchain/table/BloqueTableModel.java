package ar.edu.ub.si.blockchain.table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ar.edu.ub.si.blockchain.data.Bloque;

public class BloqueTableModel extends AbstractTableModel{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Bloque> BloqueList;
	
	
    private final String[] columnNames = new String[] {
            "Hash", "Hash Dato", "Previous Hash", "TimeStamp"
    };
    private final Class[] columnClass = new Class[] {
        String.class, String.class, String.class, Date.class
    };
    
	public BloqueTableModel(ArrayList<Bloque> bloqueList) {
		BloqueList = bloqueList;
	}
    
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }

 
    public int getColumnCount()
    {
        return columnNames.length;
    }

  
    public int getRowCount()
    {
        return BloqueList.size();
    }


    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Bloque row = BloqueList.get(rowIndex);
        if(0 == columnIndex) {
            return row.getHash();
        }
        else if(1 == columnIndex) {
            return row.getHashDato();
        }
        else if(2 == columnIndex) {
            return row.getPreviousHash();
        }
        else if(3 == columnIndex) {
            return row.getTimeStamp();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Bloque row = BloqueList.get(rowIndex);
        if(0 == columnIndex) {
            row.setHash((String) aValue);
        }
        else if(1 == columnIndex) {
            row.setHashDato((String) aValue);
        }
        else if(2 == columnIndex) {
            row.setPreviousHash((String) aValue);
        }
        else if(3 == columnIndex) {
            row.setTimeStamp((Date) aValue);
        }
    }
    
}