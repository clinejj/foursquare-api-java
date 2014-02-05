package fi.foyt.foursquare.api.entities.notifications;

/**
 * Enumeration representing notification types
 * 
 * @author Antti Leppä / Blake Dy
 */
public enum NotificationType {

  Badge ("badge"),
  Tip ("tip"),
  TipAlert ("tipAlert"),
  Leaderboard ("leaderboard"),
  Mayorship ("mayorship"),
  Message ("message"),
  Score ("score"),
  Insights ("insights"),
  NotificationTray ("notificationTray");
  
  /**
   * Private constructor
   * 
   * @param name JSON name
   */
  private NotificationType(String name) {
    this.name = name;
  }
  
  /**
   * Returns JSON name
   * 
   * @return JSON name
   */
  public String getName() {
    return name;
  }
  
  /**
   * Returns NotificationType by JSON name
   * 
   * @param name JSON name
   * @return NotificationType
   */
  public static NotificationType getByName(String name) {
    for (NotificationType notificationType : values()) {
      if (notificationType.getName().equals(name)) {
        return notificationType;
      }
    }
    
    return null;
  }
  
  private String name;
}
