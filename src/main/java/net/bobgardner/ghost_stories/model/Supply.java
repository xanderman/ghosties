package net.bobgardner.ghost_stories.model;

import com.google.common.collect.Multiset;
import java.util.Set;

/**
 * The available tao tokens, buddhas, etc.
 */
public interface Supply {
  Multiset<Color> taoTokens();
  Set<Color> yinYangTokens();
  int neutralPowerTokens();
  int buddhas();
  int enfeblementToken();
  int taoDice();
}
