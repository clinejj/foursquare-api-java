package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class Recommendation implements FoursquareEntity {

  private static final long serialVersionUID = -1033058777540900197L;

  public ReasonGroup getReasons() {
    return reasons;
  }
  
  public CompactVenue getVenue() {
    return venue;
  }
  
  public CompleteTip[] getTips() {
    return tips;
  }
  
  private ReasonGroup reasons;
  private CompactVenue venue;
  private CompleteTip[] tips;
}
