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
import com.app.server.repository.organizationextendedbc.locationextended.RegionRepository;
import com.app.shared.organizationextendedbc.locationextended.Region;
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
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RegionTestCase extends EntityTestCriteria {

    @Autowired
    private RegionRepository<Region> regionRepository;

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

    private Region createRegion(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCurrencySymbol("cEYakJftpcxkQzfAmBBbq2MQGMVjumsf");
        country.setCapital("NeddtlKQNy9BT875Qj5dnPn3lKqPZWEZ");
        country.setCountryCode2("Ica");
        country.setCapitalLongitude(1);
        country.setCountryFlag("MzR5ZlOWdCZr59Ntu8fsLysMACaCPp4ZJ9gjLut0VfdefMRSfO");
        country.setCountryCode1("QQQ");
        country.setCapitalLatitude(3);
        country.setCurrencyName("HiQClW9pNcPEaLC7VnvdijjOtEsB3jEo9ZrNkpOG5pnHETVtWv");
        country.setCountryName("vKDIvgwtwu29nbvfCGSqtScjNJecH4dTiEt7SymSzmffdv5EYh");
        country.setIsoNumeric(732);
        country.setCurrencyCode("4sw");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCode(1);
        state.setStateFlag("qNeF9574t3Bh1NALpV8IDKgec6WYyII6rdYMRhHECxP4u3Io3o");
        state.setStateCapitalLongitude(9);
        state.setStateCodeChar2("HH2JsaqsjFOfagt9d03GLSap7phTeQ8S");
        state.setStateDescription("2kLYu4HpU2RUloHgDQF10x4JsZwH4uVxZxOREjBELTVLS70xGq");
        state.setStateCodeChar3("PdXPMa0dXADwy0tIybWK0ei33FEm9Ytk");
        state.setStateName("QixVJVNXIgHXR5N6DaSXsQr8FX2FVOcRoHA22jdVIxqFKZf9qn");
        state.setStateCapital("2yXy4aPDWScplXIfisRYyKo2OKJLZ6XE1hHiTYR4daqY4VEkHR");
        state.setStateCode(1);
        state.setStateFlag("3VDOCnVcCqAfDJJ8MMXHUMwCMF9aU1cqj4ovafynlXtk5b7RMq");
        state.setStateCapitalLongitude(9);
        state.setStateCodeChar2("MZG5MElblIEUXmDidYyMlDamoIieE7o3");
        state.setStateDescription("YmrzcHgSwl2S7zgF2uiWlnE52FkvyGFi95E69UAPyHud5VPWjf");
        state.setStateCodeChar3("7158H7Jw3RS77CzOb0Urv0DviUUHBWfZ");
        state.setStateName("JXY5XkIk6eJvBerfQZLQAs2EXt9qrW4zWUhSHmnObKmQvnhjmb");
        state.setStateCapital("BaqQ92O6tRZv5JTKqLg653ALGXKRzZ9cJ615DNrlMcN8twCTU2");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(6);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        Region region = new Region();
        region.setRegionDescription("YnRx9bZabvUctwV58f7luNbvGYVALuJgEZ0rfOWFuSa6XRLxgq");
        region.setRegionCodeChar2("iW8SlcNLZr2xwNjQl0ZcyzbXZC856zct");
        region.setRegionLongitude(5);
        region.setRegionName("FkNJqEmy3EchQcPLzfNey2bCud1hLYjrEuGS5j9niGg8yeg9L7");
        region.setRegionFlag("spbW9fKZLazgjpWW378NhvUKJ7EPikV3MnGfO7lD8v34qvqfG2");
        region.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionCode1(3);
        region.setStateId((java.lang.String) StateTest._getPrimarykey());
        region.setRegionLatitude(6);
        region.setEntityValidator(entityValidator);
        return region;
    }

    @Test
    public void test1Save() {
        try {
            Region region = createRegion(true);
            region.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            region.isValid();
            regionRepository.save(region);
            map.put("RegionPrimaryKey", region._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("RegionPrimaryKey"));
            Region region = regionRepository.findById((java.lang.String) map.get("RegionPrimaryKey"));
            region.setRegionDescription("CADKUROapBN3vuXkfg5T2MVzIkPGu48C61vNgELzF4p1PjOQpw");
            region.setRegionCodeChar2("9vUzp9xECvPlRC3jpEXqf6PbQGBEzhx4");
            region.setRegionLongitude(10);
            region.setRegionName("5iYy8RRtOEVPKtwKkhMthnNOAS9B901u6oWLBalxK5yOrgNz4Y");
            region.setRegionFlag("AHyCRIFt19VL9rO990oSvAYjO67ZpwJ3O1fuoF9SMKkZVF7aMY");
            region.setRegionCode1(2);
            region.setRegionLatitude(9);
            region.setVersionId(1);
            region.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            regionRepository.update(region);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Region> listofcountryId = regionRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Region> listofstateId = regionRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
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
            org.junit.Assert.assertNotNull(map.get("RegionPrimaryKey"));
            regionRepository.findById((java.lang.String) map.get("RegionPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("RegionPrimaryKey"));
            regionRepository.delete((java.lang.String) map.get("RegionPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateRegion(EntityTestCriteria contraints, Region region) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            region.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            region.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            region.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            regionRepository.save(region);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "regionName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "regionName", "rH7nSxmAW2JZfedApo5YeS9Bf1rlV8s3ldYSFTxUCXf3sKNccvJkFVfMe3nBar7QhnrzbddN95IXy9SyPzp6h1QdqezAAVJZfLf0T3QM77jbD5QSvSlhsLxAauLS0YgRvdz8qoIrJmeDOH7f9UEtg9batxdyq1lWFpdNVfP6rOG8jhUdGyTYhMWYmPPO0liP4eWzhcsDE9ksS37VaRCrIgYAvCQXMMJ1ncBL0R36HMol1xubAgDMnJAgFQycaiJOQ"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "regionCode1", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "regionCode1", 6));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "regionCodeChar2", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "regionCodeChar2", "OeaprBFCcBwmpA7faFB9UNFRyBmn8ccH8"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "regionDescription", "A3KcyICYMNYZHDod05YCpkiVsef1C1h6xbvooi2aITN9Yf6ROkabY1VFcdeJObQacd6Us2NOLCX6Z31Cl5AdEsKuFOuACmcX40uZwOo7ybIjyRsxrfVT3NSiWq9miSO1rnrv6LwM4eFHPJcrQ1D7vL0DKuYArxMChnMUqIs1fcQnMbSJOJncIsfV2s1B7rPq1uOrc50uO4WElVr11nPqXzo1JXwdOPdwRDA6N75AYxWgMKixbSeRkUXuDzHEAgbJv"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "regionFlag", "8lUOiYCXKDIacHHjpueGuhqaBs1goSgO2Zqh0KxAckQSsLxedWGHR2VtvQkGMUTp11uO5cNI4TwAeljylZDZLOPDyQ5sEqKeC7oTZM89rv7yJfUEwnfG4xUlKsThSGGm5"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "regionLatitude", 22));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "regionLongitude", 14));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Region region = createRegion(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = region.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(region, null);
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 2:
                        region.setRegionName(contraints.getNegativeValue().toString());
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(region, null);
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 4:
                        region.setRegionCode1(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(region, null);
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 6:
                        region.setRegionCodeChar2(contraints.getNegativeValue().toString());
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 7:
                        region.setRegionDescription(contraints.getNegativeValue().toString());
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 8:
                        region.setRegionFlag(contraints.getNegativeValue().toString());
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 9:
                        region.setRegionLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateRegion(contraints, region);
                        failureCount++;
                        break;
                    case 10:
                        region.setRegionLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateRegion(contraints, region);
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
