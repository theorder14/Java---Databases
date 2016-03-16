package musicController;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

import musicModel.TopModel.MyTopModel;
import musicModel.TopModel.Table;
import musicView.MyMainView;



public class MyController implements ActionListener {

	MyMainView view;
	MyTopModel topModel;
	
	public MyController(MyMainView view, MyTopModel topModel) {
		this.view = view;
		view.addRadioButtonListener(new RadioButtonListener());
		view.addMouseListener(new TableMouseListener());
		
		
		this.topModel = topModel;
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private class RadioButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent aevt) {
			//jag vill ha ngt i stil med:
			
			JRadioButton rObj = (JRadioButton) aevt.getSource();
			if(rObj.getText().equals("Albums")) {
				view.getMainTable().setModel(topModel.getTableData(Table.ALBUM, ""));
			}else if(rObj.getText().equals("Artists")) {
				view.getMainTable().setModel(topModel.getTableData(Table.ARTIST, ""));
			}else if(rObj.getText().equals("Tracks")) {
				view.getMainTable().setModel(topModel.getTableData(Table.TRACK, ""));
			}else {
				view.getMainTable().setModel(topModel.getTableData(Table.GENRE, ""));
			}
			//gotta add listener again, after switched model.
			view.getMainTable().addMouseListener(new TableMouseListener());
			//disables the user to move the colons.
			view.getMainTable().getTableHeader().setReorderingAllowed(false);
			//disables the user to change cell values.
			
			
			view.switchEastPanel(rObj);
		}
		
	}
	private class TableMouseListener extends MouseAdapter {
		
		
		public void mouseClicked(MouseEvent mevt) {
			System.out.println("HEllo you have clicked on the table.");
			JRadioButton rObj = view.getSelectedRb();
			JTable table = view.getMainTable();
			JTextField[] txtFields;
			//just for clearification, naming variables and NOT FORLOOPING.
			//check that one row is selected!
			if(table.getSelectedRow()!=-1) {
				if(rObj.getText().equals("Albums")) {
//					String id = table.getValueAt(table.getSelectedRow(), 0).toString();
//					String name = table.getValueAt(table.getSelectedRow(), 1).toString();
//					String fkArtistId = table.getValueAt(table.getSelectedRow(), 2).toString();
//					txtFields = view.getTxtFieldsAlb();
//					txtFields[0].setText(id);
//					txtFields[1].setText(name);
//					txtFields[2].setText(fkArtistId);
					txtFields = view.getTxtFieldsAlb();
					for(int i=0; i<3; i++) 
						txtFields[i].setText(table.getValueAt(table.getSelectedRow(),i).toString());
					
				}else if(rObj.getText().equals("Artists")) {
//					String id = table.getValueAt(table.getSelectedRow(), 0).toString();
//					String name = table.getValueAt(table.getSelectedRow(), 1).toString();
//					String country = table.getValueAt(table.getSelectedRow(), 2).toString();
//					txtFields = view.getTxtFieldsArt();
//					txtFields[0].setText(id);
//					txtFields[1].setText(name);
//					txtFields[2].setText(country);
					
					//same as above, but more compact! :)
					txtFields = view.getTxtFieldsArt();
					for(int i=0; i<3; i++) 
						txtFields[i].setText(table.getValueAt(table.getSelectedRow(),i).toString());
					
				}else if(rObj.getText().equals("Tracks")) {
//					String id = table.getValueAt(table.getSelectedRow(), 0).toString();
//					String name = table.getValueAt(table.getSelectedRow(), 1).toString();
//					String trackTime = table.getValueAt(table.getSelectedRow(), 2).toString();
//					String fkArtistId = table.getValueAt(table.getSelectedRow(), 3).toString();
//					String fkAlbumId = table.getValueAt(table.getSelectedRow(), 4).toString();
//					String releaseDate = table.getValueAt(table.getSelectedRow(), 5).toString();
//					String fkGenreId = table.getValueAt(table.getSelectedRow(), 6).toString();
//					txtFields = view.getTxtFieldsTrk();
//					txtFields[0].setText(id);
//					txtFields[1].setText(name);
//					txtFields[2].setText(trackTime);
//					txtFields[3].setText(fkArtistId);
//					txtFields[4].setText(fkAlbumId);
//					txtFields[5].setText(releaseDate);
//					txtFields[6].setText(fkGenreId);
					
					//same as above, but more compact! :)
					txtFields = view.getTxtFieldsTrk();
					for(int i=0; i<6; i++) 
						txtFields[i].setText(table.getValueAt(table.getSelectedRow(),i).toString());	
				
				}else {
//					String id = table.getValueAt(table.getSelectedRow(), 0).toString();
//					String name = table.getValueAt(table.getSelectedRow(), 1).toString();
//					txtFields = view.getTxtFieldsGen();
//					txtFields[0].setText(id);
//					txtFields[1].setText(name);	
					
					//same as above, but more compact! :)
					txtFields = view.getTxtFieldsGen();
					for(int i=0; i<2; i++)
						txtFields[i].setText(table.getValueAt(table.getSelectedRow(),i).toString());
				}
			}	
		}
	}
}
