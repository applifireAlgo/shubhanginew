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
import com.app.server.repository.organizationextendedbc.locationextended.VillageRepository;
import com.app.shared.organizationextendedbc.locationextended.Village;
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
import com.app.shared.organizationextendedbc.locationextended.Taluka;
import com.app.server.repository.organizationextendedbc.locationextended.TalukaRepository;
import com.app.shared.organizationextendedbc.locationextended.District;
import com.app.server.repository.organizationextendedbc.locationextended.DistrictRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class VillageTestCase extends EntityTestCriteria {

    @Autowired
    private VillageRepository<Village> villageRepository;

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

    private Village createVillage(Boolean isSave) throws Exception {
        Region region = new Region();
        region.setRegionDescription("0FTPT1h7ag4irirLaV3K7zZiuWEgC25yiU5BtRv13wi2fEd9lu");
        region.setRegionCodeChar2("p2Eqn8l4zt5pfbG7GGGE2OBofwFauYaK");
        region.setRegionLongitude(4);
        region.setRegionName("Xwr517EZhhixNv1b9QWJCGddcOiSI3qryHkWMjwU1hAmQIpWjE");
        region.setRegionFlag("nGT9KeXf0IRiEshs7EIG2kup7DFB538ofPAVxFluZudPBjC6ly");
        Country country = new Country();
        country.setCurrencySymbol("p1BNdVI5cfKONH7Fo4zc2Lsg6j2wEQLT");
        country.setCapital("A5bG6eGUEKmfiwL3gaUctsV6ZEVWEU1c");
        country.setCountryCode2("GkF");
        country.setCapitalLongitude(9);
        country.setCountryFlag("Jf6okIEIlk9QQFUpFwJitxV3xJFizlLQGsbpZDkMw1Ur7jPAtE");
        country.setCountryCode1("aFk");
        country.setCapitalLatitude(4);
        country.setCurrencyName("EByxaDrnvD1YAOyeRUkltFExcWWPhdMWt4thL1bEEMMNEObF32");
        country.setCountryName("Vkm36rsCZucRYuOkQtLI4xX2CAopsvki3hslOMfwtmACN91YHt");
        country.setIsoNumeric(252);
        country.setCurrencyCode("55F");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCode(1);
        state.setStateFlag("S6zwJKlekUjc4yeQjafRKsVLu06ovIcz4Y4w165LTDItsyyR5l");
        state.setStateCapitalLongitude(11);
        state.setStateCodeChar2("0XsHyyGl3EbV1Weoc4YTCxhfJA27hwdC");
        state.setStateDescription("gJQrcbXOvFBIyFxbRBd6UYb0sFwIbEZCTIkn4RQbUDZKWng8B2");
        state.setStateCodeChar3("FdKTVfPKUfSx40wk8eex5ZVtnQqXIRdA");
        state.setStateName("KMhdVudWTh0sK8T7KznPNKLZ1n7JWdJkM3jXYXFvURUQMbavOv");
        state.setStateCapital("bM0AU1fISRL2r9P9Sjfk71vy6piJUDgaFznO4RRXbPyMrwFtpQ");
        state.setStateCode(2);
        state.setStateFlag("oSUDZPPfPUKBebHqGLOYiZAMM9Zbxvn9ZLsdZ14HUNMLqFtUsT");
        state.setStateCapitalLongitude(11);
        state.setStateCodeChar2("SdslLSM0h2KdOEl6HNlhxPcso4er3oZz");
        state.setStateDescription("EWuq71jjUOM8P4hQqmAhVnpQrUQwY2ULJpEl9ag8evx8guQKP8");
        state.setStateCodeChar3("IoI5tm5dIEzRMG8w7k0q91ZCp6Bwi1bZ");
        state.setStateName("HbyHlRGU7mbkS0TD7S66red9pRYlmDuK4DVF6MPhFw7LBQe2e6");
        state.setStateCapital("7zyYPULBLg0x4DHAZ1VdJMl6Nz23y8eZvhNbKGAVAwJ3mEzxOT");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(5);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        region.setRegionDescription("dSPNzNGQ5Hc3g2ZukS02BuGI1YKQa6uMcno4YwJtpulEdRdk3G");
        region.setRegionCodeChar2("MGx0xKJxZXyb2WTXnT7CVv7Gt9mNRsVP");
        region.setRegionLongitude(6);
        region.setRegionName("C0IHtoT058jir0AmLTmnpJ7vLCHBP8EN2M5svKitgJyM1dD8Xm");
        region.setRegionFlag("o5vRUv2QwRzGCuf19YyUMa48lg9yHfet7dLtgO0GSEYgg6wRUK");
        region.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionCode1(2);
        region.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        region.setRegionLatitude(11);
        Region RegionTest = new Region();
        if (isSave) {
            RegionTest = regionRepository.save(region);
            map.put("RegionPrimaryKey", region._getPrimarykey());
        }
        Taluka taluka = new Taluka();
        District district = new District();
        district.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setName("lVKk5iyRSbwaTguIwbuRQWDvPfLkWEn3EIQbEioorIWAhaIR4C");
        district.setDistrictDescription("kbmNUMttzfutVGmcunMZUGR7OSSuKaGw280PpMAIu1XIptAT2a");
        district.setDistrictFlag("BPZwhYAEKKePB5Q4iTXhvIrjHgROmkOjT1xBDBTZxtlOaV1MMW");
        district.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setDistrictLongitude(2);
        district.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        district.setDistrictLatitude(8);
        district.setCode2("Ua3twjsrlMpZIeLoxh5wIFLeC8itNLaE");
        District DistrictTest = new District();
        if (isSave) {
            DistrictTest = districtRepository.save(district);
            map.put("DistrictPrimaryKey", district._getPrimarykey());
        }
        taluka.setDistrictId((java.lang.String) DistrictTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setTalukaLongitude(1);
        taluka.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setTalukaLatitude(1);
        taluka.setTalukaName("LtX3SNqSHHDwsX0XPFELn0cvDacjd7c0jz285zk9SoRN55nLcH");
        taluka.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        taluka.setTalukaCode("e2mnSH1xxxpG6vshPTLlgUbuJmSKB8D0");
        taluka.setTalukaDescription("yZRILnXdkoxiA4E6vvBwJlLKqE2PeZzNtXX5E8fjQLkVgqGTSy");
        taluka.setTalukaFlag("iLLLCmlCW2kHjfmcRApt5k2Yx6iZRyGZWo1CWbNU43zgXtKE7K");
        Taluka TalukaTest = new Taluka();
        if (isSave) {
            TalukaTest = talukaRepository.save(taluka);
            map.put("TalukaPrimaryKey", taluka._getPrimarykey());
        }
        Village village = new Village();
        village.setRegionId((java.lang.String) RegionTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setTalukaaId((java.lang.String) TalukaTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setVillageCode("fjkmm64RTQMRyLbTTvd8sdAku5ddAJtP");
        village.setVillageName("oxYeN1vSB63zk6Cq4jJbAA19yMb2j5g3rj23BuhOV9Qo27LJT9");
        village.setVillageLatitude("aX0JBDdow32");
        village.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setVillageDescription("Rfp6i3Dtncj38siSjqzYZnrh9aiIj6geRIzpBZfxj9qEDAf3UQ");
        village.setDistrictId((java.lang.String) DistrictTest._getPrimarykey()); /* ******Adding refrenced table data */
        village.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        village.setVillageFlag("lScnZBpxTDHolWj1oa9RgmPvzAb6KpkGVIDkoU1Ssih2fFrG4p");
        village.setVillageLongtitude(7);
        village.setEntityValidator(entityValidator);
        return village;
    }

    @Test
    public void test1Save() {
        try {
            Village village = createVillage(true);
            village.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            village.isValid();
            villageRepository.save(village);
            map.put("VillagePrimaryKey", village._getPrimarykey());
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

    @Autowired
    private TalukaRepository<Taluka> talukaRepository;

    @Autowired
    private DistrictRepository<District> districtRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("VillagePrimaryKey"));
            Village village = villageRepository.findById((java.lang.String) map.get("VillagePrimaryKey"));
            village.setVillageCode("PBYNlDKCYwaenQrgbsvv3rrw39Sq02nQ");
            village.setVillageName("bys3znNHOUwRTspS5oSBppR5ScuIqubfcJPZut8xSQsjDPFoSt");
            village.setVillageLatitude("YT5jGCUvBTk");
            village.setVersionId(1);
            village.setVillageDescription("WMob6uy1DsIe8JNfwquR08hasuRHFEYzYlVXfrBCncOr7sshE8");
            village.setVillageFlag("lqbHAelTm0CsRi33aJbsJcc2fjG3bxGFOS6nqRxQPb9i9OWShP");
            village.setVillageLongtitude(5);
            village.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            villageRepository.update(village);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByregionId() {
        try {
            java.util.List<Village> listofregionId = villageRepository.findByRegionId((java.lang.String) map.get("RegionPrimaryKey"));
            if (listofregionId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytalukaaId() {
        try {
            java.util.List<Village> listoftalukaaId = villageRepository.findByTalukaaId((java.lang.String) map.get("TalukaPrimaryKey"));
            if (listoftalukaaId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("VillagePrimaryKey"));
            villageRepository.findById((java.lang.String) map.get("VillagePrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Village> listofstateId = villageRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBydistrictId() {
        try {
            java.util.List<Village> listofdistrictId = villageRepository.findByDistrictId((java.lang.String) map.get("DistrictPrimaryKey"));
            if (listofdistrictId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Village> listofcountryId = villageRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            org.junit.Assert.assertNotNull(map.get("VillagePrimaryKey"));
            villageRepository.delete((java.lang.String) map.get("VillagePrimaryKey")); /* Deleting refrenced data */
            talukaRepository.delete((java.lang.String) map.get("TalukaPrimaryKey")); /* Deleting refrenced data */
            districtRepository.delete((java.lang.String) map.get("DistrictPrimaryKey")); /* Deleting refrenced data */
            regionRepository.delete((java.lang.String) map.get("RegionPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateVillage(EntityTestCriteria contraints, Village village) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            village.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            village.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            village.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            villageRepository.save(village);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "villageName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "villageName", "rzZOSR3RVZt2XAjmQZxnNzM3uQEBF0P4tx4uesooOXnozFPcsM2ha4sCvFV8y707a0vNEOyYEXkaeuMPMQRRl4BjcgVvTPzDbsXjoN3akgCRueZPyKGKtforWLJMBF0hMA733BedhpcUnJIURnqpEfH8S3IpZVg5RFNGdmi8pSw6fjW4UhSjJNW3T7r9nYnwf2G7tb1skeB1mTxbzzdX6DZfgyVY1cLTRa6Y6XSOH0TkXxNud0qfZxz3FiZ3uzwRz"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "villageDescription", "nxgCzFwbeNZarKDjSqCh8Wmz5Uh8VE1vNofFFoJ0nNoJaARVsuOi2VB2dldXcZiODRNVoG8Fw51TQVTUpEPhtllV33oj5XFbC4Kv11ueAA7IV5EKXBDdhgIfjgOKxweBFUcWegkrFKaGKyZjBHbhBK3iPX24GgMuZAu2tBu8yjQrV6ivibBoORhhComVExEwWj4PCCfX8eZngP60RZgtPfWwnCmjxNmXZUcnfbEWhel4ltECs830sU5ylTd4VKPdh"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "villageFlag", "GS7vmANgfhlYe3gOUDHNQGiE0hyfScS4IkOyHQCUxHUOoYHSGLgk1pDPWpLHRSB9U"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "villageCode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "villageCode", "ZITe7dNpTdO4b1O4fnG1Ni1W4UuQaiveP"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "villageLatitude", "rWm9BqBTLUSL"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "villageLongtitude", 20));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Village village = createVillage(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = village.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(village, null);
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 2:
                        village.setVillageName(contraints.getNegativeValue().toString());
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 3:
                        village.setVillageDescription(contraints.getNegativeValue().toString());
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 4:
                        village.setVillageFlag(contraints.getNegativeValue().toString());
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(village, null);
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 6:
                        village.setVillageCode(contraints.getNegativeValue().toString());
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 7:
                        village.setVillageLatitude(contraints.getNegativeValue().toString());
                        validateVillage(contraints, village);
                        failureCount++;
                        break;
                    case 8:
                        village.setVillageLongtitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateVillage(contraints, village);
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
