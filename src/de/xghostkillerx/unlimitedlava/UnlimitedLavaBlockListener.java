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
      if (surounded2(event.getToBlock()))
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
  
  private boolean surounded2(Block block)
  {
    if (block.getTypeId() != 10)
      return false;
    int m = 0;
    if (spawnable2(block, -1, 0))
      m++;
    if (spawnable2(block, 0, 1))
      m++;
    if (spawnable2(block, -1, 1))
      m++;
    return m == 3;
  }

  private boolean spawnable(Block block, int x, int z)
  {
    return ((block.getRelative(x, 0, z).getTypeId() == 10) || (block.getRelative(x, 0, z).getTypeId() == 11)) && (block.getRelative(x, 0, z).getData() == 0);
  }
  
  private boolean spawnable2(Block block, int x, int z)
  {
    return ((block.getRelative(x-1, 0, z+1).getTypeId() == 10) || (block.getRelative(x, 0, z+1).getTypeId() == 11)) && (block.getRelative(x-1, 0, z).getData() == 0);
  }
}
