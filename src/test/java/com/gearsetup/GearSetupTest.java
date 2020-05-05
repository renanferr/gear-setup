package com.gearsetup;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class GearSetupTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(GearSetupPlugin.class);
		RuneLite.main(args);
	}
}