package com.feroov.frv.entities.render;


import com.feroov.frv.entities.hostile.WallOfFlesh;
import com.feroov.frv.entities.model.WallOfFleshModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class WallOfFleshRenderer extends GeoEntityRenderer<WallOfFlesh>
{

    public WallOfFleshRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new WallOfFleshModel());
        this.shadowRadius = 0.65F;
    }
    @Override
    public RenderType getRenderType(WallOfFlesh animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityCutout(textureLocation);
    }

    @Override
    public void renderEarly(WallOfFlesh animatable, PoseStack stackIn, float ticks,
                            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                            int packedOverlayIn, float red, float green, float blue, float partialTicks)
    {
        stackIn.scale(3.25F,3.25F,3.25F);
    }

    @Override
    protected float getDeathMaxRotation(WallOfFlesh entityLivingBaseIn)
    {
        return 0;
    }

}