/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.sets.alarareborn;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.effects.OneShotEffect;
import mage.abilities.keyword.CascadeAbility;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.Rarity;
import mage.constants.WatcherScope;
import mage.constants.Zone;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.stack.Spell;
import mage.target.targetpointer.FixedTarget;
import mage.watchers.Watcher;

/**
 *
 * @author jeffwadsworth
 */
public class MaelstromNexus extends CardImpl {

    public MaelstromNexus(UUID ownerId) {
        super(ownerId, 130, "Maelstrom Nexus", Rarity.MYTHIC, new CardType[]{CardType.ENCHANTMENT}, "{W}{U}{B}{R}{G}");
        this.expansionSetCode = "ARB";

        // The first spell you cast each turn has cascade.
        this.addAbility(new MaelstromNexusTriggeredAbility(), new FirstSpellCastThisTurnWatcher());

    }

    public MaelstromNexus(final MaelstromNexus card) {
        super(card);
    }

    @Override
    public MaelstromNexus copy() {
        return new MaelstromNexus(this);
    }
}

class MaelstromNexusTriggeredAbility extends TriggeredAbilityImpl {

    public MaelstromNexusTriggeredAbility() {
        super(Zone.BATTLEFIELD, new CascadeEffect());
    }

    public MaelstromNexusTriggeredAbility(MaelstromNexusTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        if (event.getType() == GameEvent.EventType.SPELL_CAST) {
            Spell spell = game.getStack().getSpell(event.getTargetId());
            FirstSpellCastThisTurnWatcher watcher = (FirstSpellCastThisTurnWatcher) game.getState().getWatchers().get("FirstSpellCastThisTurn", this.getSourceId());
            if (spell != null
                    && watcher != null
                    && watcher.conditionMet()) {
                this.getEffects().get(0).setTargetPointer(new FixedTarget(spell.getSourceId()));
                return true;
            }
        }
        return false;
    }

    @Override
    public MaelstromNexusTriggeredAbility copy() {
        return new MaelstromNexusTriggeredAbility(this);
    }
    
    @Override
    public String getRule() {
        return "The first spell you cast each turn has cascade.";
    }
}

class FirstSpellCastThisTurnWatcher extends Watcher {

    int spellCount = 0;

    public FirstSpellCastThisTurnWatcher() {
        super("FirstSpellCastThisTurn", WatcherScope.CARD);
    }

    public FirstSpellCastThisTurnWatcher(final FirstSpellCastThisTurnWatcher watcher) {
        super(watcher);
    }

    @Override
    public void watch(GameEvent event, Game game) {
        if (event.getType() == GameEvent.EventType.SPELL_CAST && event.getPlayerId() == controllerId) {
            Spell spell = (Spell) game.getObject(event.getTargetId());
            if (spell != null) {
                spellCount++;
                if (spellCount == 1) {
                    condition = true;
                } else {
                    condition = false;
                }
            }
        }
    }

    @Override
    public FirstSpellCastThisTurnWatcher copy() {
        return new FirstSpellCastThisTurnWatcher(this);
    }

    @Override
    public void reset() {
        super.reset();
        spellCount = 0;
    }
}

class CascadeEffect extends OneShotEffect {

    public CascadeEffect() {
        super(Outcome.PutCardInPlay);
    }

    public CascadeEffect(CascadeEffect effect) {
        super(effect);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        return CascadeAbility.applyCascade(outcome, game, source);
    }

    @Override
    public CascadeEffect copy() {
        return new CascadeEffect(this);
    }

}
