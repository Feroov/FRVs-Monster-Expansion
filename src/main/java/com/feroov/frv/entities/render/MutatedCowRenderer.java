package com.feroov.frv.entities.render;


import com.feroov.frv.entities.hostile.MutatedCow;
import com.feroov.frv.entities.model.MutatedCowModel;
import com.feroov.frv.entities.variants.MutatedCowVariant;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;
import java.util.Map;

public class MutatedCowRenderer extends GeoEntityRenderer<MutatedCow>
{
    public static final Map<MutatedCowVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MutatedCowVariant.class), (p_114874_) ->
            {
                p_114874_.put(MutatedCowVariant.MAIN, new ResourceLocation("frv:textures/entity/mutated_cow/mutated_cow.png"));
                p_114874_.put(MutatedCowVariant.COW2, new ResourceLocation("frv:textures/entity/mutated_cow/mutated_cow2.png"));
                p_114874_.put(MutatedCowVariant.COW3, new ResourceLocation("frv:textures/entity/mutated_cow/mutated_cow3.png"));
                p_114874_.put(MutatedCowVariant.COW4, new ResourceLocation("frv:textures/entity/mutated_cow/mutated_cow4.png"));
            });

    public MutatedCowRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new MutatedCowModel());
        this.shadowRadius = 0.44F;
    }
    @Override
    public RenderType getRenderType(MutatedCow animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer,
                                    @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        return RenderType.entityCutout(textureLocation);
    }

    @Override
    public void renderEarly(MutatedCow animatable, PoseStack stackIn, float ticks,
                            @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                            int packedOverlayIn, float red, float green, float blue, float partialTicks)
    {
        stackIn.scale(2.0F,2.0F,2.0F);
    }

    @Override
    protected float getDeathMaxRotation(MutatedCow entityLivingBaseIn)
    {
        return 0;
    }

    @Override
    public ResourceLocation getTextureLocation(MutatedCow entity)
    {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

}