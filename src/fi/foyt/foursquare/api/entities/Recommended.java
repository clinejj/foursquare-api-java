package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

public class Recommended implements FoursquareEntity {

  private static final long serialVersionUID = -6061498154797516492L;
  
  public Recommended(KeywordGroup keywords, RecommendationGroup[] groups, Warning warning) {
    this.keywords = keywords;
    this.groups = groups;
    this.warning = warning;
  }

  public KeywordGroup getKeywords() {
    return keywords;
  }
  
  public RecommendationGroup[] getGroups() {
    return groups;
  }
  
  public Warning getWarning() {
    return warning;
  }
  
  private KeywordGroup keywords;
  private RecommendationGroup[] groups;
  private Warning warning;
}
