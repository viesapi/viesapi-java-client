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

/**
 * EU member country status
 */
public class CountryStatus {

    public final static String UNKNOWN = "Unknown";
    public final static String AVAILABLE = "Available";
    public final static String UNAVAILABLE = "Unavailable";

	protected String countryCode;
	protected String status;

	/**
	 * Create new object
	 */
	public CountryStatus()
    {
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
     * Country status
     * @return status name
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * Country status
     * @param status status name
     */
    public void setStatus(String status)
    {
        this.status = status;
    }

    @Override
	public String toString()
    {
		return "CountryStatus: [countryCode = " + countryCode
			+ ", status = " + status
			+ "]";
	}
}
