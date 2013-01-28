package net.bobgardner.ghost_stories.model;

/**
 * A power is a generic description of a game-altering effect. It is applicable as a Taoist's power,
 * as a Ghost's (enter/Yin/exit) effect, and a Villager's power.
 */
public interface Power {
  /**
   * Apply this power, altering the game state in some arbitrary fashion.
   * 
   * @param gameState the state of the game when the power is invoked
   * @param player the player using the power, or the board the Ghost is on
   * @param ghost the Ghost using the power
   */
  void apply(Game gameState, Taoist player, Ghost ghost);
}
