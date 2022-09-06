package br.alkazuz.antibot.bungee.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.haproxy.HAProxyMessageDecoder;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ListenerInfo;
import net.md_5.bungee.netty.HandlerBoss;
import net.md_5.bungee.netty.PipelineUtils;
import net.md_5.bungee.protocol.KickStringWriter;
import net.md_5.bungee.protocol.LegacyDecoder;
import net.md_5.bungee.protocol.MinecraftDecoder;
import net.md_5.bungee.protocol.MinecraftEncoder;
import net.md_5.bungee.protocol.Protocol;
import java.lang.reflect.*;

public class Pipeline{
	
	public Pipeline() {
		try {
			
			Field[] declaredFields;
	        for (int length = (declaredFields = PipelineUtils.class.getDeclaredFields()).length, i = 0; i < length; ++i) {
	          final Field field = declaredFields[i];
	         System.out.println(field.getName());
	          if (field != null && field.getName().equalsIgnoreCase("SERVER_CHILD")) {
	        	  field.setAccessible(true);
	        	  
	        	  Field modifiersField = Field.class.getDeclaredField("modifiers");
	      		modifiersField.setAccessible(true);
	      		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
	        	  field.set(PipelineUtils.class, SERVER_CHILD);
	        	  System.out.println(PipelineUtils.SERVER_CHILD.getClass().getName());
	          }
	        }
			
/*		Field field = PipelineUtils.class.getField("SERVER_CHILD");
		field.setAccessible(true);

	      Field modifiersField = Field.class.getDeclaredField("modifiers");
	      modifiersField.setAccessible(true);
	      modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

	      field.set(null, SERVER_CHILD);*/
		}catch (Exception e) {e.printStackTrace();}
	}
	
	public static ChannelInitializer<Channel> SERVER_CHILD = new ChannelInitializer<Channel>()
    {
        @Override
        protected void initChannel(Channel ch) throws Exception
        {
            ListenerInfo listener = ch.attr( net.md_5.bungee.netty.PipelineUtils.LISTENER ).get();
            net.md_5.bungee.netty.PipelineUtils.BASE.initChannel( ch );
            ch.pipeline().addBefore( net.md_5.bungee.netty.PipelineUtils.FRAME_DECODER, net.md_5.bungee.netty.PipelineUtils.LEGACY_DECODER, new LegacyDecoder() );
            ch.pipeline().addAfter( net.md_5.bungee.netty.PipelineUtils.FRAME_DECODER, net.md_5.bungee.netty.PipelineUtils.PACKET_DECODER, new MinecraftDecoder( Protocol.HANDSHAKE, true, ProxyServer.getInstance().getProtocolVersion() ) );
            ch.pipeline().addAfter( net.md_5.bungee.netty.PipelineUtils.FRAME_PREPENDER, net.md_5.bungee.netty.PipelineUtils.PACKET_ENCODER, new MinecraftEncoder( Protocol.HANDSHAKE, true, ProxyServer.getInstance().getProtocolVersion() ) );
            ch.pipeline().addBefore( net.md_5.bungee.netty.PipelineUtils.FRAME_PREPENDER, net.md_5.bungee.netty.PipelineUtils.LEGACY_KICKER, new KickStringWriter() );
            ch.pipeline().get( HandlerBoss.class ).setHandler( new HandlerLogin( BungeeCord.getInstance(), listener ) );

            if ( listener.isProxyProtocol() )
            {
                ch.pipeline().addFirst( new HAProxyMessageDecoder() );
            }
        }
    };

}
