package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.City;
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
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CityTestCase extends EntityTestCriteria {

    @Autowired
    private CityRepository<City> cityRepository;

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

    private City createCity(Boolean isSave) throws Exception {
        State state = new State();
        state.setStateCode(2);
        state.setStateFlag("V2zZzwLVf70E9dmlfODRAafhcqpNF5OMDvNFTmC8J7D4frAWGv");
        state.setStateCapitalLongitude(2);
        state.setStateCodeChar2("qUAK8UIYQuMnIFpaGfpKKZ2Gp2IVPkCs");
        state.setStateDescription("72PHfUCSFFV33RpJBnnaDYcPjxhjJ1eVbQybW3MtHnqrRelwdW");
        state.setStateCodeChar3("ceONxWOryBq0fKm8NbN9Bwpe3bVWM6Ob");
        state.setStateName("FA7KGAwouWkrTb0VJQzCnYp35n1ekdSsDt06Vmnf1ju2J028ks");
        state.setStateCapital("bLAZuFlGvTeQwMpfPAh9wj28FiLaBWNIxvGQvE9AoNUi389UBD");
        Country country = new Country();
        country.setCurrencySymbol("o0PAIZ4ME4z12ogBvItsdjvu9qIN1fgP");
        country.setCapital("4eWdXMa32glGMVA9B8XOCgAk4Vt2HEkG");
        country.setCountryCode2("mjt");
        country.setCapitalLongitude(3);
        country.setCountryFlag("awNZuzBfqbx8RyAUw6Mn7Pf18RobNjyAJvuNprkl90cCnFlI6A");
        country.setCountryCode1("SJd");
        country.setCapitalLatitude(9);
        country.setCurrencyName("qTwC2pecTGLV5RCyV8yerOjgfRWEBJ0d0vFrCrEILsWN06Eeng");
        country.setCountryName("jguYrkS1H1w78jkw1yenpLp9cIRahBL8caFWkzvKj3OrBnclhP");
        country.setIsoNumeric(141);
        country.setCurrencyCode("IlM");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCode(1);
        state.setStateFlag("YsWFPWTwypLRGQHGvrkjCJuIhvmlPy3fEwISx3rbMsMLFzieh6");
        state.setStateCapitalLongitude(6);
        state.setStateCodeChar2("V72bAQjeXZCj771I8s3tRa40HmlpfhaH");
        state.setStateDescription("fWUK8nSPqQrYMoVSe6Yrx2CHJnAJkne91x9wUoEOmYr5w6QFve");
        state.setStateCodeChar3("Bty4YC94GGb9P6KdogoT7IKkMap7AcsP");
        state.setStateName("7WO5EjFLymGXFZq5XSsi3onQb1sDoBFKZfaiKSfPfpIBYYVpli");
        state.setStateCapital("pWI4xQmKPeqN6HuF4f2wcVV4ouwc6ZRXMxCNILU0Rzb5iYTdyq");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(2);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityCodeChar2("3wUqRkjJZNGj7kCebcR65vMZxKW7vLse");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("3u7WjwXLR79bhkp94QPbiuxtNiDLshyUZCRD4ZJ5BymJtoCSHv");
        city.setCityCode(2);
        city.setCityName("rMCVoIrAxLX6P45cM7AvXr5DYnPXltmu4esFsksIxvMGJjR9Dg");
        city.setCityDescription("GsijfTaBjPCM60NBfZY4NOy0HnlYcvTsS2mDrDFKYVQJHv18mt");
        city.setCityLatitude(7);
        city.setCityLongitude(10);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        city.setEntityValidator(entityValidator);
        return city;
    }

    @Test
    public void test1Save() {
        try {
            City city = createCity(true);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            city.isValid();
            cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            City city = cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
            city.setCityCodeChar2("xOG23TMLhgeUiGJykoUduuttp6HwNkf8");
            city.setVersionId(1);
            city.setCityFlag("7EPOzPgRQQVa9jcdCjLb7X6k9ri1URzGEHSS3J74CrCjcodytu");
            city.setCityCode(1);
            city.setCityName("7N3Z3c0F9CQa10SVbldPjiTDtPjvh2DEKKHimYyDclY89aXmfw");
            city.setCityDescription("mrJVNhQSGqlLtvPPjSBALQndeDakA3xZDLacP5geNNXMCFLs8D");
            city.setCityLatitude(8);
            city.setCityLongitude(5);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<City> listofstateId = cityRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<City> listofcountryId = cityRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCity(EntityTestCriteria contraints, City city) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            city.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            city.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            city.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cityRepository.save(city);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "cityName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "HZSb7Y8lHBvt27vCHcvbJRby2lmFO0cJ1QfnmR4p6zIP6wFxwYPqVEKznVx74vbiSiuMoc8y8G00VRPriSfpyW5VAVG6XBBd4mppmDTaEXTAzY0Aulv6xnXfUeuY73ciqnakaJhrsbo3jwVyMTc1U2uFIsFDDz3OniO5uQT2vxMLKpfTflOtnDX3Qo6WGgsQKqLr1bCM22bkpNRhETPirKTm9jScKK2wtS9Mr1isoU3lsqGA6EFGsIVvAed1WLqeO"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "GQ9auoKdaNEjKDbnJuELf6naoszFT1B12"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 6));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "QFaJZk8o8rSxIOj5ILkqhi5cVbyC4C4rZok1uJYLAp33kehH9nxL3YPedn3enxcbgmDCiYfdkPbSOmvm4a4gs8iIGiGkZHg8PZSWK4M9hFR04MNNNwFvf15IqCtsBf4ok"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "GxZLjB3t3HKBvLriArENJS2SZvpiVZAttrB3374hodA1xMBe1OrsoHnGgi7HB7emWZmFFmM6SndYhds2pBFxzJgq4cqBg1EE5FkGKWQk3J1sAVdI8OQ2b9QQPEfK9QZpG"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 16));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "cityLongitude", 13));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                City city = createCity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = city.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 2:
                        city.setCityName(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 4:
                        city.setCityCodeChar2(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 6:
                        city.setCityCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 7:
                        city.setCityDescription(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 8:
                        city.setCityFlag(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 9:
                        city.setCityLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 10:
                        city.setCityLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
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
