package fi.foyt.foursquare.api.entities.notifications;

public enum NotificationType {

  Badge ("badge"),
  Tip ("tip"),
  TipAlert ("tipAlert"),
  Leaderboard ("leaderboard"),
  Mayorship ("mayorship"),
  Message ("message"),
  Score ("score");
  
  private NotificationType(String name) {
    this.name = name;
  }
  
  public String getName() {
    return name;
  }
  
  public static NotificationType getByName(String name) {
    for (NotificationType notificationType : values()) {
      if (notificationType.getName().equals(name))
        return notificationType;
    }
    
    return null;
  }
  
  private String name;
}
