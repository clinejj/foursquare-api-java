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

public class CommentGroup extends Group<Comment> {

  private static final long serialVersionUID = 7117134064654710100L;

  @Override
  public Comment[] getItems() {
    return items;
  }
  
  private Comment[] items;
}
