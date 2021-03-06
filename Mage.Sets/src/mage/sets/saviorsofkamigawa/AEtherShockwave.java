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
package mage.sets.saviorsofkamigawa;

import java.util.UUID;
import mage.abilities.Mode;
import mage.abilities.effects.common.TapAllEffect;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Rarity;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.Predicates;
import mage.filter.predicate.mageobject.SubtypePredicate;

/**
 *
 * @author LevelX2
 */
public class AEtherShockwave extends CardImpl {

    private static final FilterCreaturePermanent filterSpirit = new FilterCreaturePermanent("Spirits");
    private static final FilterCreaturePermanent filterNonSpirit = new FilterCreaturePermanent("non-Spirit creatures");
    static {
        filterSpirit.add(new SubtypePredicate("Spirit"));
        filterNonSpirit.add(Predicates.not(new SubtypePredicate("Spirit")));
    }

    public AEtherShockwave(UUID ownerId) {
        super(ownerId, 1, "AEther Shockwave", Rarity.UNCOMMON, new CardType[]{CardType.INSTANT}, "{3}{W}");
        this.expansionSetCode = "SOK";

        this.color.setWhite(true);

        // Choose one - Tap all Spirits; or tap all non-Spirit creatures.
        this.getSpellAbility().addEffect(new TapAllEffect(filterSpirit));
        
        Mode mode = new Mode();
        mode.getEffects().add(new TapAllEffect(filterNonSpirit));
        this.getSpellAbility().addMode(mode);
    }

    public AEtherShockwave(final AEtherShockwave card) {
        super(card);
    }

    @Override
    public AEtherShockwave copy() {
        return new AEtherShockwave(this);
    }
}
