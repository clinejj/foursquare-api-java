package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class Link implements FoursquareEntity {

  private static final long serialVersionUID = -3591269038202708130L;

  public LinkProvider getProvider() {
    return provider;
  }
  
  public String getUrl() {
    return url;
  }
  
  public String getLinkedId() {
    return linkedId;
  }
  
  private LinkProvider provider;
  private String url;
  private String linkedId;
}
