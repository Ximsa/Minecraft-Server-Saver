package com.ximsa.serversaver;

import static com.ximsa.serversaver.MyMod.MODID;

import net.minecraft.server.MinecraftServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import me.guichaguri.tickratechanger.api.TickrateAPI;

public class EventHandler {

    public final Logger LOG = LogManager.getLogger(MODID);

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        LOG.debug("onPlayerLogin");
        var currentTickrate = TickrateAPI.getServerTickrate();
        if (currentTickrate != Config.activeTickrate) {
            LOG.debug("First player login");
            setActive();
        }
    }

    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        LOG.debug("onPlayerLogout");
        MinecraftServer server = MinecraftServer.getServer();
        if (server.getCurrentPlayerCount() == 1) {
            LOG.debug("{} is the last Player logging out, pausing", event.player.getDisplayName());
            setIdle();
        }
    }

    private void setIdle() {
        TickrateAPI.changeServerTickrate(Config.idleTickrate);
    }

    private void setActive() {
        TickrateAPI.changeServerTickrate(Config.activeTickrate);
    }
}
