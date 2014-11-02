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
 * Class representing Hours entity
 * 
 * @see <a href="https://developer.foursquare.com/docs/responses/hoursformatted.html" target="_blank">https://developer.foursquare.com/docs/responses/hoursformatted.html</a>
 * 
 * @author Blake Dy
 */
public class Hours implements FoursquareEntity {

  private static final long serialVersionUID = 2767020224759128634L;

  /**
   * Returns a localized string indicating if the venue is currently open or closed.
   * 
   * @return a localized string indicating if the venue is currently open or closed.
   */
  public String getStatus() {
    return status;
  }
  
  /**
   * Returns a boolean indicating if the venue is currently open or closed.
   * 
   * @return a boolean indicating if the venue is currently open or closed.
   */
  public Boolean getIsOpen() {
    return isOpen;
  }
  
  /**
   * Returns an array of timeframes describing days of the week that have the same hours.
   * 
   * @return an array of timeframes describing days of the week that have the same hours.
   */
  public Timeframe[] getTimeframes() {
    return timeframes;
  }
  
  private String status;
  private Boolean isOpen;
  private Timeframe[] timeframes;
}
