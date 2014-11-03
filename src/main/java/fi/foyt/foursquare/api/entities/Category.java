/*
 * FoursquareAPI - Foursquare API for Java
 * Copyright (C) 2008 - 2011 Antti Leppä / Foyt
 * http://www.foyt.fi
 * Copyright (C) 2014 - Blake Dy / Wallaby
 * http://walla.by
 * 
 * License: 
 * 
 * Licensed under GNU Lesser General Public License Version 3 or later (the "LGPL")
 * http://www.gnu.org/licenses/lgpl.html
 */

package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * Class representing Category entity
 * 
 * @author Antti Leppä / Blake Dy
 */
public class Category implements FoursquareEntity {
  
  private static final long serialVersionUID = -4573082152802069375L;
  
  /**
   * Returns category id
   * 
   * @return category id
   */
  public String getId() {
    return id;
  }

  /**
   * Returns category name
   * 
   * @return category name
   */
  public String getName() {
    return name;
  }
  
  /**
   * Return plural name
   * 
   * @return plural name
   */
  public String getPluralName() {
    return pluralName;
  }

  /**
   * Returns icon
   * 
   * @return icon
   */
  public String getIcon() {
    return icon;
  }

  /**
   * Returns parents as array of Strings
   * 
   * @return parents as array of Strings
   */
  public String[] getParents() {
    return parents;
  }

  /**
   * Returns if this is a primary category
   * 
   * @return is this a primary category
   */
  public Boolean getPrimary() {
    return primary;
  }
  
  /**
   * Returns sub categories
   * 
   * @return sub categories
   */
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
  
  // TODO
  private String shortName;
}
