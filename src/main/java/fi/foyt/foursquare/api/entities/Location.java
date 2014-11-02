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
 * Class representing Location entity
 * 
 * @author Antti Leppä / Blake Dy
 */
public class Location implements FoursquareEntity {
  
  private static final long serialVersionUID = -76729758415926344L;
  
  /**
   * Returns address
   * 
   * @return address
   */
  public String getAddress() {
    return address;
  }

  /**
   * Returns cross street
   * 
   * @return cross street
   */
  public String getCrossStreet() {
    return crossStreet;
  }

  /**
   * Returns city
   * 
   * @return city
   */
  public String getCity() {
    return city;
  }

  /**
   * Returns state
   *  
   * @return state
   */
  public String getState() {
    return state;
  }

  /**
   * Returns postal Code
   * 
   * @return postal Code
   */
  public String getPostalCode() {
    return postalCode;
  }

  /**
   * Returns country
   * 
   * @return country
   */
  public String getCountry() {
    return country;
  }
  
  /**
   * Returns name
   * 
   * @return name
   */
  public String getName() {
    return name;
  }
  
  /**
   * Returns cc
   * 
   * @return cc
   */
  public String getCc() {
    return cc;
  }

  /**
   * Returns latitude
   * 
   * @return latitude
   */
  public Double getLat() {
    return lat;
  }

  /**
   * Returns longitude
   * 
   * @return longitude
   */
  public Double getLng() {
    return lng;
  }

  /**
   * Returns distance
   * 
   * @return distance
   */
  public Double getDistance() {
    return distance;
  }

  private String address;
  private String crossStreet;
  private String city;
  private String state;
  private String postalCode;
  private String country;
  private String name;
  private String cc;
  private Double lat;
  private Double lng;
  private Double distance;
}
