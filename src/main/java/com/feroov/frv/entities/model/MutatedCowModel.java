package com.feroov.frv.entities.model;

import com.feroov.frv.Frv;
import com.feroov.frv.entities.hostile.MutatedCow;
import com.feroov.frv.entities.render.MutatedCowRenderer;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class MutatedCowModel extends AnimatedGeoModel<MutatedCow>
{
    public MutatedCowModel() {}

    @Override
    public ResourceLocation getModelLocation(MutatedCow object)
    {
        return new ResourceLocation(Frv.MOD_ID, "geo/mutated_cow.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(MutatedCow entity)
    {
        return MutatedCowRenderer.LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public ResourceLocation getAnimationFileLocation(MutatedCow object)
    {
        return new ResourceLocation(Frv.MOD_ID, "animations/mutated_cow.animation.json");
    }

   /* @Override
    public void setLivingAnimations(MutatedCow entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");
        IBone head2 = this.getAnimationProcessor().getBone("head2");
        IBone head3 = this.getAnimationProcessor().getBone("head3");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        head.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        head2.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head2.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
        head3.setRotationX(extraData.headPitch * ((float) Math.PI / 180F));
        head3.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180F));
    }

    */
}
