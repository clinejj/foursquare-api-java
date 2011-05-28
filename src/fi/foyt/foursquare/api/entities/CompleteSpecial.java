/*
 * FoursquareAPI - Foursquare API for Java
 * Copyright (C) 2008 - 2011 Antti Leppä / Foyt
 * http://www.foyt.fi
 * 
 * License: 
 * 
 * Licensed under GNU Lesser General Public License Version 3 or later (the "LGPL")
 * http://www.gnu.org/licenses/lgpl.html
 */

package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class CompleteSpecial implements FoursquareEntity {
  
  private static final long serialVersionUID = 3919231353933056022L;
  
  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public String getMessage() {
    return message;
  }
  
  public String getFinePrint() {
    return finePrint;
  }

  public String getDescription() {
    return description;
  }

  public Boolean getUnlocked() {
    return unlocked;
  }
  
  public String getIcon() {
    return icon;
  }
  
  public String getTitle() {
    return title;
  }
  
  public String getState() {
    return state;
  }
  
  public String getProvider() {
    return provider;
  }
  
  public String getRedemption() {
    return redemption;
  }
  
  public CompactVenue getVenue() {
    return venue;
  }
  
  private String id;
  private String type;
  private String message;
  private String finePrint;
  private String description;
  private Boolean unlocked;
  private String icon;
  private String title;
  private String state;
  private String provider;
  private String redemption;
  private CompactVenue venue;
}
