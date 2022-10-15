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

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Vector;

/**
 * HTTP proxy
 */
public class WebProxy extends Authenticator {
	
	private String host;
	private int port;
	
	private String login;
	private String password;
	
	private final Vector<String> list;
	
	/**
	 * Create new object
	 * @param host server address
	 * @param port server port number
	 * @param login user login
	 * @param password user password
	 * @param list list of host names excluded from the proxy
	 */
	public WebProxy(String host, int port, String login, String password, String list)
	{
		this.host = host;
		this.port = port;
		this.login = login;
		this.password = password;
		this.list = new Vector<>();
		
		if (list != null) {
			String[] hosts = list.split("[,; ]");

			for (String h: hosts) {
				this.list.add(h.toLowerCase());
			}
		}
	}

	/**
	 * Create new object
	 * @param host server address
	 * @param port server port number
	 * @param login user login
	 * @param password user password
	 */
	public WebProxy(String host, int port, String login, String password)
	{
		this(host, port, login, password, null);
	}

	/**
	 * Create new object
	 */
	public WebProxy()
	{
		this(null, 0, null, null, null);
	}

	/**
	 * Server address
	 * @return server address
	 */
	public String getHost()
	{
		return host;
	}

	/**
	 * Server address
	 * @param host server address
	 */
	public void setHost(String host)
	{
		this.host = host;
	}

	/**
	 * Server port number
	 * @return port number
	 */
	public int getPort()
	{
		return port;
	}

	/**
	 * server port number
	 * @param port port number
	 */
	public void setPort(int port)
	{
		this.port = port;
	}

	/**
	 * User login
	 * @return user login
	 */
	public String getLogin()
	{
		return login;
	}

	/**
	 * User login
	 * @param login user login
	 */
	public void setLogin(String login)
	{
		this.login = login;
	}

	/**
	 * User password
	 * @return user password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * User password
	 * @param password user password
	 */
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	/**
	 * Check if specified host is excluded from the proxy
	 * @param host host name to check
	 * @return true if host is excluded from the proxy
	 */
	public boolean isExcluded(String host)
	{
		if (host == null) {
			return false;
		}
		
		return list.contains(host.toLowerCase());
	}
	
	@Override
	public String toString()
	{
		return "WebProxy: [host = " + host
				+ ", port = " + port
				+ ", login = " + login
				+ ", password = " + (password != null ? "set" : "not set")
				+ "]";
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(login, password.toCharArray());
	}
}
