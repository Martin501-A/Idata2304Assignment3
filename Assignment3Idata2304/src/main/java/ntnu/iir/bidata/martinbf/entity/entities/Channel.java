package ntnu.iir.bidata.martinbf.entity.entities;

public enum Channel {

  NRK1("NRK1"),
  NRK2("NRK2"),
  TV2("TV2"),
  TV3("TV3"),
  TVNorge("TVNorge"),
  NONE("NONE");


  private final String name;


  Channel(String name) {
    this.name = name;
  }

  /**
   * Returns the command as a string.
   *
   * @return the command as a string.
   */
  @Override
  public String toString() {
    return name;
  }
}
