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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import com.mobius.software.telco.protocols.gtp.api.GTPServer;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.messages.GTPMessage;
import com.mobius.software.telco.protocols.gtp.api.messages.GTPTagMessage;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class GTPServerImpl implements GTPServer
{
	private Logger logger=Logger.getLogger(GTPServerImpl.class);
	
	private EventLoopGroup group;
	private Bootstrap connectionlessBootstrap;
	
	private List<Channel> serverChannels=new ArrayList<Channel>();
	private AtomicInteger portNumber=new AtomicInteger(0);
	
	@Override
	public void start(String host,Integer port,Integer poolSize,Boolean useEpoll, GTPPacketHandler handler) 
	{
		if(useEpoll)
			group = new EpollEventLoopGroup(poolSize);
		else
			group = new NioEventLoopGroup(poolSize);
		
		connectionlessBootstrap=new Bootstrap();
		connectionlessBootstrap.option(ChannelOption.SO_SNDBUF, 256*1024);
		connectionlessBootstrap.option(ChannelOption.SO_RCVBUF, 256*1024);
		if(useEpoll)
		{
			connectionlessBootstrap.option(EpollChannelOption.SO_REUSEPORT, true);
			connectionlessBootstrap.option(EpollChannelOption.IP_RECVORIGDSTADDR, true);
			connectionlessBootstrap.option(EpollChannelOption.IP_FREEBIND, true);
			connectionlessBootstrap.channel(EpollDatagramChannel.class);
		}		
		else
			connectionlessBootstrap.channel(NioDatagramChannel.class);
				
		connectionlessBootstrap.group(group);
		
		ChannelFuture future;
		Integer maxPorts=poolSize;
		if(!useEpoll)
			maxPorts=1;
		
		connectionlessBootstrap.handler(handler);					
				
		for(int i = 0; i < maxPorts; ++i)
		{
			future=connectionlessBootstrap.bind(new InetSocketAddress(host, port));
			future.awaitUninterruptibly();
			if(!future.isSuccess())
				logger.error("CHANNEL NOT CONNECTED:" + future.cause());
			
			serverChannels.add(future.channel());
		}
		
		logger.info("GTP Server started");	
	}

	@Override
	public void sendMessage(GTPMessage message,InetSocketAddress address) throws GTPParseException 
	{
		ByteBuf data=Unpooled.buffer();		
		message.encode(data);
		serverChannels.get(portNumber.getAndIncrement()%serverChannels.size()).writeAndFlush(new DatagramPacket(data, address));
	}

	@Override
	public void sendMessage(GTPTagMessage message,InetSocketAddress address) throws GTPParseException 
	{
		ByteBuf data=Unpooled.buffer();		
		message.encode(data);
		serverChannels.get(portNumber.getAndIncrement()%serverChannels.size()).writeAndFlush(new DatagramPacket(data, address));
	}

	@Override
	public void stop() 
	{
		logger.info("Closing GTP Server");
		for(int i=0;i<serverChannels.size();i++)
		{
			ChannelFuture channelFuture=serverChannels.get(i).close();
			channelFuture.awaitUninterruptibly();
		}
		
		group.shutdownGracefully();
		logger.info("GTP Server stopped");
	}
}