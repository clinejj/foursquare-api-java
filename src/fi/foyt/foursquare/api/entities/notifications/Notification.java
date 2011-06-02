package fi.foyt.foursquare.api.entities.notifications;

public class Notification<T> {
  
  public Notification(NotificationType type, T item) {
    this.type = type;
    this.item = item;
  }
  
  public NotificationType getType() {
    return type;
  }
  
  public T getItem() {
    return item;
  }
  
  private NotificationType type;
  private T item;
}
