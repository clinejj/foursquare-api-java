package fi.foyt.foursquare.api.entities;

public class LeaderboardItemGroup extends Group<LeaderboardItem> {

  private static final long serialVersionUID = 4074401404859223744L;

  @Override
  public LeaderboardItem[] getItems() {
    return items;
  }
  
  private LeaderboardItem[] items;
}
