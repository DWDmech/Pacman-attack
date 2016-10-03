package my.game.objects.gui.maps;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import my.game.abstracts.AbstractGameObject;

public class ImageRenderer extends DefaultTableCellRenderer{
	private static final long serialVersionUID = -5872712802332865418L;
	
	private JLabel lb1 = new JLabel();
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		lb1.setText(null);
		lb1.setIcon(((AbstractGameObject)value).getIcon());
		
		return lb1;
	}

}
