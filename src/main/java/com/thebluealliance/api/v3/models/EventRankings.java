package com.thebluealliance.api.v3.models;

import lombok.Value;

/**
 * The event rankings and tiebreaker information for an event
 */
public class EventRankings {

	/**
	 * @return List of rankings at the event
	 */
	Ranking[] rankings;
	/**
	 * @return List of year-specific values provided in the `sort_orders` array for each team.
	 */
	SortOrderInfo[] sort_order_info;

	public Ranking[] getRankings() {
		return rankings;
	}

	public SortOrderInfo[] getSortOrderInfo() {
		return sort_order_info;
	}

	/**
	 * The Sort order of the rankings for a particular year
	 */
	@Value
	public class SortOrderInfo {

		/**
		 * @return Name of the field used in the <code>sort_order</code> array.
		 */
		String name;
		/**
		 * @return Integer expressing the number of digits of precision in the number provided in sort_orders.
		 */
		int precision;

	}

}
