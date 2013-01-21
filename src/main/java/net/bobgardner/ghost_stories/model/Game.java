package net.bobgardner.ghost_stories.model;

/**
 * Overall game state.
 */
public interface Game {
  Board getBoard();
  Supply getSupply();
}
