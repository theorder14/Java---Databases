import javax.swing.JFrame;

import musicController.MyController;
import musicModel.TopModel.MyTopModel;
import musicView.MyMainView;


public class MyMain extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new MyMain();
	}
	
	public MyMain() {
		MyMainView view = new MyMainView(this);
		add(view);
		MyTopModel topModel = new MyTopModel();
		MyController cont = new MyController(view, topModel);
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
	}

}
