package fi.foyt.foursquare.api.io;

public class MultipartParameter {

  public MultipartParameter(String name, String contentType, byte[] content) {
    this.name = name;
    this.contentType = contentType;
    this.content = content;
  }

  public byte[] getContent() {
    return content;
  }
  
  public String getContentType() {
    return contentType;
  }
  
  public String getName() {
    return name;
  }
  
  private String name;

  private String contentType;
  private byte[] content;
}
