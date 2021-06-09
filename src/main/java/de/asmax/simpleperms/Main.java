package de.asmax.simpleperms;

import de.asmax.simpleperms.commands.*;
import de.asmax.simpleperms.groups.GroupManager;
import de.asmax.simpleperms.permissions.PermissionManager;
import de.asmax.simpleperms.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    private String prefix = "§8[§9SimplePerms§8] §7";
    private String error = "§8[§4ERROR§8] §f";
    private Config config;
    private PermissionManager permissionManager;
    private GroupManager groupManager;

    @Override
    public void onLoad() {
        instance = this;
        config = new Config();
    }

    @Override
    public void onEnable() {
        CommandRegistration();
        ListenerRegistration();

        permissionManager = new PermissionManager();
        groupManager = new GroupManager();
    }

    @Override
    public void onDisable() {
        config.save();
    }

    private void CommandRegistration() {
        getCommand("spadd").setExecutor(new AddPermissionCommand());
        getCommand("spremove").setExecutor(new RemovePermissionCommand());
        getCommand("sptemp").setExecutor(new AddTempPermission());
        getCommand("spget").setExecutor(new GetPermissionCommand());
        getCommand("spgadd").setExecutor(new AddGroupCommand());
        getCommand("spgpadd").setExecutor(new AddGroupPermissionCommand());
    }

    private void ListenerRegistration() {
        PluginManager pluginManager = Bukkit.getPluginManager();
    }

    public static Main getInstance() {
        return instance;
    }

    public Config getConfiguration() {
        return config;
    }

    public String getError() {
        return error;
    }

    public String getPrefix() {
        return prefix;
    }

    public PermissionManager getPermissionManager() {
        return permissionManager;
    }

    public GroupManager getGroupManager() {
        return groupManager;
    }
}
