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
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum(String.class)
public enum SelectionModeType 
{	
	MS_OR_NETWORK__PROVIDED_APN_SUBSCRIBED_VERIFIED(0),
	MS_PROVIDED_APN_SUBSCRIPTION_NOT_VERIFIED(1),
	NETWORK_PROVIDED_APN_SUBSCRIPTION_NOT_VERIFIED(2),
	UNKNOWN(3);

	private static final Map<Integer, SelectionModeType> intToTypeMap = new HashMap<Integer, SelectionModeType>();
	static 
	{
	    for (SelectionModeType type : SelectionModeType.values()) 
	    {
	        intToTypeMap.put(type.value, type);
	    }
	}

	public static SelectionModeType fromInt(int i) 
	{
		SelectionModeType type = intToTypeMap.get(Integer.valueOf(i));
	    if (type == null) 
	        return SelectionModeType.UNKNOWN;
	    
	    return type;
	}
	
	private int value;
	
	private SelectionModeType(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
