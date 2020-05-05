package com.gearsetup;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import lombok.Getter;

import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;

//import net.runelite.client.util.HotkeyListener;
import net.runelite.client.util.ImageUtil;

import com.gearsetup.ui.GearSetupPanel;

@Slf4j
@PluginDescriptor(
	name = "Gear Setup"
)
public class GearSetupPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientToolbar clientToolbar;

	private NavigationButton navButton;

	@Inject
	@Getter
	private GearSetupConfig config;

	private GearSetupPanel panel;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Gear setup started!");

		panel = new GearSetupPanel(this);

		navButton = NavigationButton.builder()
				.tooltip("Gear Setup")
				.icon(ImageUtil.getResourceStreamFromClass(getClass(), "/icon.png"))
				.priority(70)
				.panel(panel)
				.build();

		clientToolbar.addNavigation(navButton);

	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Gear setup stopped!");
		clientToolbar.removeNavigation(navButton);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
	}

	@Provides
	GearSetupConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(GearSetupConfig.class);
	}
}
