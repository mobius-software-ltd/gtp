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

import com.mobius.software.telco.protocols.gtp.api.bcontexts.v2.CreateIndirectDataForwardingTunnelRequestBearerContext;
import com.mobius.software.telco.protocols.gtp.api.exceptions.GTPParseException;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.GTP2MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.IMSI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.MEI;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;
import com.mobius.software.telco.protocols.gtp.api.messages.v2.CreateIndirectDataForwardingTunnelRequest;

public class CreateIndirectDataForwardingTunnelRequestImpl extends AbstractGTP2Message implements CreateIndirectDataForwardingTunnelRequest
{
	IMSI imsi;
	MEI mei;
	Indication indication;
	FTEID senderFTEIDControlPlane;
	List<CreateIndirectDataForwardingTunnelRequestBearerContext> bearerContext;
	Recovery recovery;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public GTP2MessageType getMessageType() 
	{
		return GTP2MessageType.CREATE_INDIRECT_DATA_FORWARDING_TUNNEL_REQUEST;
	}

	@Override
	public void applyTLV(TLV2 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{		
			case IMSI:
				imsi=(IMSI)tlv;
				break;
			case MEI:
				mei=(MEI)tlv;
				break;
			case INDICATION:
				indication=(Indication)tlv;
				break;
			case FTEID:
				switch(tlv.getInstance())
				{
					case 0:
						senderFTEIDControlPlane=(FTEID)tlv;
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case BEARER_CONTEXT:
				switch(tlv.getInstance())
				{
					case 0:
						if(bearerContext==null)
							bearerContext=new ArrayList<CreateIndirectDataForwardingTunnelRequestBearerContext>();
						
						bearerContext.add((CreateIndirectDataForwardingTunnelRequestBearerContext)tlv);
						break;
					default:
						throw new GTPParseException("Invalid TLV instance ID received,type:" + tlv.getElementType() + ",ID:" + tlv.getInstance());
				}
				break;
			case RECOVERY:
				recovery=(Recovery)tlv;
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
		if(imsi!=null)
			output.add(imsi);
		
		if(mei!=null)
			output.add(mei);
		
		if(indication!=null)
			output.add(indication);
		
		if(senderFTEIDControlPlane!=null)
			output.add(senderFTEIDControlPlane);
		
		if(bearerContext==null || bearerContext.size()==0)
			throw new GTPParseException("Bearer Context not set");
		
		output.addAll(bearerContext);
		
		if(recovery!=null)
			output.add(recovery);
		
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
	public IMSI getIMSI() 
	{
		return imsi;
	}

	@Override
	public void setIMSI(IMSI imsi) 
	{
		imsi.setInstance(0);
		this.imsi=imsi;
	}

	@Override
	public MEI getMEI() 
	{
		return mei;
	}

	@Override
	public void setMEI(MEI mei) 
	{
		mei.setInstance(0);
		this.mei=mei;
	}
	
	@Override
	public Indication getIndication() 
	{
		return this.indication;
	}

	@Override
	public void setIndication(Indication indication) 
	{
		indication.setInstance(0);
		this.indication=indication;
	}

	@Override
	public FTEID getSenderFTEIDControlPlane() 
	{
		return this.senderFTEIDControlPlane;
	}

	@Override
	public void setSenderFTEIDControlPlane(FTEID fteid) 
	{
		fteid.setInstance(0);
		this.senderFTEIDControlPlane=fteid;
	}

	@Override
	public List<CreateIndirectDataForwardingTunnelRequestBearerContext> getBearerContext() 
	{
		return bearerContext;
	}

	@Override
	public void setBearerContext(List<CreateIndirectDataForwardingTunnelRequestBearerContext> bearerContext) 
	{
		if(bearerContext!=null)
			for(CreateIndirectDataForwardingTunnelRequestBearerContext curr:bearerContext)				
				curr.setInstance(0);
		
		this.bearerContext=bearerContext;
	}

	@Override
	public Recovery getRecovery() 
	{
		return recovery;
	}

	@Override
	public void setRecovery(Recovery recovery) 
	{
		recovery.setInstance(0);
		this.recovery=recovery;
	}
}