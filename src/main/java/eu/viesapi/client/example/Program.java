/**
 * Copyright 2022-2023 NETCAT (www.netcat.pl)
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
 * @copyright 2022-2023 NETCAT (www.netcat.pl)
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package eu.viesapi.client.example;

import eu.viesapi.client.AccountStatus;
import eu.viesapi.client.VIESAPIClient;
import eu.viesapi.client.VIESData;

/**
 * Example program
 */
public class Program {

	public static void main(String[] args)
	{
		try {
			// Create client object and establish connection to the production system
			// id – API identifier
			// key – API key (keep it secret)
			// VIESAPIClient viesapi = new VIESAPIClient("id", "key");

			// Create client object and establish connection to the test system
			VIESAPIClient viesapi = new VIESAPIClient();

			String nip_eu = "PL7171642051";

			// Get current account status
			AccountStatus account = viesapi.getAccountStatus();

			if (account != null) {
				System.out.println(account);
			} else {
				System.out.println("Error: " + viesapi.getLastError() + " (code: " + viesapi.getLastErrorCode() + ")");
			}
			
			// Get VIES data from VIES system
			VIESData vies = viesapi.getVIESData(nip_eu);

			if (vies != null) {
				System.out.println(vies);
			} else {
				System.out.println("Error: " + viesapi.getLastError() + " (code: " + viesapi.getLastErrorCode() + ")");
			}
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
}
