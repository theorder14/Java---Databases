package musicController;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
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
		view.addTableMouseListener(new TableMouseListener());
		view.addCRUDButtonsListener(new CRUDButtonsListener());
		view.addSearchButtonListener(new SearchButtonListener());
		
		this.topModel = topModel;
	}
	

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//TODO
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
			
			view.switchEastPanel(rObj);
		}
		
	}
	//TODO 
	private class TableMouseListener extends MouseAdapter {
		
		private JTable table;
		private JTextField[] txtFields;
		JRadioButton rObj;
		public void mouseClicked(MouseEvent mevt) {
			System.out.println("HEllo you have clicked on the table.");
			rObj = view.getSelectedRb();
			table = view.getMainTable();

			//changfe data in JTextFields to clicked row.
			if(table.getSelectedRow()!=-1) {
				if(rObj.getText().equals("Albums")) {
					txtFields = view.getTxtFieldsAlb();
					updateTxtFields();
				}else if(rObj.getText().equals("Artists")) {
					txtFields = view.getTxtFieldsArt();
					updateTxtFields();
				}else if(rObj.getText().equals("Tracks")) {
					txtFields = view.getTxtFieldsTrk();
					updateTxtFields();	
				}else {
					txtFields = view.getTxtFieldsGen();
					updateTxtFields();
				}
			}	
		}
		
		private void updateTxtFields() {
			for(int i=0; i<txtFields.length; i++) 
				txtFields[i].setText(table.getValueAt(table.getSelectedRow(),i).toString());
		}	
	}
	
	//TODO ****fix: shorter code!******
	private class CRUDButtonsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent aevt) {
			// TODO Auto-generated method stub
			System.out.println("CRUD TO BE COMING MATE");
			JButton bObj = (JButton) aevt.getSource();
			JTextField[] txtFields;
			JRadioButton rObj = view.getSelectedRb();
			String[] stuff = null;
			
			if(bObj.getText().equals("ADD")) {
				if(rObj.getText().equals("Albums")) {
					txtFields = view.getTxtFieldsAlb();
					stuff = new String[txtFields.length];
					
					//will not use addStuff[0], since id is AI
					for(int i=0;i<stuff.length;i++)
						stuff[i] = txtFields[i].getText();
					
					//System.out.println(addStuff[1].getClass().getName());
					//System.out.println(addStuff[2].getClass().getName());
					//System.out.println(addStuff[2]);
					addDialog(topModel.addData(Table.ALBUM, stuff));
				}else if(rObj.getText().equals("Artists")) {
					txtFields = view.getTxtFieldsArt();
					stuff = new String[txtFields.length];
					//will not use addStuff[0], since id is AI
					for(int i=0;i<stuff.length;i++)
						stuff[i] = txtFields[i].getText();
					
					addDialog(topModel.addData(Table.ARTIST, stuff));		
				}else if(rObj.getText().equals("Tracks")) {
					txtFields = view.getTxtFieldsTrk();
					stuff = new String[txtFields.length];
					for(int i=0;i<stuff.length;i++)
						stuff[i] = txtFields[i].getText();
					
					addDialog(topModel.addData(Table.TRACK, stuff));	
				}else {
					txtFields = view.getTxtFieldsGen();
					stuff = new String[txtFields.length];
					for(int i=0;i<stuff.length;i++)
						stuff[i] = txtFields[i].getText();
					
					addDialog(topModel.addData(Table.GENRE, stuff));	
				}
				
				
				
			}else if(bObj.getText().equals("REGENERATE")) {
				
				
				
				
			}else if(bObj.getText().equals("UPDATE")) {
				if(rObj.getText().equals("Albums")) {
					txtFields = view.getTxtFieldsAlb();
					stuff = new String[txtFields.length];
					
					for(int i=0;i<stuff.length;i++)
						stuff[i] = txtFields[i].getText();
					
					updateDialog(topModel.updateData(Table.ALBUM, stuff));
				}else if(rObj.getText().equals("Artists")) {
					txtFields = view.getTxtFieldsArt();
					stuff = new String[txtFields.length];
					//will not use addStuff[0], since id is AI
					for(int i=0;i<stuff.length;i++)
						stuff[i] = txtFields[i].getText();
					
					updateDialog(topModel.updateData(Table.ARTIST, stuff));		
				}else if(rObj.getText().equals("Tracks")) {
					txtFields = view.getTxtFieldsTrk();
					stuff = new String[txtFields.length];
					for(int i=0;i<stuff.length;i++)
						stuff[i] = txtFields[i].getText();
					
					updateDialog(topModel.updateData(Table.TRACK, stuff));	
				}else {
					txtFields = view.getTxtFieldsGen();
					stuff = new String[txtFields.length];
					for(int i=0;i<stuff.length;i++)
						stuff[i] = txtFields[i].getText();
					
					updateDialog(topModel.updateData(Table.GENRE, stuff));	
				}
				
			//FIX***** short down this code, I've intentionally made it longer!!!(quick code)
			}else {//Deletation.
				if(deleteConfirm()) {
					if(rObj.getText().equals("Albums")) {
						txtFields = view.getTxtFieldsAlb();
						stuff = new String[txtFields.length];
						for(int i=0;i<stuff.length;i++)
							stuff[i] = txtFields[i].getText();
						deleteDialog(topModel.deleteData(Table.ALBUM, stuff));
					}else if(rObj.getText().equals("Artists")) {
						txtFields = view.getTxtFieldsArt();
						stuff = new String[txtFields.length];
						for(int i=0;i<stuff.length;i++)
							stuff[i] = txtFields[i].getText();
						deleteDialog(topModel.deleteData(Table.ARTIST, stuff));
					}else if(rObj.getText().equals("Tracks")) {
						txtFields = view.getTxtFieldsTrk();
						stuff = new String[txtFields.length];
						for(int i=0;i<stuff.length;i++)
							stuff[i] = txtFields[i].getText();
						deleteDialog(topModel.deleteData(Table.TRACK, stuff));
					}else {
						txtFields = view.getTxtFieldsGen();
						stuff = new String[txtFields.length];
						for(int i=0;i<stuff.length;i++)
							stuff[i] = txtFields[i].getText();
						deleteDialog(topModel.deleteData(Table.GENRE, stuff));
					}
				}else {
					JOptionPane.showMessageDialog(null, "Data was not deleted");
				}
			}
		}
		
		private void addDialog(boolean bool) {
			if(bool)
				JOptionPane.showMessageDialog(null, "Data was sucessfully added");
			else
				JOptionPane.showMessageDialog(null, "Data was not sucessfully added");
		}
		private void updateDialog(boolean bool) {
			if(bool)
				JOptionPane.showMessageDialog(null, "Data was sucessfully updated");
			else
				JOptionPane.showMessageDialog(null, "Data was not sucessfully updated");
		}
		private void deleteDialog(boolean bool) {
			if(bool)
				JOptionPane.showMessageDialog(null, "Data was sucessfully deleted");
			else
				JOptionPane.showMessageDialog(null, "Data was not sucessfully deleted");
		}
		private boolean deleteConfirm() {
			Object[] options = {"HELL YEAH", "HELL NO"};
			int answer = JOptionPane.showOptionDialog(null,
				"Do you really want to delete this data?",
				"CONFIRM DELETE",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				options,
				options[1]); //1 of safety reason, lal ;) (if user missclicks data will not be deleted).
			if(answer==0) return true;
			else return false;
		}
		
	}
	
	//TODO ***fix: its very similar to RadioButtonListener class, repeated code can be done better! :)
	private class SearchButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent aevt) {
			JRadioButton rObj = view.getSelectedRb();
			String searchStr = view.getSearchField().getText();
			if(rObj.getText().equals("Albums")) {
				view.getMainTable().setModel(topModel.getTableData(Table.ALBUM, searchStr));
			}else if(rObj.getText().equals("Artists")) {
				view.getMainTable().setModel(topModel.getTableData(Table.ARTIST, searchStr));
			}else if(rObj.getText().equals("Tracks")) {
				view.getMainTable().setModel(topModel.getTableData(Table.TRACK, searchStr));
			}else {
				view.getMainTable().setModel(topModel.getTableData(Table.GENRE, searchStr));
			}
			view.getMainTable().addMouseListener(new TableMouseListener());
		}
		
	}
}
