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
 * Class representing Photo entity
 * 
 * @see <a href="https://developer.foursquare.com/docs/responses/photo.html" target="_blank">https://developer.foursquare.com/docs/responses/photo.html</a>
 * 
 * @author Antti Leppä / Blake Dy
 */
public class Photo implements FoursquareEntity {

  private static final long serialVersionUID = -6641038454071667700L;

  /**
   * Returns photo's id
   * 
   * @return photo's id
   */
  public String getId() {
    return id;
  }
  
  /**
   * Returns seconds since epoch when this photo was created.
   * 
   * @return seconds since epoch when this photo was created.
   */
  public Long getCreatedAt() {
    return createdAt;
  }
  
  /**
   * Returns the url for the original uploaded file.
   * 
   * @return the url for the original uploaded file.
   */
  public String getUrl() {
    return url;
  }
  
  /**
   * Returns image sizes
   * 
   * @return image sizes
   */
  public SizeGroup getSizes() {
    return sizes;
  }
  
  /**
   * Returns source of the image (Application)
   * 
   * @return source of the image 
   */
  public Source getSource() {
    return source;
  }
  
  /**
   * Returns user that uploaded the image
   * 
   * @return user that uploaded the image
   */
  public CompactUser getUser() {
    return user;
  }
  
  /**
   * Returns venue where photo was taken
   * 
   * @return venue where photo was taken
   */
  public CompactVenue getVenue() {
    return venue;
  }
  
  /**
   * Returns tip in which photo is related to
   * 
   * @return tip in which photo is related to
   */
  public CompleteTip getTip() {
    return tip;
  }
  
  /**
   * Returns checkin in which photo is related to
   * 
   * @return checkin in which photo is related to
   */
  public Checkin getCheckin() {
    return checkin;
  }
  
  private String id;
  private Long createdAt;
  private String url;
  private SizeGroup sizes;
  private Source source;
  private CompactUser user;
  private CompactVenue venue;
  private CompleteTip tip;
  private Checkin checkin;
  
  // TODO
  private Integer height;
  private Integer width;
  private String visibility;
  private String prefix;
  private String suffix;

  @Override
  public String toString() {
    return "Photo{" +
            "id='" + id + '\'' +
            ", createdAt=" + createdAt +
            ", url='" + url + '\'' +
            ", sizes=" + sizes +
            ", source=" + source +
            ", user=" + user +
            ", venue=" + venue +
            ", tip=" + tip +
            ", checkin=" + checkin +
            ", height=" + height +
            ", width=" + width +
            ", visibility='" + visibility + '\'' +
            ", prefix='" + prefix + '\'' +
            ", suffix='" + suffix + '\'' +
            '}';
  }
}
