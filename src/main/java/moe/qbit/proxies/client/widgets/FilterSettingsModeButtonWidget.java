package moe.qbit.proxies.client.widgets;

import com.mojang.blaze3d.matrix.MatrixStack;
import moe.qbit.proxies.Proxies;
import moe.qbit.proxies.api.filtering.FilterSettings;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.function.Consumer;

public class FilterSettingsModeButtonWidget extends CycleButtonWidget {
    private static final TranslationTextComponent TITLE = new TranslationTextComponent("tooltip."+ Proxies.MODID +".toggle_whitelist");
    private static final TranslationTextComponent TOOLTIP_WHITELIST = new TranslationTextComponent("tooltip."+ Proxies.MODID +".whitelist");
    private static final TranslationTextComponent TOOLTIP_BLACKLIST = new TranslationTextComponent("tooltip."+ Proxies.MODID +".blacklist");
    private final FilterSettings filterSettings;
    private Screen screen;

    public FilterSettingsModeButtonWidget(FilterSettings filterSettings, int x, int y, Screen screen, Consumer<FilterSettingsModeButtonWidget> pressedAction) {
        super(x, y, TITLE, (button)->pressedAction.accept((FilterSettingsModeButtonWidget)button));
        this.filterSettings = filterSettings;
        this.screen = screen;
        this.setTextureData(CycleButtonWidget.COMMON_ATLAS_LOCATION, 0, 0);
    }

    @Override
    public void renderToolTip(MatrixStack matrixStack, int mouseX, int mouseY) {
        FontRenderer fontRenderer = this.screen.getMinecraft().fontRenderer;
        this.screen.renderToolTip(matrixStack, fontRenderer.trimStringToWidth(
                this.filterSettings.isWhitelist()?TOOLTIP_WHITELIST:TOOLTIP_BLACKLIST,
                Math.max(this.screen.width / 2 - 43, 170)
        ), mouseX, mouseY, fontRenderer);
    }

    @Override
    public int getSegmentIndex() {
        return this.filterSettings.isWhitelist()?0:1;
    }

}
