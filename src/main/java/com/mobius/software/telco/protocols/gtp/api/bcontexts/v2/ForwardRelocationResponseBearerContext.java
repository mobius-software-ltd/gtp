package com.mobius.software.telco.protocols.gtp.api.bcontexts.v2;
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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PacketFlowID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;

public interface ForwardRelocationResponseBearerContext extends TLV2
{
	EPSBearerID getEPSBearerID();
	
	void setEPSBearerID(EPSBearerID bearerID);
	
	PacketFlowID getPacketFlowID();
	
	void setPacketFlowID(PacketFlowID packetFlowID);
	
	FTEID getENodeBDLFTEID();
	
	void setENodeBDLFTEID(FTEID fteid);
	
	FTEID getENodeBULFTEID();
	
	void setENodeBULFTEID(FTEID fteid);
	
	FTEID getSGWDLFTEID();
	
	void setSGWDLFTEID(FTEID fteid);
	
	FTEID getRNCDLFTEID();
	
	void setRNCDLFTEID(FTEID fteid);
	
	FTEID getSGSNDLFTEID();
	
	void setSGSNDLFTEID(FTEID fteid);		
	
	FTEID getSGWULFTEID();	
	
	void setSGWULFTEID(FTEID fteid);
}