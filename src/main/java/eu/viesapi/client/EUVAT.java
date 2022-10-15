/**
 * Copyright 2022 NETCAT (www.netcat.pl)
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
 * @copyright 2022 NETCAT (www.netcat.pl)
 * @license http://www.apache.org/licenses/LICENSE-2.0
 */

package eu.viesapi.client;

import java.util.HashMap;
import java.util.Map;

/**
 * EU VAT number verificator
 */
public class EUVAT {
	
	private static final Map<String,String> map = new HashMap<>();

	/**
	 * Normalizes form of the VAT number
	 * @param number EU VAT number in any valid format
	 * @return normalized VAT number
	 */
	public static String normalize(String number)
	{
		if (number == null || number.length() == 0) {
			return null;
		}

		number = number.replaceAll("-", "");
		number = number.replaceAll(" ", "");
		number = number.trim().toUpperCase();

		if (!number.matches("[A-Z]{2}[A-Z0-9]{2,12}")) {
			return null;
		}

		return number;
	}

	/**
	 * EU VAT number verificator
	 * @param number input number
	 * @return true if number is valid
	 */
	public static boolean isValid(String number)
	{
		if ((number = normalize(number)) == null) {
			return false;
		}

		String cc = number.substring(0, 2).toUpperCase();
		String num = number.substring(2).toUpperCase();

		if (!map.containsKey(cc)) {
			return false;
		}

		if (!number.matches(map.get(cc))) {
			return false;
		}

		if (cc.equals("PL")) {
			return NIP.isValid(num);
		}

		return true;
	}
	
	static {
		map.put("AT", "ATU\\d{8}");
		map.put("BE", "BE0\\d{9}");
		map.put("BG", "BG\\d{9,10}");
		map.put("CY", "CY\\d{8}[A-Z]{1}");
		map.put("CZ", "CZ\\d{8,10}");
		map.put("DE", "DE\\d{9}");
		map.put("DK", "DK\\d{8}");
		map.put("EE", "EE\\d{9}");
		map.put("EL", "EL\\d{9}");
		map.put("ES", "ES[A-Z0-9]{9}");
		map.put("FI", "FI\\d{8}");
		map.put("FR", "FR[A-Z0-9]{2}\\d{9}");
		map.put("GB", "GB[A-Z0-9]{5,12}");
		map.put("HR", "HR\\d{11}");
		map.put("HU", "HU\\d{8}");
		map.put("IE", "IE[A-Z0-9]{8,9}");
		map.put("IT", "IT\\d{11}");
		map.put("LT", "LT\\d{9,12}");
		map.put("LU", "LU\\d{8}");
		map.put("LV", "LV\\d{11}");
		map.put("MT", "MT\\d{8}");
		map.put("NL", "NL\\d{9}B\\d{2}");
		map.put("PL", "PL\\d{10}");
		map.put("PT", "PT\\d{9}");
		map.put("RO", "RO\\d{2,10}");
		map.put("SE", "SE\\d{12}");
		map.put("SI", "SI\\d{8}");
		map.put("SK", "SK\\d{10}");
	}
}
