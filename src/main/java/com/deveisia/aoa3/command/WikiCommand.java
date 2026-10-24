package com.deveisia.aoa3.command;

import com.deveisia.aoa3.common.packet.AoAPackets;
import com.deveisia.aoa3.common.packet.packets.WikiSearchPacket;
import com.deveisia.aoa3.util.StringUtil;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WikiCommand implements Command<CommandSourceStack> {
	private static final WikiCommand CMD = new WikiCommand();

	public static ArgumentBuilder<CommandSourceStack, ?> register(CommandDispatcher<CommandSourceStack> dispatcher) {
		LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("wiki").executes(CMD);

		builder.then(Commands.argument("search", StringArgumentType.greedyString()).requires(command -> command.hasPermission(0))
				.executes(WikiCommand::sendPacket));

		return builder;
	}

	@OnlyIn(Dist.CLIENT)
	public static void handleSearchRequest(String search) {
		String baseUrl = "?";

		if (search.equalsIgnoreCase("random"))
			search = "Special:Random";

		try {
			String testRootUrl = "https://adventofascension.gamepedia.com/" + StringUtil.toTitleCase(URLEncoder.encode(search, "UTF-8").replace("+", " ")).replace(" ", "_");
			baseUrl = "https://adventofascension.gamepedia.com/index.php?search=" + URLEncoder.encode(search, "UTF-8") + "&title=Special:Search&go=Go";

			HttpURLConnection connection = (HttpURLConnection)new URL(testRootUrl).openConnection();

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK)
				baseUrl = testRootUrl;
		}
		catch (UnsupportedEncodingException | MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException ex) {
			Minecraft.getInstance().player.sendSystemMessage(AoACommand.getCmdPrefix("Wiki").append(Component.translatable("command.aoa.wiki.connectionFail").setStyle(Style.EMPTY.applyFormat(AoACommand.CommandFeedbackType.ERROR.getColour()))));
		}

		if (search.equals("Special:Random"))
			search = "???";

		MutableComponent responseComponent = getComponentFromKeys("command.aoa.wiki.response", baseUrl, StringUtil.toSentenceCase(search));
		Minecraft.getInstance().player.sendSystemMessage(AoACommand.getCmdPrefix("Wiki").append(responseComponent != null ? responseComponent.setStyle(Style.EMPTY.applyFormat(ChatFormatting.GRAY)) : Component.translatable("command.aoawiki.response", baseUrl)));
	}

	@Nullable
	private static MutableComponent getComponentFromKeys(String langKey, String url, String pageTitle) {
		return Component.Serializer.fromJson("{\"translate\":\"" + langKey + "\",\"with\":[{\"text\":\"" + pageTitle + "\",\"color\":\"red\",\"underlined\":true,\"clickEvent\":{\"action\":\"open_url\",\"value\":\"" + url + "\"}}]}");
	}

	private static int sendPacket(CommandContext<CommandSourceStack> cmd) throws CommandSyntaxException {
		AoAPackets.messagePlayer((ServerPlayer)cmd.getSource().getEntityOrException(), new WikiSearchPacket(StringArgumentType.getString(cmd, "search")));

		return 1;
	}

	@Override
	public int run(CommandContext<CommandSourceStack> context) {
		AoACommand.feedback(context.getSource(), "Wiki", "command.aoa.wiki.desc", AoACommand.CommandFeedbackType.INFO);

		return 1;
	}
}
