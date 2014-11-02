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
 * Class representing Checkin entity
 *
 * @see <a href="https://developer.foursquare.com/docs/responses/checkin.html" target="_blank">https://developer.foursquare.com/docs/responses/checkin.html</a>
 *
 * @author Antti Leppä
 */
public class Checkin implements FoursquareEntity {

  private static final long serialVersionUID = 4805612286360679516L;

  /**
   * Returns checkin id
   *
   * @return checkin id
   */
  public String getId() {
    return id;
  }

  /**
   * Returns checkin type (checkin, shout, or venueless)
   *
   * @return checkin type
   */
  public String getType() {
    return type;
  }

  /**
   * Returns is this a private checkin
   *
   * @return is this a private checkin
   */
  public Boolean isPrivate() {
    return isPrivate;
  }

  /**
   * Returns user who checked in
   *
   * @return user who checked in
   */
  public CompactUser getUser() {
    return user;
  }

  /**
   * Returns is the checked user mayor
   *
   * @return is the checked user mayor
   */
  public Boolean getIsMayor() {
    return isMayor;
  }

  /**
   * Returns venue if any
   *
   * @return venue if any
   */
  public CompactVenue getVenue() {
    return venue;
  }

  /**
   * Returns Location of the checkin. Field may be present if the type of this checkin is shout or venueless.
   *
   * @return Location of the checkin
   */
  public Location getLocation() {
    return location;
  }

  /**
   * Returns shout text
   *
   * @return shout text
   */
  public String getShout() {
    return shout;
  }

  /**
   * Returns when this checkin was created (epoch)
   *
   * @return when this checkin was created
   */
  public Long getCreatedAt() {
    return createdAt;
  }

  /**
   * Returns photos of the checkin
   *
   * @return photos of the checkin
   */
  public PhotoGroup getPhotos() {
    return photos;
  }

  /**
   * Returns comments for checkin
   *
   * @return comments for checkin
   */
  public CommentGroup getComments() {
    return comments;
  }

  /**
   * Returns Source object containing application name and url that created this checkin
   *
   * @return Checkin source
   */
  public Source getSource() {
    return source;
  }

  /**
   * Returns checkins from friends checked into the same venue around the same time.
   *
   * @return Overlapping checkins
   */
  public CheckinGroup getOverlaps() {
    return overlaps;
  }

  /**
   * Returns the time zone offset of the checkin
   *
   * @return the time zone offset
   */
  public int getTimeZoneOffset() {
      return timeZoneOffset;
  }

  /**
   * Returns the groups of users who have liked this checkin
   *
   * @return
   */
  public UserGroups getLikes() {
      return likes;
  }

  /**
   * Returns the score for this checkin
   *
   * @return
   */
  public ScoreGroup getScore() {
      return score;
  }

  /**
   * Returns the reason why a user cannot see comments for this checkin
   *
   * @return
   */
  public String getReasonCannotSeeComments() {
      return reasonCannotSeeComments;
  }

  /**
   * Returns the reason why a user cannot add comments to this checkin
   *
   * @return
   */
  public String getReasonCannotAddComments() {
      return reasonCannotAddComments;
  }

  /**
   * Returns whether or not the user has liked this checkin
   * @return
   */
  public Boolean getLike() {
      return like;
  }

  /**
   * Returns the list of leaderboard IDs associated with this checkin
   * @return
   */
  public String[] getObjectLeaderboards() {
      return objectLeaderboards;
  }

  /**
   * Returns posts associated with this checkin.
   * NOTE: This class is not fully implemented
   *
   * @return
   */
  public PostsGroup getPosts() {
      return posts;
  }

  /**
   * Setter method for "private" field.
   *
   * Method is needed because "private" is reserved word and thus normal field access can not be used to set field value
   *
   * @param isPrivate field value
   */
  @SuppressWarnings("unused")
  private void setPrivate(Boolean isPrivate) {
    this.isPrivate = isPrivate;
  }

  private String id;
  private String type;
  private Boolean isPrivate;
  private CompactUser user;
  private Boolean isMayor;
  private CompleteVenue venue;
  private Location location;
  private String shout;
  private Long createdAt;
  private Source source;
  private PhotoGroup photos;
  private CommentGroup comments;
  private CheckinGroup overlaps;
  private Integer timeZoneOffset;
  private UserGroups likes;
  private ScoreGroup score;
  private String reasonCannotSeeComments;
  private String reasonCannotAddComments;
  private Boolean like;
  private String[] objectLeaderboards;
  private PostsGroup posts;
}
