package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class Reason implements FoursquareEntity {

  private static final long serialVersionUID = -6388056927086935959L;

  public String getType() {
    return type;
  }
  
  public String getMessage() {
    return message;
  }
  
  private String type;
  private String message;
}