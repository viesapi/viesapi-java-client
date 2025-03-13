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
 * NIP (polish VAT number) verificator
 */
public class NIP {

	/**
	 * Normalizes form of the NIP number
	 * @param nip NIP number in any valid format
	 * @return normalized NIP number
	 */
	public static String normalize(String nip)
	{
		if (nip == null || nip.length() == 0) {
			return null;
		}

		nip = nip.replaceAll("-", "");
		nip = nip.trim();

		if (!nip.matches("[0-9]{10}")) {
			return null;
		}
		
		return nip;
	}

	/**
	 * Checks if specified NIP is valid
	 * @param nip input number
	 * @return true if number is valid
	 */
	public static boolean isValid(String nip)
	{
		if ((nip = normalize(nip)) == null) {
			return false;
		}

		int w[] = {
			6, 5, 7, 2, 3, 4, 5, 6, 7
		};

		int sum = 0;

		for (int i = 0; i < w.length; i++) {
			sum += Character.digit(nip.charAt(i), 10) * w[i];
		}

		sum %= 11;

		if (sum != Character.digit(nip.charAt(9), 10)) {
			return false;
		}

		return true;
	}
}
