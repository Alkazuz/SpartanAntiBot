package br.alkazuz.antibot.bungee.handler.event;

import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.protocol.packet.Handshake;

/**
 * Event called to represent a player first making their presence and username
 * known.
 */
public class PlayerHandshakeEvent extends Event implements Cancellable
{

	private boolean cancelled;
    /**
     * Connection attempting to login.
     */
    private final PendingConnection connection;
    /**
     * The handshake.
     */
    private final Handshake handshake;

    public PlayerHandshakeEvent(PendingConnection connection, Handshake handshake)
    {
        this.connection = connection;
        this.handshake = handshake;
    }

	public PendingConnection getConnection() {
		return connection;
	}

	public Handshake getHandshake() {
		return handshake;
	}

	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return cancelled;
	}

	@Override
	public void setCancelled(boolean arg0) {
		cancelled = arg0;
	}
}