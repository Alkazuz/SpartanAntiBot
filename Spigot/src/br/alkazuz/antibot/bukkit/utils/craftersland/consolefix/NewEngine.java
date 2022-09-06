package br.alkazuz.antibot.bukkit.utils.craftersland.consolefix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import br.alkazuz.antibot.bukkit.main.SpartanAntiBot;

public class NewEngine
  implements EngineInterface
{
  private SpartanAntiBot csf;
  private int msgHidden = 0;
  
  public int getHiddenMessagesCount()
  {
    return this.msgHidden;
  }
  
  public void addHiddenMsg()
  {
    this.msgHidden += 1;
  }
  
  public void hideConsoleMessages()
  {
    ((Logger)LogManager.getRootLogger()).addFilter(new LogFilter(this.csf));
  }
}
