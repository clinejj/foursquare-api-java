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
 * Class representing Contact entity
 * 
 * @author Antti Leppä / Blake Dy
 */
public class Contact implements FoursquareEntity {
  
  private static final long serialVersionUID = -7810041187718129997L;
  
  /**
   * Returns phone number
   * 
   * @return phone number
   */
  public String getPhone() {
    return phone;
  }
  
  /**
   * Returns twitter id
   * 
   * @return twitter id
   */
  public String getTwitter() {
    return twitter;
  }
  
  /**
   * Returns user's email
   * 
   * @return user's email
   */
  public String getEmail() {
    return email;
  }
  
  /**
   * Returns user's facebook id
   * 
   * @return user's facebook id
   */
  public String getFacebook() {
    return facebook;
  }
  
  /**
   * Returns formatted phone
   * 
   * @return formatted phone
   */
  public String getFormattedPhone() {
    return formattedPhone;
  }
  
  private String email;
  private String facebook;
  private String twitter;
  private String phone;
  private String formattedPhone;
}
