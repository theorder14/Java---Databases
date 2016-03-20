package musicController;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import musicModel.TopModel.MyTopModel;
import musicModel.TopModel.SqlQry;
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
		view.addMenjuBarListener(new MenjuBarListener());
		view.addComboBokListener(new ComboBokListener());
		view.addQryButtonListener(new QryButtonListener());
		this.topModel = topModel;
	}
	

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//TODO
		JTextArea tObj = (JTextArea) view.getQryTxtArea();
		
	}
	
	private class QryButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent aevt) {
			SqlQry q = SqlQry.CSTM;
			q.setQry(view.getQryTxtArea().getText());
			JOptionPane.showMessageDialog(null, q.getQry());
			view.getMainTable().setModel(topModel.fetchSpecificTableData(q.CSTM, "searchStr"));
			
		}
		
	}
	
	private class ComboBokListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent aevt) {
			//TODO fix joins working with model.
			
			JComboBox jObj = (JComboBox) aevt.getSource();
			if(jObj.getSelectedIndex() == 0) {
				//System.out.println("its join1, here its time to call view.getMainTable().setModel(topModel.fetchJoinData(SqlQry.JOIN1, 'searchStr'));");
				view.getMainTable().setModel(topModel.fetchSpecificTableData(SqlQry.JOIN1, "searchStr"));
				view.getQryTxtArea().setText(SqlQry.JOIN1.getQry());
			}else if(jObj.getSelectedIndex() == 1) {
				view.getMainTable().setModel(topModel.fetchSpecificTableData(SqlQry.JOIN2, "searchStr"));
				view.getQryTxtArea().setText(SqlQry.JOIN2.getQry());
				//System.out.println("its join2, here its time to call view.getMainTable().setModel(topModel.fetchJoinData(SqlQry.JOIN2, 'searchStr'));");
			}
		}
	}
	//TODO switch betwen panels!
	private class MenjuBarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent aevt) {

			JMenuItem mObj = (JMenuItem) aevt.getSource();
			//System.out.println(mObj.getText());
			if(mObj.getText().equals("Edit tables")) {
				view.getMainTable().setModel(new DefaultTableModel()); // clear table
				view.displayEditTablePanels();
				System.out.println("hehe inne i edit table grejjen");
			}else {
				//TODO make GUI for these new joins. Example: let every join-option that
				//you give the user be a radioButton on the new panel
				//JOIN TABLES
				//view.getMainTable().setModel(topModel.fetchJoinData(SqlQry.JOIN1, "searchStr"));
				System.out.println("hehe inne i join grejjen");
				view.displayJoinTablePanels();
				view.getMainTable().setModel(new DefaultTableModel()); // clear table

			}
			view.revalidate();
			view.repaint();
		}
		
	}
	
	private class RadioButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent aevt) {
			//jag vill ha ngt i stil med:
			
			JRadioButton rObj = (JRadioButton) aevt.getSource();
			if(rObj.getText().equals("Albums")) {
				view.getMainTable().setModel(topModel.fetchTableData(SqlQry.ALBUM, ""));
			}else if(rObj.getText().equals("Artists")) {
				view.getMainTable().setModel(topModel.fetchTableData(SqlQry.ARTIST, ""));
			}else if(rObj.getText().equals("Tracks")) {
				view.getMainTable().setModel(topModel.fetchTableData(SqlQry.TRACK, ""));
			}else {
				view.getMainTable().setModel(topModel.fetchTableData(SqlQry.GENRE, ""));
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
		private JRadioButton rObj;
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
	
	//TODO auto refresh after CRUD:ing
	private class CRUDButtonsListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent aevt) {
			// TODO Auto-generated method stub
			System.out.println("CRUD TO BE COMING MATE");
			JButton bObj = (JButton) aevt.getSource();
			JRadioButton rObj = view.getSelectedRb();
				
			//TODO add "addConfirm"-method just as deleteConfirm.
			if(bObj.getText().equals("ADD")) {
				if(addConfirm()) {
					if(rObj.getText().equals("Albums")) {
						addDialog(topModel.addData(Table.ALBUM,
							getGatheredStuff(view.getTxtFieldsAlb(), view.getTxtFieldsAlb().length)));
					}else if(rObj.getText().equals("Artists")) {
						addDialog(topModel.addData(Table.ARTIST,
							getGatheredStuff(view.getTxtFieldsArt(), view.getTxtFieldsArt().length)));		
					}else if(rObj.getText().equals("Tracks")) {
						addDialog(topModel.addData(Table.TRACK,
							getGatheredStuff(view.getTxtFieldsTrk(), view.getTxtFieldsTrk().length)));	
					}else {
						addDialog(topModel.addData(Table.GENRE,
							getGatheredStuff(view.getTxtFieldsGen(), view.getTxtFieldsGen().length)));	
					}
				}else {
					JOptionPane.showMessageDialog(null, "Data was not added");
				}
			}else if(bObj.getText().equals("REGENERATE")) {
				//TODO, no functionality yet, add thisd l8r
				
				
			//TODO add "updateConfirm" method just as deleteConfirm beneath.	
			}else if(bObj.getText().equals("UPDATE")) {
				if(updateConfirm()) {
					if(rObj.getText().equals("Albums")) {
						updateDialog(topModel.updateData(Table.ALBUM,
							getGatheredStuff(view.getTxtFieldsAlb(), view.getTxtFieldsAlb().length)));
					}else if(rObj.getText().equals("Artists")) {
						updateDialog(topModel.updateData(Table.ARTIST,
							getGatheredStuff(view.getTxtFieldsArt(), view.getTxtFieldsArt().length)));		
					}else if(rObj.getText().equals("Tracks")) {
						updateDialog(topModel.updateData(Table.TRACK,
							getGatheredStuff(view.getTxtFieldsTrk(), view.getTxtFieldsTrk().length)));	
					}else {
						updateDialog(topModel.updateData(Table.GENRE,
							getGatheredStuff(view.getTxtFieldsGen(), view.getTxtFieldsGen().length)));	
					}
				}else {
					JOptionPane.showMessageDialog(null, "Data was not updated");
				}
			}else {//Deletation.
				if(deleteConfirm()) {
					if(rObj.getText().equals("Albums")) {
						deleteDialog(topModel.deleteData(Table.ALBUM, 
							getGatheredStuff(view.getTxtFieldsAlb(), view.getTxtFieldsAlb().length)));
					}else if(rObj.getText().equals("Artists")) {
						deleteDialog(topModel.deleteData(Table.ARTIST,
							getGatheredStuff(view.getTxtFieldsArt(), view.getTxtFieldsArt().length)));
					}else if(rObj.getText().equals("Tracks")) {
						deleteDialog(topModel.deleteData(Table.TRACK, 
							getGatheredStuff(view.getTxtFieldsTrk(), view.getTxtFieldsTrk().length)));
					}else {
						deleteDialog(topModel.deleteData(Table.GENRE, 
							getGatheredStuff(view.getTxtFieldsGen(), view.getTxtFieldsGen().length)));
					}
				}else {
					JOptionPane.showMessageDialog(null, "Data was not deleted");
				}
			}

//			view.getMainTable().repaint();
//			view.getMainTable().revalidate();
			view.refreshView();
		}
		
		private String[] getGatheredStuff(JTextField[] txtFields, int numOfStuff) {
			String[] stuff = new String[numOfStuff];
			for(int i=0;i<stuff.length;i++)
				stuff[i] = txtFields[i].getText();
			return stuff;
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
		private boolean updateConfirm() {
			Object[] options = {"HELL YEAH", "HELL NO"};
			int answer = JOptionPane.showOptionDialog(null,
				"Do you really want to update this data?",
				"CONFIRM DELETE",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.WARNING_MESSAGE,
				null,
				options,
				options[1]); //1 of safety reason, lal ;) (if user missclicks data will not be deleted).
			if(answer==0) return true;
			else return false;
		}
		private boolean addConfirm() {
			Object[] options = {"HELL YEAH", "HELL NO"};
			int answer = JOptionPane.showOptionDialog(null,
				"Do you really want to add this data?",
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
				view.getMainTable().setModel(topModel.fetchTableData(SqlQry.ALBUM, searchStr));
			}else if(rObj.getText().equals("Artists")) {
				view.getMainTable().setModel(topModel.fetchTableData(SqlQry.ARTIST, searchStr));
			}else if(rObj.getText().equals("Tracks")) {
				view.getMainTable().setModel(topModel.fetchTableData(SqlQry.TRACK, searchStr));
			}else {
				view.getMainTable().setModel(topModel.fetchTableData(SqlQry.GENRE, searchStr));
			}
			view.getMainTable().addMouseListener(new TableMouseListener());
		}
		
	}
}
