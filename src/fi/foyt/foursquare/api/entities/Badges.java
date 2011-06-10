package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class Badges implements FoursquareEntity {

  private static final long serialVersionUID = 4610940737520258516L;

  public Badges(BadgeSets sets, Badge[] badges, String defaultSetType) {
    this.sets = sets;
    this.badges = badges;
    this.defaultSetType = defaultSetType;
  }

  public BadgeSets getSets() {
    return sets;
  }
  
  public Badge[] getBadges() {
    return badges;
  }
  
  public String getDefaultSetType() {
    return defaultSetType;
  }
  
  private BadgeSets sets;
  private Badge[] badges;
  private String defaultSetType;
}
