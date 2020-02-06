package com.mobius.software.telco.protocols.gtp.api.messages.v2;
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
import java.util.List;

import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ExtendedProtocolConfigurationOptions;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FContainer;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FlowQOS;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.Indication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.OverloadControlInformation;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProcedureTransactionID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.RatType;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.ServingNetwork;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.SignalingPriorityIndication;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TrafficAggregationDescriptor;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.UserLocationInformation;

public interface BearerResourceCommand extends GTP2Message
{
	EPSBearerID getLinkedEPSBearedID();
	
	void setLinkedEPSBearedID(EPSBearerID epsBearerID);
	
	ProcedureTransactionID getProcedureTransactionID();
	
	void setProcedureTransactionID(ProcedureTransactionID procedureTransactionID);
	
	FlowQOS getFlowQOS();
	
	void setFlowQOS(FlowQOS flowQOS);
	
	TrafficAggregationDescriptor getTrafficAggregationDescriptor();
	
	void setTrafficAggregationDescriptor(TrafficAggregationDescriptor trafficAggregationDescriptor);
	
	RatType getRatType();
	
	void setRatType(RatType ratType);
	
	ServingNetwork getServingNetwork();
	
	void setServingNetwork(ServingNetwork servingNetwork);
	
	UserLocationInformation getUserLocationInformation();
	
	void setUserLocationInformation(UserLocationInformation userLocationInformation);
	
	EPSBearerID getEPSBearerID();
	
	void setEPSBearerID(EPSBearerID epsBearerID);
	
	Indication getIndication();
	
	void setIndication(Indication indication);
	
	FTEID getSGSNFTEID();
	
	void setSGSNFTEID(FTEID sgsnFTEID);
	
	FTEID getRNCFTEID();
	
	void setRNCFTEID(FTEID rncFTEID);
	
	ProtocolConfigurationOption getProtocolConfigurationOption();
	
	void setProtocolConfigurationOption(ProtocolConfigurationOption protocolConfigurationOption);
		
	SignalingPriorityIndication getSignalingPriorityIndication();
	
	void setSignalingPriorityIndication(SignalingPriorityIndication signalingPriorityIndication);
		
	OverloadControlInformation getSGSNOverloadControlInformation();
	
	void setSGSNOverloadControlInformation(OverloadControlInformation overloadControlInformation);
		
	OverloadControlInformation getSGWOverloadControlInformation();
	
	void setSGWOverloadControlInformation(OverloadControlInformation overloadControlInformation);
		
	FContainer getNBIFOMContainer();
	
	void setNBIFOMContainer(FContainer nbifomContainer);
			
	ExtendedProtocolConfigurationOptions getExtendedProtocolConfigurationOptions();
	
	void setExtendedProtocolConfigurationOptions(ExtendedProtocolConfigurationOptions extendedProtocolConfigurationOptions);
	
	FTEID getSenderFTEIDForControlPane();
	
	void setSenderFTEIDForControlPane(FTEID senderFTEIDForControlPage);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}