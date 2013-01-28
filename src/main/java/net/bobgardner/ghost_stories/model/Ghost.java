package net.bobgardner.ghost_stories.model;

import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.collect.EnumMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;

/**
 * A Ghost.
 */
public class Ghost {
  public static final Ghost NullGhost = new Ghost("None", Color.NONE,
      ImmutableMultiset.<Color>of(), ImmutableList.<Power>of(), ImmutableList.<Power>of(),
      ImmutableList.<Power>of(), false);

  private String name;
  private Color color;
  private Multiset<Color> resistance;
  private List<Power> effectsOnEnter;
  private List<Power> effectsOnYin;
  private List<Power> effectsOnExit;
  private boolean enfeebled;

  Ghost(String name, Color color, Multiset<Color> resistance, List<Power> effectsOnEnter,
      List<Power> effectsOnYin, List<Power> effectsOnExit, boolean isEnfeebled) {
    this.name = name;
    this.color = color;
    this.resistance = resistance;
    this.effectsOnEnter = effectsOnEnter;
    this.effectsOnYin = effectsOnYin;
    this.effectsOnExit = effectsOnExit;
    this.enfeebled = isEnfeebled;
  }

  public String getName() {
    return name;
  }

  public Color getColor() {
    return color;
  }

  public Multiset<Color> getResistance() {
    return resistance;
  }

  public List<Power> getEffectsOnEnter() {
    return effectsOnEnter;
  }

  public List<Power> getEffectsOnYin() {
    return effectsOnYin;
  }

  public List<Power> getEffectsOnExit() {
    return effectsOnExit;
  }

  public boolean isEnfeebled() {
    return enfeebled;
  }

  public void enfeeble() {
    enfeebled = true;
  }

  public void removeEnfeeblement() {
    enfeebled = false;
  }

  public static class Builder {
    private String name;
    private Color color;
    private Multiset<Color> resistance;
    private List<Power> effectsOnEnter;
    private List<Power> effectsOnYin;
    private List<Power> effectsOnExit;

    public Builder() {
      resistance = EnumMultiset.create(Color.class);
      effectsOnEnter = Lists.newArrayList();
      effectsOnYin = Lists.newArrayList();
      effectsOnExit = Lists.newArrayList();
    }

    public void setName(String name) {
      this.name = name;
    }

    public void setColor(Color color) {
      Preconditions.checkArgument(color != Color.NONE, "Ghost must have a color");
      this.color = color;
    }

    public void addResistance(Color c, int count) {
      resistance.add(c, count);
    }

    public void addEffectOnEnter(Power p) {
      effectsOnEnter.add(p);
    }

    public void addEffectOnYin(Power p) {
      effectsOnYin.add(p);
    }

    public void addEffectOnExit(Power p) {
      effectsOnExit.add(p);
    }

    public Ghost build() {
      return new Ghost(name, color, ImmutableMultiset.copyOf(resistance),
          ImmutableList.copyOf(effectsOnEnter), ImmutableList.copyOf(effectsOnYin),
          ImmutableList.copyOf(effectsOnExit), false);
    }
  }
}
