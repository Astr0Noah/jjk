package net.dna.jjkmod.item;

import net.dna.jjkmod.JujutsuKaisenMod;
import net.dna.jjkmod.item.custom.SukunaFinger;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS,JujutsuKaisenMod.MOD_ID);

    public static final RegistryObject<Item>SUKUNA_FINGER = ITEMS.register("sukuna_finger",
            ()-> new SukunaFinger(new Item.Properties().stacksTo(1).food(new FoodProperties.Builder().alwaysEat().nutrition(0).build())));



    public static void register(IEventBus eventBus){ITEMS.register(eventBus);}
}
