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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * VIES API service client
 */
public class VIESAPIClient {
	
	public final static String VERSION = "1.2.4";

	public final static String PRODUCTION_URL = "https://viesapi.eu/api";
	public final static String TEST_URL = "https://viesapi.eu/api-test";
	
	public final static String TEST_ID = "test_id";
	public final static String TEST_KEY = "test_key";

	private final static String HMAC_ALG = "HmacSHA256";
	
	private DocumentBuilderFactory dbf;
	private XPathFactory xpf;
	private XPath xp;

	private SSLSocketFactory ssf;
	private HostnameVerifier hv;
	private SecureRandom sr;
	private WebProxy wp;

	private URL url;
	private final String id;
	private final String key;
	
	private String err;
	private int errcode;

	/**
	 * Create new client object
	 * @param url VIES API service URL address
	 * @param id API key identifier
	 * @param key API key
	 */
	public VIESAPIClient(URL url, String id, String key)
	{
		this.url = url;
		this.id = id;
		this.key = key;
		
		try {
			this.dbf = DocumentBuilderFactory.newInstance();
			this.dbf.setNamespaceAware(true);

			xpf = XPathFactory.newInstance();
			xp = xpf.newXPath();

			this.sr = SecureRandom.getInstance("SHA1PRNG");
			
			String seed = (System.getProperties().toString() + new Date().toString() + this.hashCode()
				+ url + id + key);
			
			this.sr.setSeed(seed.getBytes());
		} catch (NoSuchAlgorithmException ignored) {
		}
	}
	
	/**
	 * Create new client object for production service
	 * @param id API key identifier
	 * @param key API key
	 * @throws MalformedURLException if service URL is invalid
	 */
	public VIESAPIClient(String id, String key)
		throws MalformedURLException
	{
		this(new URL(PRODUCTION_URL), id, key);
	}
	
	/**
	 * Create new client object for test service
	 * @throws MalformedURLException if service URL is invalid
	 */
	public VIESAPIClient()
		throws MalformedURLException
	{
		this(new URL(TEST_URL), TEST_ID, TEST_KEY);
	}

	/**
	 * Set non default service URL
	 * @param url service URL
	 */
	public void setURL(URL url)
	{
		this.url = url;
	}
	
	/**
	 * Set custom SSL socker factory
	 * @param ssf obiekt fabryki SSL
	 */
	public void setSSLSocketFactory(SSLSocketFactory ssf)
	{
		this.ssf = ssf;
	}
	
	/**
	 * Set custom hostname verifier
	 * @param hv hostname verifier
	 */
	public void setHostnameVerifier(HostnameVerifier hv)
	{
		this.hv = hv;
	}

	/**
	 * Set custom web proxy
	 * @param wp web proxy
	 */
	public void setWebProxy(WebProxy wp)
	{
		this.wp = wp;
	}
	
	/**
	 * Get VIES data for specified number from EU VIES system
	 * @param euvat EU VAT number with 2-letter country prefix
	 * @return VIES data or null in case of error
	 */
	public VIESData getVIESData(String euvat)
	{
		try {
			// clear error
			clear();

	        // validate number and construct path
			String suffix = null;
			
	        if ((suffix = getPathSuffix(Number.EUVAT, euvat)) == null) {
	            return null;
	        }
			
			// prepare url
			String url = (this.url.toString() + "/get/vies/" + suffix);
			
			// prepare request
			byte[] b = get(url);
			
			if (b == null) {
				set(Error.CLI_CONNECT);
				return null;
			}
			
			// parse response
			Document doc = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(b));
			
			if (doc == null) {
				set(Error.CLI_RESPONSE);
				return null;
			}
			
			String code = getString(doc, "/result/error/code", null);
			
			if (code != null) {
				set(Integer.parseInt(code), getString(doc, "/result/error/description", null));
				return null;
			}

			VIESData vies = new VIESData();
			
			vies.setUid(getString(doc, "/result/vies/uid", null));
			vies.setCountryCode(getString(doc, "/result/vies/countryCode", null));
			vies.setVatNumber(getString(doc, "/result/vies/vatNumber", null));
			
			vies.setValid(getString(doc, "/result/vies/valid", "false").equals("true"));
			
			vies.setTraderName(getString(doc, "/result/vies/traderName", null));
			vies.setTraderCompanyType(getString(doc, "/result/vies/traderCompanyType", null));
			vies.setTraderAddress(getString(doc, "/result/vies/traderAddress", null));

			vies.setId(getString(doc, "/result/vies/id", null));
			vies.setDate(getDate(doc, "/result/vies/date"));
			vies.setSource(getString(doc, "/result/vies/source", null));

			return vies;
		} catch (Exception ignored) {
		}
		
