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

import fi.foyt.foursquare.api.FoursquareEntity;

public class Location implements FoursquareEntity {
  
  private static final long serialVersionUID = -76729758415926344L;
  
  public String getAddress() {
    return address;
  }

  public String getCrossStreet() {
    return crossStreet;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public String getCountry() {
    return country;
  }

  public Double getLat() {
    return lat;
  }

  public Double getLng() {
    return lng;
  }

  public Double getDistance() {
    return distance;
  }

  private String address;
  private String crossStreet;
  private String city;
  private String state;
  private String postalCode;
  private String country;
  private Double lat;
  private Double lng;
  private Double distance;
}
