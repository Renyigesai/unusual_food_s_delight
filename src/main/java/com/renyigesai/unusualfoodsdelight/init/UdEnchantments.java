package com.renyigesai.unusualfoodsdelight.init;

import com.renyigesai.unusualfoodsdelight.UnusualFoodDelight;
import com.renyigesai.unusualfoodsdelight.enchantment.WhackEnchantment;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class UdEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(Registries.ENCHANTMENT, UnusualFoodDelight.MODID);

    public static final RegistryObject<Enchantment> WHACK = ENCHANTMENTS.register("whack",
            () -> new WhackEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlot.MAINHAND));
}
