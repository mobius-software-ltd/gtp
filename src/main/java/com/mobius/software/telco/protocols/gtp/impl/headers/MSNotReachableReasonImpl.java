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
import com.mobius.software.telco.protocols.gtp.api.headers.ElementType;
import com.mobius.software.telco.protocols.gtp.api.headers.MSNotReachableReason;
import com.mobius.software.telco.protocols.gtp.api.headers.ReasonForAbsence;

public class MSNotReachableReasonImpl extends AbstractTLV implements MSNotReachableReason 
{
	private ReasonForAbsence reason;
	
	@Override
	public ElementType getElementType() 
	{
		return ElementType.MS_NOT_REACHABLE_REASON;
	}

	@Override
	public Integer getLength() 
	{
		return 1;
	}

	@Override
	public Boolean lengthPresent() 
	{
		return false;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		if(reason!=null)
			buffer.writeByte(reason.getValue() & 0x0FF);
		else
			throw new MissingArgumentException("Reason is not set");
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		reason=ReasonForAbsence.fromInt(buffer.readByte() & 0x0FF);
	}

	@Override
	public ReasonForAbsence getReason() 
	{
		return reason;
	}

	@Override
	public void setReason(ReasonForAbsence reason) 
	{
		this.reason=reason;
	}
}