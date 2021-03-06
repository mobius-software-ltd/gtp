package com.mobius.software.telco.protocols.gtp.impl.messages;
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
import com.mobius.software.telco.protocols.gtp.api.headers.DataRecordPacket;
import com.mobius.software.telco.protocols.gtp.api.headers.MessageType;
import com.mobius.software.telco.protocols.gtp.api.headers.PacketTransferCommand;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.SequenceNumbersOfCancelledPackets;
import com.mobius.software.telco.protocols.gtp.api.headers.SequenceNumbersOfReleasedPackets;
import com.mobius.software.telco.protocols.gtp.api.headers.TLV1;
import com.mobius.software.telco.protocols.gtp.api.messages.DataRecordTransferRequest;

public class DataRecordTransferRequestImpl extends AbstractGTPTagMessage implements DataRecordTransferRequest
{
	private PacketTransferCommand packetTransferCommand;
	private DataRecordPacket dataRecordPacket;
	private SequenceNumbersOfReleasedPackets sequenceNumbersOfReleasedPackets;
	private SequenceNumbersOfCancelledPackets sequenceNumbersOfCancelledPackets;
	private List<PrivateExtention> privateExtentions;
	
	@Override
	public MessageType getMessageType() 
	{
		return MessageType.DATA_RECORD_TRANSFER_REQUEST;
	}

	@Override
	public void applyTLV(TLV1 tlv,Boolean ignoreUnknown) throws GTPParseException 
	{
		switch(tlv.getElementType())
		{
			case PACKET_TRANSFER_COMMAND:
				packetTransferCommand=(PacketTransferCommand)tlv;
				break;
			case DATA_RECORD_PACKET:
				dataRecordPacket=(DataRecordPacket)tlv;
				break;
			case SEQUENCE_NUMBER_OF_RELEASED_PACKETS:
				sequenceNumbersOfReleasedPackets=(SequenceNumbersOfReleasedPackets)tlv;
				break;
			case SEQUENCE_NUMBER_OF_CANCELLED_PACKETS:
				sequenceNumbersOfCancelledPackets=(SequenceNumbersOfCancelledPackets)tlv;
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
	public List<TLV1> getTLVs() throws GTPParseException 
	{
		ArrayList<TLV1> output=new ArrayList<TLV1>();
		if(packetTransferCommand==null)
			throw new GTPParseException("command is missing");
		
		output.add(packetTransferCommand);
		
		if(dataRecordPacket!=null)
			output.add(packetTransferCommand);
		
		if(sequenceNumbersOfReleasedPackets!=null)
			output.add(sequenceNumbersOfReleasedPackets);
		
		if(sequenceNumbersOfCancelledPackets!=null)
			output.add(sequenceNumbersOfCancelledPackets);
		
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
	public PacketTransferCommand getPacketTransferCommand() 
	{
		return packetTransferCommand;
	}

	@Override
	public void setPacketTransferCommand(PacketTransferCommand command) 
	{
		this.packetTransferCommand=command;
	}

	@Override
	public DataRecordPacket getDataRecordPacket() 
	{
		return this.dataRecordPacket;
	}

	@Override
	public void setDataRecordPacket(DataRecordPacket packet) 
	{
		this.dataRecordPacket=packet;
	}

	@Override
	public SequenceNumbersOfReleasedPackets getSequenceNumbersOfReleasedPackets() 
	{
		return this.sequenceNumbersOfReleasedPackets;
	}

	@Override
	public void setSequenceNumbersOfReleasedPackets(SequenceNumbersOfReleasedPackets sequence) 
	{
		this.sequenceNumbersOfReleasedPackets=sequence;
	}

	@Override
	public SequenceNumbersOfCancelledPackets getSequenceNumbersOfCancelledPackets() 
	{
		return this.sequenceNumbersOfCancelledPackets;
	}

	@Override
	public void setSequenceNumbersOfCancelledPackets(SequenceNumbersOfCancelledPackets sequence) 
	{
		this.sequenceNumbersOfCancelledPackets=sequence;
	}
}