package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * Class representing Recommendation entity
 * 
 * @author Antti Leppä
 */
public class Recommendation implements FoursquareEntity {

  private static final long serialVersionUID = -1033058777540900197L;

  /**
   * Returns reasons for recommendation
   * 
   * @return reasons for recommendation
   */
  public ReasonGroup getReasons() {
    return reasons;
  }
  
  /**
   * Returns recommended venues
   * 
   * @return recommended venues
   */
  public CompactVenue getVenue() {
    return venue;
  }
  
  /**
   * Returns array of recommended tips
   * 
   * @return array of recommended tips
   */
  public CompleteTip[] getTips() {
    return tips;
  }
  
  private ReasonGroup reasons;
  private CompactVenue venue;
  private CompleteTip[] tips;
}
