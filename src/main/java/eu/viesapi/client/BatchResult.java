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

import java.util.ArrayList;
import java.util.List;

/**
 * Batch result
 */
public class BatchResult {

	protected List<VIESData> numbers;
	protected List<VIESError> errors;

	/**
	 * Create new object
	 */
	public BatchResult()
	{
		numbers = new ArrayList<>();
		errors = new ArrayList<>();
	}

	/**
	 * Get list of VIES results
	 * @return list of results
	 */
	public List<VIESData> getNumbers() {
		return numbers;
	}

	/**
	 * Set list of VIES results
	 * @param numbers list of results
	 */
	public void setNumbers(List<VIESData> numbers) {
		this.numbers = numbers;
	}

	/**
	 * Add VIES result
	 * @param number result to add
	 */
	public void addNumber(VIESData number) {
		numbers.add(number);
	}

	/**
	 * Get list of VIES errors
	 * @return list of errors
	 */
	public List<VIESError> getErrors() {
		return errors;
	}

	/**
	 * Set list of VIES errors
	 * @param errors list of errors
	 */
	public void setErrors(List<VIESError> errors) {
		this.errors = errors;
	}

	/**
	 * Add VIES error
	 * @param error error to add
	 */
	public void addError(VIESError error) {
		errors.add(error);
	}

	@Override
	public String toString()
	{
		return "BatchResult: [numbers = " + numbers
			+ ", errors = " + errors
			+ "]";
	}
}
