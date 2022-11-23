package com.tuxpenguins;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

public final class HP extends JavaPlugin implements Listener {


    public static Scoreboard globalBoard;
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        globalBoard = Bukkit.getScoreboardManager().getNewScoreboard();
        globalBoard.registerNewObjective("HEALTH", Criteria.HEALTH, "HP");
        globalBoard.getObjective("HEALTH").setDisplaySlot(DisplaySlot.BELOW_NAME);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (event.getEntityType() == EntityType.PLAYER)
        {
            Player player = (Player) event.getEntity();
            globalBoard.getObjective("HEALTH").getScore(player.getDisplayName()).setScore((int) player.getHealth());
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()){
                player.setScoreboard(globalBoard);
        }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        int health = (int) player.getHealth();
        globalBoard.getObjective("HEALTH").getScore(player.getDisplayName()).setScore(health);
        for (Player onlinePlayer : Bukkit.getOnlinePlayers())
        {
            onlinePlayer.setScoreboard(globalBoard);
        }
    }
}
