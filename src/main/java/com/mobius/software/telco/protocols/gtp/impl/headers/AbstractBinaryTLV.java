package com.mobius.software.telco.protocols.gtp.impl.headers;
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
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;

public abstract class AbstractBinaryTLV extends AbstractTLV
{
	public abstract ByteBuf getValue();
	
	public abstract void setValue(ByteBuf value);
	
	@Override
	public abstract ElementType getElementType();

	@Override
	public abstract Boolean lengthPresent();

	@Override
	public Integer getLength() 
	{
		Integer length=0;
		ByteBuf value=getValue();
		if(value!=null)
			length=value.readableBytes();
		
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(getValue()!=null)
			buffer.writeBytes(getValue());
		else
			throw new MissingArgumentException("Value not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		if(length>0)
		{
			setValue(buffer.slice(buffer.readerIndex(), length));
			buffer.skipBytes(length);
		}
		else
			setValue(Unpooled.EMPTY_BUFFER);
	}
}