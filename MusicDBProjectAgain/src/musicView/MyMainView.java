package musicView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class MyMainView extends JPanel {


	private static final long serialVersionUID = -6586691518396309146L;

	private JPanel cPanel,nPanel, sPanel, ePanel, wPanel;
	
	private JPanel ePanelAlb, ePanelArt, ePanelTrk, ePanelGen;
	private CustomTxtField[] txtFieldsAlb, txtFieldsArt, txtFieldsTrk, txtFieldsGen;
	private JLabel[] labelsAlb, labelsArt, labelsTrk, labelsGen;
//	private JButton updateB, addB, deleteB, regenerateB;
	private JButton[] crudBtns;
	private JRadioButton[] rbs;
	private JTable mainTable;
	private JScrollPane mainScrollPane;
	
	//private DefaultTableModel[] dm;
	
	
	
	public MyMainView() {
		initGUI();
	}
	
	private void initGUI() {
		setLayout(new BorderLayout());
		makeCenterPanel();
		makeNorthPanel();
		makeSouthPanel();
		makeEastPanel();
		makeWestPanel();
	}
	
	private void makeCenterPanel() {
		cPanel = new JPanel();	
		
		String[] colonTitles = {"colon1", "colon2", "colon3", "colon4"};
		Object[][] rowData = {
			{"data1","data2","data3","data4"},
			{"data1","data2","data3","data4"},
			{"data1","data2","data3","data4"},
			{"data1","data2","data3","data4"},
			{"data1","data2","data3","data4"}
		};
		
		mainTable = new JTable(rowData, colonTitles);
		mainTable.setFillsViewportHeight(true);
		mainScrollPane = new JScrollPane(mainTable);
		
		cPanel.add(mainScrollPane);
		
		add(cPanel, BorderLayout.CENTER);
	}
	
//	updateB, addB, deleteB, regenerateB;
	private void makeNorthPanel() {
		nPanel = new JPanel(); // new BoxLayout(nPanel,BoxLayout.X_AXIS)
		crudBtns = new JButton[4];
		crudBtns[0] = new JButton("ADD");
		crudBtns[1] = new JButton("REGENERATE");
		crudBtns[2] = new JButton("UPDATE");
		crudBtns[3] = new JButton("DELETE");
		
		for(int i=0;i<crudBtns.length;i++)
			nPanel.add(crudBtns[i]);
	
		add(nPanel, BorderLayout.NORTH);
	}

	private void makeSouthPanel() {
		sPanel = new JPanel(new GridLayout(1,4));
		
		rbs = new JRadioButton[4];
		
		rbs[0] = new JRadioButton("Albums");
		rbs[1] = new JRadioButton("Artists");
		rbs[2] = new JRadioButton("Tracks");
		rbs[3] = new JRadioButton("Genres");
		
		ButtonGroup btnGrp = new ButtonGroup();
		for(int i=0; i<rbs.length; i++) {
			btnGrp.add(rbs[i]);
			sPanel.add(rbs[i]);
		}	
		add(sPanel, BorderLayout.SOUTH);
	}
	
//FIX*** make common JTextField and JLabel inner classes, to style the gui l8r.
	private void makeEastPanel() {
		ePanel = new JPanel();
		
		//album
		ePanelAlb = new JPanel(new GridLayout(6,1));
		txtFieldsAlb = new CustomTxtField[3];
		txtFieldsAlb[0] = new CustomTxtField("pk_album_id");
		txtFieldsAlb[1] = new CustomTxtField("album_name");
		txtFieldsAlb[2] = new CustomTxtField("fk_artist_id");
		
		labelsAlb = new JLabel[3];
		labelsAlb[0] = new JLabel("pk_album_id");
		labelsAlb[1] = new JLabel("album_name");
		labelsAlb[2] = new JLabel("fk_artist_id");
		
		for(int i=0; i<txtFieldsAlb.length;i++) { // eller labelsAlb.length
			ePanelAlb.add(labelsAlb[i]);
			ePanelAlb.add(txtFieldsAlb[i]);
		}
		

		//artist
		ePanelArt = new JPanel(new GridLayout(6,1));
		txtFieldsArt = new CustomTxtField[3];
		txtFieldsArt[0] = new CustomTxtField("pk_artist_id");
		txtFieldsArt[1] = new CustomTxtField("artist_name");
		txtFieldsArt[2] = new CustomTxtField("country");
		
		labelsArt = new JLabel[3];
		labelsArt[0] = new JLabel("pk_artist_id");
		labelsArt[1] = new JLabel("artist_name");
		labelsArt[2] = new JLabel("country");
		
		for(int i=0; i<txtFieldsArt.length;i++) { // eller labelsArt.length
			ePanelArt.add(labelsArt[i]);
			ePanelArt.add(txtFieldsArt[i]);
		}
		
		
		//track
		ePanelTrk = new JPanel(new GridLayout(14,1));
		txtFieldsTrk = new CustomTxtField[7];
		txtFieldsTrk[0] = new CustomTxtField("pk_track_id");
		txtFieldsTrk[1] = new CustomTxtField("track_name");
		txtFieldsTrk[2] = new CustomTxtField("track_time");
		txtFieldsTrk[3] = new CustomTxtField("fk_artist_id");
		txtFieldsTrk[4] = new CustomTxtField("fk_album_id");
		txtFieldsTrk[5] = new CustomTxtField("release_date");
		txtFieldsTrk[6] = new CustomTxtField("fk_genre_id");
		
		labelsTrk = new JLabel[7];
		labelsTrk[0] = new JLabel("pk_track_id");
		labelsTrk[1] = new JLabel("track_name");
		labelsTrk[2] = new JLabel("track_time");
		labelsTrk[3] = new JLabel("fk_artist_id");
		labelsTrk[4] = new JLabel("fk_album_id");
		labelsTrk[5] = new JLabel("release_date");
		labelsTrk[6] = new JLabel("fk_genre_id");
		
		for(int i=0; i<txtFieldsTrk.length;i++) { // eller labelsTrk.length
			ePanelTrk.add(labelsTrk[i]);
			ePanelTrk.add(txtFieldsTrk[i]);
		}
		
		
		//genre
		ePanelGen = new JPanel(new GridLayout(4,1));
		txtFieldsGen = new CustomTxtField[2];
		txtFieldsGen[0] = new CustomTxtField("pk_genre_id");
		txtFieldsGen[1] = new CustomTxtField("genre_name");
		
		labelsGen = new JLabel[2];
		labelsGen[0] = new JLabel("pk_genre_id");
		labelsGen[1] = new JLabel("genre_name");
		
		for(int i=0; i<txtFieldsGen.length;i++) { // eller labelsTrk.length
			ePanelGen.add(labelsGen[i]);
			ePanelGen.add(txtFieldsGen[i]);
		}
		
		
		//ePanelAlb, ePanelArt, ePanelTrk, ePanelGen OK
		add(ePanel, BorderLayout.EAST);
	}
	
	//ePanelAlb, ePanelArt, ePanelTrk, ePanelGen  will be the ones who will pend here.
	public void switchEastPanel(JRadioButton rb) {
		ePanel.removeAll();
		switch(rb.getText()) {
			case "Albums":
				ePanel.add(ePanelAlb, BorderLayout.EAST);
				break;
			case "Artists":
				ePanel.add(ePanelArt, BorderLayout.EAST);
				break;
			case "Tracks":
				ePanel.add(ePanelTrk, BorderLayout.EAST);
				break;
			case "Genres":
				ePanel.add(ePanelGen, BorderLayout.EAST);
				break;
		}
		ePanel.repaint();
		ePanel.revalidate();
	}

	private void makeWestPanel() {
		wPanel = new JPanel();
			
		add(wPanel, BorderLayout.WEST);
	}
	
	public void addRadioButtonListener(ActionListener actionListen) {
		for(int i=0; i<rbs.length; i++) 
			rbs[i].addActionListener(actionListen);
	}
	
	public void addTableMouseListener(MouseListener moutseListen) {
		mainTable.addMouseListener(moutseListen);

	}
	
	public JTable getMainTable() {
		return mainTable;
	}
	
	public JRadioButton getSelectedRb() {
		if(rbs[0].isSelected())
			return rbs[0];
		else if(rbs[1].isSelected())
			return rbs[1];
		else if(rbs[2].isSelected())
			return rbs[2];
		else 
			return rbs[3];
	}
	
	public JRadioButton getAlbRb() {
		return rbs[0];
	}
	public JRadioButton getArtRb() {
		return rbs[1];
	}
	public JRadioButton getTrkRb() {
		return rbs[2];
	}
	public JRadioButton getGenRb() {
		return rbs[3];
	}
	
	
	public JTextField[] getTxtFieldsAlb() {
		return txtFieldsAlb;
	}
	public JTextField[] getTxtFieldsArt() {
		return txtFieldsArt;
	}
	public JTextField[] getTxtFieldsTrk() {
		return txtFieldsTrk;
	}
	public JTextField[] getTxtFieldsGen() {
		return txtFieldsGen;
	}
	
	private class CustomTxtField extends JTextField {

		private final int WIDTH = 150;
//		private final int HEIGHT = 50;
		
		public CustomTxtField(String string) {
			super(string);
			setPreferredSize(new Dimension(WIDTH,HEIGHT));
		}
		
	}
	

}
