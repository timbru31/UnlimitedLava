This is the README of UnlimitedLava
Thanks to loganwm for helping me :)!
Thanks to ferrybig for the awesome fall code!
Thanks for the new check code, Xastabus!
Thanks for using!
For support visit the old forum thread: http://bit.ly/n1Wex2
or the new dev.bukkit.org page: http://bit.ly/pCj7v3

This plugin sends usage statistics! If you wish to disable the usage stats, look at /plugins/PluginMetrics/config.yml!
This plugin is released under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported (CC BY-NC-SA 3.0) license.

Standard config:

# For help please refer to this topic: http://bit.ly/n1Wex2 or http://bit.ly/pCj7v3
configuration:
  # Should permissions be enabled or not?
  permissions: true
  # Should a message displayed, if a player without the permission picks up lava?
  messages: true
  # Enabled the custom furnace, configure the output of a lava bucket below
  furnace: true
  # At which height (block - not line of sight!) is the lava unlimited
  height: 60
sources:
  # Different sources -> See the schematics below
  three: true
  two: true
  other: false
  big: false
  lava_fall: true
  water_fall: false
  plus: true
  T: true
  ring: true
furnace:
  # What item should appear in the furnace if you place a lava bucket as the fuel?
  item: BUCKET
debug: false


Sources:
L are still (full) lava blocks, X is the unlimited lava block!

3x3 (three):

L L L
L X L
L L L

2x2 (two):

X X
X X

Plus:

  L
L X L
  L
  
T Shape:

L X L
  L

or

  L
L X L

or

  L
L X
  L
  
or

L
X L
L

Other:

L X L

or

L
X
L

or

L X
  L

or

X L
L

or

L
X L

or

  L
L X

Big:

You can get infinite lava from pools bigger than 3x3 (4x4, 5x5, etc.) from any block inside!
Border blocks won't work!

Falls:

You can get infinite lava or water from a fall. Just let it flow into a hole an pick it up!

Commands & Permissions (if no permissions system is detected, only OPs are able to use the commands!)
Only bukkit's permissions system is supported!

Node: unlimitedlava.use
Description: Ability to use the unlimited blocks

/unlimitedlava reload
/ulava reload
Node: unlimitedlava.reload
Description: Reloads the config

/unlimitedlava help
/ulava help
Node: unlimitedlava.help
Description: Displays the help

/unlimitedlava status
/ulava status
Node: unlimitedlava.status
Description: Displays the shows the status

/unlimitedlava enable all
/ulava enable all
Node: unlimitedlava.enable.all
Description: Enables all sources

/unlimitedlava enable three
/ulava enable three
Node: unlimitedlava.enable.three
Description: Enables the 3x3 source

/unlimitedlava enable two
/ulava enable two
Node: unlimitedlava.enable.two
Description: Enables the 2x2 source

/unlimitedlava enable other
/ulava enable other
Node: unlimitedlava.enable.other
Description: Enables the other source

/unlimitedlava enable big
/ulava enable big
Node: unlimitedlava.enable.big
Description: Enables the big sources

/unlimitedlava enable plus
/ulava enable plus
Node: unlimitedlava.enable.plus
Description: enables the plus sources

/unlimitedlava enable T
/ulava enable T
Node: unlimitedlava.enable.T
Description: enables the T sources

/unlimitedlava enable permissions
/ulava enable permissions
Node: unlimitedlava.enable.permissions
Description: Enables the permissions! (Only OPs or player with the permission can use a specific command)

/unlimitedlava enable messages
/ulava enable messages
Node: unlimitedlava.enable.messages
Description: Enables the messages!

/unlimitedlava enable furnace
/ulava enable furnace
Node: unlimitedlava.enable.furnace
Description: Enables the (custom) furnace!

/unlimitedlava enable lava_fall
/ulava enable lava_fall
Node: unlimitedlava.enable.lava_fall
Description: Enables the lava_fall

/unlimitedlava enable water_fall
/ulava enable water_fall
Node: unlimitedlava.enable.water_fall
Description: Enables the water_fall

/unlimitedlava disable all
/ulava disable all
Node: unlimitedlava.disable.all
Description: Disables all sources

/unlimitedlava disable three
/ulava disable three
Node: unlimitedlava.disable.three
Description: Disables the 3x3 source

/unlimitedlava disable two 
/ulava disable two
Node: unlimitedlava.disable.two
Description: Disables the 2x2 source

/unlimitedlava disable other
/ulava disable other
Node: unlimitedlava.disable.other
Description: Disables the other source

/unlimitedlava disable big
/ulava disable big
Node: unlimitedlava.disable.big
Description: Disables the big sources

/unlimitedlava disable plus
/ulava disable plus
Node: unlimitedlava.disable.plus
Description: Disables the plus sources

/unlimitedlava disable T
/ulava disable T
Node: unlimitedlava.disable.T
Description: Disables the T sources

/unlimitedlava disable permissions
/ulava disable permissions
Node: unlimitedlava.disable.permissions
Description: Disables the permissions! ALL players can use the commands!

/unlimitedlava disable messages
/ulava disable messages
Node: unlimitedlava.disable.messages
Description: Disables the messages!

/unlimitedlava disable furnace
/ulava disable furnace
Node: unlimitedlava.disable.furnace
Description: Disables the (custom) furnace!

/unlimitedlava disable lava_fall
/ulava disable lava_fall
Node: unlimitedlava.disable.lava_fall
Description: Disables the lava_fall

/unlimitedlava disable water_fall
/ulava disable water_fall
Node: unlimitedlava.disable.water_fall
Description: Disables the water_fall