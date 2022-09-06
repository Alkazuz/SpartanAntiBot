package br.alkazuz.antibot.bungee.handler;

import java.net.InetSocketAddress;

import br.alkazuz.antibot.bungee.handler.event.PlayerHandshakeEvent;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.config.ListenerInfo;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.connection.InitialHandler;
import net.md_5.bungee.protocol.packet.Handshake;

public class HandlerLogin extends InitialHandler implements PendingConnection{

	public BungeeCord bungee;
	public ListenerInfo listener;
	public HandlerLogin(BungeeCord bungee, ListenerInfo listener) {
		
		super(bungee, listener);
		this.bungee = bungee;
		this.listener = listener;
	}
	
	@Override
    public void handle(Handshake handshake) throws Exception
    {
        
		PlayerHandshakeEvent event = new PlayerHandshakeEvent(this, handshake );
		
		if ( handshake.getHost().contains( "\0" ) )
        {
            String[] split = handshake.getHost().split( "\0", 2 );
            handshake.setHost( split[0] );
        }

        // SRV records can end with a . depending on DNS / client.
        if ( handshake.getHost().endsWith( "." ) )
        {
            handshake.setHost( handshake.getHost().substring( 0, handshake.getHost().length() - 1 ) );
        }
		
		InetSocketAddress.createUnresolved( handshake.getHost(), handshake.getPort() );
		
		BungeeCord.getInstance().getPluginManager().callEvent(event);
		if(event.isCancelled()) {
			return;
		}
		super.handle(handshake);
    }

}
