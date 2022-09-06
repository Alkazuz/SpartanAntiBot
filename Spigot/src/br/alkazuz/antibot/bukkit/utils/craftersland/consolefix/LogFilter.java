package br.alkazuz.antibot.bukkit.utils.craftersland.consolefix;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;

import br.alkazuz.antibot.bukkit.config.Config;
import br.alkazuz.antibot.bukkit.main.SpartanAntiBot;
import br.alkazuz.antibot.utils.Utils;

public class LogFilter
  implements Filter
{
  private SpartanAntiBot pl;
  
  public LogFilter(SpartanAntiBot plugin)
  {
    this.pl = plugin;
  }
  
  public Filter.Result checkMessage(String message)
  {
	 
	if(message.contains("! For help, type")){
		Utils.loaded = true;
		SpartanAntiBot.theInstance().sendConcoleMessage("Servidor está completamente ligado, aceitando conexões!");
	}
    if (!Config.getConfig().getStringList("MessagesOcultas").isEmpty()) {
      for (String s : Config.getConfig().getStringList("MessagesOcultas")) {
        if (message.contains(s))
        {
          this.pl.getEngine().addHiddenMsg();
          return Filter.Result.DENY;
        }
      }
    }
    return Filter.Result.NEUTRAL;
  }
  
  public void initialize() {}
  
  public boolean isStarted()
  {
    return true;
  }
  
  public boolean isStopped()
  {
    return false;
  }
  
  public void start() {}
  
  public void stop() {}
  
  public Filter.Result filter(LogEvent event)
  {
    return checkMessage(event.getMessage().getFormattedMessage());
  }
  
  public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object... arg4)
  {
    return checkMessage(message);
  }
  
  public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4)
  {
    return checkMessage(message);
  }
  
  public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, Object message, Throwable arg4)
  {
    return checkMessage(message.toString());
  }
  
  public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, Message message, Throwable arg4)
  {
    return checkMessage(message.getFormattedMessage());
  }
  
  public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4, Object arg5)
  {
    return checkMessage(message);
  }
  
  public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4, Object arg5, Object arg6)
  {
    return checkMessage(message);
  }
  
  public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4, Object arg5, Object arg6, Object arg7)
  {
    return checkMessage(message);
  }
  
  public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8)
  {
    return checkMessage(message);
  }
  
  public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9)
  {
    return checkMessage(message);
  }
  
  public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10)
  {
    return checkMessage(message);
  }
  
  public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10, Object arg11)
  {
    return checkMessage(message);
  }
  
  public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10, Object arg11, Object arg12)
  {
    return checkMessage(message);
  }
  
  public Filter.Result filter(Logger arg0, Level arg1, Marker arg2, String message, Object arg4, Object arg5, Object arg6, Object arg7, Object arg8, Object arg9, Object arg10, Object arg11, Object arg12, Object arg13)
  {
    return checkMessage(message);
  }
  
  public Filter.Result getOnMatch()
  {
    return Filter.Result.NEUTRAL;
  }
  
  public Filter.Result getOnMismatch()
  {
    return Filter.Result.NEUTRAL;
  }
}
