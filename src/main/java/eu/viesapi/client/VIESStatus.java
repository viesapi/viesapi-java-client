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

import java.util.ArrayList;
import java.util.List;

/**
 * Current VIES system status information
 */
public class VIESStatus {

    protected String uid;
	protected boolean available;
	protected List<CountryStatus> countries;

    /**
     * Create new object
     */
    public VIESStatus()
    {
        countries = new ArrayList<>();
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
     * Get availability flag
     * @return the flag value
     */
    public boolean isAvailable()
    {
        return available;
    }

    /**
     * Set availability flag
     * @param available the flag value
     */
    public void setAvailable(boolean available)
    {
        this.available = available;
    }

    /**
     * Get list of member countries
     * @return list of countries
     */
    public List<CountryStatus> getCountries()
    {
        return countries;
    }

    /**
     * Set list of member countries
     * @param countries list of countries
     */
    public void setCountries(List<CountryStatus> countries)
    {
        this.countries = countries;
    }

    /**
     * Add member country
     * @param country country to add
     */
    public void addCountry(CountryStatus country)
    {
        countries.add(country);
    }

	@Override
	public String toString()
    {
		return "VIESStatus: [uid = " + uid
            + ", available = " + available
			+ ", countries = " + countries
			+ "]";
	}
}
