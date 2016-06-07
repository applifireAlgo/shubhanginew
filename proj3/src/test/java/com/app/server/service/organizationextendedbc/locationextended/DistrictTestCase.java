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
import com.app.server.repository.organizationextendedbc.locationextended.DistrictRepository;
import com.app.shared.organizationextendedbc.locationextended.District;
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
import com.app.shared.organizationextendedbc.locationextended.Region;
import com.app.server.repository.organizationextendedbc.locationextended.RegionRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class DistrictTestCase extends EntityTestCriteria {

    @Autowired
    private DistrictRepository<District> districtRepository;

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

    private District createDistrict(Boolean isSave) throws Exception {
        Region region = new Region();
        region.setRegionDescription("Cbcepq2eOyuN6vyjLI6Npz5Hq7DXFuej3rUlVtJOPvwIzimoW5");
        region.setRegionCodeChar2("ixlMRNS0EyhiwIo0hLzoVFfkJ6TVfK7S");
        region.setRegionLongitude(3);
        region.setRegionName("yvj5BxBUEOaVwfZuZbp4bj4O8U28uNm7VNEFsqnHDkkwxcJKsv");
        region.setRegionFlag("HvF4dMWk5qOzV4HcBWT75VuFabjbL3YPG79yXMlpLwpd2lgHXV");
        Country country = new Country();
        country.setCurrencySymbol("1IwcR4A4xOOlI1jWJlwnRw31VfleHToQ");
        country.setCapital("A9fKGkL0qqbp87IHJHclhUt9uxqZKkQx");
        country.setCountryCode2("aOn");
        country.setCapitalLongitude(1);
        country.setCountryFlag("vBpYwCyi46RKuMYg1CAmFpBpNDFzVyXzgkTWP0KYGIzBcH7lcW");
        country.setCountryCode1("A5w");
        country.setCapitalLatitude(9);
        country.setCurrencyName("h3ti91TKnmaOuwgBk4zmvosEqAp0rJWVZmleE2zvD2hBGdSeCS");
        country.setCountryName("28NGbGgVBIrPe4JlKoGY252fFowAbhJ6FBBStls1x542cQzw9s");
        country.setIsoNumeric(981);
        country.setCurrencyCode("g1Y");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCode(1);
        state.setStateFlag("KO73fQRk56CzQTmUqOgcZIT8wkQjm1G21ewpfqz7YdEP17i5tv");
        state.setStateCapitalLongitude(11);
        state.setStateCodeChar2("gCOYqC5fA1gNwgOhjhkZv4C154gCgtOf");
        state.setStateDescription("ROnrdmkAod6msUcsnG5IAJZHLDef2SVTmPQWYOSeJ29A6SnwP2");
        state.setStateCodeChar3("DnicUhyt6oD8KrgiLWKOKH8oDW2iixts");
        state.setStateName("mquCsVHbkx4JfgSW06EMkByk5dAamxCSswQuvSVgLTJBjFS45k");
        state.setStateCapital("TBBy3osn9qdeZpXEtgvZENoU2qLSzzU674EIEHVGWkq5Q3R4lx");
        state.setStateCode(2);
        state.setStateFlag("UcVjL2dEpAA0ERDh5BEevp0MQxStlu4chANrR4QrdbbwD24fHy");
        state.setStateCapitalLongitude(4);
        state.setStateCodeChar2("tKckVSU9yYhqHT3uDOglwPcH97H3wQXE");
        state.setStateDescription("ml735yPvGXRX2T2243RjrjyTby0SznUMS3oNWzVJZUHUDSCSkS");
        state.setStateCodeChar3("VUW2wSJBW4JKj0HXdhUvzziF87WiWBn2");
        state.setStateName("PxHWvXkhMJecLGoi1zWjPXZe6qewqq6Jl06udQ5kOVpbbjqV0j");
        state.setStateCapital("9BbF1vR8x0FtiISBJalZjJQxxXt6z7L4jmx6pbXflBcP2vaYek");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(9);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        region.setRegionDescription("DD6CzGp1NdzE1CF96XKRVaAL2V6gF5kp1iuTuTk9qeF1dce4CO");
        region.setRegionCodeChar2("eYgoxeRBoSuHAAk5t9ZJR6ZQ3uu8K4m1");
        region.setRegionLongitude(3);
        region.setRegionName("FEgEGPFjie4eqwnEzCkMOYdwOVUEgIZkK1LFvCCjGqBnr2xr9q");
        region.setRegionFlag("7ysWnF5RHiWjRXL5B56UzdgWxE8CHvW1OPydcdEuDPu2VKrEMF");
        region.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionCode1(2);
        region.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionLatitude(3);
        Region RegionTest = new Region();
        if (isSave) {
            RegionTest = regionRepository.save(region);
            map.put("RegionPrimaryKey", region._getPrimarykey());
        }
        District district = new District();
        district.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setName("8ykpMsU0EteGa36G9MErU3nD7ELtLZ35tlILYsvcji4cjmUWGO");
        district.setDistrictDescription("wmXAdqltdZpwyHzKtpzJZzbMLOavv3ODEdzsQyBrdAcxiDrG2v");
        district.setDistrictFlag("nEX1HoYEInipg1TVE30bAKd57Mu92l7sD35YFm4rBapunRZW7L");
        district.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setDistrictLongitude(7);
        district.setStateId((java.lang.String) StateTest._getPrimarykey());
        district.setDistrictLatitude(7);
        district.setCode2("qCVquN8x0DEw2z69LeORovUw8Ea3rXXI");
        district.setEntityValidator(entityValidator);
        return district;
    }

    @Test
    public void test1Save() {
        try {
            District district = createDistrict(true);
            district.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            district.isValid();
            districtRepository.save(district);
            map.put("DistrictPrimaryKey", district._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private RegionRepository<Region> regionRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("DistrictPrimaryKey"));
            District district = districtRepository.findById((java.lang.String) map.get("DistrictPrimaryKey"));
            district.setName("Lvh0NRrqKHUW0Bvw1vbKgNHCaBZVzp8sU9t5a00nXtJPCzFggO");
            district.setDistrictDescription("rjxNeLBVbfAdLfP8hvvDjx6XSrNpgK2lFOyIi5vm0fhzkF2cEb");
            district.setVersionId(1);
            district.setDistrictFlag("eqPcDHDFKdPdp3f4dl1YnPyvLUUJgoxJNnIgLShYjkuNWUa0LT");
            district.setDistrictLongitude(2);
            district.setDistrictLatitude(2);
            district.setCode2("n493pqPK87hGuImpyqVkKUC9j0j5AAWy");
            district.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            districtRepository.update(district);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByregionId() {
        try {
            java.util.List<District> listofregionId = districtRepository.findByRegionId((java.lang.String) map.get("RegionPrimaryKey"));
            if (listofregionId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<District> listofcountryId = districtRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            org.junit.Assert.assertNotNull(map.get("DistrictPrimaryKey"));
            districtRepository.findById((java.lang.String) map.get("DistrictPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<District> listofstateId = districtRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("DistrictPrimaryKey"));
            districtRepository.delete((java.lang.String) map.get("DistrictPrimaryKey")); /* Deleting refrenced data */
            regionRepository.delete((java.lang.String) map.get("RegionPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateDistrict(EntityTestCriteria contraints, District district) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            district.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            district.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            district.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            districtRepository.save(district);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "Name", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "name", "cMjgIpbyXVH9cbDKpJ0QYK6z3Hc2h5ncu9s1HhGT58tMT2WSF9G2lNkUOMgETlhwjF4REa2OFhBxbHTIe4b1Cxt3FxjUa7zlZYBgsAAyD0fhZjYQX4PrNPsvdjPYRMSLNpoZ4JhLl1TZ8ZfHGWHP7D9nxzJMK6a8Zh3kmCHrf23lAqgKpjLUeVD3KVPcQGMHFquXaibRle4JZeziEVzWkL7HKfbuug1xUmmURYoxyo7Fz9Vo9o4qGbFoWmV2ekLjl"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "code2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "code2", "nXRFDDdwoWnNs3FvLApcdq38WuqzXGtMM"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "districtDescription", "vuG5AuFCNcGkrs2i9bZMdjQpjGqqRgGoQHdEAanh83cXg8k1UoLKIfYm72A30SdNR8gC8yMMl4T14QLCDkT8uigu8TkpNpqfkRAm2FXzPOTQytVvibMlSHtTDlBJ4mNDp"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "districtFlag", "yTVTciUVknYn6C2eCUHEoVHPnGThXxxtVoGirRHYcsNcIxhDeOfzngNO1d20ilBy4Chjc2LKDMMvOMxNn9eWFoNG0hExwhpoJRWb6HtVpr3PczDK4gshyzW58tGzwnBom"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "districtLatitude", 15));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "districtLongitude", 14));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                District district = createDistrict(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = district.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(district, null);
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 2:
                        district.setName(contraints.getNegativeValue().toString());
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(district, null);
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 4:
                        district.setCode2(contraints.getNegativeValue().toString());
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 5:
                        district.setDistrictDescription(contraints.getNegativeValue().toString());
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 6:
                        district.setDistrictFlag(contraints.getNegativeValue().toString());
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 7:
                        district.setDistrictLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateDistrict(contraints, district);
                        failureCount++;
                        break;
                    case 8:
                        district.setDistrictLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateDistrict(contraints, district);
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
