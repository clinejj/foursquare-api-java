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

public class Todo implements FoursquareEntity {

  private static final long serialVersionUID = 8713217262629234118L;

  public Long getCreatedAt() {
    return createdAt;
  }
  
  public String getId() {
    return id;
  }
  
  public String getTip() {
    return tip;
  }
  
  private String id;
  private Long createdAt;
  private String tip;
}
