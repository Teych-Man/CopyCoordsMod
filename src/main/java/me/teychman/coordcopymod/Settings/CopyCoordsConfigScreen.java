package me.teychman.coordcopymod.Settings;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public class CopyCoordsConfigScreen extends Screen {

    private final Screen parent;

    public CopyCoordsConfigScreen(Screen parent) {
        super(Component.literal("CopyCoords Settings"));
        this.parent = parent;
    }

    @Override
    protected void init() {

        StringWidget string = new StringWidget(
                width / 2 - 100,
                height / 2 - 50,
                200,
                20,
                Component.literal("Введите шаблон для копирования"),
                font
        );
        addRenderableWidget(string);

        EditBox editBox = new EditBox(
                font,
                width / 2 - 100,
                height / 2 - 30,
                200,
                20,
                Component.literal("Введите шаблон")
        );
        editBox.setValue(ModConfigs.COORDS_FORMAT.get());
        editBox.setResponder(ModConfigs.COORDS_FORMAT::set);
        addRenderableWidget(editBox);

        StringWidget template_placeholders = new StringWidget(
                width / 2 - 100,
                height / 2 - 10,
                200,
                20,
                Component.literal("Доступные плейсхолдеры: {x} {y} {z} {world}").withStyle(ChatFormatting.GRAY),
                font
        );
        addRenderableWidget(template_placeholders);

        addRenderableWidget(
                Button.builder(
                                Component.literal("Назад"),
                                b -> minecraft.setScreen(parent)
                        ).bounds(width / 2 - 100, height - 30, 200, 20)
                        .build()
        );
    }


    @Override
    public void render(@NotNull GuiGraphics gg, int mouseX, int mouseY, float partialTicks) {
        renderBackground(gg);
        super.render(gg, mouseX, mouseY, partialTicks);
    }
}
