package com.mobius.software.telco.protocols.gtp.impl.messages.v2;
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
import java.util.ArrayList;
import java.util.List;

import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RIMRoutingAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.S121TransparentContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.RIMInformationTransfer;

public class RIMInformationTransferImpl extends AbstractGTP2Message implements RIMInformationTransfer
{
	private S121TransparentContainer container;
	private RIMRoutingAddress routingAddress;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.RIM_INFORMATION_TRANSFER;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case S_121_TRANSPARENT_CONTAINER:
				container=(S121TransparentContainer)tlv;
				break;
			case RIM_ROUTING_ADDRESS:
				routingAddress=(RIMRoutingAddress)tlv;
				break;
			case PRIVATE_EXTENTION:
				if(privateExtentions==null)
					privateExtentions=new ArrayList<PrivateExtention>();
				
				privateExtentions.add((PrivateExtention)tlv);
				break;
			default:
				if(ignoreUnknown==null || !ignoreUnknown)
					throw new GTPParseException("Unknown TLV received,type:" + tlv.getElementType());
		}
	}

	@Override
	public List<TLV2> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV2> output=new ArrayList<TLV2>();
		if(container==null)
			throw new GTPParseException("Container not set");
		
		output.add(container);
		
		if(routingAddress==null)
			throw new GTPParseException("Routing Address not set");
		
		output.add(routingAddress);
		
		if(privateExtentions!=null)
		{
			for(PrivateExtention curr:privateExtentions)
				output.add(curr);
		}
		
		return output;
	}

	@Override
	public List<PrivateExtention> getPrivateExtentions() 
	{
		return privateExtentions;
	}

	@Override
	public void setPrivateExtentions(List<PrivateExtention> privateExtention) 
	{
		this.privateExtentions=privateExtention;
	}

	@Override
	public S121TransparentContainer getS121TransparentContainer() 
	{
		return container;
	}

	@Override
	public void setS121TransparentContainer(S121TransparentContainer container) 
	{
		this.container=container;
		this.container.setInstance(0);
	}

	@Override
	public RIMRoutingAddress getRIMRoutingAddress() 
	{
		return routingAddress;
	}

	@Override
	public void setRIMRoutingAddress(RIMRoutingAddress routingAddress) 
	{
		this.routingAddress=routingAddress;
		this.routingAddress.setInstance(0);
	}
}