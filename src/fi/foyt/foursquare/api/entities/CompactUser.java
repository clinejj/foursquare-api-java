/*
 * FoursquareAPI - Foursquare API for Java
 * Copyright (C) 2008 - 2011 Antti Leppä / Foyt
 * http://www.foyt.fi
 * 
 * License: 
 * 
 * Licensed under GNU Lesser General Public License Version 2.1 or later (the "LGPL") 
 * http://www.gnu.org/licenses/lgpl.html
 */

package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class CompactUser implements FoursquareEntity {

  private static final long serialVersionUID = 477096997911461087L;

  public String getId() {
    return id;
  }
  
  public String getFirstName() {
    return firstName;
  }
  
  public String getLastName() {
    return lastName;
  }
  
  public String getHomeCity() {
    return homeCity;
  }
  
  public String getPhoto() {
    return photo;
  }
  
  public String getGender() {
    return gender;
  }
  
  public String getRelationship() {
    return relationship;
  }
  
  private String id;
  private String firstName;
  private String lastName;
  private String homeCity;
  private String photo;
  private String gender;
  private String relationship;
}
