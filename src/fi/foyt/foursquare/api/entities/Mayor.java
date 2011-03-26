package fi.foyt.foursquare.api.entities;

public class Mayor extends Count {

  private static final long serialVersionUID = -6838261267509832567L;

  public CompactUser getUser() {
    return user;
  }
  
  private CompactUser user;
}
