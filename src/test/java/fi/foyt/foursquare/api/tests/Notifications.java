package fi.foyt.foursquare.api.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.Checkin;
import fi.foyt.foursquare.api.entities.LeaderboardItem;
import fi.foyt.foursquare.api.entities.notifications.BadgeNotification;
import fi.foyt.foursquare.api.entities.notifications.LeaderboardNotification;
import fi.foyt.foursquare.api.entities.notifications.MayorshipNotification;
import fi.foyt.foursquare.api.entities.notifications.MessageNotification;
import fi.foyt.foursquare.api.entities.notifications.Notification;
import fi.foyt.foursquare.api.entities.notifications.NotificationType;
import fi.foyt.foursquare.api.entities.notifications.ScoreNotification;
import fi.foyt.foursquare.api.entities.notifications.TipAlertNotification;
import fi.foyt.foursquare.api.entities.notifications.TipNotification;

public class Notifications {
  
  @Test
  public final void testNotificationType() throws FoursquareApiException {
    assertEquals(NotificationType.Badge, NotificationType.getByName("badge"));
    assertEquals(NotificationType.Tip, NotificationType.getByName("tip"));
    assertEquals(NotificationType.TipAlert, NotificationType.getByName("tipAlert"));
    assertEquals(NotificationType.Leaderboard, NotificationType.getByName("leaderboard"));
    assertEquals(NotificationType.Mayorship, NotificationType.getByName("mayorship"));
    assertEquals(NotificationType.Message, NotificationType.getByName("message"));
    assertEquals(NotificationType.Score, NotificationType.getByName("score"));
    assertEquals(null, NotificationType.getByName("nonsense"));
    assertEquals(null, NotificationType.getByName(""));
  }

  @SuppressWarnings("unchecked")
  @Test
  public final void testNotifications() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<Checkin> result = foursquareApi.checkinsAdd("408c5100f964a520c6f21ee3", null, null, "public", null, null, null, null);

    Checkin checkin = result.getResult();
    assertEquals("408c5100f964a520c6f21ee3", checkin.getVenue().getId());

    List<Notification<?>> notifications = result.getNotifications();

    Notification<MessageNotification> messageNotification = (Notification<MessageNotification>) notifications.get(0);
    Notification<BadgeNotification> badgeNotification = (Notification<BadgeNotification>) notifications.get(1);
    Notification<MayorshipNotification> mayorshipNotification = (Notification<MayorshipNotification>) notifications.get(2);
    Notification<LeaderboardNotification> leaderboardNotification = (Notification<LeaderboardNotification>) notifications.get(3);
    Notification<TipNotification> tipNotification = (Notification<TipNotification>) notifications.get(4);
    Notification<ScoreNotification> scoreNotification = (Notification<ScoreNotification>) notifications.get(5);

    assertEquals(messageNotification.getType(), NotificationType.Message);
    assertEquals("OK! We've got you @ Tompkins Square Park. You've been here 1 time.", messageNotification.getItem().getMessage());

    assertEquals(badgeNotification.getType(), NotificationType.Badge);
    assertEquals("4c4f08667a0803bbaa202ab7", badgeNotification.getItem().getBadge().getId());
    assertEquals("Newbie", badgeNotification.getItem().getBadge().getName());
    assertEquals("Congrats on your first check-in! In foursquare, you earn badges for your best check-ins \u2013 like going to museums, staying out late, or working out at the gym ten times in a month. Have fun exploring!", badgeNotification.getItem().getBadge().getDescription());
    assertEquals("https://playfoursquare.s3.amazonaws.com/badge/", badgeNotification.getItem().getBadge().getImage().getPrefix());
    assertEquals("/newbie.png", badgeNotification.getItem().getBadge().getImage().getName());
    assertArrayEquals(new Integer[] { 57, 114, 300, 200, 400 }, badgeNotification.getItem().getBadge().getImage().getSizes());

    assertEquals(mayorshipNotification.getType(), NotificationType.Mayorship);
    assertEquals("nochange", mayorshipNotification.getItem().getType());
    assertEquals(new Long(48), mayorshipNotification.getItem().getCheckins());
    assertEquals(new Long(48), mayorshipNotification.getItem().getDaysBehind());
    assertEquals("942305", mayorshipNotification.getItem().getUser().getId());
    assertEquals("Edizen A. is the Mayor of Tompkins Square Park.", mayorshipNotification.getItem().getMessage());
    assertEquals("https://playfoursquare.s3.amazonaws.com/userpix_thumbs/JU3OOSS5L3UCO1IN.jpg", mayorshipNotification.getItem().getImage());

