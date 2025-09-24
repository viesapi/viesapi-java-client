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
 * Address components
 */
public class AddressComponents {
    
    protected String country;
    protected String postalCode;
    protected String city;
    protected String street;
    protected String streetNumber;
    protected String houseNumber;
    protected String other;
    
    public AddressComponents() {
    }

    /**
     * Country
     * @return country name
     */
    public String getCountry() {
        return country;
    }

    /**
     * Country
     * @param country country name
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Postal code
     * @return postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Postal code
     * @param postalCode postal code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * City or locality
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * City or locality
     * @param city city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Street name
     * @return street name
     */
    public String getStreet() {
        return street;
    }

    /**
     * Street name
     * @param street street name
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Street number
     * @return street number
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * Street number
     * @param streetNumber street number
     */
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * House number
     * @return house number
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * House number
     * @param houseNumber house number
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * Get other address details (area name, building name, floor number, etc.)
     * @return other details
     */
    public String getOther()
    {
        return other;
    }

    /**
     * Set other address details (area name, building name, floor number, etc.)
     * @param other other details
     */
    public void setOther(String other)
    {
        this.other = other;
    }

    @Override
    public String toString()
    {
        return "AddressComponents: [country = " + country
            + ", postalCode = " + postalCode
            + ", city = " + city
            + ", street = " + street
            + ", streetNumber = " + streetNumber
            + ", houseNumber = " + houseNumber
            + ", other = " + other
            + "]";
    }
}
