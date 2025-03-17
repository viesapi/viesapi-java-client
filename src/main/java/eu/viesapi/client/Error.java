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

import java.util.HashMap;
import java.util.Map;

/**
 * Error codes
 */
public class Error {

	public final static int NIP_BAD               = 7;
	public final static int CONTENT_SYNTAX        = 8;
	public final static int INVALID_PATH          = 10;
	public final static int EXCEPTION             = 11;
	public final static int NO_PERMISSION         = 12;
	public final static int GEN_INVOICES          = 13;
	public final static int GEN_SPEC_INV          = 14;
	public final static int SEND_INVOICE          = 15;
	public final static int SEND_ANNOUNCEMENT     = 17;
	public final static int INVOICE_PAYMENT       = 18;
	public final static int SEARCH_KEY_EMPTY      = 20;
	public final static int EUVAT_BAD             = 22;
	public final static int VIES_SYNC             = 23;
	public final static int PLAN_FEATURE          = 26;
	public final static int SEARCH_TYPE           = 27;
	public final static int NIP_FEATURE           = 30;
	public final static int TEST_MODE             = 33;
	public final static int ACCESS_DENIED         = 35;
	public final static int MAINTENANCE           = 36;
	public final static int BILLING_PLANS         = 37;
	public final static int DOCUMENT_PDF          = 38;
	public final static int EXPORT_PDF            = 39;
	public final static int GROUP_CHECKS          = 42;
	public final static int CLIENT_COUNTERS       = 43;
	public final static int SEND_REMAINDER        = 47;
	public final static int EXPORT_JPK            = 48;
	public final static int GEN_ORDER_INV         = 49;
	public final static int SEND_EXPIRATION       = 50;
	public final static int ORDER_CANCEL          = 52;
	public final static int AUTH_TIMESTAMP        = 54;
	public final static int AUTH_MAC              = 55;
	public final static int SEND_MAIL             = 56;
	public final static int AUTH_KEY              = 57;
	public final static int VIES_TOO_MANY_REQ     = 58;
	public final static int VIES_UNAVAILABLE      = 59;
	public final static int GEOCODE               = 60;
	public final static int BATCH_SIZE            = 61;
	public final static int BATCH_PROCESSING      = 62;
	public final static int BATCH_REJECTED        = 63;

	public final static int DB_AUTH_IP            = 101;
	public final static int DB_AUTH_KEY_STATUS    = 102;
	public final static int DB_AUTH_KEY_VALUE     = 103;
	public final static int DB_AUTH_OVER_PLAN     = 104;
	public final static int DB_CLIENT_LOCKED      = 105;
	public final static int DB_CLIENT_TYPE        = 106;
	public final static int DB_CLIENT_NOT_PAID    = 107;
	public final static int DB_AUTH_KEYID_VALUE   = 108;

	public final static int CLI_CONNECT           = 201;
	public final static int CLI_RESPONSE          = 202;
	public final static int CLI_NUMBER            = 203;
	public final static int CLI_NIP               = 204;
	public final static int CLI_EUVAT             = 205;
	public final static int CLI_EXCEPTION         = 206;
	public final static int CLI_DATEFORMAT        = 207;
	public final static int CLI_INPUT             = 208;
	public final static int CLI_BATCH_SIZE        = 209;

	private final static Map<Integer, String> codes = new HashMap<>();

	static {
		codes.put(CLI_CONNECT,     "Failed to connect to the VIES API service");
		codes.put(CLI_RESPONSE,    "VIES API service response has invalid format");
		codes.put(CLI_NUMBER,      "Invalid number type");
		codes.put(CLI_NIP,         "NIP is invalid");
		codes.put(CLI_EUVAT,       "EU VAT ID is invalid");
		codes.put(CLI_EXCEPTION,   "Function generated an exception");
		codes.put(CLI_DATEFORMAT,  "Date has an invalid format");
		codes.put(CLI_INPUT,       "Invalid input parameter");
		codes.put(CLI_BATCH_SIZE,  "Batch size limit exceeded [2-99]");
	}

	/**
	 * Get error message
	 * @param code error code
	 * @return error message
	 */
	public static String message(int code)
	{
		if (code < CLI_CONNECT || code > CLI_BATCH_SIZE) {
			return null;
		}

		return codes.get(code);
	}
}
