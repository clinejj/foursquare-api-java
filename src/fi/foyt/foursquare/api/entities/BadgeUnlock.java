package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class BadgeUnlock implements FoursquareEntity {

  private static final long serialVersionUID = -1266578502619350500L;

  public Checkin[] getCheckins() {
    return checkins;
  }

  private Checkin[] checkins;
}
