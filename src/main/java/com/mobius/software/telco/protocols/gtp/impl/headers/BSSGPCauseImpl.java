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

import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.BSSGPCause;
import com.mobius.software.telco.protocols.gtp.api.headers.BSSGPCauseType;
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;

public class BSSGPCauseImpl extends AbstractTLV implements BSSGPCause 
{
	private BSSGPCauseType causeType;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.BSSGP_CAUSE;
	}

	@Override
	public Integer getLength() 
	{
		return 1;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return true;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(causeType!=null)
			buffer.writeByte(causeType.getValue());
		else
			throw new MissingArgumentException("Cause is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		causeType=BSSGPCauseType.fromInt(buffer.readByte());
	}

	@Override
	public BSSGPCauseType getCause() 
	{
		return causeType;
	}

	@Override
	public void setCause(BSSGPCauseType value) 
	{
		this.causeType=value;				
	}

}
