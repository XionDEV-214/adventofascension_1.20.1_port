package com.deveisia.aoa3.client.model.compat;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import org.joml.Quaternionf;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class LegacyModelPart {
	private static final Set<Direction> ALL_FACES = EnumSet.allOf(Direction.class);

	private float xTexSize = 64;
	private float yTexSize = 32;
	private int xTexOffs;
	private int yTexOffs;
	public float x;
	public float y;
	public float z;
	public float xRot;
	public float yRot;
	public float zRot;
	public boolean mirror;
	public boolean visible = true;
	public final List<ModelPart.Cube> cubes = new ArrayList<>();
	public final List<LegacyModelPart> children = new ArrayList<>();

	public LegacyModelPart(LegacyModel model) {
		setTexSize(model.getTexWidth(), model.getTexHeight());
	}

	public LegacyModelPart(LegacyModel model, int texOffsX, int texOffsY) {
		this(model.getTexWidth(), model.getTexHeight(), texOffsX, texOffsY);
	}

	public LegacyModelPart(int texWidth, int texHeight, int texOffsX, int texOffsY) {
		setTexSize(texWidth, texHeight);
		texOffs(texOffsX, texOffsY);
	}

	public void copyFrom(LegacyModelPart other) {
		this.xRot = other.xRot;
		this.yRot = other.yRot;
		this.zRot = other.zRot;
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}

	public void copyFrom(ModelPart other) {
		this.xRot = other.xRot;
		this.yRot = other.yRot;
		this.zRot = other.zRot;
		this.x = other.x;
		this.y = other.y;
		this.z = other.z;
	}

	public void addChild(LegacyModelPart child) {
		this.children.add(child);
	}

	public LegacyModelPart texOffs(int x, int y) {
		this.xTexOffs = x;
		this.yTexOffs = y;

		return this;
	}

	public LegacyModelPart addBox(String partName, float x, float y, float z, int width, int height, int depth, float delta, int texX, int texY) {
		texOffs(texX, texY);
		addCube(this.xTexOffs, this.yTexOffs, x, y, z, width, height, depth, delta, delta, delta, this.mirror);

		return this;
	}

	public LegacyModelPart addBox(float x, float y, float z, float width, float height, float depth) {
		addCube(this.xTexOffs, this.yTexOffs, x, y, z, width, height, depth, 0, 0, 0, this.mirror);

		return this;
	}

	public LegacyModelPart addBox(float x, float y, float z, float width, float height, float depth, boolean mirror) {
		addCube(this.xTexOffs, this.yTexOffs, x, y, z, width, height, depth, 0, 0, 0, mirror);

		return this;
	}

	public void addBox(float x, float y, float z, float width, float height, float depth, float delta) {
		addCube(this.xTexOffs, this.yTexOffs, x, y, z, width, height, depth, delta, delta, delta, this.mirror);
	}

	public void addBox(float x, float y, float z, float width, float height, float depth, float deltaX, float deltaY, float deltaZ) {
		addCube(this.xTexOffs, this.yTexOffs, x, y, z, width, height, depth, deltaX, deltaY, deltaZ, this.mirror);
	}

	public void addBox(float x, float y, float z, float width, float height, float depth, float delta, boolean mirror) {
		addCube(this.xTexOffs, this.yTexOffs, x, y, z, width, height, depth, delta, delta, delta, mirror);
	}

	private void addCube(int texOffsX, int texOffsY, float x, float y, float z, float width, float height, float depth, float growX, float growY, float growZ, boolean mirror) {
		this.cubes.add(new ModelPart.Cube(texOffsX, texOffsY, x, y, z, width, height, depth, growX, growY, growZ, mirror, this.xTexSize, this.yTexSize, ALL_FACES));
	}

	public void setPos(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void render(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay) {
		render(poseStack, buffer, packedLight, packedOverlay, 1, 1, 1, 1);
	}

	public void render(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		if (!this.visible || (this.cubes.isEmpty() && this.children.isEmpty()))
			return;

		poseStack.pushPose();
		translateAndRotate(poseStack);

		PoseStack.Pose pose = poseStack.last();

		for (ModelPart.Cube cube : this.cubes) {
			cube.compile(pose, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		}

		for (LegacyModelPart child : this.children) {
			child.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		}

		poseStack.popPose();
	}

	public void translateAndRotate(PoseStack poseStack) {
		poseStack.translate(this.x / 16f, this.y / 16f, this.z / 16f);

		if (this.xRot != 0 || this.yRot != 0 || this.zRot != 0)
			poseStack.mulPose(new Quaternionf().rotationZYX(this.zRot, this.yRot, this.xRot));
	}

	public LegacyModelPart setTexSize(int width, int height) {
		this.xTexSize = width;
		this.yTexSize = height;

		return this;
	}

	public ModelPart.Cube getRandomCube(RandomSource random) {
		return this.cubes.get(random.nextInt(this.cubes.size()));
	}
}
