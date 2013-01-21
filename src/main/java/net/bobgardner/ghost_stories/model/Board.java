package net.bobgardner.ghost_stories.model;

import java.util.List;
import java.util.Map;

/**
 * The game board as laid out.
 */
public interface Board {
  enum Direction {
    SOUTH, WEST, NORTH, EAST;
  }

  enum HaunterPosition {
    NONE, CARD, BOARD;
  }

  Map<Direction, Toaist> players();
  Toaist taoist(Direction d);

  Villager[][] village();
  Villager villager(int x, int y);

  Color prayerCircleToken();

  Map<Direction, List<HaunterPosition>> haunters();
  HaunterPosition haunterPosition(Direction d, int position);

  Map<Direction, List<Boolean>> buddhas();
  boolean hasBuddha(Direction d, int position);
}
