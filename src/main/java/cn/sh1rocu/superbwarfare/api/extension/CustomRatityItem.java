package cn.sh1rocu.superbwarfare.api.extension;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;

public class CustomRatityItem extends Item implements IRarity {
    private final ChatFormatting rarityColor;

    public CustomRatityItem(ChatFormatting color) {
        super(new Item.Properties());
        this.rarityColor = color;
    }

    @Override
    public ChatFormatting getRarityColor() {
        return rarityColor;
    }
}
