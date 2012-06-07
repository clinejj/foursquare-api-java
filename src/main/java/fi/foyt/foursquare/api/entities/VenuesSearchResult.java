package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * Result object for venues/search request
 * 
 * In version 20110615 Foursquare changes venues/search request to return 
 * array of venues instead of venue groups what request used to result, 
 * with an exception that sometimes request might still return groups. 
 * For example, on election days, foursquare may choose to temporarily 
 * add a pollingplaces group, which your application should handle gracefully.
 * 
 * @author Antti Leppä
 */
public class VenuesSearchResult implements FoursquareEntity {

  private static final long serialVersionUID = -4811474739114637413L;
  

  private CompactVenue[] venues;
  private VenueGroup[] groups;
  private GeoCode geocode;
  /**
   * Constructor
   * 
   * @param venues array of resulted venues
   * @param groups array of resulted groups 
   */
  public VenuesSearchResult(CompactVenue[] venues, VenueGroup[] groups) {
    this.venues = venues;
    this.groups = groups;
  }
  
  public VenuesSearchResult(CompactVenue[] venues, VenueGroup[] groups, GeoCode geocode) {
	    this(venues,groups);
	    this.geocode = geocode;
	  }
  
  /**
   * Returns resulted venues
   * 
   * @return resulted venues
   */
  public CompactVenue[] getVenues() {
    return venues;
  }
  
  /**
   * Returns resulted venue groups
   * 
   * @return resulted venue groups
   */
  public VenueGroup[] getGroups() {
    return groups;
  }

public GeoCode getGeocode() {
	return geocode;
}

public void setGeocode(GeoCode geocode) {
	this.geocode = geocode;
}
  
}
