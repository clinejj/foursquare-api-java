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
 * Class representing Timeframe entity
 * 
 * @see <a href="https://developer.foursquare.com/docs/responses/hoursformatted.html" target="_blank">https://developer.foursquare.com/docs/responses/hoursformatted.html</a>
 * 
 * @author Blake Dy
 */
public class Timeframe implements FoursquareEntity {

  private static final long serialVersionUID = 1030343929266284079L;

  /**
   * Returns a localized list of day names.
   * 
   * @return a localized list of day names.
   */
  public String getDays() {
    return days;
  }
  
  /**
   * Returns an array of times the venue is open on days within the timeframe.
   * 
   * @return an array of times the venue is open on days within the timeframe.
   */
  public String[] getOpen() {
    return open;
  }
  
  /**
   * Returns a boolean that indicates if this timeframe includes today.
   * 
   * @return a boolean that indicates if this timeframe includes today.
   */
  public Boolean getIncludesToday() {
    return includesToday;
  }
  
  /**
   * Returns an array of describing the named segments of the days in this timeframe in which the venue is open.
   * 
   * @return an array of describing the named segments of the days in this timeframe in which the venue is open.
   */
  public String[] getSegments() {
    return segments;
  }
  
  private String days;
  private Boolean includesToday;
  // TODO
  private String[] open;
  private String[] segments;
}
