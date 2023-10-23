package com.fidelity.payroll;

public class PayComparator {
		public int compare(Employee employee1, Employee employee2) {
			int payComparator = employee1.calculateMonthlyPay().compareTo(employee1.calculateMonthlyPay());
			if (payComparator == 0) {
				return employee1.getName().compareTo(employee2.getName());
			}
			return payComparator;
		}
}
