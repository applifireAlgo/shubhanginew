package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import com.spartan.pluggable.logger.api.LogManager;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessDomainTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    private static List<EntityTestCriteria> entityContraint;

    @Autowired
    private ArtMethodCallStack methodCallStack;

    protected MockHttpSession session;

    protected MockHttpServletRequest request;

    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
        session = null;
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        org.springframework.web.context.request.RequestContextHolder.setRequestAttributes(new org.springframework.web.context.request.ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((org.springframework.web.context.request.ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).requestCompleted();
        org.springframework.web.context.request.RequestContextHolder.resetRequestAttributes();
        request = null;
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        org.junit.Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityContraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("9WCisDEFJJmc5GLsrdOeveMTuQEK173BDzFKJKTmJs9t4i9GFQ");
        useraccessdomain.setDomainHelp("RlvD8nf2XsLPsljFNvEhQdMSspX1XGRzllA8O9VGuSE5jpxR68");
        useraccessdomain.setDomainName("diPII7SEPVZmvXj5JPJpSyqsWvNeFh2N3DLShp12Ul54t3HnYC");
        useraccessdomain.setDomainDescription("TvLfeuKVzrLoNIF0Vw752CCPaUhKzI2m9Q6ZqJcOHMjnIFaMft");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setUserAccessDomain(33722);
            useraccessdomain.setDomainIcon("flYL41mJxrK9f538N2O1BUljE5GJ3d3aw0H0Lfjx4K9wOn5S0X");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainHelp("mQHbqyZxnIHFgAog7xu94KlKZIlAo8fw4ynWB1IllcbyYoFYf9");
            useraccessdomain.setDomainName("gJQhYGwi9A3wEKsr5jcXag8qas0SOrwN3E7QweW1GrqsNDB7B8");
            useraccessdomain.setDomainDescription("LOMtKFjAOem78xug3WitsFGVry66BieNyeQT92wsMHBzw6n5lp");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 190240));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "yv1B0F1FOarsCmgsnbKbqTrfDCQpX705xYaY9Vg2RpevZF2M3jARhIhri7f61W0mxwPaKjm3YUql6mCJ4kJv2qTrhn0aQnTcwRKS0GqKXuG60ngBVDhzxtY7SzxsewW4CUK3e2t3D2DbsvT2sr4WvcCCzTq5E51ClNoPRtxW0XvIFArf8d4rqNhWTS6rLAongXWnDNmQZMiDBwG6BO1d8XZFOHw2KjepDxAMIG3nJqxFnaDnhFu0s1zPLBGcQS148"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "25B9oo8u537BI7Ei4BGd1I4gHb8ukB5FfIExDLzwuKjCky1yAzaosSf1f7mHhaMlgnXZD8yT8oW8YKbcgvQwnlRCyFUIFYvYSJGbnl4bHyJqWsNgYJqKWdFNTizgCnwA4tCIyRV4o9ncUDOJxCajXP8UFY0eakqrTRtnpLpmbH9L2bgPEW9XeGPlbW2XKbVNAnBVMCWV4BrZnWpukBMetObqUQUiqoxv3sfUoBwRWyPfpNNzWPM7l3rjKsUwar7Ln"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "jlGcwomIdJbkmSPlddjPn7bRkVy2xZvmqrUZjW4qUfGt3kY1qTxfX8axfcDoZVV3RGilPrRctw5eMLrbKBCIvB2Py8ToGjCPnEAw2tY6tB5Kv6xnNOIUmn4gzc1toZKr7LJS8hSuK6gG8v1se5US1GoTZOs93TOLHMwuQFiq0pbDLsGl2jYSFXWCAcdmQhuKacXPSBB0d5RHhjJbFoOkDVhrY97r1nVFpySfKmvTWnwQ8IoeULqnKCUEbhAye12w9"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            org.junit.Assert.fail();
        }
    }
}
