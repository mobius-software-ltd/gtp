package com.mobius.software.telco.protocols.gtp.impl.headers.v2;
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
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RemoteUEIPInformation;

public class RemoteUEIPInformationImpl extends AbstractTLV2 implements RemoteUEIPInformation 
{
	private Inet4Address ipv4Address;
	private Integer ipv4Port;
	private Inet6Address ipv6AddressPrefix;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.REMOTE_UE_IP_INFORMATION;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=6;
		if(ipv6AddressPrefix!=null)
			length=8;
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(ipv4Address==null && ipv6AddressPrefix==null)
			throw new MissingArgumentException("IP Address is not set");
		
		if(ipv4Address!=null && ipv4Port==null)
			throw new MissingArgumentException("IP Port is not set");
		
		if(ipv4Address!=null)
		{
			buffer.writeBytes(ipv4Address.getAddress());
			buffer.writeShort(ipv4Port.shortValue());
		}
		else if(ipv6AddressPrefix!=null)
		{
			byte[] data=ipv4Address.getAddress();
			buffer.writeBytes(data,0,8);
		}
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		try
		{
			if(length==6)
			{
				byte[] data=new byte[4];
				buffer.readBytes(data);
				ipv4Address=(Inet4Address)InetAddress.getByAddress(data);
				ipv4Port=buffer.readUnsignedShort();
			}
			
			if(length==8)
			{
				byte[] data=new byte[16];
				buffer.readBytes(data,0,8);
				ipv6AddressPrefix=(Inet6Address)InetAddress.getByAddress(data);		
			}
		}
		catch(UnknownHostException ex)
		{
			throw new GTPParseException(ex.getMessage());
		}
	}

	@Override
	public Inet4Address getV4Address() 
	{
		return this.ipv4Address;
	}

	@Override
	public Inet6Address getV6AddressPrefix() 
	{
		return this.ipv6AddressPrefix;
	}

	@Override
	public void setV4Address(Inet4Address address) 
	{
		this.ipv4Address=address;
	}

	@Override
	public void setV6AddressPrefix(Inet6Address ipv6AddressPrefix) 
	{
		this.ipv6AddressPrefix=ipv6AddressPrefix;
	}

	@Override
	public Integer getV4AddressPort() 
	{
		return ipv4Port;
	}

	@Override
	public void setV4AddressPort(Integer value) 
	{
		this.ipv4Port=value;
	}
}