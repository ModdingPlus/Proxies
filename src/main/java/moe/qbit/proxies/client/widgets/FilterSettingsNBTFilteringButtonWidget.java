package moe.qbit.proxies.client.widgets;

import com.mojang.blaze3d.matrix.MatrixStack;
import moe.qbit.proxies.Proxies;
import moe.qbit.proxies.api.filtering.FilterSettings;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.function.Consumer;

public class FilterSettingsNBTFilteringButtonWidget extends CycleButtonWidget {
    private static final TranslationTextComponent TITLE = new TranslationTextComponent("tooltip."+ Proxies.MODID +".toggle_nbt_filtering");
    private static final TranslationTextComponent TOOLTIP_MATCH_NBT = new TranslationTextComponent("tooltip."+ Proxies.MODID +".match_nbt");
    private static final TranslationTextComponent TOOLTIP_IGNORE_NBT = new TranslationTextComponent("tooltip."+ Proxies.MODID +".ignore_nbt");
    private final FilterSettings filterSettings;
    private Screen screen;

    public FilterSettingsNBTFilteringButtonWidget(FilterSettings filterSettings, int x, int y, Screen screen, Consumer<FilterSettingsNBTFilteringButtonWidget> pressedAction) {
        super(x, y, TITLE, (button)->pressedAction.accept((FilterSettingsNBTFilteringButtonWidget)button));
        this.filterSettings = filterSettings;
        this.screen = screen;
        this.setTextureData(CycleButtonWidget.COMMON_ATLAS_LOCATION, 0, 80);
    }

    @Override
    public void renderToolTip(MatrixStack matrixStack, int mouseX, int mouseY) {
        FontRenderer fontRenderer = this.screen.getMinecraft().fontRenderer;
        this.screen.renderToolTip(matrixStack, fontRenderer.trimStringToWidth(
                this.filterSettings.isIgnoreNBT()? TOOLTIP_IGNORE_NBT : TOOLTIP_MATCH_NBT,
                Math.max(this.screen.width / 2 - 43, 170)
        ), mouseX, mouseY, fontRenderer);
    }

    @Override
    public int getSegmentIndex() {
        return this.filterSettings.isIgnoreNBT()?1:0;
    }

}
