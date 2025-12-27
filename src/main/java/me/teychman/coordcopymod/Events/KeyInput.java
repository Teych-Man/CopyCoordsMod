package me.teychman.coordcopymod.Events;

import me.teychman.coordcopymod.CopyCoordsMod;
import me.teychman.coordcopymod.Settings.ModConfigs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class KeyInput {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        String template = ModConfigs.COORDS_FORMAT.get();

        if (CopyCoordsMod.COPY_KEY.consumeClick()) {
            Vec3 pos = player.position();
            String coords = template
                    .replace("{x}", String.valueOf((int) pos.x))
                    .replace("{y}", String.valueOf((int) pos.y))
                    .replace("{z}", String.valueOf((int) pos.z))
                    .replace("{world}", player.level().dimension().location().toString());

            copyToClipboard(coords, player);
            player.displayClientMessage(
                    Component.literal("§aСкопировано в буфер: §f" + coords),
                    true
            );
        }
    }

    private static void copyToClipboard(String text, Player player) {
        try {
            Minecraft.getInstance().keyboardHandler.setClipboard(text);
        } catch (Exception e) {
            player.displayClientMessage(Component.literal("§cОшибка копирования в буфер"), true);
            e.printStackTrace();
        }
    }
}
