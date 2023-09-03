package net.dna.jjkmod.networking.packet;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.commands.EffectCommands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;

import java.util.function.Supplier;

public class SukunaC2SPacket {
    public  SukunaC2SPacket(){

    }

    //this is for synchronization later
    public SukunaC2SPacket(FriendlyByteBuf buf){

    }
    public void toByte(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context>supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(()->{
            // HERE WE ARE ON THE SERVER!!
            ServerPlayer player = context.getSender();

            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2000,25));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 2000,50));

        });
        return true;
    }


}
