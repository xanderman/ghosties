package net.bobgardner.ghost_stories.model;

import com.google.common.collect.EnumMultiset;
import com.google.common.collect.Multiset;

/**
 * A player or neutral board.
 */
public class Taoist {
  Color color;
  int qi;
  Multiset<Color> taoTokens;
  int neutralPowerTokens;
  int yinYangTokens;
  int buddhaStatuettes;
  Power power;
  boolean powerBlocked;

  public Taoist(Color color, Power power) {
    this.color = color;
    qi = 0;
    taoTokens = EnumMultiset.create(Color.class);
    neutralPowerTokens = 0;
    yinYangTokens = 0;
    buddhaStatuettes = 0;
    this.power = power;
    powerBlocked = false;
  }

  /**
   * Gets the Taoist's color.
   */
  public Color getColor() {
    return color;
  }

  /**
   * Gets the number of Qi tokens the Taoist possesses.
   */
  public int getQi() {
    return qi;
  }

  /**
   * Gives the Taoist a Qi token.
   */
  public void addQi() {
    qi++;
  }

  /**
   * Removes a Qi token from the Taoist.
   * 
   * @return true if the Taoist dies from the lost of Qi
   */
  public boolean loseQi() {
    if (qi > 0) {
      qi--;
    }
    return isDead();
  }

  /**
   * Is the Taoist dead?
   * 
   * @return true if the Taoist is dead (or the neutral board has lost all Qi)
   */
  public boolean isDead() {
    return qi == 0;
  }

  /**
   * Gets the tao tokens of the given color the Taoist possesses.
   * 
   * @return the number of tokens
   */
  public int getTaoTokens(Color c) {
    return taoTokens.count(c);
  }

  /**
   * Gives the Taoist a tao token.
   * 
   * @param c the token to add
   */
  public void addTaoToken(Color c) {
    taoTokens.add(c);
  }

  /**
   * Removes a tao token from the Taoist.
   * 
   * @param c the token to use
   * @return true if the token was taken
   */
  public boolean useTaoToken(Color c) {
    return taoTokens.remove(c);
  }

  /**
   * Removes all tao tokens from the Taoist.
   */
  public void loseAllTaoTokens() {
    taoTokens.clear();
  }

  /**
   * Gets the number of neutral power tokens the Taoist possesses.
   */
  public int getNeutralPowerTokens() {
    return neutralPowerTokens;
  }

  /**
   * Gives the Taoist a neutral power token.
   */
  public void addNeutralPowerToken() {
    neutralPowerTokens++;
  }

  /**
   * Removes a neutral power token from the Taoist.
   * 
   * @return true if the token was taken
   */
  public boolean useNeutralPowerToken() {
    if (neutralPowerTokens > 0) {
      neutralPowerTokens--;
      return true;
    }
    return false;
  }

  /**
   * Gets the number of YinYang tokens the Taoist possesses.
   */
  public int getYinYangTokens() {
    return yinYangTokens;
  }

  /**
   * Gives the Taoist a YinYang token.
   */
  public void addYinYangToken() {
    yinYangTokens++;
  }

  /**
   * Removes a YinYang token from the Taoist.
   * 
   * @return true if the token was taken
   */
  public boolean useYinYangToken() {
    if (yinYangTokens > 0) {
      yinYangTokens--;
      return true;
    }
    return false;
  }

  /**
   * Gets the number of Buddha statuettes the Taoist possesses.
   */
  public int getBuddhaStatuettes() {
    return buddhaStatuettes;
  }

  /**
   * Gives the Taoist a Buddha statuette
   */
  public void addBuddhaStatutte() {
    buddhaStatuettes++;
  }

  /**
   * Removes a Buddha statuette from the Taoist.
   * 
   * @return true if the statuette was taken
   */
  public boolean useBuddhaStatutte() {
    if (buddhaStatuettes > 0) {
      buddhaStatuettes--;
      return true;
    }
    return false;
  }

  /**
   * Gets the taoist's power.
   */
  public Power getPower() {
    return power;
  }

  /**
   * Is the Taoist's power blocked?
   * 
   * @return true if the Taoist's power is blocked
   */
  public boolean isPowerBlocked() {
    return powerBlocked;
  }

  /**
   * Blocks the Taoist's power.
   */
  public void blockPower() {
    powerBlocked = true;
  }

  /**
   * Unblocks the Taoist's power.
   */
  public void unblockPower() {
    powerBlocked = false;
  }
}
