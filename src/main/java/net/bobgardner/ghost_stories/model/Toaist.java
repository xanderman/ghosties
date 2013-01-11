package net.bobgardner.ghost_stories.model;

import com.google.common.collect.Multiset;
import java.util.List;

/**
 * A player or neutral board.
 */
public interface Toaist {
  
  /**
   * The Toaist's color.
   */
  Color color();

  /**
   * The number of Qi tokens the Taoist possesses.
   */
  int qi();

  /**
   * Is the Taoist dead?
   */
  boolean isDead();

  /**
   * The tao tokens the Taoist possesses.
   */
  Multiset<Color> taoTokens();

  /**
   * The number of neutral power tokens the Toaist possesses.
   */
  int neutralPowerTokens();

  /**
   * The number of YinYang tokens the Toaist possesses.
   */
  int yinYang();

  /**
   * The number of Buddha statues the Toaist possesses.
   */
  int buddhaStatues();

  /**
   * The possible powers the Taoist could have.
   */
  List<Power> powers();

  /**
   * The power that is active for this game.
   */
  Power activePower();

  /**
   * Set which power is active
   */
  void setActivePower(Power power);

  /**
   * Is the Taoist's power blocked?
   */
  boolean powerBlocked();

  /**
   * The ghosts on this Taoist's board.
   */
  List<Ghost> ghosts();
}
