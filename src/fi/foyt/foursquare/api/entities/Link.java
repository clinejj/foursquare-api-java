package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * Class representing Link entity
 * 
 * @see <a href="https://developer.foursquare.com/docs/responses/link.html" target="_blank">https://developer.foursquare.com/docs/responses/link.html</a>
 * 
 * @author Antti Leppä
 */
public class Link implements FoursquareEntity {

  private static final long serialVersionUID = -3591269038202708130L;

  /**
   * Returns id of the provider
   * 
   * @return id of the provider
   */
  public LinkProvider getProvider() {
    return provider;
  }
  
  /**
   * Returns a URL for additional information about this venue from this provider.
   * 
   * @return a URL for additional information about this venue
   */
  public String getUrl() {
    return url;
  }
  
  /**
   * Returns the identifer used by this provider to identify this venue.
   * 
   * @return identifer used by this provider to identify this venue
   */
  public String getLinkedId() {
    return linkedId;
  }
  
  private LinkProvider provider;
  private String url;
  private String linkedId;
}
