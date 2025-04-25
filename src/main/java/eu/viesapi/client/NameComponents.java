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
 * Name components
 */
public class NameComponents {

    protected String name;
    protected String legalForm;
    protected LegalForm legalFormCanonicalId;
    protected String legalFormCanonicalName;

    public NameComponents() {
    }

    /**
     * Trader name
     * @return trader name
     */
    public String getName() {
        return name;
    }

    /**
     * Trader name
     * @param name trader name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Legal form
     * @return legal form
     */
    public String getLegalForm() {
        return legalForm;
    }

    /**
     * Legal form
     * @param legalForm legal form
     */
    public void setLegalForm(String legalForm) {
        this.legalForm = legalForm;
    }

    /**
     * Canonical legal form id
     * @return legal form id
     */
    public LegalForm getLegalFormCanonicalId() {
        return legalFormCanonicalId;
    }

    /**
     * Canonical legal form id
     * @param legalFormCanonicalId legal form id
     */
    public void setLegalFormCanonicalId(LegalForm legalFormCanonicalId) {
        this.legalFormCanonicalId = legalFormCanonicalId;
    }

    /**
     * Canonical legal form name
     * @return legal form name
     */
    public String getLegalFormCanonicalName() {
        return legalFormCanonicalName;
    }

    /**
     * Canonical legal form name
     * @param legalFormCanonicalName legal form name
     */
    public void setLegalFormCanonicalName(String legalFormCanonicalName) {
        this.legalFormCanonicalName = legalFormCanonicalName;
    }

    @Override
    public String toString()
    {
        return "NameComponents: [name = " + name
            + ", legalForm = " + legalForm
            + ", legalFormCanonicalId = " + legalFormCanonicalId
            + ", legalFormCanonicalName = " + legalFormCanonicalName
            + "]";
    }
}
