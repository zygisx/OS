package os;

import java.util.HashMap;
  
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
}
