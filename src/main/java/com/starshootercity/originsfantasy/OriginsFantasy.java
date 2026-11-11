package com.starshootercity.originsfantasy;

import com.starshootercity.OriginsAddon;
import com.starshootercity.abilities.types.Ability;
import com.starshootercity.originsfantasy.abilities.AllayMaster;
import com.starshootercity.originsfantasy.abilities.ArrowEffectBooster;
import com.starshootercity.originsfantasy.abilities.BardicIntuition;
import com.starshootercity.originsfantasy.abilities.BowBurst;
import com.starshootercity.originsfantasy.abilities.BreathStorer;
import com.starshootercity.originsfantasy.abilities.Chime;
import com.starshootercity.originsfantasy.abilities.DaylightSensitive;
import com.starshootercity.originsfantasy.abilities.DoubleHealth;
import com.starshootercity.originsfantasy.abilities.DragonFireball;
import com.starshootercity.originsfantasy.abilities.Elegy;
import com.starshootercity.originsfantasy.abilities.EndBoost;
import com.starshootercity.originsfantasy.abilities.EndCrystalHealing;
import com.starshootercity.originsfantasy.abilities.FireResistant;
import com.starshootercity.originsfantasy.abilities.FortuneIncreaser;
import com.starshootercity.originsfantasy.abilities.HeavyBlow;
import com.starshootercity.originsfantasy.abilities.IncreasedArrowDamage;
import com.starshootercity.originsfantasy.abilities.IncreasedArrowSpeed;
import com.starshootercity.originsfantasy.abilities.IncreasedSpeed;
import com.starshootercity.originsfantasy.abilities.InfiniteHaste;
import com.starshootercity.originsfantasy.abilities.InfiniteNightVision;
import com.starshootercity.originsfantasy.abilities.LargeBody;
import com.starshootercity.originsfantasy.abilities.Leeching;
import com.starshootercity.originsfantasy.abilities.MagicResistance;
import com.starshootercity.originsfantasy.abilities.MoonStrength;
import com.starshootercity.originsfantasy.abilities.NaturalArmor;
import com.starshootercity.originsfantasy.abilities.NoteBlockPower;
import com.starshootercity.originsfantasy.abilities.OceanWish;
import com.starshootercity.originsfantasy.abilities.OceansGrace;
import com.starshootercity.originsfantasy.abilities.PerfectShot;
import com.starshootercity.originsfantasy.abilities.PermanentHorse;
import com.starshootercity.originsfantasy.abilities.PoorShot;
import com.starshootercity.originsfantasy.abilities.PotionResistant;
import com.starshootercity.originsfantasy.abilities.SmallBody;
import com.starshootercity.originsfantasy.abilities.SolidStance;
import com.starshootercity.originsfantasy.abilities.Stiff;
import com.starshootercity.originsfantasy.abilities.StoneSkin;
import com.starshootercity.originsfantasy.abilities.StrongSkin;
import com.starshootercity.originsfantasy.abilities.Stronger;
import com.starshootercity.originsfantasy.abilities.SuperJump;
import com.starshootercity.originsfantasy.abilities.UndeadAlly;
import com.starshootercity.originsfantasy.abilities.VampiricTransformation;
import com.starshootercity.originsfantasy.abilities.WaterSensitive;
import com.starshootercity.util.Metrics;
import com.starshootercity.version.MVAttribute;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.attribute.Attribute;
import org.jetbrains.annotations.NotNull;

public class OriginsFantasy extends OriginsAddon {
    private static OriginsFantasy instance;

    @Override
    public @NotNull String getNamespace() {
        return "fantasyorigins";
    }

    public static OriginsFantasy getInstance() {
        return instance;
    }

    @Override
    public @NotNull List<Ability> getRegisteredAbilities() {
        List<Ability> abilities = new ArrayList<>(
            List.of(
                new AllayMaster(),
                new ArrowEffectBooster(),
                new BardicIntuition(),
                new BowBurst(),
                new BreathStorer(),
                new Chime(),
                new DoubleHealth(),
                new DragonFireball(),
                new Elegy(),
                new EndCrystalHealing(),
                new EndBoost(),
                new FortuneIncreaser(),
                new IncreasedArrowDamage(),
                new IncreasedArrowSpeed(),
                new HeavyBlow(),
                HeavyBlow.IncreasedCooldown.increasedCooldown,
                HeavyBlow.IncreasedDamage.increasedDamage,
                EndBoost.EndHealth.endHealth,
                EndBoost.EndStrength.endStrength,
                new IncreasedSpeed(),
                new InfiniteHaste(),
                new InfiniteNightVision(),
                new OceanWish(),
                OceanWish.LandWeakness.landWeakness,
                OceanWish.LandHealth.landHealth,
                OceanWish.LandSlowness.landSlowness,
                new MagicResistance(),
                new MoonStrength(),
                new NaturalArmor(),
                new NoteBlockPower(),
                new PerfectShot(),
                new PermanentHorse(),
                new PoorShot(),
                new StrongSkin(),
                new SuperJump(),
                new OceansGrace(),
                OceansGrace.WaterHealth.waterHealth,
                OceansGrace.WaterStrength.waterStrength,
                new VampiricTransformation(),
                new DaylightSensitive(),
                new WaterSensitive(),
                new Leeching(),
                new Stronger(),
                new UndeadAlly(),
                new SolidStance(),
                new StoneSkin(),
                new Stiff(),
                new FireResistant(),
                new PotionResistant()
            )
        );
        Attribute scale = MVAttribute.SCALE.get();
        if (scale != null) {
            abilities.add(new LargeBody(scale));
            abilities.add(new SmallBody(scale));
        }

        return abilities;
    }

    @Override
    public void onRegister() {
        instance = this;
        int pluginId = 25121;
        new Metrics(this, pluginId);
    }
}
