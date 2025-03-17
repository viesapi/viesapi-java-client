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
 * VIES error
 */
public class VIESError {

	protected String uid;

	protected String countryCode;
	protected String vatNumber;

	protected String error;

	protected Date date;
	protected String source;

	/**
	 * Create new object
	 */
	public VIESError()
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
	 * Error description
	 * @return error description
	 */
	public String getError()
	{
		return error;
	}

	/**
	 * Error description
	 * @param error error description
	 */
	public void setError(String error)
	{
		this.error = error;
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
		return "VIESError: [uid = " + uid
			+ ", countryCode = " + countryCode
			+ ", vatNumber = " + vatNumber
			+ ", error = " + error
			+ ", date = " + (date != null ? date.toString() : "")
			+ ", source = " + source
			+ "]";
	}
}
