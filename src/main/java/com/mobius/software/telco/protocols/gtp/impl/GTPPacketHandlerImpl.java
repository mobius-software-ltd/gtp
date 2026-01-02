package com.mobius.software.telco.protocols.gtp.impl;
/*Mobius Software LTD
Copyright 2019, Mobius Software LTD and individual contributors
by the @authors tag.

This program is free software: you can redistribute it and/or modify
under the terms of the GNU Affero General Public License as
published by the Free Software Foundation; either version 3 of
the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>*/
import java.net.InetSocketAddress;

import org.apache.log4j.Logger;

import com.mobius.software.telco.protocols.gtp.api.GTPListener;
import com.mobius.software.telco.protocols.gtp.api.messages.GTPMessage;
import com.mobius.software.telco.protocols.gtp.api.messages.GTPTagMessage;
import com.mobius.software.telco.protocols.gtp.api.messages.GenericGTPMessage;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.GTP2Message;
import com.mobius.software.telco.protocols.gtp.impl.messages.MessageFactory;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.ReferenceCountUtil;

@Sharable
public class GTPPacketHandlerImpl extends SimpleChannelInboundHandler<DatagramPacket> implements GTPPacketHandler
{
	private final static Logger logger = Logger.getLogger(GTPPacketHandlerImpl.class);  
	
	private GTPListener listener;
	private Boolean ignoreUnknown=false;
	
	public GTPPacketHandlerImpl()
	{		
	}
	
	@Override
	public void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) 
	{
		try
		{			
			try
			{
				InetSocketAddress address = packet.sender();
				GenericGTPMessage message=MessageFactory.decode(packet.content(),ignoreUnknown);
				
				if(listener!=null)
				{
					if(message instanceof GTPMessage)
						listener.onGTPMessage((GTPMessage)message, address);
					else if(message instanceof GTPTagMessage)
						listener.onGTPTagMessage((GTPTagMessage)message, address);
					else if(message instanceof GTP2Message)
						listener.onGTP2Message((GTP2Message)message, address);
					else 
						logger.warn("Received message with invalid format" + message);
				}
				else
					logger.warn("Received incoming message,but no listener is set yet");
				
			}
			catch(Exception ex)
			{
				logger.error("An error occured while processing incoming packet," + ex.getMessage(),ex);
			}
		}
		finally
		{
			ReferenceCountUtil.release(packet);			
		}
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) 
	{
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) 
	{
		
	}

	@Override
	public void setGTPListener(GTPListener listener) 
	{
		this.listener = listener;
	}

	@Override
	public void setIgnoreUnknown(Boolean ignoreUnknown) 
	{
		this.ignoreUnknown = ignoreUnknown;
	}
}