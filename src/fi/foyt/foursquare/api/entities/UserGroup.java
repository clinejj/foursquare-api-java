package fi.foyt.foursquare.api.entities;

public class UserGroup extends Group<CompactUser> {

  @Override
  public CompactUser[] getItems() {
    return items;
  }
  
  private CompactUser[] items;
}
