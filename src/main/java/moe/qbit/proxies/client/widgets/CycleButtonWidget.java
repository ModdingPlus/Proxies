package moe.qbit.proxies.client.widgets;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import moe.qbit.proxies.Proxies;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class CycleButtonWidget extends Button {
    public static final ResourceLocation COMMON_ATLAS_LOCATION = new ResourceLocation(Proxies.MODID, "textures/gui/common_atlas.png");

    private ResourceLocation iconTexture = CycleButtonWidget.COMMON_ATLAS_LOCATION;
    private int segmentIndex = 0;
    private int segmentBaseOffsetX = 0;
    private int segmentBaseOffsetY = 0;
    private int segmentDeltaX = 16;
    private int segmentDeltaY = 16;

    public CycleButtonWidget(int x, int y, ITextComponent title, IPressable pressedAction) {
        super(x, y, 18, 18, title, pressedAction);
    }

    public CycleButtonWidget setTextureData(ResourceLocation iconTexture, int segmentBaseOffsetX, int segmentBaseOffsetY){
        this.setTextureData(iconTexture, segmentBaseOffsetX, segmentBaseOffsetY, 16, 0);
        return this;
    }

    public void setTextureData(ResourceLocation iconTexture, int segmentBaseOffsetX, int segmentBaseOffsetY, int segmentDeltaX, int segmentDeltaY){
        this.iconTexture = iconTexture;
        this.segmentBaseOffsetX = segmentBaseOffsetX;
        this.segmentBaseOffsetY = segmentBaseOffsetY;
        this.segmentDeltaX = segmentDeltaX;
        this.segmentDeltaY = segmentDeltaY;
    }

    public int getSegmentIndex(){
        return this.segmentIndex;
    }
    public void setSegmentIndex(int index){
        this.segmentIndex = index;
    }

    @Override
    public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getTextureManager().bindTexture(COMMON_ATLAS_LOCATION);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
        int i = this.getYImage(this.isHovered());
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        this.blit(matrixStack, this.x, this.y, 80, i * 18, 18, 18);
        minecraft.getTextureManager().bindTexture(this.iconTexture);
        this.blit(matrixStack, this.x+1, this.y+1, this.segmentBaseOffsetX+this.segmentDeltaX*this.getSegmentIndex(), this.segmentBaseOffsetY+this.segmentDeltaY*this.getSegmentIndex(), 16, 16);
        this.renderBg(matrixStack, minecraft, mouseX, mouseY);

        if (this.isHovered()) {
            this.renderToolTip(matrixStack, mouseX, mouseY);
        }
    }
}
