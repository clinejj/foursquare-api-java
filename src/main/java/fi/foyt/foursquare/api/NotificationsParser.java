package fi.foyt.foursquare.api;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fi.foyt.foursquare.api.entities.notifications.BadgeNotification;
import fi.foyt.foursquare.api.entities.notifications.LeaderboardNotification;
import fi.foyt.foursquare.api.entities.notifications.MayorshipNotification;
import fi.foyt.foursquare.api.entities.notifications.MessageNotification;
import fi.foyt.foursquare.api.entities.notifications.Notification;
import fi.foyt.foursquare.api.entities.notifications.NotificationType;
import fi.foyt.foursquare.api.entities.notifications.ScoreNotification;
import fi.foyt.foursquare.api.entities.notifications.TipAlertNotification;
import fi.foyt.foursquare.api.entities.notifications.TipNotification;

/**
 * Class responsible of parsing notifications
 * 
 * @author Antti Leppä / Blake Dy
 */
public class NotificationsParser {
  
  /**
   * Utility class so no constructor needed.
   */
  private NotificationsParser() {
    
  }
  
  /**
   * Static method that parses JSON array into list of notifications
   * 
   * @param notifications JSON Array
   * @param skipNonExistingFields whether parser should ignore non-existing fields
   * @return list of notifications 
   * @throws FoursquareApiException when something unexpected happens
   */
  public static List<Notification<?>> parseNotifications(JSONArray notifications, boolean skipNonExistingFields) throws FoursquareApiException {
    List<Notification<?>> result = new ArrayList<Notification<?>>();
    
    try {
      JSONObject notification;

      for (int i = 0, l = notifications.length(); i < l; i++) {
        notification = notifications.getJSONObject(i);
        String type = notification.getString("type");
        JSONObject item = notification.getJSONObject("item");

        NotificationType notificationType = NotificationType.getByName(type);
        if (notificationType != null) {
          switch (notificationType) {
            case Badge:
              BadgeNotification badgeNotification = (BadgeNotification) JSONFieldParser.parseEntity(BadgeNotification.class, item, skipNonExistingFields);
              result.add(new Notification<BadgeNotification>(notificationType, badgeNotification));
            break;
            case Leaderboard:
              LeaderboardNotification leaderboard = (LeaderboardNotification) JSONFieldParser.parseEntity(LeaderboardNotification.class, item, skipNonExistingFields);
              result.add(new Notification<LeaderboardNotification>(notificationType, leaderboard));            
            break;
            case Mayorship:
              MayorshipNotification mayorship = (MayorshipNotification) JSONFieldParser.parseEntity(MayorshipNotification.class, item, skipNonExistingFields);
              result.add(new Notification<MayorshipNotification>(notificationType, mayorship));
            break;
            case Message:
              MessageNotification messageNotification = (MessageNotification) JSONFieldParser.parseEntity(MessageNotification.class, item, skipNonExistingFields);
              result.add(new Notification<MessageNotification>(notificationType, messageNotification));
            break;
            case Tip:
              TipNotification tipNotification = (TipNotification) JSONFieldParser.parseEntity(TipNotification.class, item, skipNonExistingFields);
              result.add(new Notification<TipNotification>(notificationType, tipNotification));
            break;
            case TipAlert:
              TipAlertNotification tipAlertNotification = (TipAlertNotification) JSONFieldParser.parseEntity(TipAlertNotification.class, item, skipNonExistingFields);
              result.add(new Notification<TipAlertNotification>(notificationType, tipAlertNotification));
            break;
            case Score:
              ScoreNotification scoresNotification = (ScoreNotification) JSONFieldParser.parseEntity(ScoreNotification.class, item, skipNonExistingFields);
              result.add(new Notification<ScoreNotification>(notificationType, scoresNotification));
            break;
            case NotificationTray:
              // TODO
            break;
            case Insights:
              // TODO
            break;
            default:
              throw new FoursquareApiException("Unknown notification type: " + type);
          }
        } else {
          if (!skipNonExistingFields) {
            throw new FoursquareApiException("Unknown notification type: " + type);
          }
        }
      }

    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
    
    return result;
  }
}