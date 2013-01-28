package net.bobgardner.ghost_stories.model;

import java.util.Arrays;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Possible locations to have a Ghost (or Buddha)
 */
public enum GhostPosition {
  SOUTH0(Direction.SOUTH, 0),
  SOUTH1(Direction.SOUTH, 1),
  SOUTH2(Direction.SOUTH, 2),
  WEST0(Direction.WEST, 0),
  WEST1(Direction.WEST, 1),
  WEST2(Direction.WEST, 2),
  NORTH0(Direction.NORTH, 0),
  NORTH1(Direction.NORTH, 1),
  NORTH2(Direction.NORTH, 2),
  EAST0(Direction.EAST, 0),
  EAST1(Direction.EAST, 1),
  EAST2(Direction.EAST, 2);

  public static Iterable<GhostPosition> forDirection(final Direction d) {
    return Iterables.filter(Arrays.asList(values()), new Predicate<GhostPosition>() {
      @Override
      public boolean apply(@Nullable GhostPosition input) {
        return input.direction == d;
      }
    });
  }

  private final Direction direction;
  private final int slot;

  GhostPosition(Direction direction, int slot) {
    this.direction = direction;
    this.slot = slot;
  }

  public Direction direction() {
    return direction;
  }

  public int slot() {
    return slot;
  }

  public Location getNextLocationToHaunt(Board board) {
    switch (this) {
      case SOUTH0:
        return firstUnHaunted(board, Location.SOUTHWEST, Location.WEST, Location.NORTHWEST);
      case SOUTH1:
        return firstUnHaunted(board, Location.SOUTH, Location.CENTER, Location.NORTH);
      case SOUTH2:
        return firstUnHaunted(board, Location.SOUTHEAST, Location.EAST, Location.NORTHEAST);
      case WEST0:
        return firstUnHaunted(board, Location.NORTHWEST, Location.NORTH, Location.NORTHEAST);
      case WEST1:
        return firstUnHaunted(board, Location.WEST, Location.CENTER, Location.EAST);
      case WEST2:
        return firstUnHaunted(board, Location.SOUTHWEST, Location.SOUTH, Location.SOUTHEAST);
      case NORTH0:
        return firstUnHaunted(board, Location.NORTHEAST, Location.EAST, Location.SOUTHEAST);
      case NORTH1:
        return firstUnHaunted(board, Location.NORTH, Location.CENTER, Location.SOUTH);
      case NORTH2:
        return firstUnHaunted(board, Location.NORTHWEST, Location.WEST, Location.SOUTHWEST);
      case EAST0:
        return firstUnHaunted(board, Location.SOUTHEAST, Location.SOUTH, Location.SOUTHWEST);
      case EAST1:
        return firstUnHaunted(board, Location.EAST, Location.CENTER, Location.WEST);
      case EAST2:
        return firstUnHaunted(board, Location.NORTHEAST, Location.NORTH, Location.NORTHWEST);
      default:
        return null;
    }
  }

  private Location firstUnHaunted(final Board board, Location... locations) {
    return Iterables.find(Arrays.asList(locations), new Predicate<Location>() {
      @Override
      public boolean apply(@Nullable Location input) {
        return !board.getVillager(input).isHaunted();
      }
    }, null);
  }
}
