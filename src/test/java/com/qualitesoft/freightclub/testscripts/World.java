package com.qualitesoft.freightclub.testscripts;

import lombok.Getter;
import lombok.Setter;
import models.managedefaults.ManageDefaults;
import models.managedefaults.DefaultsDropdownOptions;

public final class World {
	@Getter
	@Setter
	private static ManageDefaults manageDefaults;

	@Getter
	@Setter
	private static DefaultsDropdownOptions dropdownOptions;
}
