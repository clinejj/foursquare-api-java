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

package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * Class representing List entity
 * 
 * @see <a href="https://developer.foursquare.com/docs/responses/list.html" target="_blank">https://developer.foursquare.com/docs/responses/list.html</a>
 * 
 * @author Blake Dy
 */
public class List implements FoursquareEntity {

  private static final long serialVersionUID = -7255898184182462517L;
  
  // TODO
  private String id;
  private String name;
  private String description;
  private String type;
  private String user;
  private String following;
  private String followers;
  private String editable;
  private String collaborative;
  private String collaborators;
  private String url;
  private String canonicalUrl;
  private String photo;
  private String venueCount;
  private String visitedCount;
  private String listItems;
  private String createdAt;
  private String updatedAt;
  private String entities;
  private Boolean isPublic; public void setPublic(Boolean isPublic) { this.isPublic = isPublic; }
  private Boolean logView;
}