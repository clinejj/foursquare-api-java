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

public class Badge implements FoursquareEntity {

  private static final long serialVersionUID = -1700328878776397998L;

  public String getId() {
    return id;
  }
  
  public String getBadgeId() {
    return badgeId;
  }
  
  public String getName() {
    return name;
  }
  
  public String getDescription() {
    return description;
  }
  
  public BadgeImage getImage() {
    return image;
  }
  
  public Checkin[] getUnlocks() {
    return unlocks;
  }

  private String id;
  private String badgeId;
  private String name;
  private String description;
  private BadgeImage image;
  private Checkin[] unlocks;
}
