package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class VenueHistory implements FoursquareEntity {

  private static final long serialVersionUID = 427279593882572350L;

  public Integer getBeenHere() {
    return beenHere;
  }
  
  public CompactVenue getVenue() {
    return venue;
  }
  
  private Integer beenHere;
  private CompactVenue venue;
}
