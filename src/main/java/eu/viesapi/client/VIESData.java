/**
 * Copyright 2022-2025 NETCAT (www.netcat.pl)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author NETCAT <firma@netcat.pl>
 * @copyright 2022-2025 NETCAT (www.netcat.pl)
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package eu.viesapi.client;

import java.util.Date;

/**
 * VIES data
 */
public class VIESData {
	
	protected String uid;
	
	protected String countryCode;
	protected String vatNumber;
	
	protected boolean valid;
	
	protected String traderName;
	protected String traderCompanyType;
	protected String traderAddress;
	protected AddressComponents traderAddressComponents;

	protected String id;
	protected Date date;
	protected String source;

	/**
	 * Create new object
	 */
	public VIESData()
	{
	}

	/**
	 * Unique response ID
	 * @return id
	 */
	public String getUid()
	{
		return uid;
	}

	/**
	 * Unique response ID
	 * @param uid id
	 */
	public void setUid(String uid)
	{
		this.uid = uid;
	}

	/**
	 * Country code (2-letters)
	 * @return country code
	 */
	public String getCountryCode()
	{
		return countryCode;
	}

	/**
	 * Country code (2-letters)
	 * @param countryCode country code
	 */
	public void setCountryCode(String countryCode)
	{
		this.countryCode = countryCode;
	}

	/**
	 * VAT number
	 * @return the number
	 */
	public String getVatNumber()
	{
		return vatNumber;
	}

	/**
	 * VAT number
	 * @param vatNumber the number
	 */
	public void setVatNumber(String vatNumber)
	{
		this.vatNumber = vatNumber;
	}

	/**
	 * Validity flag
	 * @return validity flag
	 */
	public boolean isValid()
	{
		return valid;
	}

	/**
	 * Validity flag
	 * @param valid validity flag
	 */
	public void setValid(boolean valid)
	{
		this.valid = valid;
	}

	/**
	 * Trader name
	 * @return the name
	 */
	public String getTraderName()
	{
		return traderName;
	}

	/**
	 * Trader name
	 * @param traderName the name
	 */
	public void setTraderName(String traderName)
	{
		this.traderName = traderName;
	}

	/**
	 * Trader company type
	 * @return company type
	 */
	public String getTraderCompanyType()
	{
		return traderCompanyType;
	}

	/**
	 * Trader company type
	 * @param traderCompanyType company type
	 */
	public void setTraderCompanyType(String traderCompanyType)
	{
		this.traderCompanyType = traderCompanyType;
	}

	/**
	 * Trader address
	 * @return trader address
	 */
	public String getTraderAddress()
	{
		return traderAddress;
	}

	/**
	 * Trader address
	 * @param traderAddress trader address
	 */
	public void setTraderAddress(String traderAddress)
	{
		this.traderAddress = traderAddress;
	}

	/**
	 * Trader address components
	 * @return address components
	 */
	public AddressComponents getTraderAddressComponents() {
		return traderAddressComponents;
	}

	/**
	 * Trader address components
	 * @param traderAddressComponents address components
	 */
	public void setTraderAddressComponents(AddressComponents traderAddressComponents) {
		this.traderAddressComponents = traderAddressComponents;
	}

	/**
	 * Request ID from EU VIES system
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Request ID from EU VIES system
	 * @param id the id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Check date time
	 * @return check date
	 */
	public Date getDate()
	{
		return date;
	}

	/**
	 * Check date time
	 * @param date check date
	 */
	public void setDate(Date date)
	{
		this.date = date;
	}

	/**
	 * The source of returned information
	 * @return the siurce
	 */
	public String getSource()
	{
		return source;
	}

	/**
	 * The source of returned information
	 * @param source the source
	 */
	public void setSource(String source)
	{
		this.source = source;
	}

	@Override
	public String toString()
	{
		return "VIESData: [uid = " + uid
			+ ", countryCode = " + countryCode
			+ ", vatNumber = " + vatNumber
			+ ", valid = " + valid
			+ ", traderName = " + traderName
			+ ", traderCompanyType = " + traderCompanyType
			+ ", traderAddress = " + traderAddress
			+ ", traderAddressComponents = " + traderAddressComponents
			+ ", id = " + id
			+ ", date = " + (date != null ? date.toString() : "")
			+ ", source = " + source
			+ "]";
	}
}
