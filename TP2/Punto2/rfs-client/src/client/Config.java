package client;

import java.util.Properties;
import java.io.FileInputStream;

public class Config {
	
	private static final String CONF_FILE_PATH = "src/conf.properties";
	private static Properties properties;
	
	public Config() throws Exception {
				
	}
	
	public static Properties getProperties() throws Exception {
		if (properties == null) {
			properties = new Properties();
			try {
				FileInputStream is = new FileInputStream(CONF_FILE_PATH);
				properties.load(is);
				is.close();		
			} catch (Exception e) {
				e.printStackTrace();
				properties = null;
				throw new Exception("No pudo cargarse la configuración");
				
			}
		}
		
		return properties;
	}
}
