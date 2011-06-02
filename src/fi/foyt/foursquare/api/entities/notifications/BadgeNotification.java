package fi.foyt.foursquare.api.entities.notifications;

import fi.foyt.foursquare.api.FoursquareEntity;
import fi.foyt.foursquare.api.entities.Badge;

public class BadgeNotification implements FoursquareEntity {

  private static final long serialVersionUID = 2152749838200069020L;

  public Badge getBadge() {
    return badge;
  }
  
  public Badge badge;
}
