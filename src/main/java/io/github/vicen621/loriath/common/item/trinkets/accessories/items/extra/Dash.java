package io.github.vicen621.loriath.common.item.trinkets.accessories.items.extra;

//TODO Hacerlo un trinket/encantamiento
public class Dash {

    /*public static KeyBinding DASH_KEYBIND;

    public Dash() {
        ServerPlayNetworking.registerGlobalReceiver(LoriathMod.id("dash"), this::onPacketRecieve);
    }

    private void onPacketRecieve(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender) {
        World world = player.getWorld();

        Random rand = world.random;
        for (int i = 0; i < 3; i++) {
            double d0 = rand.nextGaussian() * 0.02D;
            double d1 = rand.nextGaussian() * 0.02D;
            double d2 = rand.nextGaussian() * 0.02D;
            double x = player.getX() + (double) (rand.nextFloat() * 2.0F) - d0 * 10.0D;
            double y = player.getY() + (double) (rand.nextFloat() * 2.0F) - d1 * 10.0D;
            double z = player.getZ() + (double) (rand.nextFloat() * 2.0F) - d2 * 10.0D;
            if (world instanceof ServerWorld sv)
                sv.spawnParticles(ParticleTypes.POOF, x, y, z, 10,
                        d0, d1, d2, 0.0);
        }
        world.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.ENTITY_GHAST_SHOOT, SoundCategory.PLAYERS, 0.5f, 1.5f);

        player.setVelocity(player.getVelocity().add(calculateVector(player)));
        player.velocityModified = true;
    }

    private Vec3d calculateVector(ServerPlayerEntity p) {
        double radians = Math.toRadians(p.getYaw());
        double x = -Math.sin(radians) * 2;
        double y = 0.7;
        double z = Math.cos(radians) * 2;
        return new Vec3d(x, y, z);
    }

    public static class DashEnchantment extends Enchantment {

        protected DashEnchantment(Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
            super(weight, type, slotTypes);
        }
    }*/
}
