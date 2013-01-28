package net.bobgardner.ghost_stories.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * The game board as laid out.
 */
public class Board {
  private Map<Direction, Taoist> players;
  private Map<Location, Villager> village;
  private Map<Color, Location> taoists;
  private Color prayerCircleToken;
  private Map<GhostPosition, Ghost> ghosts;
  private Map<GhostPosition, HaunterPosition> haunters;
  private Map<GhostPosition, Boolean> buddhas;

  Board(Map<Direction, Taoist> players, Map<Location, Villager> village,
      Map<Color, Location> taoists) {
    this.players = players;
    this.village = village;
    this.taoists = taoists;
    this.prayerCircleToken = Color.NONE;
    this.ghosts = Maps.newEnumMap(GhostPosition.class);
    this.haunters = Maps.newEnumMap(GhostPosition.class);
    this.buddhas = Maps.newEnumMap(GhostPosition.class);
  }

  public Taoist getPlayer(Direction d) {
    return players.get(d);
  }

  public Direction findPlayerDirection(Color playerColor) {
    Preconditions.checkArgument(playerColor != Color.NONE, "Player must have a color");
    Preconditions.checkArgument(playerColor != Color.BLACK, "There is no black player");
    for (Direction d : Direction.values()) {
      if (getPlayer(d).color == playerColor) {
        return d;
      }
    }
    return null;
  }

  public Villager getVillager(Location location) {
    return village.get(location);
  }

  public Location getTaoist(Color c) {
    return taoists.get(c);
  }

  public void moveTaoist(Color c, Location l) {
    checkState(taoists.containsKey(c), "You can't move a taoist that isn't playing");
    taoists.put(c, l);
  }

  public Color getPrayerCircleToken() {
    return prayerCircleToken;
  }

  /**
   * Put a new token on the prayer circle. Does not handle acquiring the new token from the supply,
   * or returning the old token.
   * 
   * @param newColor the new token color
   * @return the old token color
   */
  public Color changePrayerCircleToken(Color newColor) {
    Color oldColor = prayerCircleToken;
    prayerCircleToken = newColor;
    return oldColor;
  }

  public Ghost getGhost(GhostPosition ghostPosition) {
    return ghosts.containsKey(ghostPosition) ? ghosts.get(ghostPosition) : Ghost.NullGhost;
  }

  /**
   * Gets the position of the given ghost.
   * 
   * @param ghost the ghost to find
   * @return the ghost's GhostPosition
   */
  public GhostPosition findGhostPosition(Ghost ghost) {
    for (GhostPosition position : GhostPosition.values()) {
      if (getGhost(position) == ghost) {
        return position;
      }
    }
    return null;
  }

  public void addGhost(GhostPosition ghostPosition, Ghost ghost) {
    checkState(!ghosts.containsKey(ghostPosition), "There's already a ghost there");
    ghosts.put(ghostPosition, ghost);
  }

  public void removeGhost(GhostPosition ghostPosition) {
    ghosts.put(ghostPosition, Ghost.NullGhost);
  }

  /**
   * Returns the total number of ghosts on the board.
   * 
   * @return the number of ghosts
   */
  public int countGhosts() {
    int count = 0;
    for (GhostPosition position : GhostPosition.values()) {
      if (getGhost(position) != Ghost.NullGhost) {
        count++;
      }
    }
    return count;
  }

  public HaunterPosition getHaunterPosition(GhostPosition ghostPosition) {
    return haunters.containsKey(ghostPosition) ? haunters.get(ghostPosition) : HaunterPosition.NONE;
  }

  public void addHaunter(GhostPosition ghostPosition) {
    haunters.put(ghostPosition, HaunterPosition.CARD);
  }

  /**
   * Advances a haunter figurine.
   * 
   * @param ghostPosition the position of the haunter to advance
   * @return true if the haunter haunts a tile
   */
  public boolean advanceHaunter(GhostPosition ghostPosition) {
    HaunterPosition currentPosition = getHaunterPosition(ghostPosition);
    checkState(currentPosition != HaunterPosition.NONE,
        "You can't advance a haunter that isn't there");
    if (currentPosition == HaunterPosition.CARD) {
      haunters.put(ghostPosition, HaunterPosition.BOARD);
      return false;
    }
    haunters.put(ghostPosition, HaunterPosition.CARD);
    return true;
  }

  public void removeHaunter(GhostPosition ghostPosition) {
    haunters.put(ghostPosition, HaunterPosition.NONE);
  }

  public boolean hasBuddha(GhostPosition ghostPosition) {
    return buddhas.containsKey(ghostPosition) ? buddhas.get(ghostPosition) : false;
  }

  public void placeBuddha(GhostPosition ghostPosition) {
    buddhas.put(ghostPosition, true);
  }

  public void removeBuddha(GhostPosition ghostPosition) {
    buddhas.put(ghostPosition, false);
  }

  public static class Builder {
    private Map<Direction, Taoist> players;
    private Map<Location, Villager> village;
    private Set<Color> taoists;

    public Builder() {
      players = Maps.newEnumMap(Direction.class);
      village = Maps.newEnumMap(Location.class);
      taoists = EnumSet.noneOf(Color.class);
    }

    public void setPlayer(Direction direction, Taoist taoist) {
      players.put(direction, taoist);
    }

    public void setVillager(Location location, Villager villager) {
      village.put(location, villager);
    }

    public void addTaoist(Color color) {
      checkArgument(color != Color.NONE, "Taoists must have a color");
      checkArgument(color != Color.BLACK, "There is no black taoist");
      taoists.add(color);
    }

    public Board build() {
      // Check players
      checkArgument(players.size() == 4, "Not all player boards are placed");
      Set<Color> playerColors = EnumSet.noneOf(Color.class);
      for (Taoist t : players.values()) {
        playerColors.add(t.getColor());
      }
      checkArgument(playerColors.size() == 4, "Not all player colors are represented");
      checkArgument(playerColors.contains(Color.BLUE), "Blue player is missing");
      checkArgument(playerColors.contains(Color.GREEN), "Green player is missing");
      checkArgument(playerColors.contains(Color.RED), "Red player is missing");
      checkArgument(playerColors.contains(Color.YELLOW), "Yellow player is missing");

      // Check village
      checkArgument(village.size() == 9, "Not all village locations are set");
      Set<Villager> villagers = Sets.newHashSet(village.values());
      checkArgument(villagers.size() == 9, "Duplicate village tiles");

      // Check taoists
      checkArgument(taoists.size() > 0, "At least one taoist must actually play");

      // Put all taoist figurines in the center to start
      Map<Color, Location> taoistLocations = Maps.newEnumMap(Color.class);
      for (Color c : taoists) {
        taoistLocations.put(c, Location.CENTER);
      }

      return new Board(players, village, taoistLocations);
    }
  }
}
