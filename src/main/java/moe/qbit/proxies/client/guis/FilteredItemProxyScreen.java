package moe.qbit.proxies.client.guis;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import moe.qbit.proxies.Proxies;
import moe.qbit.proxies.api.filtering.FilterSettings;
import moe.qbit.proxies.client.widgets.FilterSettingsModeButtonWidget;
import moe.qbit.proxies.client.widgets.FilterSettingsNBTFilteringButtonWidget;
import moe.qbit.proxies.common.containers.FilteredCapabilityProxyContainer;
import moe.qbit.proxies.common.tileentities.filters.FilteredFluidProxyTileEntity;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class FilteredItemProxyScreen extends ContainerScreen<FilteredCapabilityProxyContainer> {

    private ResourceLocation GUI = new ResourceLocation(Proxies.MODID, "textures/gui/container/filtered_capability_proxy.png");
    private ResourceLocation GUI_FLUID = new ResourceLocation(Proxies.MODID, "textures/gui/container/filtered_fluid_proxy.png");

    public FilteredItemProxyScreen(FilteredCapabilityProxyContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
        this.xSize = 176;
        this.ySize = 132;
        this.playerInventoryTitleY = this.ySize - 94;
    }

    @Override
    protected void init() {
        super.init();
        this.addButton(new FilterSettingsModeButtonWidget(this.container.getFilterSettings(), this.guiLeft+this.xSize-8-17, this.guiTop+17,this, (button)->{
            this.container.getFilterSettings().toggleMode();
            this.syncFilterSettings(this.container.getFilterSettings());
        }));
        this.addButton(new FilterSettingsNBTFilteringButtonWidget(this.container.getFilterSettings(), this.guiLeft+this.xSize-8-17-20, this.guiTop+17,this, (button)->{
            this.container.getFilterSettings().toggleIgnoreTags();
            this.syncFilterSettings(this.container.getFilterSettings());
        }));
    }

    public void syncFilterSettings(FilterSettings filterSettings){
        if(this.minecraft!=null && this.minecraft.playerController!=null)
            this.minecraft.playerController.sendEnchantPacket(this.container.windowId, filterSettings.toNumber());
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(this.container.getTileEntity() instanceof FilteredFluidProxyTileEntity ? GUI_FLUID : GUI);
        this.blit(matrixStack, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }
}
