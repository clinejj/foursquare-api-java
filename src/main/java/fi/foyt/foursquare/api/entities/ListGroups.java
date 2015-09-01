/*
 * FoursquareAPI - Foursquare API for Java
 * Copyright (C) 2014 - Blake Dy / Wallaby
 * http://walla.by
 * 
 * License: 
 * 
 * Licensed under GNU Lesser General Public License Version 3 or later (the "LGPL")
 * http://www.gnu.org/licenses/lgpl.html
 */

package fi.foyt.foursquare.api.entities;

import java.util.Arrays;

/**
 * Group of List entities
 * 
 * @author Blake Dy
 */
public class ListGroups extends Count {

  private static final long serialVersionUID = -234201262230424517L;

  /**
   * Returns list groups
   * 
   * @return list groups
   */
  public ListGroup[] getGroups() {
    return groups;
  }
  
  private ListGroup[] groups;

  @Override
  public String toString() {
    return "ListGroups{" +
            "count=" + count +
            ", groups=" + Arrays.toString(groups) +
            '}';
  }
}
