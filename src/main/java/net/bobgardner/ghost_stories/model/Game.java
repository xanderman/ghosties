package net.bobgardner.ghost_stories.model;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.EnumMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multiset;

import net.bobgardner.ghost_stories.setup.Powers;

/**
 * Overall game state.
 */
public class Game {
  private final Predicate<GhostPosition> openPosition = new Predicate<GhostPosition>() {
    @Override
    public boolean apply(@Nullable GhostPosition input) {
      return getBoard().getGhost(input) == Ghost.NullGhost;
    }
  };

  private Board board;
  private Supply supply;
  private Queue<Ghost> ghostDeck;
  private Taoist activePlayer;
  private List<Power> curseDie;

  public Game(Board board, Supply supply, Queue<Ghost> ghostDeck) {
    this.board = board;
    this.supply = supply;
    this.ghostDeck = ghostDeck;
    this.activePlayer = board.getPlayer(Direction.SOUTH);
    curseDie = ImmutableList.<Power>of(Powers.NOOP, Powers.NOOP, Powers.HAUNT_TILE,
        Powers.DRAW_GHOST, Powers.MINUS_QI, Powers.LOSE_ALL_TAO_TOKENS);
  }

  public Board getBoard() {
    return board;
  }

  public Supply getSupply() {
    return supply;
  }

  /**
   * Draws the next ghost from the deck and places it on the board.
   */
  public void drawGhost() {
    if (getBoard().countGhosts() == GhostPosition.values().length) {
      activePlayer.loseQi();
      return;
    }
    Ghost ghost = ghostDeck.remove();
    Color placementColor = ghost.getColor();
    if (placementColor == Color.BLACK) {
      placementColor = activePlayer.getColor();
    }
    Direction placementDirection = getBoard().findPlayerDirection(placementColor);
    Iterable<GhostPosition> positions = GhostPosition.forDirection(placementDirection);
    positions = Iterables.filter(positions, openPosition);
    if (Iterables.size(positions) == 0) {
      positions = Iterables.filter(Arrays.asList(GhostPosition.values()), openPosition);
    }
    // TODO player interaction to pick available spot
  }

  public Multiset<Color> rollTaoDice(int numDice) {
    Multiset<Color> result = EnumMultiset.create(Color.class);
    for (int i = 0; i < numDice; i++) {
      Random r = new Random();
      Color c = Color.values()[r.nextInt(Color.values().length)];
      result.add(c);
    }
    return result;
  }

  public Power rollCurseDie() {
    Random r = new Random();
    return curseDie.get(r.nextInt(curseDie.size()));
  }
}
