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
package mage.sets.dragonsmaze;

import java.util.UUID;

import mage.constants.CardType;
import mage.constants.Rarity;
import mage.MageInt;
import mage.abilities.common.SpellCastControllerTriggeredAbility;
import mage.abilities.effects.common.continuous.SwitchPowerToughnessSourceEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.constants.Duration;
import mage.filter.FilterSpell;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.CardTypePredicate;

/**
 *
 * @author LevelX2
 */
public class Fluxcharger extends CardImpl {

    private static final FilterSpell filter = new FilterSpell("instant or sorcery spell");
    static {
        filter.add(Predicates.or(
                new CardTypePredicate(CardType.INSTANT),
                new CardTypePredicate(CardType.SORCERY)));
    }

    public Fluxcharger(UUID ownerId) {
        super(ownerId, 71, "Fluxcharger", Rarity.UNCOMMON, new CardType[]{CardType.CREATURE}, "{2}{U}{R}");
        this.expansionSetCode = "DGM";
        this.subtype.add("Weird");

        this.color.setRed(true);
        this.color.setBlue(true);
        this.power = new MageInt(1);
        this.toughness = new MageInt(5);

        // Flying
        this.addAbility(FlyingAbility.getInstance());
        // Whenever you cast an instant or sorcery spell, you may switch Fluxcharger's power and toughness until end of turn.
        this.addAbility(new SpellCastControllerTriggeredAbility(new SwitchPowerToughnessSourceEffect(Duration.EndOfTurn), filter, true));

    }

    public Fluxcharger(final Fluxcharger card) {
        super(card);
    }

    @Override
    public Fluxcharger copy() {
        return new Fluxcharger(this);
    }
}
