package fi.foyt.foursquare.api.entities;

public class UserGroup extends Group<CompactUser> {

  private static final long serialVersionUID = 3181702805520905399L;

  @Override
  public CompactUser[] getItems() {
    return items;
  }
  
  private CompactUser[] items;
}
