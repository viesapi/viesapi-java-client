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

import java.util.Date;

/**
 * Account status information
 */
public class AccountStatus {

    protected String uid;

	protected String type;
	protected Date validTo;
    protected String billingPlanName;

    protected float subscriptionPrice;
    protected float itemPrice;
    protected float itemPriceStatus;
	protected float itemPriceParsed;

    protected int limit;
    protected int requestDelay;
    protected int domainLimit;
	protected boolean overPlanAllowed;

    protected boolean excelAddIn;
	protected boolean app;
	protected boolean cli;
    protected boolean stats;
    protected boolean monitor;

    protected boolean funcGetViesData;
	protected boolean funcGetViesDataParsed;

    protected int viesDataCount;
	protected int viesDataParsedCount;
    protected int totalCount;
	
	/**
	 * Create new object
	 */
	public AccountStatus()
	{
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
	 * Account type
	 * @return account type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Account type
	 * @param type account type
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * Account validity date (only for pre-paid accounts)
	 * @return validity date
	 */
	public Date getValidTo()
	{
		return validTo;
	}

	/**
	 * Account validity date (only for pre-paid accounts)
	 * @param validTo validity date
	 */
	public void setValidTo(Date validTo)
	{
		this.validTo = validTo;
	}

	/**
	 * Billing plan name
	 * @return name
	 */
	public String getBillingPlanName()
	{
		return billingPlanName;
	}

	/**
	 * Billing plan name
	 * @param billingPlanName name
	 */
	public void setBillingPlanName(String billingPlanName)
	{
		this.billingPlanName = billingPlanName;
	}

	/**
	 * Monthly subscription net price
	 * @return net price
	 */
	public float getSubscriptionPrice()
	{
		return subscriptionPrice;
	}

	/**
	 * Monthly subscription net price
	 * @param subscriptionPrice net price
	 */
	public void setSubscriptionPrice(float subscriptionPrice)
	{
		this.subscriptionPrice = subscriptionPrice;
	}

	/**
	 * Single query cost off-plan (only for standard plans)
	 * @return net price
	 */
	public float getItemPrice()
	{
		return itemPrice;
	}

	/**
	 * Single query cost off-plan (only for standard plans)
	 * @param itemPrice net price
	 */
	public void setItemPrice(float itemPrice)
	{
		this.itemPrice = itemPrice;
	}

	/**
	 * Net price of a single query for an individual plan
	 * @return net price
	 */
	public float getItemPriceStatus()
	{
		return itemPriceStatus;
	}

	/**
	 * Net price of a single query for an individual plan
	 * @param itemPriceStatus net price
	 */
	public void setItemPriceStatus(float itemPriceStatus)
	{
		this.itemPriceStatus = itemPriceStatus;
	}

	/**
	 * Net price of a single query for an individual plan
	 * @return net price
	 */
	public float getItemPriceparsed()
	{
		return itemPriceParsed;
	}

	/**
	 * Net price of a single query for an individual plan
	 * @param itemPriceParsed net price of parsed data
	 */
	public void setItemPriceParsed(float itemPriceParsed)
	{
		this.itemPriceParsed = itemPriceParsed;
	}

	/**
	 * Maximum number of queries in the plan
	 * @return number of queries
	 */
	public int getLimit()
	{
		return limit;
	}

	/**
	 * Maximum number of queries in the plan
	 * @param limit number of queries
	 */
	public void setLimit(int limit)
	{
		this.limit = limit;
	}

	/**
	 * The minimum time interval between queries
	 * @return number of seconds
	 */
	public int getRequestDelay()
	{
		return requestDelay;
	}

	/**
	 * The minimum time interval between queries
	 * @param requestDelay number of seconds
	 */
	public void setRequestDelay(int requestDelay)
	{
		this.requestDelay = requestDelay;
	}

	/**
	 * Maximum number of domains (API keys)
	 * @return number of domains
	 */
	public int getDomainLimit()
	{
		return domainLimit;
	}

	/**
	 * Maximum number of domains (API keys)
	 * @param domainLimit number of domains
	 */
	public void setDomainLimit(int domainLimit)
	{
		this.domainLimit = domainLimit;
	}

	/**
	 * Ability to exceed the maximum number of queries in the plan
	 * @return true if allowed
	 */
	public boolean isOverPlanAllowed()
	{
		return overPlanAllowed;
	}

	/**
	 * Ability to exceed the maximum number of queries in the plan
	 * @param overPlanAllowed true if allowed
	 */
	public void setOverPlanAllowed(boolean overPlanAllowed)
	{
		this.overPlanAllowed = overPlanAllowed;
	}

	/**
	 * Access to MS Excel add-in
	 * @return true if allowed
	 */
	public boolean isExcelAddIn()
	{
		return excelAddIn;
	}

	/**
	 * Access to MS Excel add-in
	 * @param excelAddIn true if allowed
	 */
	public void setExcelAddIn(boolean excelAddIn)
	{
		this.excelAddIn = excelAddIn;
	}

	/**
	 * Access to VIES Checker App application
	 * @return true if allowed
	 */
	public boolean isApp()
	{
		return app;
	}

	/**
	 * Access to VIES Checker App application
	 * @param app true if allowed
	 */
	public void setApp(boolean app)
	{
		this.app = app;
	}

	/**
	 * Access to VIES Checker CLI/CMD command line application
	 * @return true if allowed
	 */
	public boolean isCli()
	{
		return cli;
	}

	/**
	 * Access to VIES Checker CLI/CMD command line application
	 * @param cli true if allowed
	 */
	public void setCli(boolean cli)
	{
		this.cli = cli;
	}

	/**
	 * Access to the statistics of the queries made
	 * @return true if allowed
	 */
	public boolean isStats()
	{
		return stats;
	}

	/**
	 * Access to the statistics of the queries made
	 * @param stats true if allowed
	 */
	public void setStats(boolean stats)
	{
		this.stats = stats;
	}

	/**
	 * Access to monitoring the status of entities
	 * @return true if allowed
	 */
	public boolean isMonitor()
	{
		return monitor;
	}

	/**
	 * Access to monitoring the status of entities
	 * @param monitor true if allowed
	 */
	public void setMonitor(boolean monitor)
	{
		this.monitor = monitor;
	}

	/**
	 * Access to entity status checking functions in the VIES system
	 * @return true if allowed
	 */
	public boolean isFuncGetViesData()
	{
		return funcGetViesData;
	}

	/**
	 * Access to entity status checking functions returning parsed data in the VIES system
	 * @param funcGetViesData true if allowed
	 */
	public void setFuncGetViesData(boolean funcGetViesData)
	{
		this.funcGetViesData = funcGetViesData;
	}

	/**
	 * Access to entity status checking functions in the VIES system
	 * @return true if allowed
	 */
	public boolean isFuncGetViesDataParsed()
	{
		return funcGetViesDataParsed;
	}

	/**
	 * Access to entity status checking functions returning parsed data in the VIES system
	 * @param funcGetViesDataParsed true if allowed
	 */
	public void setFuncGetViesDataParsed(boolean funcGetViesDataParsed)
	{
		this.funcGetViesDataParsed = funcGetViesDataParsed;
	}

	/**
	 * Number of queries to the VIES system performed in the current month
	 * @return number of queries
	 */
	public int getViesDataCount()
	{
		return viesDataCount;
	}

	/**
	 * Number of queries to the VIES system performed in the current month
	 * @param viesDataCount number of queries
	 */
	public void setViesDataCount(int viesDataCount)
	{
		this.viesDataCount = viesDataCount;
	}

	/**
	 * Number of queries to the VIES system performed in the current month
	 * @return number of queries
	 */
	public int getViesDataParsedCount()
	{
		return viesDataParsedCount;
	}

	/**
	 * Number of queries to the VIES system performed in the current month
	 * @param viesDataParsedCount number of queries
	 */
	public void setViesDataParsedCount(int viesDataParsedCount)
	{
		this.viesDataParsedCount = viesDataParsedCount;
	}

	/**
	 * Total number of queries performed in the current month
	 * @return number of queries
	 */
	public int getTotalCount()
	{
		return totalCount;
	}

	/**
	 * Total number of queries performed in the current month
	 * @param totalCount number of queries
	 */
	public void setTotalCount(int totalCount)
	{
		this.totalCount = totalCount;
	}
	
	@Override
	public String toString()
	{
		return "AccountStatus: [uid = " + uid
			+ ", type = " + type
			+ ", validTo = " + (validTo != null ? validTo.toString() : "")
			+ ", billingPlanName = " + billingPlanName

			+ ", subscriptionPrice = " + subscriptionPrice
			+ ", itemPrice = " + itemPrice
			+ ", itemPriceStatus = " + itemPriceStatus
			+ ", itemPriceParsed = " + itemPriceParsed

			+ ", limit = " + limit
			+ ", requestDelay = " + requestDelay
			+ ", domainLimit = " + domainLimit
			+ ", overPlanAllowed = " + overPlanAllowed

			+ ", excelAddIn = " + excelAddIn
			+ ", app = " + app
			+ ", cli = " + cli
			+ ", stats = " + stats
			+ ", monitor = " + monitor

			+ ", funcGetViesData = " + funcGetViesData
			+ ", funcGetViesDataParsed = " + funcGetViesDataParsed

			+ ", viesDataCount = " + viesDataCount
			+ ", viesDataparsedCount = " + viesDataParsedCount
			+ ", totalCount = " + totalCount
			+ "]";
	}
}
