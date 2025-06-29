package mane.wizardion.mixin.items;

import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.LightningEntity;

public class WandItem extends Item {
    public WandItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            // 计算玩家正前方10格的位置
            Vec3d lookVec = player.getRotationVec(1.0F); // 获取玩家视线方向
            Vec3d targetPos = player.getPos().add(lookVec.multiply(10)); // 正前方10格

            // 创建闪电实体
            LightningEntity lightning = new LightningEntity(world, false);
            // 在世界中生成闪电
            world.spawnEntity(lightning);
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }
}

