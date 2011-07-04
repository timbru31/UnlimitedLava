package de.xghostkillerx.unlimitedlava;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockListener;

public class UnlimitedLavaBlockListener extends BlockListener
{
  public static UnlimitedLava plugin;

  public UnlimitedLavaBlockListener(UnlimitedLava instance)
  {
    plugin = instance;
  }

  public void onBlockFromTo(BlockFromToEvent event)
  {
    if (event.getBlock().getTypeId() == 11)
    {
      if (surounded(event.getToBlock()))
      {
        event.getToBlock().setTypeId(11);
      }
    }
  }

  private boolean surounded(Block block)
  {
    if (block.getTypeId() != 10)
      return false;
    int n = 0;

    if (spawnable(block, 1, 0))
      n++;
    if (spawnable(block, -1, 0))
      n++;
    if (spawnable(block, 0, 1))
      n++;
    if (spawnable(block, 0, -1))
      n++;
    if (spawnable(block, 1, -1))
      n++;
    if (spawnable(block, 1, 1))
      n++;
    if (spawnable(block, -1, -1))
      n++;
    if (spawnable(block, -1, 1))
      n++;
    return n == 8;
  }

  private boolean spawnable(Block block, int x, int z)
  {
    return ((block.getRelative(x, 0, z).getTypeId() == 10) || (block.getRelative(x, 0, z).getTypeId() == 11)) && (block.getRelative(x, 0, z).getData() == 0);
  }
}