package os;

import java.util.HashMap;
import java.util.Iterator;
  
/**
 * class represents resources list. 
 * For better performance recourses keeping in HashMao
 * for fast finding
 * @author zee
 *
 */
public class ResourcesList {

	
	private HashMap<String, Resource> resources;
	
	/**
	 * constructor that initializes resources hashmap
	 */
	public ResourcesList() {
		this.resources = new HashMap<String, Resource>();
	}
	
	public boolean isExists(String id) {
		return this.resources.containsKey(id);
	}
	
	/**
	 * add new resource to list. Key is resource r id (r.getId()), value is r; 
	 * @param r - Resource
	 */
	public void create(Resource r) {
		this.resources.put(r.getId(), r);
	}
	
	/**
	 * returns Resource object by id
	 * @param id - resource id
	 * @return Resource object where its id value equals param id
	 */
	public Resource get(String id) {
		return this.resources.get(id);
	}
	
	/**
	 * removes Resource from resources list
	 * @param id - resource id
	 */
	public void destroy(String id) {
		this.resources.remove(id);
	}
	
	/**
	 * method for getting for example vm program source which starts with DATA
	 * @param start key start string
	 * @return resource which key starts with start
	 */
	public Resource getStartsWith(String start) {
		Iterator<String > i = this.resources.keySet().iterator();
		while (i.hasNext()) {
			String key = i.next();
			if ( key.startsWith(start) ) {
				return this.resources.get(key);
			}
		}
		return null;
		
	}
}
