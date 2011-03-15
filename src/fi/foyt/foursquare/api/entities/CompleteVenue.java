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

public class CompleteVenue extends CompactVenue {

  public String getDescription() {
    return description;
  }
  
  public CompactUser getMayor() {
    return mayor;
  }
  
  public TipGroup getTips() {
    return tips;
  }
  
  public CompleteTodos getTodos() {
    return todos;
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
  
  public PhotoGroup getPhotos() {
    return photos;
  }

  private String description;
  private CompactUser mayor;
  private TipGroup tips;
  private CompleteTodos todos;
  private String[] tags;
  private Count beenHere;
  private String shortUrl;
  private CompleteSpecial[] specialsNearby;
  private PhotoGroup photos;
}
