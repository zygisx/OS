package os;

import java.util.Iterator;
import java.util.LinkedList;
  
/**
 * class represents resources list. 
 * For better performance recourses keeping in HashMao
 * for fast finding
 * @author zee
 *
 */
public class ResourcesList {

	
	private LinkedList<Resource> resources;
	
	/**
	 * constructor that initializes resources hashmap
	 */
	public ResourcesList() {
		this.resources = new LinkedList<Resource>();
	}
	
	public boolean isExists(String id) {
		for (Resource r : this.resources) {
			if (r.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * add new resource to list. Key is resource r id (r.getId()), value is r; 
	 * @param r - Resource
	 */
	public void create(Resource r) {
		this.resources.add(r);
	}
	
	/**
	 * returns Resource object by id
	 * @param id - resource id
	 * @return Resource object where its id value equals param id
	 */
	public Resource get(String id) {
		for (Resource r : this.resources) {
			if (r.getId().equals(id)) {
				return r;
			}
		}
		return null;
	}
	
	/**
	 * removes Resource from resources list
	 * @param id - resource id
	 */
	public void destroy(String id) {

		Iterator<Resource> i = this.resources.iterator();
		while (i.hasNext()) {
			Resource r = i.next();
			if (r.getId().equals(id)) {
				this.resources.remove(r);
			}
		}
	}
	
	/**
	 * method for getting for example vm program source which starts with DATA
	 * @param start key start string
	 * @return resource which key starts with start
	 */
	public Resource getStartsWith(String start) {
		Iterator<Resource> i = this.resources.iterator();
		while (i.hasNext()) {
			Resource r = i.next();
			if ( r.getId().startsWith(start) ) {
				return r;
			}
		}
		return null;
		
	}
}
