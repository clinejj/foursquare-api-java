package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class BadgeSets implements FoursquareEntity {

  private static final long serialVersionUID = -538891009716828719L;

  public BadgeSet[] getGroups() {
    return groups;
  }
  
  private BadgeSet[] groups;
}
