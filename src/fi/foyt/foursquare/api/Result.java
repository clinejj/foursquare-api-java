package fi.foyt.foursquare.api;

import java.util.List;

import fi.foyt.foursquare.api.entities.notifications.Notification;

public class Result<T> {

  public Result(ResultMeta meta, T result, List<Notification<?>> notifications) {
    this.result = result;
    this.meta = meta;
    this.notifications = notifications;
  }
  
  public Result(ResultMeta meta, T result) {
    this(meta, result, null);
  }

  public T getResult() {
    return result;
  }
  
  public ResultMeta getMeta() {
    return meta;
  }
  
  public List<Notification<?>> getNotifications() {
    return notifications;
  }
  
  private T result;
  private ResultMeta meta;
  private List<Notification<?>> notifications;
}
