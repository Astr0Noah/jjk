package net.dna.jjkmod;

import com.mojang.logging.LogUtils;
import dev.zanckor.api.filemanager.quest.register.LoadQuest;
import dev.zanckor.mod.QuestApiMain;
import net.dna.jjkmod.item.ModItems;
import net.dna.jjkmod.networking.ModMessages;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.IModBusEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.io.IOException;

//commit test
// The value here should match an entry in the META-INF/mods.toml file
@Mod(JujutsuKaisenMod.MOD_ID)
public class JujutsuKaisenMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "jjkmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public JujutsuKaisenMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);


        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);



    }


    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ModMessages.register();
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event){
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.accept(ModItems.SUKUNA_FINGER);
        }
    }





    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }

    }

    @Mod.EventBusSubscriber(modid = JujutsuKaisenMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public class registerTest{
        @SubscribeEvent
        public static void registerTemplates(ServerAboutToStartEvent e)throws IOException{
            JujutsuKaisenMod.LOGGER.debug("This should be called");
            LoadQuest.registerQuest(e.getServer(), JujutsuKaisenMod.MOD_ID);

        }

    }



}
