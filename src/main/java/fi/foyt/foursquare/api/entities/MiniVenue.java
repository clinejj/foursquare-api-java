package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * MiniVenues are for responses from the autoComplete service
 * https://developer.foursquare.com/docs/venues/suggestcompletion
 * 
 * @author rmangi
 * 
 */
public class MiniVenue implements FoursquareEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7279890086931237254L;

	private String id;
	
	private String name;
	
	private Location location;
	
	private Category[] categories;

	/**
	 * get the id
	 * @return id
	 */
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * name of the venue
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * location of the venue
	 * @return Location object
	 */
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	/**
	 * categories for the venue
	 * @return array of Category objects
	 */
	public Category[] getCategories() {
		return categories;
	}
	
	public void setCategories(Category[] categories) {
		this.categories = categories;
	}

}
