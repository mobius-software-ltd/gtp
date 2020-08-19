package com.mobius.software.telco.protocols.gtp.api.headers;
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
import java.net.Inet4Address;
import java.net.Inet6Address;


public interface RABSetupInformation extends TLV1
{
	public Integer getNSAPI();
	
	public void setNSAPI(Integer nsapi);
	
	public Long getTunnelEndpointIdentifier();
	
	public void setTunnelEndpointIdentifier(Long data);
	
	Boolean isV4();
	
	Inet4Address getV4Address(); 
	
	Inet6Address getV6Address();
	
	void setV4Address(Inet4Address address);
	
	void setV6Address(Inet6Address address); 
}