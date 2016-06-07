package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
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
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.contactmanagement.CommunicationData;
import com.app.shared.organization.contactmanagement.CommunicationGroup;
import com.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.app.shared.organization.contactmanagement.CommunicationType;
import com.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CoreContactsTestCase extends EntityTestCriteria {

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Gender gender = new Gender();
        gender.setGender("2THzqbtn2OPcvVYXOZVkgEdS0lHcDcM16eqXvQmcRfGcZJ3OLt");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha2("BJ");
        language.setLanguage("2dP0x9Q43Et6bWWt0rppWEidTpkjai8Y0YqqhOAfVikWbwGmAP");
        language.setAlpha4("Vmxx");
        language.setLanguageIcon("KfPB70tXYzm70Aayp0XGAKaPclChUjzy28kv19KQqNOUjfyUqx");
        language.setAlpha4parentid(1);
        language.setLanguageDescription("R4VHkvNOPBXDZqFf9RIj7jdnWn4VQR0L3Z1fEfrzeT367GJ2A1");
        language.setLanguageType("kQ586fqs9REVU4u5wmsc59o2kfnsfxx1");
        language.setAlpha3("nit");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("YKF0lt6nDmWodNTr7Kup6DOl5KuBQuMz9FfDClsjUNmMGco4FG");
        timezone.setCountry("LV80MTvs7IvWLHZOguN23d2R99d9kXTnQ42yEWFVd6dt0LLWr3");
        timezone.setGmtLabel("jy49VyKzScnFJ7JuMwNEP3IVnX6M59x42I6DzCXqQc1UvUz560");
        timezone.setCities("c2bV2H1LoRXmrpTbHtpPHwvGrfBLQJ5L0kf8iYQkcBtxgk5V8H");
        timezone.setUtcdifference(8);
        Title title = new Title();
        title.setTitles("mGly8mS6FKTTJ76mqFmHqm5WhW8wETKIlQ3duTySHdIz09AXJz");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465284043921l));
        corecontacts.setNativeFirstName("e7eURBTkZs3iqMoXVcxjBZ9u0Ln6bCIAKxSy0PkoqIw5HSgq2c");
        corecontacts.setEmailId("CYoi7kGHze1TiiXZ9Y3UUnOwcRgMRICqCgWf0qbHpjTZw9z41U");
        corecontacts.setMiddleName("YpHdsD8WrpozXRVq3dpG3h4FUOrXDRw3tXCmyVapm8jrb3MFRl");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465284043949l));
        corecontacts.setNativeLastName("XKzJOYu2wkwACs6N5vOvBOa4SjSrW9IxTBchJIAOwFX4nlSxvn");
        corecontacts.setPhoneNumber("88oisFY1CdbAKJLAJQfj");
        corecontacts.setFirstName("cqHBkKB071O0rxcaIIIXWjGoV0Eb0WIG9JU6lwqfNhAYJg5b81");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setAge(53);
        corecontacts.setNativeTitle("ds8gnp41IT8CGeYeDnc1OPddX8AwD3hNWQO6JkowAxPFn7LZjs");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeMiddleName("eToFD2Sotnyer5VnCGimMJ0LrInFSGWMw5WEkGUOyQ7aphAjCD");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("JWfjuLZ1Z2l27XLhaXa2PuhQMhKaaBugYJ0wsKWNDLyxscm5Tt");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        Country country = new Country();
        country.setCurrencySymbol("Os1w1Czzt57daF6T4SsyiZSQY3Ccw7EH");
        country.setCapital("mBqnu5VpaYTdMCDdBmKfN6FQIzJrrhwR");
        country.setCountryCode2("iYg");
        country.setCapitalLongitude(6);
        country.setCountryFlag("7cAHUGiDOHijZ2TjH701BgCGVrbegkDKvtDFxwROqtEX1r44jM");
        country.setCountryCode1("SrC");
        country.setCapitalLatitude(9);
        country.setCurrencyName("9dXQ6FfjiDYXUVAsQ4Qjy4RSvSzGTsIg1jnypVKKYrkjYEaXT9");
        country.setCountryName("fGZ8Chlqwise8N3K4N1zD0sIqsEfT2LeIh06n0pulV7CMH2kpc");
        country.setIsoNumeric(803);
        country.setCurrencyCode("Vl1");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("k9UcBY9SJh8xLgoCS4d8XhYhTUujvh9r0A3J47kvyKcQlVRKdT");
        addresstype.setAddressTypeIcon("PCrucIUpU2SpZGGjbfjRiTcmdH5JOHLzhTMXmE4ZA6ifdfBp2I");
        addresstype.setAddressTypeDesc("btlMm1MmbrRt6G5XCKw1OWy5HlJME0HW49vZiBVBjZDgKZm8xf");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityCodeChar2("mlM0kIw6TCnstojH53Y0AobM1E0rsIzb");
        State state = new State();
        state.setStateCode(2);
        state.setStateFlag("XZqokg3slneiKPckQl18i4kaXoHQMqPEWUzEuiyO0gwXIbSepG");
        state.setStateCapitalLongitude(7);
        state.setStateCodeChar2("QCoXUtcUCm6RU1T9FNMiFcvcKmq0MAdD");
        state.setStateDescription("b9ujV4i4NlvXPTKZLLs0rUt0vFIoRBBuVJpAsXsDfa0SYD5bJs");
        state.setStateCodeChar3("6GJ21VYnhoSGzcbPHaUPdx2B2wS6QQHd");
        state.setStateName("3KmjLgG3bqiZzGkK07NgAxvNXZRmsZ3T3EL8R0pjQnuExP7w2E");
        state.setStateCapital("mxFEfnHNTAGlnoZaBqQI0fn1SwkfAReYE5mJS0UBj0RFIGLoSL");
        state.setStateCode(1);
        state.setStateFlag("bJJk333uuckwY9UsCR0JRZSdJHGCfIz9Hl6Ljbeu91VVW4bWOl");
        state.setStateCapitalLongitude(10);
        state.setStateCodeChar2("JzpasOFYXReB1FRZIjAKS3yn5OPoXCdO");
        state.setStateDescription("PuB1yVu8z7onXHXXVlTTcAU3lsTrBBo7nkF6XjISJYp7WWlEgw");
        state.setStateCodeChar3("B8s9Aor2K2kJnPvXdD8y4UxKBeGDyMGU");
        state.setStateName("C8lAb6iQxFg73Jbn9sAQLxMEFOGw00imIepKhR5SUg1iPSFEUc");
        state.setStateCapital("uvqEnfDMlQJzt2a0YQbsen7Yu4FBpUYKmD9kJrfn4tBcD4K84O");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(7);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityCodeChar2("FZNRCA8kjGdjhKACFNIz1KqwK617ekTG");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("rMRZNRxlTpMyFHRYWADEcPISLS9MJunSUeZ800hiEiacoi6pzV");
        city.setCityCode(3);
        city.setCityName("jvC4BpE4bLracHZY4HCBd4KjjYfJqPhKIRLOClHFz0wnBYKfWd");
        city.setCityDescription("z6ahDQnlmqqEeO2EgFYJGGlXfcjmnbTQNBaqzeinWxygmQVaHX");
        city.setCityLatitude(9);
        city.setCityLongitude(8);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("M0RsTr5OACx");
        address.setLongitude("DrF9jMFeo6fqNDwNlSDsjeeJdXF8Uocbx3maUgGrgswrn3EPWr");
        address.setAddress1("GkyUsIrabitQPPT0FRcQDI3smMJA8mJMUs4q7twFJ1McmVp1FZ");
        address.setLatitude("Fj1wDLdd8kTkjSKT9WUt0xnKdrR1mAzOpNLdsxLSxOMKqE0cbQ");
        address.setZipcode("o6bneh");
        address.setAddress3("brbGL0WP9zg1TLfKOkGSXpC17DXJb64wUECpSXrPkV4ZM1rkBj");
        address.setAddress2("eZ7enu8RgmJNZB02yk6MgyNuWeJcF26FWCikrOlJa6jBb1Oppm");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("qZOyXHudxTCd5iZ4ESez5K7B9tMl6V0OJ4eaauRXPNlfoskpBZ");
        communicationgroup.setCommGroupDescription("aGyvblqZfmVacOl4oJmuuryPDsJPElWlLE1P79eO8ynIFFNHZv");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("B4jfwQjRI5za6zJIeVzyuzfSZxtitEcx3WPmyVi9hifPb22FhO");
        communicationtype.setCommTypeDescription("HiLJPit9CUdQhrAX5AqWmBogjw4GJ4C8X9pgnP11BpFAN0z7R9");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey());
        communicationdata.setCommData("25mKdWLFPc");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setDateofbirth(new java.sql.Timestamp(1465284044274l));
            corecontacts.setNativeFirstName("vqM9gB1ErNCnGuzuDI4EZZzIxb7JR50O4mvlcIMC652NNxkOSk");
            corecontacts.setEmailId("rnfUNc1jufAm97d7TOD5iDLpzwH7sM2yKRJGw7fLm0sNIwwSUB");
            corecontacts.setMiddleName("4oZeRUblu4MyAFnok1JjeJXANfOTQYuAJsiFUHc20L3wHbFuaX");
            corecontacts.setVersionId(1);
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1465284044313l));
            corecontacts.setNativeLastName("ZMR2nOdIByuxqgW3NEStyrieL5fZUonOWVcYyjriAwXyUhe6JA");
            corecontacts.setPhoneNumber("0VVLYVVx3yHKwU2sbgJZ");
            corecontacts.setFirstName("zVkCdHK1r3zCgOugG4xhNDJNNDfsC0CjbBIMw5gXVLXocJ7P7V");
            corecontacts.setAge(73);
            corecontacts.setNativeTitle("joGdbtXdoVWzPr2SoXq06ZzGAC6Rkv12FcfYHmIGwalna5IG9j");
            corecontacts.setNativeMiddleName("8BborIMDBDSe6z737IyDyC987Hb6xpLZgFXqlvBgka9q9GAgZm");
            corecontacts.setLastName("axAaB9ldTcYrc6UpEtH8gOuNo7MwikNAeQBSbgCOw0bXogjVKE");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "ITKZjiZAHLuQ329wizD51tSake4XRNx4fvPz6qMhhyHCErpeaEzgd13sSoAGsAWnV7eLM5dIp9WYChp8stBYCtbXrMwUS3JnGViUnuhPgYOh5B6cVtYqOCtOXihzipqdkoq5iF6mJClGxzEDf9O3cl5DzFKIdrtvFXbYsxtBdbyh5YPUwVCvrzz41dXc0HIKoWS7DbeBxKAEwuRBTVLOC5v2Jrw77JbNGpjcaELO0Q2LLCo8Q3K0aZ1LSNyrXOAVO"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "WLgaZF0khTEUDVYmtxFnEGYKD5pLiInDrbgq9YMldbjqK6ND7Tn8UIXv7Lg9WxwEFMrEvooN21t8qTtjJ6JeWTGRlRYqaHrUbhSBiNMEcyDEW6gnqiJ9CvYzt9e1vnh6vGpSJjpp1vf3XUaTlDQG7sZTHKz4FUNJa6gvDOUD3BDGdd8PsjptgwA3F6dAhGXr9wFF94fx6r67eKtDSXKHiaCmPoOekg2KALjICOO5qkl9VWQ9tl0hZkVw9xlOlsmT2"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "uDJdiINKaLpGs60M87jhUnPgvXh5nDU9YUBaWQX5V0HXtPB9FHzRqWmA3a61X4cxtw942GefTomXNFMzdohegTZww9bcxuDSaNo9Gw3P7EJsfdvI1YTIl94iVYn5ihVVm9YRVDhRTLAJDdKGntzrz8Mj7KnfCFjOhm2Lv3KjGnIFVHzfm2E8OIzvPeX37zmexSODqcMxzJmzh23rXc8Eoch7UQ4Uq1flPYN1AHDqVlBxeGyObXpGBK0IQQdMX5pFZ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "CMYDhRcRDzZUvwgL7gSHIdiG9zN0SvZSvrp0uPkEHd6TdmJriiR9pp7V2yI3nCZJG"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "NRlkk0mN67hsqXW4hLPqfzvrNBCGxeGZfTw6EosXq2qGpYNpgBdLFgzrxNFvNOMVyIRYZSJy8BtSrJFKUXtIcgF0UrU5svJyGdKiZMR1y3xkyspe8sXkjvrzLFseuzXvwwq9EdnFRtiIkwH3iHoPQksBzTd6LZ71njxyFj5gPWRQHtCXnVsUtQLQsipAOrofLRnrAbv4uoN9UATwWej3WBewTKabAYEoX88oT1IeiuesMpvwR6POJYWwPv6lI3usg"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "eYQvzhdSSjX313rFx2AFU2H2KEOgeZnomdxkiwDPPDZdDu2D3XWhnZr5yEzKMKtKgmaZUVUMpOaW9YSwDcoV6k9NnUqyEy51bBni8mnLZoi49kZu2hOAN0S1ou4pOSlJeQoPVynQL5qMjjNXBSFn4aChDHhVi46zi74WHIMCLDNGbZ3izfLtL0321tdi09xAqC6v9YnlqRnx7oS6XznOKlyPRLj56IKZTG2RyY51JdyP3xFuJGDwTYCiQwg65EHE6"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "nYnW1aSvv9Se2CH4IPpi77aElwrk8RNy3pX9RGQ5FkpJmfDORKErC4cKRj5Da9wEyZP3E4vfsloUSd7Tfjeg22ynyooTtnwlw6dS9Jj90rhmtd0QKc6RP9xUnPcFIAjuKhTJSLyQ6V6unL67UZIwzV8h6tGrKn3j4po1yGOffNvaXJ6Ko3fzAFJAPuuM4WronewoT1jpQl9xtmFKIGMa74x4MiqinpCG8Sgj6Lry8jNRNDX4EItGgWfGmIk11Nbns"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 250));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "QAVG78PjiBuvgvd7Y6LI275pkX0ZTbuaDOK5pRm0HDIYqmCBfTGW6m0oJRfUhmagwHzw1RoxA1UayGS7b0xzDFTSm6rMZ6ntqSC9Da1INhg3W0S1II3niceSDdAH2QYpcmJmdB2lhK3egT0JNzE9fwYFWioCBYpJGC4rXMubL6rQZt3zW0ajWAej9hx73cpjWdtU48E0lFzXMrJi1RpDo9NnRnXTAUbquC9K4CzMtodXuOZEdomUWenVL8xPWvW5Q"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "EqzErGlJ3ceVXpiticni1"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
