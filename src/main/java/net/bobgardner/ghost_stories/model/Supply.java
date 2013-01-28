package net.bobgardner.ghost_stories.model;

import com.google.common.base.Preconditions;
import com.google.common.collect.EnumMultiset;
import com.google.common.collect.Multiset;

/**
 * The available tao tokens, buddhas, etc, not held by a player or ghost.
 */
public class Supply {
  private Multiset<Color> taoTokens;
  private Multiset<Color> yinYangTokens;
  private int neutralPowerTokens;
  private int buddhas;
  private int enfeeblementTokens;
  private int taoDice;

  Supply(Multiset<Color> taoTokens, Multiset<Color> yinYangTokens, int neutralPowerTokens,
      int buddhas, int enfeeblementTokens, int taoDice) {
    this.taoTokens = taoTokens;
    this.yinYangTokens = yinYangTokens;
    this.neutralPowerTokens = neutralPowerTokens;
    this.buddhas = buddhas;
    this.enfeeblementTokens = enfeeblementTokens;
    this.taoDice = taoDice;
  }

  /**
   * Gets the number of available tao tokens of the given color.
   * 
   * @param c the token color
   * @return the number of available tokens
   */
  public int getTaoTokens(Color c) {
    return taoTokens.count(c);
  }

  /**
   * Removes a tao token from the supply. If there are no tokens of the given color in the supply,
   * the supply is not modified and <code>false</code> is returned.
   * 
   * @param c the token to take
   * @return true if a token was removed
   */
  public boolean takeTaoToken(Color c) {
    return taoTokens.remove(c);
  }

  /**
   * Puts a tao token back into the supply
   * 
   * @param c the token to return
   */
  public void returnTaoToken(Color c) {
    taoTokens.add(c);
  }

  /**
   * Gets the number of available yinyang tokens of the given color.
   * 
   * @param c the yinyang color
   * @return the number of available tokens
   */
  public int getYinYangTokens(Color c) {
    return yinYangTokens.count(c);
  }

  /**
   * Removes a yinyang token from the supply. If the given yinyang token is not in the supply, the
   * supply is not modified and <code>false</code> is returned.
   * 
   * @param c the yinyang to take
   * @return true if a token was removed
   */
  public boolean takeYinYangToken(Color c) {
    return yinYangTokens.remove(c);
  }

  /**
   * Puts a yinyang token back into the supply.
   * 
   * @param c the token to return
   */
  public void returnYinYangToken(Color c) {
    yinYangTokens.add(c);
  }

  /**
   * Gets the number of available neutral power tokens.
   * 
   * @return the number of available tokens
   */
  public int getNeutralPowerTokens() {
    return neutralPowerTokens;
  }

  /**
   * Removes a neutral power token from the supply. If there are no neutral power tokens in the
   * supply, the supply is not modified and <code>false</code> is returned.
   * 
   * @return true if a token was removed
   */
  public boolean takeNeutralPowerToken() {
    if (neutralPowerTokens > 0) {
      neutralPowerTokens--;
      return true;
    }
    return false;
  }

  /**
   * Puts a neutral power token back into the supply.
   */
  public void returnNeutralPowerToken() {
    neutralPowerTokens++;
  }

  /**
   * Gets the number of available buddha statuettes.
   * 
   * @return the number of available statuettes
   */
  public int getBuddhaStatuettes() {
    return buddhas;
  }

  /**
   * Removes a buddha statuette from the supply. If there are no buddha statuettes in the supply,
   * the supply is not modified and <code>false</code> is returned.
   * 
   * @return true if a statuette was removed
   */
  public boolean takeBuddhaStatuette() {
    if (buddhas > 0) {
      buddhas--;
      return true;
    }
    return false;
  }

  /**
   * Puts a buddha statuette back into the supply.
   */
  public void returnBuddhaStatuette() {
    buddhas++;
  }

  /**
   * Gets the number of available enfeeblement tokens.
   * 
   * @return the number of available tokens
   */
  public int getEnfeeblementTokens() {
    return enfeeblementTokens;
  }

  /**
   * Removes an enfeeblement token from the supply. If there are no enfeeblement tokens in the
   * supply, the supply is not modified and <code>false</code> is returned.
   * 
   * @return true if a token was removed
   */
  public boolean takeEnfeeblementToken() {
    if (enfeeblementTokens > 0) {
      enfeeblementTokens--;
      return true;
    }
    return false;
  }

  /**
   * Puts an enfeeblement token back into the supply.
   */
  public void returnEnfeeblementToken() {
    enfeeblementTokens++;
  }

  /**
   * Gets the number of available tao dice.
   * 
   * @return the number of available tokens
   */
  public int getTaoDice() {
    return taoDice;
  }

  /**
   * Removes a tao die from the supply. If there are no tao dice in the supply, the supply is not
   * modified and <code>false</code> is returned.
   * 
   * @return true if the die was removed
   */
  public boolean takeTaoDie() {
    if (taoDice > 0) {
      taoDice--;
      return true;
    }
    return false;
  }

  /**
   * Puts a tao die back into the supply.
   */
  public void returnTaoDie() {
    taoDice++;
  }

  public static class Builder {
    private Multiset<Color> taoTokens;
    private Multiset<Color> yinYangTokens;
    private int neutralPowerTokens;
    private int buddhas;
    private int enfeeblementTokens;
    private int taoDice;

    public Builder() {
      taoTokens = EnumMultiset.create(Color.class);
      yinYangTokens = EnumMultiset.create(Color.class);
    }

    public void addTaoTokens(Color c, int quantity) {
      Preconditions.checkArgument(c != Color.NONE, "Tao tokens must have a color");
      taoTokens.add(c, quantity);
    }

    public void addYinYangToken(Color c) {
      Preconditions.checkArgument(c != Color.NONE, "YinYang tokens must have a color");
      Preconditions.checkArgument(c != Color.BLACK, "There is no black YinYang token");
      yinYangTokens.add(c);
    }

    public void setNeutralPowerTokens(int quantity) {
      neutralPowerTokens = quantity;
    }

    public void setBuddhas(int quantity) {
      buddhas = quantity;
    }

    public void setEnfeeblementTokens(int quantity) {
      enfeeblementTokens = quantity;
    }

    public void setTaoDice(int quantity) {
      taoDice = quantity;
    }

    public Supply build() {
      return new Supply(taoTokens, yinYangTokens, neutralPowerTokens, buddhas, enfeeblementTokens,
          taoDice);
    }
  }
}
