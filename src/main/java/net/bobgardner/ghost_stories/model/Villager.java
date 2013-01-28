package net.bobgardner.ghost_stories.model;

/**
 * A villager board tile.
 */
public class Villager {
  private String name;
  private boolean haunted;
  private Power power;

  public Villager(String name, Power power) {
    this.name = name;
    this.haunted = false;
    this.power = power;
  }

  /**
   * Gets the villager's name.
   * 
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Is the tile haunted?
   * 
   * @return true if the villager is haunted
   */
  boolean isHaunted() {
    return haunted;
  }

  /**
   * Haunts this tile.
   */
  public void haunt() {
    haunted = true;
  }

  /**
   * Un-haunts this tile.
   */
  public void unHaunt() {
    haunted = false;
  }

  /**
   * Gets the villager's power.
   * 
   * @return the power
   */
  Power getPower() {
    return power;
  }
}
