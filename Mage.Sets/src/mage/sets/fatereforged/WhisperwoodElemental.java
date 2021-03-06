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
package mage.sets.fatereforged;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.Ability;
import mage.abilities.common.BeginningOfEndStepTriggeredAbility;
import mage.abilities.common.DiesTriggeredAbility;
import mage.abilities.common.SimpleActivatedAbility;
import mage.abilities.costs.common.SacrificeSourceCost;
import mage.abilities.effects.Effect;
import mage.abilities.effects.common.continuous.GainAbilityControlledEffect;
import mage.abilities.effects.keyword.ManifestEffect;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.Rarity;
import mage.constants.TargetController;
import mage.constants.Zone;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.other.FaceDownPredicate;
import mage.filter.predicate.permanent.TokenPredicate;

/**
 *
 * @author LevelX2
 */
public class WhisperwoodElemental extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("face-up, nontoken creatures you control");

    static {
        filter.add(Predicates.not(new FaceDownPredicate()));
        filter.add(Predicates.not(new TokenPredicate()));
    }

    public WhisperwoodElemental(UUID ownerId) {
        super(ownerId, 145, "Whisperwood Elemental", Rarity.MYTHIC, new CardType[]{CardType.CREATURE}, "{3}{G}{G}");
        this.expansionSetCode = "FRF";
        this.subtype.add("Elemental");
        this.power = new MageInt(4);
        this.toughness = new MageInt(4);

        // At the beginning of your end step, manifest the top card of your library.
        this.addAbility(new BeginningOfEndStepTriggeredAbility(new ManifestEffect(1), TargetController.YOU, false));
        
        // Sacrifice Whisperwood Elemental: Until end of turn, face-up, nontoken creatures you control gain "When this creature dies, manifest the top card of your library."
        Ability abilityToGain = new DiesTriggeredAbility(new ManifestEffect(1));
        Effect effect = new GainAbilityControlledEffect(abilityToGain, Duration.EndOfTurn, filter);
        effect.setText("Until end of turn, face-up, nontoken creatures you control gain \"When this creature dies, manifest the top card of your library.\"");
        this.addAbility(new SimpleActivatedAbility(
                Zone.BATTLEFIELD, effect, new SacrificeSourceCost()));
    }

    public WhisperwoodElemental(final WhisperwoodElemental card) {
        super(card);
    }

    @Override
    public WhisperwoodElemental copy() {
        return new WhisperwoodElemental(this);
    }
}
