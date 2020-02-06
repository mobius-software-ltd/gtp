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

import com.mobius.software.telco.protocols.gtp.api.headers.APN;
import com.mobius.software.telco.protocols.gtp.api.headers.Cause;
import com.mobius.software.telco.protocols.gtp.api.headers.EndUserAddress;
import com.mobius.software.telco.protocols.gtp.api.headers.PrivateExtention;
import com.mobius.software.telco.protocols.gtp.api.headers.ProtocolConfigurationOption;
import com.mobius.software.telco.protocols.gtp.api.headers.TunnerEndpointIdentifier1;

public interface PduNotificationRejectRequest extends GTPMessage 
{
	Cause getCause();
	
	void setCause(Cause cause);
	
	TunnerEndpointIdentifier1 getTEI();
	
	void setTEI(TunnerEndpointIdentifier1 tei);
	
	EndUserAddress getEndUserAddress();
	
	void setEndUserAddress(EndUserAddress address);
	
	APN getAPN();
	
	void setAPN(APN apn);
	
	ProtocolConfigurationOption getProtocolConfigurationOption();
	
	void setProtocolConfigurationOption(ProtocolConfigurationOption option);
	
	List<PrivateExtention> getPrivateExtentions();
	
	void setPrivateExtentions(List<PrivateExtention> privateExtention);
}