package net.bobgardner.ghost_stories.model;

import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * Possible locations for villagers and taoists.
 */
public enum Location {
  NORTHWEST,
  NORTH,
  NORTHEAST,
  WEST,
  CENTER,
  EAST,
  SOUTHWEST,
  SOUTH,
  SOUTHEAST;

  public List<GhostPosition> adjacentGhostPositions() {
    switch (this) {
      case NORTHWEST:
        return ImmutableList.of(GhostPosition.WEST0, GhostPosition.NORTH2);
      case NORTH:
        return ImmutableList.of(GhostPosition.NORTH1);
      case NORTHEAST:
        return ImmutableList.of(GhostPosition.NORTH0, GhostPosition.EAST2);
      case WEST:
        return ImmutableList.of(GhostPosition.WEST1);
      case CENTER:
        return ImmutableList.of();
      case EAST:
        return ImmutableList.of(GhostPosition.EAST1);
      case SOUTHWEST:
        return ImmutableList.of(GhostPosition.SOUTH0, GhostPosition.WEST2);
      case SOUTH:
        return ImmutableList.of(GhostPosition.SOUTH1);
      case SOUTHEAST:
        return ImmutableList.of(GhostPosition.EAST0, GhostPosition.SOUTH2);
      default:
        return ImmutableList.of();
    }
  }

  public List<Location> adjacentLocations() {
    switch (this) {
      case NORTHWEST:
        return ImmutableList.of(NORTH, WEST, CENTER);
      case NORTH:
        return ImmutableList.of(NORTHWEST, NORTHEAST, WEST, CENTER, EAST);
      case NORTHEAST:
        return ImmutableList.of(NORTH, CENTER, EAST);
      case WEST:
        return ImmutableList.of(NORTHWEST, NORTH, CENTER, SOUTHWEST, SOUTH);
      case CENTER:
        return ImmutableList.of(NORTHWEST, NORTH, NORTHEAST, WEST, EAST, SOUTHWEST, SOUTH,
            SOUTHEAST);
      case EAST:
        return ImmutableList.of(NORTH, NORTHEAST, CENTER, SOUTH, SOUTHEAST);
      case SOUTHWEST:
        return ImmutableList.of(WEST, CENTER, SOUTH);
      case SOUTH:
        return ImmutableList.of(WEST, CENTER, EAST, SOUTHWEST, SOUTHEAST);
      case SOUTHEAST:
        return ImmutableList.of(CENTER, EAST, SOUTH);
      default:
        return ImmutableList.of();
    }
  }
}
