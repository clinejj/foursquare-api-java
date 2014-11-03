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
 * Class representing Price entity
 * 
 * @see <a href="https://developer.foursquare.com/docs/responses/venue.html" target="_blank">https://developer.foursquare.com/docs/responses/venue.html</a>
 * 
 * @author Blake Dy
 */
public class Price implements FoursquareEntity {

  private static final long serialVersionUID = -2989680497387372698L;

  /**
   * Returns the price tier from 1 (least pricey) - 4 (most pricey).
   * 
   * @return the price tier from 1 (least pricey) - 4 (most pricey).
   */
  public Integer getTier() {
    return tier;
  }
  
  /**
   * Returns a message describing the price tier.
   * 
   * @return a message describing the price tier.
   */
  public String getMessage() {
    return message;
  }
  
  private Integer tier;
  private String message;
}
