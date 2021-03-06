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
import java.net.InetAddress;
import java.net.UnknownHostException;

import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IPV4ConfigurationParameters;

public class IPV4ConfigurationParametersImpl extends AbstractTLV2 implements IPV4ConfigurationParameters 
{
	private Inet4Address ipv4Address;
	private Integer subnetPrefixLength;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.IPV4_CONFIGURATION_PARAMETERS;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=5;		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(ipv4Address==null)
			throw new MissingArgumentException("IP Address is not set");
		
		if(subnetPrefixLength==null)
			throw new MissingArgumentException("Subnet prefix length is not set");
		
		buffer.writeByte(subnetPrefixLength);
		buffer.writeBytes(ipv4Address.getAddress());					
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) throws GTPParseException 
	{
		subnetPrefixLength=buffer.readByte() & 0x0FF;
		
		try
		{
			byte[] data=new byte[4];
			buffer.readBytes(data);
			ipv4Address=(Inet4Address)InetAddress.getByAddress(data);
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
	public Integer getSubnetPrefixLength() 
	{
		return this.subnetPrefixLength;
	}

	@Override
	public void setV4Address(Inet4Address address) 
	{
		this.ipv4Address=address;
	}

	@Override
	public void setSubnetPrefixLength(Integer subnetPrefixLength) 
	{
		this.subnetPrefixLength=subnetPrefixLength;
	}
}