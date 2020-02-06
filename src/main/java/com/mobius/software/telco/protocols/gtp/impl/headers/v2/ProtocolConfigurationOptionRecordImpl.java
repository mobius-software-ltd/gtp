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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Protocol;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProtocolConfigurationOptionRecord;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class ProtocolConfigurationOptionRecordImpl implements ProtocolConfigurationOptionRecord {

	private Protocol protocol;
	private ByteBuf data;
	
	@Override
	public Protocol getProtocol() 
	{
		return protocol;
	}

	@Override
	public void setProtocol(Protocol value) 
	{
		this.protocol=value;
	}

	@Override
	public Integer getLength() 
	{
		if(data==null)
			return 0;
		
		return data.readableBytes();
	}

	@Override
	public ByteBuf getProtocolData() 
	{
		return Unpooled.wrappedBuffer(data);
	}

	@Override
	public void setProtocolData(ByteBuf data) 
	{
		this.data=Unpooled.copiedBuffer(data);
	}
}