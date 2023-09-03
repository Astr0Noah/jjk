package net.dna.jjkmod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SukunaFinger extends Item {
    public SukunaFinger(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(level.isClientSide() && hand == InteractionHand.MAIN_HAND){
            //outputRandomNumber(player);


        }



        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level p_41422_, List<Component> components, TooltipFlag flag) {
        if(Screen.hasShiftDown()){
            components.add(Component.literal("Eat to obtain Sukuna's strength").withStyle(ChatFormatting.DARK_RED));

        }else{
            components.add(Component.literal("Press SHIFT for more info").withStyle(ChatFormatting.DARK_PURPLE));
        }


        super.appendHoverText(itemStack, p_41422_, components, flag);

    }

    private void outputRandomNumber(Player player){
        player.sendSystemMessage(Component.literal("Your Number is "+ getRandom()));
    }

    private int getRandom(){
        return RandomSource.createNewThreadLocalInstance().nextInt(10);
    }
}
