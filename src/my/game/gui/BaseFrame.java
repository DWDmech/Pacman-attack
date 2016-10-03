package my.game.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public abstract class BaseFrame extends JFrame{

	private static final long serialVersionUID = 7305503580080311775L;
	
	private JFrame parentFrame;

	public BaseFrame() {
		setCloseOperation();
		setResizable(false);
	}

	public JFrame getParent() {
		return parentFrame;
	}
	
	protected void setCloseOperation() {
		super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		super.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeFrame();
			} 	
		});
	}
	
	protected void closeFrame(){
		if(parentFrame == null){
			throw new IllegalArgumentException("Parent farme is null");
		}
		parentFrame.setVisible(true);
		super.setVisible(false);
	}
	
	protected void showFrame(JFrame parent){
		this.parentFrame = parent;
		parent.setVisible(false);
		super.setVisible(true);
	}
}
