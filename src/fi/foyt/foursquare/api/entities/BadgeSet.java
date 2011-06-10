package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class BadgeSet implements FoursquareEntity {

  private static final long serialVersionUID = 6647018689695570878L;

  public String getType() {
    return type;
  }
  
  public String getName() {
    return name;
  }
  
  public BadgeImage getImage() {
    return image;
  }
  
  public String[] getItems() {
    return items;
  }
  
  public BadgeSet[] getGroups() {
    return groups;
  }
  
  private String type;
  private String name;
  private BadgeImage image;
  private String[] items;
  private BadgeSet[] groups;
}