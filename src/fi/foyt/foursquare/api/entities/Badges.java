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
 * Class representing Badges
 * 
 * @author Antti Leppä
 */
public class Badges implements FoursquareEntity {

  private static final long serialVersionUID = 4610940737520258516L;

  /**
   * Constructor
   * 
   * @param sets badge sets
   * @param badges array of badges
   * @param defaultSetType default badge set type
   */
  public Badges(BadgeSets sets, Badge[] badges, String defaultSetType) {
    this.sets = sets;
    this.badges = badges;
    this.defaultSetType = defaultSetType;
  }

  /**
   * Returns badge sets
   * 
   * @return badge sets
   */
  public BadgeSets getSets() {
    return sets;
  }
  
  /**
   * Returns array of badges
   * 
   * @return array of badges
   */
  public Badge[] getBadges() {
    return badges;
  }
  
  /**
   * Returns default badge set type
   *  
   * @return default badge set type
   */
  public String getDefaultSetType() {
    return defaultSetType;
  }
  
  private BadgeSets sets;
  private Badge[] badges;
  private String defaultSetType;
}
