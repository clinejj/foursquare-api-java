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

public class CompleteVenue extends CompactVenue {

  private static final long serialVersionUID = -3318179465794411655L;

  public String getDescription() {
    return description;
  }
  
  public Mayor getMayor() {
    return mayor;
  }
  
  public String[] getTags() {
    return tags;
  }
  
  public Count getBeenHere() {
    return beenHere;
  }
  
  public String getShortUrl() {
    return shortUrl;
  }
  
  public CompleteSpecial[] getSpecialsNearby() {
    return specialsNearby;
  }
  
  public Photos getPhotos() {
    return photos;
  }
  
  public String getTimeZone() {
    return timeZone;
  }

  private String description;
  private Mayor mayor;
  private String[] tags;
  private Count beenHere;
  private String shortUrl;
  private CompleteSpecial[] specialsNearby;
  private Photos photos;
  private String timeZone;
}
