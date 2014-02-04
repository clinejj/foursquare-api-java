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

package fi.foyt.foursquare.api.entities.venue;

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * Class representing Menu entity
 * 
 * @see <a href="https://developer.foursquare.com/docs/responses/venue.html" target="_blank">https://developer.foursquare.com/docs/responses/venue.html</a>
 * 
 * @author Blake Dy
 */
public class Menu implements FoursquareEntity {

  private static final long serialVersionUID = 1824476602094308156L;

  /**
   * Returns type.
   * 
   * @return type.
   */
  public String getType() {
    return type;
  }
  
  /**
   * Returns label.
   * 
   * @return label.
   */
  public String getLabel() {
    return label;
  }
  
  /**
   * Returns anchor.
   * 
   * @return anchor.
   */
  public String getAnchor() {
    return anchor;
  }
  
  /**
   * Returns menu's url.
   * 
   * @return menu's url.
   */
  public String getUrl() {
    return url;
  }
  
  /**
   * Returns menu's mobile url.
   * 
   * @return menu's mobile url.
   */
  public String getMobileUrl() {
    return mobileUrl;
  }
  
  private String type;
  private String label;
  private String anchor;
  private String url;
  private String mobileUrl;
}
