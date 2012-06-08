package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * Just holds the geocode data
 * @author rmangi
 *
 */
public class GeoCode implements FoursquareEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2673015081507846654L;
	
	GeoCodeFeature feature;

	public GeoCode() {
		super();
	}
	

	public GeoCode(GeoCodeFeature feature) {
		super();
		this.feature = feature;
	}

	public GeoCodeFeature getFeature() {
		return feature;
	}

	public void setFeature(GeoCodeFeature feature) {
		this.feature = feature;
	}

	
	
	
}
