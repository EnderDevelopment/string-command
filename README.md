# String Replenisher Minecraft Plugin

String Replenisher is a Minecraft plugin that adds the `/string` command. When a player uses it, the plugin starts a repeating string reward with a 30-second cooldown interval, giving the player one string every 30 seconds while they remain online.

## Install

1. Build the plugin with Java 21 and Maven:
   ```text
   mvn package
   ```
2. Copy the generated `plugin.jar` file from the `target` directory into your Paper server's `plugins` directory.
3. Start or restart the server.
4. Confirm that the `StringReplenisher` plugin loads successfully.

## Use

1. Join the Minecraft server as a player.
2. Run:
   ```text
   /string
   ```
3. The plugin starts the replenishment timer.
4. Every 30 seconds, the player receives one string.
5. If the inventory is full, the string is dropped naturally at the player's location.
6. Running `/string` again while replenishment is active does not create another timer.
7. The replenishment stops when the player goes offline or when the plugin is disabled.

The command requires the `string.use` permission, which is available to players by default.

---

More Minecraft plugin projects by EnderDevelopment: https://github.com/EnderDevelopment

Project page: https://dash.enderdevelopment.com/community/0fe1f201-20d7-4517-9e99-d97bdbada9c4?utm_source=github&utm_medium=readme&utm_campaign=auto_publish&utm_content=0fe1f201-20d7-4517-9e99-d97bdbada9c4
