package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class Keyword implements FoursquareEntity {

  private static final long serialVersionUID = -3697725825647022962L;

  public String getDisplayName() {
    return displayName;
  }
  
  public String getKeyword() {
    return keyword;
  }
  
  private String displayName;
  private String keyword;
}