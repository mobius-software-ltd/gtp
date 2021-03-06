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
import com.mobius.software.telco.protocols.gtp.api.headers.v2.BearerQos;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.EPSBearerID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.FTEID;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TFT;
import com.mobius.software.telco.protocols.gtp.api.headers.v2.TLV2;

public interface CreateSessionRequestBearerContextToBeCreated extends TLV2
{
	EPSBearerID getEPSBearerID();
	
	void setEPSBearerID(EPSBearerID bearerID);
	
	TFT getTFT();
	
	void setTFT(TFT tft);
	
	FTEID getS1UENodeBFTEID();
	
	void setS1UENodeBFTEID(FTEID fteid);
	
	FTEID getS4SGSNFTEID();
	
	void setS4SGSNFTEID(FTEID fteid);
	
	FTEID getS5S8USGWFTEID();
	
	void setS5S8USGWFTEID(FTEID fteid);
	
	FTEID getS5S8UPGWFTEID();
	
	void setS5S8UPGWFTEID(FTEID fteid);
	
	FTEID getS12RNCFTEID();
	
	void setS12RNCFTEID(FTEID fteid);
	
	FTEID getS2bUePDGFTEID();
	
	void setS2bUePDGFTEID(FTEID fteid);
	
	FTEID getS2aUTWANFTEID();
	
	void setS2aUTWANFTEID(FTEID fteid);
	
	BearerQos getBearerQos();
	
	void setBearerQos(BearerQos bearerQos);
	
	FTEID getS11UMMEFTEID();
	
	void setS11UMMEFTEID(FTEID fteid);
}