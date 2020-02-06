package com.mobius.software.telco.protocols.gtp.impl.headers.extention;
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

import com.mobius.software.telco.protocols.gtp.api.headers.ExtentionHeaderType;
import com.mobius.software.telco.protocols.gtp.impl.headers.AbstractExtentionHeader;

public class EmptyNegativeExtentionHeaderImpl extends AbstractExtentionHeader
{
	@Override
	public ExtentionHeaderType getExtentionHeaderType()
	{
		return ExtentionHeaderType.EMPTY_NEGATIVE;
	}

	@Override
	public Integer getLength() 
	{
		return 0;
	}

	@Override
	protected void writeValue(ByteBuf buffer) 
	{		
	}

	@Override
	protected void readValue(ByteBuf buffer,Integer length) 
	{		
	}
}