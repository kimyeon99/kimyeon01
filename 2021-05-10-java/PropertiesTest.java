package scpark3;

import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Set;

public class PropertiesTest {

	public static void main(String[] args) {
	//	Map<String, String> env = System.getenv();
    //	Set<String> keys = env.keySet();
	//	for(String key: keys) {
	//		System.out.println(key + " : " + env.get(key));
	//	}
		
		Properties props = new Properties();
		File file = new File("dict.props");
		try(FileReader reader = new FileReader("dict.props");){
			props.load(reader);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		props.put("name", "È«±æµ¿");
		props.put("age", "20");
		
		Set<Object> keys = props.keySet();
		for(Object key : keys) {
			System.out.println(key + " : " + props.get(key));
		}
		
		props.put("³ª¹«", "tree");
		try(PrintWriter out = new PrintWriter(new File("dict.props"))){
			props.store(out	, "My Dictionary");	
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
