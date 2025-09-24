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

package eu.viesapi.client.example;

import eu.viesapi.client.*;

import java.util.ArrayList;
import java.util.List;

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

			String eu_vat = "PL7171642051";

            // Get current EU VIES system status
            VIESStatus viesStatus = viesapi.getVIESStatus();

            if (viesStatus != null) {
                System.out.println(viesStatus);
            } else {
                System.out.println("Error: " + viesapi.getLastError() + " (code: " + viesapi.getLastErrorCode() + ")");
            }

			// Get current account status
			AccountStatus account = viesapi.getAccountStatus();

			if (account != null) {
				System.out.println(account);
			} else {
				System.out.println("Error: " + viesapi.getLastError() + " (code: " + viesapi.getLastErrorCode() + ")");
			}
			
			// Get VIES data from VIES system
			VIESData vies = viesapi.getVIESData(eu_vat);

			if (vies != null) {
				System.out.println(vies);
			} else {
				System.out.println("Error: " + viesapi.getLastError() + " (code: " + viesapi.getLastErrorCode() + ")");
			}

			// Get VIES data from VIES system returning parsed data
			VIESData viesParsed = viesapi.getVIESDataParsed(eu_vat);

			if (viesParsed != null) {
				System.out.println(viesParsed);
			} else {
				System.out.println("Error: " + viesapi.getLastError() + " (code: " + viesapi.getLastErrorCode() + ")");
			}

			// Upload batch of VAT numbers and get their current VAT statuses and traders data
			List<String> numbers = new ArrayList<>();
			numbers.add(eu_vat);
			numbers.add("DK56314210");
			numbers.add("CZ7710043187");

			String token = viesapi.getVIESDataAsync(numbers);

			if (token != null) {
				System.out.println("Batch token: " + token);
			} else {
				System.out.println("Error: " + viesapi.getLastError() + " (code: " + viesapi.getLastErrorCode() + ")");
				return;
			}

			// Check batch result and download data (at production it usually takes 2-3 min for result to be ready)
			BatchResult result;

			while ((result = viesapi.getVIESDataAsyncResult(token)) == null) {
				if (viesapi.getLastErrorCode() != eu.viesapi.client.Error.BATCH_PROCESSING) {
					System.out.println("Error: " + viesapi.getLastError() + " (code: " + viesapi.getLastErrorCode() + ")");
					return;
				}

				System.out.println("Batch is still processing, waiting...");
				Thread.sleep(10000);
			}

			// Batch result is ready
			System.out.println(result);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
}
