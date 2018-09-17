package ui;

import controllers.RemoteFilesController;
import remoteobjects.FileMetadata;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class RemoteFilesPanel extends ScrollPane{

	JPanel panel;
	RemoteFilesController controller;
	List<FileMetadata> available_remote_files; 
	
	public RemoteFilesPanel(RemoteFilesController controller) {
		this.controller = controller;
		this.setAvailableRemoteFiles(this.controller.getAvailableFiles());

		this.setBackground(Color.WHITE);
        
        
        
        this.panel = new JPanel(new MigLayout());
        this.panel.setBackground(Color.WHITE);
        int count = 0;
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder(
        		null, 
        		"ARCHIVOS DISPONIBLES EN EL SERVIDOR", 
        		javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
        		javax.swing.border.TitledBorder.DEFAULT_POSITION, 
        		new java.awt.Font("DejaVu Sans", Font.PLAIN, 18), 
        		new java.awt.Color(0, 0, 0))
        	);
        
        for (FileMetadata fileMetadata : available_remote_files) {
            ImageIcon icon = getIcone(getExtension(fileMetadata.getFileName().toLowerCase()));
            JButton img = new JButton(fileMetadata.getFileName());
            img.setBackground(Color.WHITE);
            img.setIcon(icon);
            img.addActionListener(this.controller);
            img.setActionCommand(fileMetadata.getFileName());
            if (count >= 4) {
            	count = 0;
            	this.panel.add(img, "wrap");
            } else {            	
            	count++;
            	this.panel.add(img);
            }
		}
        
        this.add(this.panel);
        this.revalidate();
	}
    public String getExtension(String name)
    {
            if(name.lastIndexOf(".")!=-1)
            {
                    String extensionPossible = name.substring(name.lastIndexOf(".")+1, name.length());
                    if(extensionPossible.length()>6){
                            return "";
                    }else{
                            return extensionPossible;
                    }
            }
            else return "";
    }

	public ImageIcon getIcone(String extension) {
		
		String iconName = extension+"-icon-48x48.png"; 
		String iconPath = "/ui/images/"+iconName;
		
		return new ImageIcon(this.getClass().getResource(iconPath));
	}
	
	public void setController(RemoteFilesController controller) {
		this.controller = controller;
		this.setAvailableRemoteFiles(this.controller.getAvailableFiles());
	}
	
	public void setAvailableRemoteFiles(List<FileMetadata> available_remote_files) {
		this.available_remote_files = new ArrayList<>(available_remote_files);
	}
	
	public FileMetadata getFile(String file_name) {
		Object file = this.available_remote_files
			.stream()
			.filter(f -> f.getFileName().equals(file_name))
			.findFirst()
			.get();
		if(file == null)
			return null;

		return (FileMetadata) file;
		
	}
	
}
