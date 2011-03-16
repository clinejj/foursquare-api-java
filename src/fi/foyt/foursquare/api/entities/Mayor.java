package fi.foyt.foursquare.api.entities;

public class Mayor extends Count {

  public CompactUser getUser() {
    return user;
  }
  
  private CompactUser user;
}
