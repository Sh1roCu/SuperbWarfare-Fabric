package com.atsuishio.superbwarfare.item.common;

import cn.sh1rocu.superbwarfare.api.extension.IRarity;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class BlueprintItem extends Item implements IRarity {

    private final ChatFormatting rarityColor;

    public BlueprintItem(Rarity rarity) {
        super(new Properties().rarity(rarity));
        this.rarityColor = rarity.color;
    }

    public BlueprintItem(ChatFormatting color) {
        super(new Properties());
        this.rarityColor = color;
    }

    @Override
    public ChatFormatting getRarityColor() {
        return rarityColor;
    }
}
