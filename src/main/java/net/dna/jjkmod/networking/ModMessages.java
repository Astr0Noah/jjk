package net.dna.jjkmod.networking;

import net.dna.jjkmod.JujutsuKaisenMod;
import net.dna.jjkmod.networking.packet.SukunaC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id(){
        return packetId++;
    }
    public static void register(){
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(JujutsuKaisenMod.MOD_ID, "messages")).
                networkProtocolVersion(()->"1.0")
                .clientAcceptedVersions(s->true)
                .serverAcceptedVersions(s->true)
                .simpleChannel();
        INSTANCE = net;
        //must register packet here

        net.messageBuilder(SukunaC2SPacket.class,id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SukunaC2SPacket::new)
                .encoder(SukunaC2SPacket::toByte)
                .consumerMainThread(SukunaC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(()->player),message);
    }
}
