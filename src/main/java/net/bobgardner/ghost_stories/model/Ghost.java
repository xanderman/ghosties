package net.bobgardner.ghost_stories.model;

import com.google.common.collect.Multiset;
import java.util.List;

/**
 * A Ghost.
 */
public interface Ghost {
  
  Multiset<Color> resistance();

  List<Power> effectsOnEnter();

  List<Power> effectsOnYin();

  List<Power> effectsOnExit();
}
