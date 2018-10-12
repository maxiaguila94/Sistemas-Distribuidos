package banco;

import java.lang.reflect.Field;

public class Model {

	public String tableName;	
	
	public Model() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		
		String classname = this.getClass().getCanonicalName().toLowerCase();
		this.tableName = classname.split("\\.")[1] + "s";
		
	}
	
	
	public String getFindQuery() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Class<? extends Model> klass = this.getClass();
		Field id = klass.getDeclaredField("id"); 
		
		String query = "select * from " + this.tableName + " where id=" + id.get(this);
		return query;
	}
	
	public String getUpdateQuery() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		
		Class<? extends Model> klass = this.getClass();  
		
		Field[] attrs = klass.getDeclaredFields(); 
		
		String query = "update " + this.tableName + " set ";
		
		for (int i = 0; i < attrs.length; i++) {
			attrs[i].setAccessible(true);
			if (attrs[i].getName() != "id" && attrs[i].getName() != "manager") {				
				if (String.class.equals(attrs[i].getType())) {
					query = query + attrs[i].getName() + "='" + ((String)attrs[i].get(this)).trim() + "'";
				} else {					
					query = query + attrs[i].getName() + "=" + attrs[i].get(this);
				}
				
				if (i < attrs.length -2)
					query = query + ", ";
			}
				
	
		}
		
//		for (Field field : attrs) {
//			field.setAccessible(true);
//			if (field.getName() != "id" && field.getName() != "manager")
//				
//				if (String.class.equals(field.getType())) {
//					query = query + field.getName() + "=" + ((String)field.get(this)).trim() + ", ";
//				} else {					
//					query = query + field.getName() + "=" + field.get(this) + ", ";
//				}
//		}
		
		query = query + " where id=" + klass.getDeclaredField("id").get(this) + ";";
		
		return query;
		
	}
	
}
