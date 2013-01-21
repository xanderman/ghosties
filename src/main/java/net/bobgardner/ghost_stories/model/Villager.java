package net.bobgardner.ghost_stories.model;

/**
 * A villager board tile.
 */
public interface Villager {

  /**
   * Is the tile haunted?
   */
  boolean isHaunted();

  /**
   * The villager's power.
   */
  Power power();
}
