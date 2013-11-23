package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * Class representing User Photo entity
 * 
 * @see <a href="https://developer.foursquare.com/docs/responses/user.html" target="_blank">https://developer.foursquare.com/docs/responses/user.html</a>
 * 
 * @author John Cline
 */
public class UserPhoto implements FoursquareEntity {

	private static final long serialVersionUID = -1665068236339487733L;

	/**
	 * Returns prefix of the URL of a profile picture for this user.
	 * 
	 * @return Prefix of the URL of a profile picture for this user.
	 */
	public String getPrefix() {
		return prefix;
	}
	
	/**
	 * Returns suffix of the URL of a profile picture for this user.
	 * 
	 * @return suffix of the URL of a profile picture for this user.
	 */
	public String getSuffix() {
		return suffix;
	}
	
	/**
	 * Returns the URL of a profile picture for this user.
	 * 
	 * @return URL of a profile picture for this user.
	 */
	public String getFullUrl() {
		return prefix + suffix;
	}
	
	private String prefix;
	private String suffix;
}
