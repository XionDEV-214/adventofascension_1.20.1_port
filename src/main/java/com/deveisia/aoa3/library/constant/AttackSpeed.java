package com.deveisia.aoa3.library.constant;

public final class AttackSpeed {
	public static final float QUADRUPLE = getCustomSpeed(4f);
	public static final float TRIPLE = getCustomSpeed(3f);
	public static final float DOUBLE = getCustomSpeed(2f);
	public static final float NORMAL = getCustomSpeed(1f);
	public static final float HALF = getCustomSpeed(0.5f);
	public static final float THIRD = getCustomSpeed(0.33f);
	public static final float QUARTER = getCustomSpeed(0.25f);

	public static final float GREATBLADE = -3.24f;

	public static float getCustomSpeed(float speedMod) {
		return -1f * (4 - 1 / (0.625f * (1 / speedMod)));
	}

	public static float forAttacksPerSecond(float aps) {
		return -4 + aps;
	}
}
