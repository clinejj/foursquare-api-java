package fi.foyt.foursquare.api.entities;

public class KeywordGroup extends Group<Keyword> {

  private static final long serialVersionUID = 8269600369584702559L;

  @Override
  public Keyword[] getItems() {
    return items;
  }
  
  private Keyword[] items;
}
