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

public class Category implements FoursquareEntity {
  
  private static final long serialVersionUID = -4573082152802069375L;
  
  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }
  
  public String getPluralName() {
    return pluralName;
  }

  public String getIcon() {
    return icon;
  }

  public String[] getParents() {
    return parents;
  }

  public Boolean getPrimary() {
    return primary;
  }
  
  public Category[] getCategories() {
    return categories;
  }

  private String id;
  private String name;
  private String pluralName;
  private String icon;
  private String[] parents;
  private Boolean primary;
  private Category[] categories;
}
