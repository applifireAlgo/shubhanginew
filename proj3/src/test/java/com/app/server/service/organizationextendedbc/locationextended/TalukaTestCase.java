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
import com.app.server.repository.organizationextendedbc.locationextended.TalukaRepository;
import com.app.shared.organizationextendedbc.locationextended.Taluka;
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
import com.app.shared.organizationextendedbc.locationextended.District;
import com.app.server.repository.organizationextendedbc.locationextended.DistrictRepository;
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
public class TalukaTestCase extends EntityTestCriteria {

    @Autowired
    private TalukaRepository<Taluka> talukaRepository;

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

    private Taluka createTaluka(Boolean isSave) throws Exception {
        District district = new District();
        Region region = new Region();
        region.setRegionDescription("H8XgwNcizGKogLsMuKi0UBiIetjkHeUkEiMBpES03ZMFzxV5zc");
        region.setRegionCodeChar2("ILn4j16Uyshhsorq4iLBvEC6g6nIbCjT");
        region.setRegionLongitude(7);
        region.setRegionName("a5nlim9a7XdW4SixKhElmoXePlOINPLrZOYwip2Lk9VN9LIaO6");
        region.setRegionFlag("YAv9v2rcwjO0sLdHuSwKqOwX5Mxva6OUm4DmGpatOZBUV0esqD");
        Country country = new Country();
        country.setCurrencySymbol("QMaATNr1H1arrj6gJH9zPvPISxLRT2VR");
        country.setCapital("vAYSVEwc0dURcEBbGOGLyrg6DgUOpFTV");
        country.setCountryCode2("2Ey");
        country.setCapitalLongitude(11);
        country.setCountryFlag("y8VULUBGXW7ohIK7Lmqf1eRWmzyrrIJfUeu1c6AcdLNHHX9ce1");
        country.setCountryCode1("3in");
        country.setCapitalLatitude(1);
        country.setCurrencyName("oWAFvP7nDfcVkorXa7230xhcltCCpFphfIOl1xiiIna57hwZ0a");
        country.setCountryName("bGmIEkrsVPL5oLlWHuKMK2pxpQ6qoNLZFpwJUVH6WKaKzdp88g");
        country.setIsoNumeric(890);
        country.setCurrencyCode("Tgx");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCode(2);
        state.setStateFlag("Ve2yXXhmXNCXmyERL39Yw5lGE5RsbFEEeesIfttUJSKDkeyWhI");
        state.setStateCapitalLongitude(6);
        state.setStateCodeChar2("ptdqzoxYijpaS91VIOldkSJJFyIwTVwc");
        state.setStateDescription("nu1q3FaazMw9oJCpUVXZ9AEsmx4zS4wzcEbwoBv3b3itPpqks6");
        state.setStateCodeChar3("3gZAPFFuMLwPFHs6mNEJh5WOMUo2EoYm");
        state.setStateName("b9pQWKheHoUgeXqz3RQ9O2qLm1eZAOHlnDE5526ICDGMrh0KGo");
        state.setStateCapital("UrWjn7fpG6CQlyTmjN5tylH8RFCLDD5OE9gqvA6G7oxQl0eqtW");
        state.setStateCode(2);
        state.setStateFlag("YLnKt7kWCavrkUZxZs4S9VOLUDM7ABEPwFt5y4CimybRVU3LNl");
        state.setStateCapitalLongitude(11);
        state.setStateCodeChar2("6AKfNT3c2IaSTxkH0XxO7K6sVw2soHEl");
        state.setStateDescription("hhWrfM6x4kPL02rbaItgFZWinF6xTKvTrZ6JgDsjT4tm6b3EVe");
        state.setStateCodeChar3("Qlvd1ObUBuFaw1USQXp63fNdPidyMafX");
        state.setStateName("B7uFOynca717mxaSVlYd7CAMXoMHP323eFljXSVeGHG93nfm6G");
        state.setStateCapital("e2XWk2bRpSreEVn93z2dNzPjnITWdfvsBzsdd7bvv6cNEDr6Cg");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(9);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        region.setRegionDescription("R8XIFn5HVaLxOfcqBZU10gXxxQy1SB9CISlGJvTkUFQsE3a8oP");
        region.setRegionCodeChar2("4sXSnJkn4hHXeTNeE8U0Oi4g3rjkZ3b3");
        region.setRegionLongitude(8);
        region.setRegionName("79hrGQ7ZbTgLFcrGfPS2W5oc5znUjM2JyzGn0iD2GqlDAzlhSg");
        region.setRegionFlag("QCVckAG26zcLGXc4dx34JJ2R8ppDhEI0M0cEZK5tQRrs5KNKMn");
        region.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionCode1(3);
        region.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionLatitude(5);
        Region RegionTest = new Region();
        if (isSave) {
            RegionTest = regionRepository.save(region);
            map.put("RegionPrimaryKey", region._getPrimarykey());
        }
        district.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setName("i7byVQBfTe2ZGXc5qHJmHrYxCAwW5pqnHdfKF7MKRBdmwqXtvp");
        district.setDistrictDescription("OQBud5uF9fdASE7F1FoGLByOK9KypN3ttuQMp0ErV1OVlcBv9I");
        district.setDistrictFlag("H6mSP7mKmKHSB3FXXUXKszbxpUqNvAuaAdETaEo5dUQbUGyP3R");
        district.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setDistrictLongitude(10);
        district.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setDistrictLatitude(6);
        district.setCode2("2Ix0JD2x38Lew63QBLrWmIm7HccQNDCy");
        District DistrictTest = new District();
        if (isSave) {
            DistrictTest = districtRepository.save(district);
            map.put("DistrictPrimaryKey", district._getPrimarykey());
        }
        Taluka taluka = new Taluka();
        taluka.setDistrictId((java.lang.String) DistrictTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setTalukaLongitude(1);
        taluka.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setTalukaLatitude(1);
        taluka.setTalukaName("LwKM7wcCBj9NcRdW2YCN1YLCxMlTEKQvZ2XMV5dr7FBV6BTJxY");
        taluka.setRegionId((java.lang.String) RegionTest._getPrimarykey());
        taluka.setTalukaCode("ibmx2rFoIjpZiA3uwtqUnVGpThVDTCLX");
        taluka.setTalukaDescription("KXDOAxF2sxBzyaacrBzYQRWHyLXM1O6pY7UmgzbabKOkNRtSXa");
        taluka.setTalukaFlag("8FWv5nwJQzbc5fatN0esIUjEvXCp1SDNxu3YwAdEo9JktfhZwm");
        taluka.setEntityValidator(entityValidator);
        return taluka;
    }

    @Test
    public void test1Save() {
        try {
            Taluka taluka = createTaluka(true);
            taluka.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            taluka.isValid();
            talukaRepository.save(taluka);
            map.put("TalukaPrimaryKey", taluka._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private DistrictRepository<District> districtRepository;

    @Autowired
    private RegionRepository<Region> regionRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("TalukaPrimaryKey"));
            Taluka taluka = talukaRepository.findById((java.lang.String) map.get("TalukaPrimaryKey"));
            taluka.setTalukaLongitude(11);
            taluka.setVersionId(1);
            taluka.setTalukaLatitude(5);
            taluka.setTalukaName("y4cwmFiJHIcP5biX28tF2aAOxJpVhS57RKhbjbXhdqpzHzXSek");
            taluka.setTalukaCode("WytPRPkISPmQwvwFgRruIWgza2kcpujT");
            taluka.setTalukaDescription("fQIcd1hJBjPBCw83vKiQHwcxdFD3jdppWgzvGDePPPXmEx7C70");
            taluka.setTalukaFlag("aeI0YQpy611eeJoaWQNe5FfhA1AVtRLs8LFbjyQMJWezKS1HHy");
            taluka.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            talukaRepository.update(taluka);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBydistrictId() {
        try {
            java.util.List<Taluka> listofdistrictId = talukaRepository.findByDistrictId((java.lang.String) map.get("DistrictPrimaryKey"));
            if (listofdistrictId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("TalukaPrimaryKey"));
            talukaRepository.findById((java.lang.String) map.get("TalukaPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Taluka> listofcountryId = talukaRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            java.util.List<Taluka> listofstateId = talukaRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByregionId() {
        try {
            java.util.List<Taluka> listofregionId = talukaRepository.findByRegionId((java.lang.String) map.get("RegionPrimaryKey"));
            if (listofregionId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("TalukaPrimaryKey"));
            talukaRepository.delete((java.lang.String) map.get("TalukaPrimaryKey")); /* Deleting refrenced data */
            districtRepository.delete((java.lang.String) map.get("DistrictPrimaryKey")); /* Deleting refrenced data */
            regionRepository.delete((java.lang.String) map.get("RegionPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateTaluka(EntityTestCriteria contraints, Taluka taluka) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            taluka.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            taluka.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            taluka.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            talukaRepository.save(taluka);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "talukaName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "talukaName", "JqPjYMqujOVk0Uin8mMJcXycmTv53h21U612WXYYDHyJxFWoUYn5iNSYohg5xtE9aC2J81urvj3cD4fiZB8cv4wUgliCkCaElKaWugLL3Dmjth43ooD0gRItzMBOfPfNQQry17o5m0vYwIBUxXsDaug5XjBWIOgExQqMf2OHqhpBn0cCXs51mCJmwn3NECEJujauXdKbXYYIBpUZsfvt739fE76kgVHbI9BPhALK1uNrbToPpeImaHOppJ7KPDB8R"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "talukaCode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "talukaCode", "TWS17wI4wRVqIYYjqzKclDlDy8TgghmWm"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "talukaDescription", "TAE02R2doTXUagsD7yjHLOQA9eeAKKzW4MFKySPIb5aGbisw2iunUJsbpwlcYOm0tQpPWjdzqySAhYrgbPFp2tx9D9V0Hq5sW4XJHeMl2eRbyCsskGpFOUP4jtwoI742X"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "talukaFlag", "Lyc2vydRmAKnJKtrGAzYiyfjP4uvfHT1GRSufgMSDNA7x6ByE9VRM1eiPwyikmMtao2m4e5QAPkUA35xe7oW7WsvTx3CAzJojfu7y5mRR5UwVL2a6LUxHwiXVadoMU5qy"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "talukaLatitude", 16));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "talukaLongitude", 17));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Taluka taluka = createTaluka(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = taluka.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(taluka, null);
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 2:
                        taluka.setTalukaName(contraints.getNegativeValue().toString());
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(taluka, null);
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 4:
                        taluka.setTalukaCode(contraints.getNegativeValue().toString());
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 5:
                        taluka.setTalukaDescription(contraints.getNegativeValue().toString());
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 6:
                        taluka.setTalukaFlag(contraints.getNegativeValue().toString());
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 7:
                        taluka.setTalukaLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateTaluka(contraints, taluka);
                        failureCount++;
                        break;
                    case 8:
                        taluka.setTalukaLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateTaluka(contraints, taluka);
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
