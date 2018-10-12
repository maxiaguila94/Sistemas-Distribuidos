package server;

import java.util.Properties;
import java.io.FileInputStream;

public class Config {
	
	private static final String CONF_FILE_PATH = "src/conf.properties";
	private static Properties properties;
	
	public Config() throws Exception {
		
		if (this.properties == null) {
			this.properties = new Properties();
			try {
				FileInputStream is = new FileInputStream(CONF_FILE_PATH);
				this.properties.load(is);
				is.close();		
			} catch (Exception e) {
				e.printStackTrace();
				this.properties = null;
				throw new Exception("No pudo cargarse la configuración");
				
			}
		}		
	}
	
	public Properties getProperties() {
		return this.properties;
	}
}
