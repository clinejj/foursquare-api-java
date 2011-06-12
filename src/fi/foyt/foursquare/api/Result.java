package fi.foyt.foursquare.api;

import java.util.List;

import fi.foyt.foursquare.api.entities.notifications.Notification;

/**
 * Class representing API query result
 * 
 * @author Antti Leppä
 *
 * @param <T> type of resulting entity
 */
public class Result<T> {

  /**
   * Constructor
   * 
   * @param meta status information
   * @param result result entity
   * @param notifications list of notifications
   */
  public Result(ResultMeta meta, T result, List<Notification<?>> notifications) {
    this.result = result;
    this.meta = meta;
    this.notifications = notifications;
  }
  
  /**
   * Constructor
   * 
   * @param meta status information
   * @param result result entity
   */
  public Result(ResultMeta meta, T result) {
    this(meta, result, null);
  }

  /**
   * Returns result entity
   * 
   * @return result entity
   */
  public T getResult() {
    return result;
  }
  
  /**
   * Returns request status information
   * 
   * @return request status information
   */
  public ResultMeta getMeta() {
    return meta;
  }
  
  /**
   * Returns list of notifications
   * 
   * @return list of notifications
   */
  public List<Notification<?>> getNotifications() {
    return notifications;
  }
  
  private T result;
  private ResultMeta meta;
  private List<Notification<?>> notifications;
}
