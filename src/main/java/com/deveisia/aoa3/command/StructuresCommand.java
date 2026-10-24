package com.deveisia.aoa3.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import java.util.Arrays;
import java.util.Collection;
import net.minecraft.ResourceLocationException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.commands.arguments.coordinates.Coordinates;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.minecraftforge.server.command.EnumArgument;

public class StructuresCommand implements Command<CommandSourceStack> {
	private static final StructuresCommand CMD = new StructuresCommand();

	public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher) {
		LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("structures").requires(sender -> sender.hasPermission(4)).executes(CMD);

		builder.then(Commands.literal("generate")
				.then(Commands.argument("structure_id", StructureIdArgument.instance())
						.then(Commands.argument("position", BlockPosArgument.blockPos())
								.executes(context -> spawnStructure(context, Mirror.NONE, Rotation.NONE, false)))
								.then(Commands.argument("mirror", EnumArgument.enumArgument(Mirror.class))
										.executes(context -> spawnStructure(context, context.getArgument("mirror", Mirror.class), Rotation.NONE, false)))
										.then(Commands.argument("rotation", EnumArgument.enumArgument(Rotation.class))
												.executes(context -> spawnStructure(context, context.getArgument("mirror", Mirror.class), context.getArgument("rotation", Rotation.class), false)))
												.then(Commands.argument("ignore_entities", BoolArgumentType.bool())
														.executes(context -> spawnStructure(context, context.getArgument("mirror", Mirror.class), context.getArgument("rotation", Rotation.class), context.getArgument("ignore_entities", Boolean.class))))));

		return builder;
	}

	private static int spawnStructure(CommandContext<CommandSourceStack> cmd, Mirror mirror, Rotation rotation, boolean ignoreEntities) throws CommandSyntaxException {
		try {
			ResourceLocation id = StructureIdArgument.getStructureId(cmd, "structure_id");
			StructureTemplate template = ServerLifecycleHooks.getCurrentServer().getStructureManager().get(id).orElse(null);
			BlockPos spawnPos = cmd.getArgument("position", Coordinates.class).getBlockPos(cmd.getSource());

			if (template == null) {
				AoACommand.feedback(cmd.getSource(), "Structures", "command.aoa.structures.invalidStructure", AoACommand.CommandFeedbackType.WARN, Component.literal(id.toString()));

				return 1;
			}
			else {
				template.placeInWorld(cmd.getSource().getLevel(), spawnPos, spawnPos, new StructurePlaceSettings().setMirror(mirror).setRotation(rotation).setIgnoreEntities(ignoreEntities).setFinalizeEntities(!ignoreEntities), RandomSource.create(), 2);
			}

			AoACommand.feedback(cmd.getSource(), "Structures", "command.aoa.structures.spawn", AoACommand.CommandFeedbackType.SUCCESS, Component.literal(id.toString()), Component.literal(spawnPos.getX() + ", " + spawnPos.getY() + ", " + spawnPos.getZ()));
		}
		catch (ResourceLocationException ex) {
			AoACommand.error(cmd.getSource(), "Structures", ex.getLocalizedMessage());
		}

		return 1;
	}

	@Override
	public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
		AoACommand.feedback(context.getSource(), "Structures", "command.aoa.structures.desc", AoACommand.CommandFeedbackType.INFO);

		return 1;
	}

	public static class StructureIdArgument implements ArgumentType<ResourceLocation> {
		private static final Collection<String> EXAMPLES = Arrays.asList("minecraft:igloo/bottom", "aoa3:abyss/abyssal_lotto_hut/abyssal_lotto_hut");
		private static final DynamicCommandExceptionType UNKNOWN_STRUCTURE_EXCEPTION = new DynamicCommandExceptionType(arg -> Component.translatable("command.aoa.structures.invalidStructure", arg));
		private static MinecraftServer server = null;

		public static StructureIdArgument instance() {
			return new StructureIdArgument();
		}

		public static ResourceLocation getStructureId(CommandContext<CommandSourceStack> context, String name) throws CommandSyntaxException {
			return findStructure(context.getArgument(name, ResourceLocation.class));
		}

		private static ResourceLocation findStructure(ResourceLocation path) throws CommandSyntaxException {
			if (server == null)
				server = ServerLifecycleHooks.getCurrentServer();

			StructureTemplateManager templateManager = server.getLevel(Level.OVERWORLD).getStructureManager();
			StructureTemplate template = templateManager.get(path).orElse(null);

			if (template == null)
				throw UNKNOWN_STRUCTURE_EXCEPTION.create(path);

			return path;
		}

		@Override
		public ResourceLocation parse(StringReader reader) throws CommandSyntaxException {
			return ResourceLocation.read(reader);
		}

		@Override
		public Collection<String> getExamples() {
			return EXAMPLES;
		}
	}
}