    assertEquals(leaderboardNotification.getType(), NotificationType.Leaderboard);
    LeaderboardItem leaderboardItem1 = leaderboardNotification.getItem().getLeaderboard()[0];
    assertEquals("7613255", leaderboardItem1.getUser().getId());
    assertEquals(new Long(35), leaderboardItem1.getScores().getRecent());
    assertEquals(new Long(35), leaderboardItem1.getScores().getMax());
    assertEquals(new Long(50), leaderboardItem1.getScores().getGoal());
    assertEquals(new Long(3), leaderboardItem1.getScores().getCheckinsCount());
    assertEquals(new Integer(1), leaderboardItem1.getRank());
    assertEquals("With that last check-in you've hit your best week ever!", leaderboardNotification.getItem().getMessage());
    assertEquals(new Integer(5), leaderboardNotification.getItem().getScores()[0].getPoints());
    assertEquals("https://playfoursquare.s3.amazonaws.com/static/img/points/foursquare.png", leaderboardNotification.getItem().getScores()[0].getIcon());
    assertEquals("Welcome to foursquare and congrats on your first check-in!", leaderboardNotification.getItem().getScores()[0].getMessage());
    assertEquals(new Long(11), leaderboardNotification.getItem().getTotal());

    assertEquals(tipNotification.getType(), NotificationType.Tip);
    assertEquals("4a67c84970c603bb3d408eb4", tipNotification.getItem().getTip().getId());
    assertEquals(new Long(1248315465), tipNotification.getItem().getTip().getCreatedAt());
    assertEquals("watch out for the rats and hippie bums at night!", tipNotification.getItem().getTip().getText());
    assertEquals(new Long(0), tipNotification.getItem().getTip().getTodo().getCount());
    assertEquals(new Long(6), tipNotification.getItem().getTip().getDone().getCount());
    assertEquals("21239", tipNotification.getItem().getTip().getUser().getId());
    assertEquals("Popular tip", tipNotification.getItem().getName());

    assertEquals(scoreNotification.getType(), NotificationType.Score);
    assertEquals(new Integer(5), scoreNotification.getItem().getScores()[0].getPoints());
    assertEquals("/img/points/foursquare.png", scoreNotification.getItem().getScores()[0].getIcon());
    assertEquals("Welcome to foursquare and congrats on your first check-in!", scoreNotification.getItem().getScores()[0].getMessage());
    assertEquals(new Long(11), scoreNotification.getItem().getTotal());
  }

  @Test
  public final void testTipAlert() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<Checkin> result = foursquareApi.checkinsAdd("4c6bbfafa48420a1b09a0a0b", null, null, "private", "61.68777583849969,27.273173332214355", null, null, null);
    Checkin checkin = result.getResult();

    assertEquals("4de3212d2271bfb844acdf5d", checkin.getId());

    @SuppressWarnings("unchecked")
    Notification<TipAlertNotification> tipAlertNotification = (Notification<TipAlertNotification>) result.getNotifications().get(0);

    assertEquals(tipAlertNotification.getType(), NotificationType.TipAlert);
    assertEquals("4bb8f41970c603bb64bf96b4", tipAlertNotification.getItem().getTip().getId());
    assertEquals(new Long(1270412313), tipAlertNotification.getItem().getTip().getCreatedAt());
    assertEquals("Mitä KB:asä ei olisi hyvää, progea, hyvää kaljaa ja loistavaa seuraa", tipAlertNotification.getItem().getTip().getText());
    assertEquals(new Long(2), tipAlertNotification.getItem().getTip().getTodo().getCount());
    assertEquals(new Long(6), tipAlertNotification.getItem().getTip().getDone().getCount());
    assertEquals("4bb634f2ef159c74e79475f7", tipAlertNotification.getItem().getTip().getVenue().getId());
    assertEquals("698591", tipAlertNotification.getItem().getTip().getUser().getId());
  }
}
