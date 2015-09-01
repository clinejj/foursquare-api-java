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

import java.util.Arrays;

/**
 * Group of Comments
 * 
 * @author Antti Leppä
 */
public class CommentGroup extends Group<Comment> {

  private static final long serialVersionUID = 7117134064654710100L;

  @Override
  public Comment[] getItems() {
    return items;
  }
  
  private Comment[] items;

  @Override
  public String toString() {
    return "CommentGroup{" +
            "items=" + Arrays.toString(items) +
            '}';
  }
}
