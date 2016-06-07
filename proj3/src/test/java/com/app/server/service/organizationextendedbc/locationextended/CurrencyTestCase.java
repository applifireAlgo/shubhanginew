package com.app.server.service.organizationextendedbc.locationextended;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organizationextendedbc.locationextended.CurrencyRepository;
import com.app.shared.organizationextendedbc.locationextended.Currency;
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
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CurrencyTestCase extends EntityTestCriteria {

    @Autowired
    private CurrencyRepository<Currency> currencyRepository;

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

    private Currency createCurrency(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCurrencySymbol("DCMkscWwBXhRQqKweaCGZIVewKQIxNCW");
        country.setCapital("7EvDyjg5hP8rfxGWXrZuSelIyF4dVxGw");
        country.setCountryCode2("Q7U");
        country.setCapitalLongitude(5);
        country.setCountryFlag("E0rb4dqvgleILFvtlMcQoYwAafOhWZJprt9U0HgPyOVeVSMv9L");
        country.setCountryCode1("jbk");
        country.setCapitalLatitude(8);
        country.setCurrencyName("wRDPKdV5ywBjzxl6XLMqfPK5wwbLsIgQV8yujDnReRhfRIsMgZ");
        country.setCountryName("RhksE3uouayUBgZJE6aY4QJ0S1aGH0SiWCAjiqUGTJ92RrQeKk");
        country.setIsoNumeric(420);
        country.setCurrencyCode("1W2");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        Currency currency = new Currency();
        currency.setUnicodeHex("9MKXqaSSnG9GnUp0mvSZMGF8T32WLYrM2vXQhNedZQsRSOWGhY");
        currency.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        currency.setCurrencyCode("xZ2HtLeSrlkxDsZt9KzfC6oX3DLjlXm4naDSEjs0LYSmevj1Jw");
        currency.setUnicodeDecimal("97TaXVsKyfnxYuwmm8VSb1j7JlC8j6FNzu7Vzu3fD3qCg2i8Ne");
        currency.setEntityValidator(entityValidator);
        return currency;
    }

    @Test
    public void test1Save() {
        try {
            Currency currency = createCurrency(true);
            currency.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            currency.isValid();
            currencyRepository.save(currency);
            map.put("CurrencyPrimaryKey", currency._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CurrencyPrimaryKey"));
            Currency currency = currencyRepository.findById((java.lang.String) map.get("CurrencyPrimaryKey"));
            currency.setUnicodeHex("MagPny1ZstRjmgzP4WH4bAEaUDDJsF7XmOSIXozWsJWFIQsOf0");
            currency.setVersionId(1);
            currency.setCurrencyCode("89IPwrikxXwH7MY7mqsB4jdfzsLP72r3W68YjzDufxdgHWqu5i");
            currency.setUnicodeDecimal("vNGjahafxEq4cAabDzKuo6Lc1QgU6qECGn4HCfPwGktvP4Ua2n");
            currency.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            currencyRepository.update(currency);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Currency> listofcountryId = currencyRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CurrencyPrimaryKey"));
            currencyRepository.findById((java.lang.String) map.get("CurrencyPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CurrencyPrimaryKey"));
            currencyRepository.delete((java.lang.String) map.get("CurrencyPrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCurrency(EntityTestCriteria contraints, Currency currency) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            currency.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            currency.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            currency.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            currencyRepository.save(currency);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "currencyCode", "IV2W3EARBIX5brzTO0Qqlawx24Wto3qdtBpoa7bKOTKtimjRw8R6ZFRZ19kefC9IMLNZKEkL5yj7MiU42k83OskZEPcIY0vASID3LFWhyhwDtH328VcdEQL2zxEboCjtv"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "unicodeDecimal", "6POk8MutHc5LWibWkMnM1ozQOa0yKx9hyxCefKuLvW9dDTHw6lkS3yn35XETbxgs9Dt6iOmHqREXcHxEO0jdkNDro0cOljLQ0a9Xf8GdvP5mJxF0yNuTkJ8BI5WeXHFd2"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "unicodeHex", "W2XzNKgTIDlpJhEl3OHKEA3uZJ0FDHLJLSpSV2NfXcpiVY2E7aMMqjKHfQoyXdx6VBbaKgenNnddyqALJqFoJoK2zv5xKJ2LpFGzqH8SxfIcYHuOrpHQPd6dBNxUp50g4"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Currency currency = createCurrency(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = currency.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        currency.setCurrencyCode(contraints.getNegativeValue().toString());
                        validateCurrency(contraints, currency);
                        failureCount++;
                        break;
                    case 2:
                        currency.setUnicodeDecimal(contraints.getNegativeValue().toString());
                        validateCurrency(contraints, currency);
                        failureCount++;
                        break;
                    case 3:
                        currency.setUnicodeHex(contraints.getNegativeValue().toString());
                        validateCurrency(contraints, currency);
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
