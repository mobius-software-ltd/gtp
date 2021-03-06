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
import io.netty.buffer.ByteBuf;

import com.mobius.software.telco.protocols.gtp.api.exceptions.MissingArgumentException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.AdditionalFlagsForSRVCC;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2ElementType;

public class AdditionalFlagsForSRVCCImpl extends AbstractTLV2 implements AdditionalFlagsForSRVCC 
{
	private Boolean imsCentralizedService;
	private Boolean vSRVCCFlag;
	
	@Override
	public GTP2ElementType getElementType() 
	{
		return GTP2ElementType.ADDITIONAL_FLAGS_FOR_SRVCC;
	}

	@Override
	public Integer getLength() 
	{
		Integer length=1;
		return length;
	}

	@Override
	protected void writeValue(ByteBuf buffer) throws MissingArgumentException 
	{
		byte currByte=0;
		if(imsCentralizedService!=null && imsCentralizedService)
			currByte|=0x01;
		
		if(vSRVCCFlag!=null && vSRVCCFlag)
			currByte|=0x02;
		
		buffer.writeByte(currByte);		
	}

	@Override
	protected void readValue(ByteBuf buffer, Integer length) 
	{
		byte currByte=buffer.readByte();
		if((currByte & 0x01)!=0)
			imsCentralizedService=true;
		else
			imsCentralizedService=false;		
		
		if((currByte & 0x02)!=0)
			vSRVCCFlag=true;
		else
			vSRVCCFlag=false;
	}

	@Override
	public Boolean getIMSCentralizedService() 
	{
		return imsCentralizedService;
	}

	@Override
	public void setIMSCentralizedService(Boolean value) 
	{
		this.imsCentralizedService=value;
	}

	@Override
	public Boolean getVSRVCCFlag() 
	{
		return this.vSRVCCFlag;
	}

	@Override
	public void setVSRVCCFlag(Boolean value) 
	{
		this.vSRVCCFlag=value;
	}
}