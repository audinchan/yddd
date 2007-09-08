package com.yodoo.rent;

import org.nestframework.addons.spring.SpringBeanInitActionHandler;
import org.nestframework.test.TestCaseUtil;

public abstract class BaseActionTestCase extends BaseTestCase {
	@Override
	protected void onSetUpBeforeTransaction() throws Exception {
		TestCaseUtil.init();
		TestCaseUtil.setPackageBase("com.yodoo.rent.webapp.action");
		// For we used spring, so we should add this handler to configuration.
		SpringBeanInitActionHandler handler = new SpringBeanInitActionHandler();
		// This handler need spring's context in test case.
		handler.setCtx(applicationContext);
		TestCaseUtil.getConfiguration().addLifecycleHandler(handler);
	}
}
