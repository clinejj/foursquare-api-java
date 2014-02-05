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

/**
 * Class representing CompleteTip entity
 * 
 * @see <a href="https://developer.foursquare.com/docs/responses/tip.html" target="_blank">https://developer.foursquare.com/docs/responses/tip.html</a>
 * 
 * @author Antti Leppä / Blake Dy
 */
public class CompleteTip extends CompactTip {
  
  private static final long serialVersionUID = 5606985476553828335L;
  
  /**
   * Returns todos for this tip
   * 
   * @return todos for this tip
   */
  public UserGroups getTodo() {
    return todo;
  }
  
  /**
   * Returns dones for this tip
   * 
   * @return dones for this tip
   */
  public UserGroups getDone() {
    return done;
  }
  
  private UserGroups todo;
  private UserGroups done;
  
  // TODO
  private String canonicalUrl;
  private String likes;
  private Boolean like;
  private Boolean logView;
}
