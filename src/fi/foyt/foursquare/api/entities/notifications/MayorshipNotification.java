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

package fi.foyt.foursquare.api.entities.notifications;

import fi.foyt.foursquare.api.FoursquareEntity;
import fi.foyt.foursquare.api.entities.CompactUser;

public class MayorshipNotification implements FoursquareEntity {

  private static final long serialVersionUID = 5504414040235439757L;

  public String getType() {
    return type;
  }
  
  public Long getCheckins() {
    return checkins;
  }
  
  public Long getDaysBehind() {
    return daysBehind;
  }
  
  public CompactUser getUser() {
    return user;
  }
  
  public String getMessage() {
    return message;
  }
  
  public String getImage() {
    return image;
  }

  private String type;
  private Long checkins;
  private Long daysBehind;
  private CompactUser user;
  private String message;
  private String image;
}