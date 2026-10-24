package com.deveisia.aoa3.command;

import com.deveisia.aoa3.advent.AdventOfAscension;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.versions.forge.ForgeVersion;

public class VersionCommand implements Command<CommandSourceStack> {
	private static final VersionCommand CMD = new VersionCommand();

	public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher) {
		LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("version").executes(CMD);

		return builder;
	}

	@Override
	public int run(CommandContext<CommandSourceStack> context) {
		AoACommand.feedback(context.getSource(), "Version", "command.aoa.version.desc", AoACommand.CommandFeedbackType.INFO, Component.literal(AdventOfAscension.VERSION).withStyle(ChatFormatting.GREEN), Component.literal(ForgeVersion.getVersion()).withStyle(ChatFormatting.GREEN));

		return 1;
	}
}
