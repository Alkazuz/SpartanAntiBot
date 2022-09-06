package br.alkazuz.antibot.bukkit.utils.craftersland.consolefix;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;

import br.alkazuz.antibot.bukkit.config.Config;

public class OldEngine
  implements EngineInterface
{
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
    ((Logger)LogManager.getRootLogger()).addFilter(new Filter()
    {
      public Filter.Result filter(LogEvent event)
      {
        if (!Config.getConfig().getStringList("MessagesOcultas").isEmpty()) {
          for (String s : Config.getConfig().getStringList("MessagesOcultas")) {
            if (event.getMessage().toString().contains(s))
            {
              OldEngine.this.msgHidden += 1;
              return Filter.Result.DENY;
            }
          }
        }
        return null;
      }
      
      public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String arg3, Object... arg4)
      {
        return null;
      }
      
      public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, Object arg3, Throwable arg4)
      {
        return null;
      }
      
      public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, Message arg3, Throwable arg4)
      {
        return null;
      }
      
      public Filter.Result getOnMatch()
      {
        return null;
      }
      
      public Filter.Result getOnMismatch()
      {
        return null;
      }
    });
  }
}
