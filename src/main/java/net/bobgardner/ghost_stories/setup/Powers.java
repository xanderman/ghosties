package net.bobgardner.ghost_stories.setup;

import com.google.common.collect.Multiset;

import net.bobgardner.ghost_stories.model.Color;
import net.bobgardner.ghost_stories.model.Direction;
import net.bobgardner.ghost_stories.model.Game;
import net.bobgardner.ghost_stories.model.Ghost;
import net.bobgardner.ghost_stories.model.GhostPosition;
import net.bobgardner.ghost_stories.model.Location;
import net.bobgardner.ghost_stories.model.Power;
import net.bobgardner.ghost_stories.model.Taoist;

/**
 * Standard set of powers for a normal game.
 * 
 * @author bobgardner
 */
public enum Powers implements Power {
  NOOP {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      return;
    }
  },
  ENFEEBLEMENT_MANTRA {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub
    }
  },
  BOTTOMLESS_POCKETS {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub

    }
  },
  DANCE_OF_THE_TWIN_WINDS {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub

    }
  },
  DANCE_OF_THE_PEAKS {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub

    }
  },
  SECOND_WIND {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub

    }
  },
  HEAVENLY_GUST {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub

    }
  },
  STRENGTH_OF_THE_MOUNTAIN {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub

    }
  },
  GODS_FAVOURITE {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub

    }
  },
  CEMETERY {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub

    }
  },
  ALTAR {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub

    }
  },
  HERBALIST {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      Multiset<Color> dieRoll = gameState.rollTaoDice(2);
      for (Color c : dieRoll) {
        if (gameState.getSupply().takeTaoToken(c)) {
          player.addTaoToken(c);
        }
      }
    }
  },
  SORCERERS_HUT {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub

    }
  },
  NIGHT_WATCHMAN {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub

    }
  },
  BUDDHIST_TEMPLE {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      if (gameState.getSupply().takeBuddhaStatuette()) {
        player.addBuddhaStatutte();
      }
    }
  },
  CIRCLE_OF_PRAYER {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub
    }
  },
  HEAVENLY_WINDS {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub
    }
  },
  TEA_HOUSE {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub
    }
  },
  DRAW_GHOST {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      if (gameState.getBoard().countGhosts() == 12) {
        player.loseQi();
      } else {
        gameState.drawGhost();
      }
    }
  },
  BLOCK_POWER {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      player.blockPower();
    }
  },
  UNBLOCK_POWER {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      player.unblockPower();
    }
  },
  BLOCK_ALL_POWERS {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      for (Direction d : Direction.values()) {
        gameState.getBoard().getPlayer(d).blockPower();
      }
    }
  },
  UNBLOCK_ALL_POWERS {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      for (Direction d : Direction.values()) {
        gameState.getBoard().getPlayer(d).unblockPower();
      }
    }
  },
  RESIST_TAO_DICE {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub
    }
  },
  PLACE_HAUNTER {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      GhostPosition position = gameState.getBoard().findGhostPosition(ghost);
      gameState.getBoard().addHaunter(position);
    }
  },
  ADVANCE_HAUNTER {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      GhostPosition position = gameState.getBoard().findGhostPosition(ghost);
      if (gameState.getBoard().advanceHaunter(position)) {
        Location tileToHaunt = position.getNextLocationToHaunt(gameState.getBoard());
        gameState.getBoard().getVillager(tileToHaunt).haunt();
      }
    }
  },
  REMOVE_HAUNTER {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      GhostPosition position = gameState.getBoard().findGhostPosition(ghost);
      gameState.getBoard().removeHaunter(position);
    }
  },
  CURSE {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      gameState.rollCurseDie().apply(gameState, player, ghost);
    }
  },
  PLUS_QI_OR_YINYAN {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub
    }
  },
  PLUS_TAO_TOKEN {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub
    }
  },
  MINUS_TAO_DIE {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      gameState.getSupply().takeTaoDie();
    }
  },
  PLUS_TAO_DIE {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      gameState.getSupply().returnTaoDie();
    }
  },
  BLOCK_TAO_TOKENS {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub
    }
  },
  MINUS_QI {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      player.loseQi();
    }
  },
  LOSE_ALL_TAO_TOKENS {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      player.loseAllTaoTokens();
    }
  },
  ALL_MINUS_TAO_TOKEN {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub
    }
  },
  HAUNT_TILE {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      GhostPosition position = gameState.getBoard().findGhostPosition(ghost);
      Location tileToHaunt = position.getNextLocationToHaunt(gameState.getBoard());
      gameState.getBoard().getVillager(tileToHaunt).haunt();
    }
  },
  PLUS_QI_AND_YINYANG {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub
    }
  },
  MUST_USE_BUDDHA {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub
    }
  },
  OPPOSITE_MUST_BE_EMPTY {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub
    }
  },
  EMPTY_PRAYER_CIRCLE {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      gameState.getBoard().changePrayerCircleToken(Color.NONE);
    }
  },
  DISABLE_WHITE_TAO {
    @Override
    public void apply(Game gameState, Taoist player, Ghost ghost) {
      // TODO Auto-generated method stub
    }
  },
}
