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

public class CompactVenue extends FoursquareEntity {
  
  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Boolean getVerified() {
    return verified;
  }

  public Contact getContact() {
    return contact;
  }

  public Location getLocation() {
    return location;
  }

  public Category[] getCategories() {
    return categories;
  }

  public CompleteSpecial[] getSpecials() {
    return specials;
  }

  public HereNow getHereNow() {
    return hereNow;
  }
  
  public Stats getStats() {
    return stats;
  }

  private String id;
  private String name;
  private Boolean verified;
  private Contact contact;
  private Location location;
  private Category[] categories;
  private CompleteSpecial[] specials;
  private HereNow hereNow;
  private Stats stats;
  
}
