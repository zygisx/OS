package os;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
  
/**
 * class represents resources list. 
 * For better performance recourses keeping in HashMao
 * for fast finding
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
		
		//FIXME
		System.out.println("\tResource " + r.getId().substring(0, (r.getId().length() > 10 ? 10 : r.getId().length() ))  + " created");
		
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
	
	public Resource getAvailable(String id) {
		for (Resource r : this.resources) {
			if (r.getId().equals(id) && r.isAvailable()) {
				return r;
			}
		}
		return null;
	}
	
	public Resource[] getAll(String id) {
		Resource[] resources = new Resource[20];
		int i = 0;
		for (Resource r : this.resources) {
			if (r.getId().equals(id)) {
				resources[i++] = r;
			}
		}
		return resources;
	}
	
	/**
	 * removes Resource from resources list
	 * @param id - resource id
	 */
	public void destroy(String id) {
		
		//FIXME  
		System.out.println("\tResource " + id.substring(0, (id.length() > 10 ? 10 : id.length() ))  + " destroyed");
		
		Iterator<Resource> i = this.resources.iterator();
		while (i.hasNext()) {
			Resource r = i.next();
			if (r.getId().equals(id)) {
				i.remove();
				return;
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
	
	public Resource get(int num) {
		return resources.get(num);
	}
	
	public int getCount() {
		return resources.size();
	}
}