		return null;
	}
	
	/**
	 * Get current account status
	 * @return account status or null in case of error
	 */
	public AccountStatus getAccountStatus()
	{
		try {
			// clear error
			clear();
			
			// prepare url
			String url = (this.url.toString()  + "/check/account/status");
			
			// prepare request
			byte[] b = get(url);
			
			if (b == null) {
				set(Error.CLI_CONNECT);
				return null;
			}
			
			// parse response
			Document doc = dbf.newDocumentBuilder().parse(new ByteArrayInputStream(b));
			
			if (doc == null) {
				set(Error.CLI_RESPONSE);
				return null;
			}
			
			String code = getString(doc, "/result/error/code", null);
			
			if (code != null) {
				set(Integer.parseInt(code), getString(doc, "/result/error/description", null));
				return null;
			}

			AccountStatus status = new AccountStatus();
			
			status.setUid(getString(doc, "/result/account/uid", null));
			status.setType(getString(doc, "/result/account/type", null));
			status.setValidTo(getDateTime(doc, "/result/account/validTo"));
			status.setBillingPlanName(getString(doc, "/result/account/billingPlan/name", null));

			status.setSubscriptionPrice(Float.parseFloat(getString(doc, "/result/account/billingPlan/subscriptionPrice", "0")));
			status.setItemPrice(Float.parseFloat(getString(doc, "/result/account/billingPlan/itemPrice", "0")));
			status.setItemPriceStatus(Float.parseFloat(getString(doc, "/result/account/billingPlan/itemPriceCheckStatus", "0")));

			status.setLimit(Integer.parseInt(getString(doc, "/result/account/billingPlan/limit", "0")));
			status.setRequestDelay(Integer.parseInt(getString(doc, "/result/account/billingPlan/requestDelay", "0")));
			status.setDomainLimit(Integer.parseInt(getString(doc, "/result/account/billingPlan/domainLimit", "0")));
			status.setOverPlanAllowed(getString(doc, "/result/account/billingPlan/overplanAllowed", "false").equals("true"));

			status.setExcelAddIn(getString(doc, "/result/account/billingPlan/excelAddin", "false").equals("true"));
			status.setApp(getString(doc, "/result/account/billingPlan/app", "false").equals("true"));
			status.setCli(getString(doc, "/result/account/billingPlan/cli", "false").equals("true"));
			status.setStats(getString(doc, "/result/account/billingPlan/stats", "false").equals("true"));
			status.setMonitor(getString(doc, "/result/account/billingPlan/monitor", "false").equals("true"));

			status.setFuncGetViesData(getString(doc, "/result/account/billingPlan/funcGetVIESData", "false").equals("true"));

			status.setViesDataCount(Integer.parseInt(getString(doc, "/result/account/requests/viesData", "0")));
			status.setTotalCount(Integer.parseInt(getString(doc, "/result/account/requests/total", "0")));
			
			return status;
		} catch (Exception ignored) {
		}
		
		return null;
	}

	/**
	 * Get last error code
	 * @return error code
	 */
	public int getLastErrorCode()
	{
		return errcode;
	}

	/**
	 * Get last error message
	 * @return error message
	 */
	public String getLastError()
	{
		return err;
	}

	/**
	 * Clear last error
	 */
	private void clear()
	{
		errcode = 0;
		err = null;
	}

	/**
	 * Set last error information
	 * @param code error code
	 * @param err error message
	 */
	private void set(int code, String err)
	{
		errcode = code;
		this.err = (err == null ? Error.message(code) : err);
	}

	/**
	 * Set last error information
	 * @param code error code
	 */
	private void set(int code)
	{
		set(code, null);
	}

	/**
	 * Create authorization header
	 * @param method HTTP method name
	 * @param url target URL address
	 * @return authorization information
	 */
	private Properties auth(String method, URL url)
	{
		try {
			// prepare auth header
			String nonce = getRandom(8);

			long ts = (new Date().getTime() / 1000);
			
			String str = "" + ts + "\n"
				+ nonce + "\n"
				+ method + "\n"
				+ url.getPath() + "\n"
				+ url.getHost() + "\n"
				+ (url.getPort() == -1 ? url.getProtocol().equals("http") ? 80 : 443 : url.getPort()) + "\n"
				+ "\n";
			
			String mac = getMac(str);
			
			if (mac == null) {
				return null;
			}
			
			// prepare request
			Properties p = new Properties();
			
			p.setProperty("Authorization", "MAC id=\"" + id + "\", ts=\"" + ts + "\", nonce=\""
				+ nonce + "\", mac=\"" + mac + "\"");
			
			return p;
		} catch (Exception ignored) {
		}
		
		return null;
	}

	/**
	 * Create user agent header
	 * @param headers object with HTTP headers
	 */
	private void userAgent(Properties headers)
	{
		headers.setProperty("User-Agent", "NIP24Client/" + VERSION + " Java/" + System.getProperty("java.version"));
	}

	/**
	 * Perform HTTP GET
	 * @param url request URL
	 * @return response or null
	 */
	private byte[] get(String url)
	{
		boolean set = false;
		
		try {
			if (url == null || url.length() == 0) {
				return null;
			}
			
			URL u = new URL(url);
			Proxy p = null;
			
			byte[] out = null;

			if (wp != null && !wp.isExcluded(u.getHost())) {
				p = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(wp.getHost(), wp.getPort()));
				
				Authenticator.setDefault(wp);
				set = true;
			}
			
			// auth
			Properties headers = auth("GET", u);
			
			if (headers == null) {
				return null;
			}

			userAgent(headers);

			// connect
			HttpURLConnection huc = null;

			if (url.toLowerCase().startsWith("https://")) {
				// https
				if (p != null) {
					huc = (HttpsURLConnection)u.openConnection(p);
				} else {
					huc = (HttpsURLConnection)u.openConnection();
				}
	
				if (hv != null) {
					((HttpsURLConnection)huc).setHostnameVerifier(hv);
				}
				
				if (ssf != null) {
					((HttpsURLConnection)huc).setSSLSocketFactory(ssf);
				}
			} else if (url.toLowerCase().startsWith("http://")) {
				// http
				if (p != null) {
					huc = (HttpURLConnection)u.openConnection(p);
				} else {
					huc = (HttpURLConnection)u.openConnection();
				}
			} else {
				return null;
			}
				
			huc.setRequestMethod("GET");

			Enumeration<?> keys = headers.keys();

			while (keys.hasMoreElements()) {
				String key = (String)keys.nextElement();
				huc.setRequestProperty(key, headers.getProperty(key));
			}

			huc.setUseCaches(false);
			huc.setDoInput(true);
			huc.setDoOutput(false);
			
			// response
			int code = huc.getResponseCode();
			
			if (code != 200) {
				return null;
			}
			
			out = read(huc.getInputStream(), true);

			return out;
		} catch (Exception ignored) {
		} finally {
    		if (set) {
    			Authenticator.setDefault(null);
    		}
		}
		
		return null;
	}

	/**
	 * Read input stream to array
	 * @param stream input stream
	 * @param close if true stream will be closed after data read
	 * @return array of bytes read
	 */
	private byte[] read(InputStream stream, boolean close)
		throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		copy(stream, baos, close);
		
		return baos.toByteArray();
	}

	/**
	 * Copy data between streams
	 * @param in input stream
	 * @param out output stream
	 * @param close if true both streams will be closed
	 */
	private void copy(InputStream in, OutputStream out, boolean close)
		throws IOException
	{
		byte[] b = new byte[8192];

		int read;

		while ((read = in.read(b)) > 0) {
			out.write(b, 0, read);
		}

		if (close) {
			in.close();
			out.close();
		}
	}
	
	/**
	 * Get random hex string
	 * @param length lenght of string
	 * @return random hex string
	 */
	private String getRandom(int length)
	{
		byte[] b = new byte[length / 2];
		
		sr.nextBytes(b);
		
		return DatatypeConverter.printHexBinary(b).toLowerCase();
	}
	
	/**
	 * Calculates HMAC256 from input string
	 * @param str input string
	 * @return HMAC256 as Base64 string or null
	 */
	private String getMac(String str)
	{
		try {
			SecretKeySpec sks = new SecretKeySpec(key.getBytes(), HMAC_ALG);
			Mac m = Mac.getInstance(HMAC_ALG);
			
			m.init(sks);
			
			byte[] b = m.doFinal(str.getBytes());
			
			return DatatypeConverter.printBase64Binary(b);
		} catch (Exception ignored) {
		}
		
		return null;
	}

	/**
	 * Find XML node
	 * @param node XML document
	 * @param path XPath expression selecting the node
	 * @return node object or null
	 */
	private Node getNode(Node node, String path)
	{
		try {
			return (Node)xp.evaluate(path, node, XPathConstants.NODE);
		} catch (Exception ignored) {
		}
		
		return null;
	}

	/**
	 * Get XML node value as string
	 * @param node XML document
	 * @param path XPath expression selecting the value
	 * @return node value or null
	 */
	private String getValue(Node node, String path)
	{
		Node n = getNode(node, path);
		
		if (n != null) {
			return (n.getTextContent() == null ? "" : n.getTextContent());
		}
		
		return null;
	}

	/**
	 * Get XML node value as string
	 * @param node XML document
	 * @param path XPath expression selecting the value
	 * @param def default value used when node does not exist
	 * @return node value or null
	 */
	private String getString(Node node, String path, String def)
	{
		String val = getValue(node, path);
		
		if (val != null) {
			return val;
		}

		return def;
	}

	/**
	 * Get XML node value as local date time
	 * @param node XML document
	 * @param path XPath expression selecting the value
	 * @return date object or null
	 */
	private Date getDateTime(Node node, String path)
	{
		try {
			String val = getValue(node, path);
			
			if (val == null) {
				return null;
			}

			Calendar c = DatatypeConverter.parseDateTime(val);
			
			return c.getTime();
		} catch (Exception ignored) {
		}

		return null;
	}

	/**
	 * Get XML node value as local date
	 * @param node XML document
	 * @param path XPath expression selecting the value
	 * @return date object or null
	 */
	private Date getDate(Node node, String path)
	{
		try {
			String val = getValue(node, path);
			
			if (val == null) {
				return null;
			}

			Calendar c = DatatypeConverter.parseDate(val);
			
			return c.getTime();
		} catch (Exception ignored) {
		}

		return null;
	}

	/**
	 * Get path suffix
	 * @param type number type
	 * @param number number value
	 * @return path fragment or null
	 */
	private String getPathSuffix(Number type, String number)
	{
	    String path = "";
	
		if (type.equals(Number.EUVAT)) {
		    if (!EUVAT.isValid(number)) {
				set(Error.CLI_EUVAT);
		        return null;
		    }
		
		    path = "euvat/" + EUVAT.normalize(number);
		} else if (type.equals(Number.NIP)) {
			if (!NIP.isValid(number)) {
				set(Error.CLI_NIP);
				return null;
			}

			path = "nip/" + NIP.normalize(number);
		} else {
			set(Error.CLI_NUMBER);
			return null;
		}
		    
		return path;
	}
}
