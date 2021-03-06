package com.mobius.software.telco.protocols.gtp.api.messages;
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

import com.mobius.software.telco.protocols.gtp.api.headers.AdditionalMBMSTraceInfo;
import com.mobius.software.telco.protocols.gtp.api.headers.AdditionalTraceInfo;
import com.mobius.software.telco.protocols.gtp.api.headers.EnhancedNSAPI;
import com.mobius.software.telco.protocols.gtp.api.headers.GSNAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.MSTimezone;
import com.mobius.software.telco.protocols.gtp.api.headers.OMCIdentity;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.RatType;
import com.mobius.software.telco.protocols.gtp.api.headers.Recovery;
import com.mobius.software.telco.protocols.gtp.api.headers.RoutingAreaIdentity;
import com.mobius.software.telco.protocols.gtp.api.headers.TraceReference;
import com.mobius.software.telco.protocols.gtp.api.headers.TraceType;
import com.mobius.software.telco.protocols.gtp.api.headers.TriggerID;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifierControlPane;
import com.mobius.software.telco.protocols.gtp.api.headers.UserLocationInformation;

public interface UpdateMBMSContextRequest extends GTPMessage 
{
	RoutingAreaIdentity getRAI();
	
	void setRAI(RoutingAreaIdentity rai);
	
	Recovery getRecovery();
	
	void setRecovery(Recovery recovery);
	
	TunnerEndpointIdentifierControlPane getControlPaneTEI();
	
	void setControlPageTEI(TunnerEndpointIdentifierControlPane tei);
	
	TraceReference getTraceReference();
	
	void setTraceReference(TraceReference reference);
	
	TraceType getTraceType();
	
	void setTraceType(TraceType traceType);
	
	GSNAddress getSGSNAddressForSignaling();
	
	void setSGSNAddressForSignaling(GSNAddress address);
	
	GSNAddress getAlternateSGSNAddressForSignaling();
	
	void setAlternateSGSNAddressForSignaling(GSNAddress address);
	
	TriggerID getTriggerID();
	
	void setTriggerID(TriggerID triggerID);
	
	OMCIdentity getOMCIdentity();
	
	void setOMCIdentity(OMCIdentity omcIdentity);
	
	RatType getRatType();
	
	void setRatType(RatType ratType);
	
	UserLocationInformation getUserLocationInformation();
	
	void setUserLocationInformation(UserLocationInformation locationInformation);
	
	MSTimezone getMSTimezone();
	
	void setMSTimezone(MSTimezone timezone);
	
	AdditionalTraceInfo getAdditionalTraceInfo();
	
	void setAdditionalTraceInfo(AdditionalTraceInfo additionalTraceInfo);
	
	EnhancedNSAPI getEnhancedNSAPI();
	
	void setEnhancedNSAPI(EnhancedNSAPI enhancedNSAPI);
	
	AdditionalMBMSTraceInfo getAdditionalMBMSTraceInfo();
	
	void setAdditionalMBMSTraceInfo(AdditionalMBMSTraceInfo additionalMBMSTraceInfo);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}