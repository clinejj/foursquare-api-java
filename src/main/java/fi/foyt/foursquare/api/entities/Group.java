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

/**
 * Base class for all groups
 * 
 * @param <T> type of entities group contains
 * 
 * @author Antti Leppä
 */
public abstract class Group<T extends FoursquareEntity> extends Count {

  private static final long serialVersionUID = -3156890964170514232L;

  /**
   * Returns type
   * 
   * @return type
   */
  public String getType() {
    return type;
  }

  /**
   * Returns name
   * 
   * @return name
   */
  public String getName() {
    return name;
  }
  
  /**
   * Returns group items
   *  
   * @return group items
   */
  public abstract T[] getItems();

  private String type;
  private String name;
}
