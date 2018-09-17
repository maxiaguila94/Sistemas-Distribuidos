package ui;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class HeaderPanel extends JPanel {
	
	public JLabel brand;
	public JLabel imgBrand;
	
	public HeaderPanel() {
		super(); 
		this.setBackground(SystemColor.control);
		this.setBorder(new LineBorder(SystemColor.windowBorder, 1, true));
		this.setLayout(new MigLayout("fillx", "[grow][][center][][grow]", "[60px:80px:120px,grow 200]"));
		
		
		this.brand = new JLabel("Remote File System Client");
		this.brand.setFont(new Font("DejaVu Sans", Font.BOLD, 20));
		this.add(brand, "cell 2 0,alignx center");
		
		this.imgBrand = new JLabel();
		this.imgBrand.setFont(new Font("Dialog", Font.BOLD, 24));
		this.imgBrand.setIcon(new ImageIcon(MainUI.class.getResource("/ui/icons/3d-printing-software(5).png")));
		
		this.add(imgBrand, "flowx,cell 4 0,alignx center");
		
		
	}
	
}
