package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * Class representing Icon entity
 * 
 * @see <a href="https://developer.foursquare.com/docs/responses/category.html" target="_blank">https://developer.foursquare.com/docs/responses/category.html</a>
 * 
 * @author John Cline
 */
public class Icon implements FoursquareEntity {

	private static final long serialVersionUID = -6661365522571579781L;
	
	/**
	 * Returns prefix of the URL of a icon image for this category.
	 * 
	 * @return Prefix of the URL of a icon image for this category.
	 */
	public String getPrefix() {
		return prefix;
	}
	
	/**
	 * Returns suffix of the URL of a icon image for this category.
	 * 
	 * @return suffix of the URL of a icon image for this category.
	 */
	public String getSuffix() {
		return suffix;
	}
	
	/**
	 * Returns the URL of a icon image for this category.
	 * 
	 * @return URL of a icon image for this category.
	 */
	public String getFullUrl() {
		return prefix + suffix;
	}
	
	private String prefix;
	private String suffix;

}
