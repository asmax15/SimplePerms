package de.asmax.simpleperms.commands;

import de.asmax.simpleperms.Main;
import de.asmax.simpleperms.groups.GroupManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveGroupPermissionCommand implements CommandExecutor {

    String prefix = Main.getInstance().getPrefix();
    String error = Main.getInstance().getError();
    private GroupManager groupManager = Main.getInstance().getGroupManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(error + "Just a Player can execute this command.");
            return true;
        }
        Player player = (Player)sender;

        if(!player.hasPermission("simpleperms.groups.permission.remove")) {
            player.sendMessage(error + "You don't have the Permission to do that.");
            return true;
        }

        if(args.length != 2) {
            player.sendMessage(error + "Please use: §9/spgpremove <group> <permission>");
            return true;
        }

        String name = args[0];
        String permission = args[1];

        if(!groupManager.getGroup(name)) {
            player.sendMessage(error + "The group §4" + name + " §fdoesn't exists.");
            return true;
        }

        if(!GroupManager.getPermission(name, permission)) {
            player.sendMessage(error + "The Group: §9" + name + " §fhas not the Permission: §9" + permission);
            return true;
        }

        GroupManager.removePermission(name, permission);
        player.sendMessage(prefix + "§aYou have successfully §cremoved §athe permission: §6" + permission + " §afrom the Group: §6" + name);
        return true;
    }
}
