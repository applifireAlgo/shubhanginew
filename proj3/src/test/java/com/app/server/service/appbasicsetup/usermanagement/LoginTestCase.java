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
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
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
import com.app.shared.appbasicsetup.usermanagement.User;
import com.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
public class LoginTestCase extends EntityTestCriteria {

    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        User user = new User();
        user.setSessionTimeout(927);
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("aQcntxTMBcoBVBbbjY8f8KotLmeTUkpo8KQGW99xbVmoScllTR");
        useraccesslevel.setLevelHelp("dWfXZafsjOp1LiXNQcVwVkhlHI2AV3itWXJpOAaKCODqs2wft5");
        useraccesslevel.setLevelDescription("9goxZxbD0Yo8v8lMMLUgqSzh9ALUNGSfrIFftF6F1qjdODiZ7U");
        useraccesslevel.setLevelIcon("5X5Hy11gYxmZdGwV3YHfAouttlpWOUDc8ItAROXkcJj1JuWoJD");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("FMKTMhkyzdxmdRUn38lMzC35RMk6Cejgpw4ajIdrZbS6QOxWfp");
        useraccessdomain.setDomainHelp("NpQYahudHywEZCLg8bfLHQrPo8jVwCWam1lksNAAVkThGITuTd");
        useraccessdomain.setDomainName("VvuEtYzC5HmF5hIY7oNwv7J8Y5OMBXSZvRCPGCAYd1PX2FF2qF");
        useraccessdomain.setDomainDescription("ycXjvTAFmm2QFNTLQrnC4qvscdNFq1tJfKKSlkiQZVv7XVlLuj");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setSessionTimeout(1438);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setIsLocked(1);
        user.setUserAccessCode(52153);
        user.setMultiFactorAuthEnabled(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1465284054688l));
        user.setChangePasswordNextLogin(1);
        user.setGenTempOneTimePassword(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1465284054688l));
        user.setPasswordAlgo("TJ8Cx70Jc3jLmkgpnRv7E6COe81cELTz4SKWB7Bt1BSnHSTqTS");
        user.setAllowMultipleLogin(1);
        user.setIsDeleted(1);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionIcon("CCIGUr7hUFcuxROmRxne8jHQr0VcWAHMTzFq05KUN1LjK9PqAh");
        question.setQuestion("8fzOe9XtCbrWGKGJXd2v2CjFdNjauzHYPhcrfqKk9wFf63DFlY");
        question.setLevelid(5);
        question.setQuestionDetails("vOVNBXsRrx");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setUser(user);
        passrecovery.setAnswer("AodliM9ZahX7H9IYZjxHWPnQbmRD9LyVwdc9ZrMpr32kzxizmT");
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setUser(user);
        userdata.setLast5Passwords("ELNsqIUKZl3NxB0EIYyfgAyDoXt98h7wy1TzqCEfByisdAhgsa");
        userdata.setOneTimePassword("ul9nTDM8YGYYRIorSyljpFnFsJXhzFOY");
        userdata.setPassword("YdFds0YUT84NPR99xpjTRlwQtaqFdKKZEnUVWeq8k5W0daSRFl");
        userdata.setOneTimePasswordExpiry(3);
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1465284054885l));
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465284054940l));
        corecontacts.setNativeFirstName("4rpFdA06mbHpXJA3YbTpaua6U4MRBYjXeg1MZuGDHH4uPOPPjP");
        corecontacts.setEmailId("ViXVPa00pEjiMtJBPSYm7MyEToie9fhskvz7BwkZ8Z7iyhljdt");
        corecontacts.setMiddleName("mZNUeYwtIA7g2qmVow28wizQtEc0IrIsH5jPoa5EEnwB5uIixJ");
        Gender gender = new Gender();
        gender.setGender("yh82ZIoSgm4UwKWKkWXHckPtmwAbt8SXzpQU2fpJq48UTcg6cT");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Language language = new Language();
        language.setAlpha2("wx");
        language.setLanguage("vROUUVA7f1VA2T017XfcKJlj31IZjoI4KOISQN1TgaIiJG7plY");
        language.setAlpha4("DLep");
        language.setLanguageIcon("19uTNGSUemt1Hzx8xPVdkgqvAHA6cnanwydeMun2BkVbL62PuO");
        language.setAlpha4parentid(5);
        language.setLanguageDescription("1cicKtFSotijOP36pkZ9aSfcv50NcXFNbRXoHpN7wQds0TPwPc");
        language.setLanguageType("fgnFwoiwSyB0q88VPPjpgUhHz8ismpTo");
        language.setAlpha3("U5K");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("tTwGD2YAUYVj2w2owgNbAOETrWr7CSc5vC76n3YODO9uUrDHpv");
        timezone.setCountry("qLWOT1e8ArGwffnGmkmU9xRpQU6NTTfOZPTIEp6sRTHjeWcCyw");
        timezone.setGmtLabel("SAhDKzjCggZPYRcm7cJOi5BANNtSJHEfnnS6br7OdCidKbGrI1");
        timezone.setCities("GWMFMAxCKmopf5o7YnfgvsG2B2lNFsl1fLAlzt1H73LtoyiQpQ");
        timezone.setUtcdifference(3);
        Title title = new Title();
        title.setTitles("WifEDazJU3p4MQyARcVH6NPwzgWLCBohX7GbVC3cXLiJ4dPuvF");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        corecontacts.setDateofbirth(new java.sql.Timestamp(1465284054957l));
        corecontacts.setNativeFirstName("EmgAbVVmgF2IMvCK5Cfu9GczCICS0I4AC8y6ju3723G3HZQw0b");
        corecontacts.setEmailId("htCXEj0zRFWYdoMvKVrAN9CA0bthmpBY07uHXjGbz10NA3drqs");
        corecontacts.setMiddleName("OocT88ezuHet0gbKjaT1qhVb8dkzus0K6v6EN63EZZMmAqZ3J9");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1465284054985l));
        corecontacts.setNativeLastName("TXjSAoebH5GjFGAnzdFPvkjfykLldFEZciHgXdL5wMc1g4Ntjq");
        corecontacts.setPhoneNumber("Ln8iKgpfa1Svrgn5VPIk");
        corecontacts.setFirstName("E4sLereRTtb6TCvrgvoLpn5vbUdNw3I5NCkNvh0sCFeZrHdNOt");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setAge(95);
        corecontacts.setNativeTitle("gaV4jj69T8FhppmtmYLDHwP6ILD9bZ3271w5xBqCEvQ23HRrz6");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeMiddleName("EfWKq9kzeg4k3ZU8YGgNkGqRbACQd0TEsKpqGCvlVWBZULDNkc");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("xagU7l8XWwzOz5is99cso0ie7EpH12UV4JttRZGpxBSrOoqdcn");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        Country country = new Country();
        country.setCurrencySymbol("jp3IXJd34Td5vaAze6CrAfbhLJDeO6ah");
        country.setCapital("gtmuHI8AcQiu3mh6JEuyVzgfFSSQdjMB");
        country.setCountryCode2("mEn");
        country.setCapitalLongitude(8);
        country.setCountryFlag("h2z2R9riy6vTe3OHQmpge8niackKhTV1QliIzktBdrm8UU3lA2");
        country.setCountryCode1("12N");
        country.setCapitalLatitude(8);
        country.setCurrencyName("XZiuQWcyXcBJW9mdebM7hbzYw6KtYjpEyJ7TG3zuiCD14bO6ZL");
        country.setCountryName("WwNS8STngbZI9p2ojspbFhUG4vXEhiqizghrJMFNezr6STzRAZ");
        country.setIsoNumeric(937);
        country.setCurrencyCode("gs6");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("ypc0pJoGrL6pGouhFIfxAAbWLqptVa2Wj77imvvR1055tiyLAO");
        addresstype.setAddressTypeIcon("H7MtKcfmIfnzQfhSkdbhKqUHRsv6xD4jwqYDHH6RFOsa0zCU4t");
        addresstype.setAddressTypeDesc("FlzUfIdrrNc7hbwWVr1vFLTWazWEa9ez065L9fKiJK89roO5Xv");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityCodeChar2("h8PyHwIChgEno6b6XmieAZ6ZTKkRDjbc");
        State state = new State();
        state.setStateCode(1);
        state.setStateFlag("RWwT0mYUoEtf9W4z8uBDDvYvnKodjQvE1BAg9a8YlMEmlKB0cF");
        state.setStateCapitalLongitude(6);
        state.setStateCodeChar2("WThMYzFn1QpL62dFvZwelAiWCpl1tkkq");
        state.setStateDescription("5Tyz99hcIgMSLCx6VTTG7qabl2ssKh865DtSE1bkXgdqKYRVR8");
        state.setStateCodeChar3("3jcTddUWrhQAVMNNWsrj1IYUd1b8d1s9");
        state.setStateName("5NHeR7OaDukq60nNIHu9vazroTPQn4sTkmvTggeinzkaFDrqZd");
        state.setStateCapital("MwhEC8rDr6XQpJNmKKUbUpCGuaTYs1gepfPvJJZ4pLUA5ZULJv");
        state.setStateCode(1);
        state.setStateFlag("j3IExSTalZ7FHFIPPHfVpU3YWW48L8QSty0ddT6idjGUJKWoVi");
        state.setStateCapitalLongitude(5);
        state.setStateCodeChar2("dPbStoOwAFfP1mxNAFglx34kZ3BSTWbw");
        state.setStateDescription("77zeWXMp0KS67tis0VtQs9A4dLoyNvvc50MtX57tDuyTtYAHf9");
        state.setStateCodeChar3("I8k80u5gT1oPYum3dp6ITlvK8wXnGIUu");
        state.setStateName("6OJjCF37ngTtm1D4PpXDinSEkUz6wjlPxczuDNObX73weODMT2");
        state.setStateCapital("Jpaa1rJCy1LLIqu81LljluOJawG6aY3uiZpfTGeO2iLSGm6Geq");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(4);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityCodeChar2("fMtBJCD663l16jcpyAH0LKjc1TaYpobT");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("8qErhC25LA6YPdg7HW1T6pbtFJMmIMwAut8X8aQTRWUjIAWDuE");
        city.setCityCode(1);
        city.setCityName("1tZeGSnnvvSpLwP9DZGd4aiF9ObDbD6SpZ3LGwRAFfeHoD7B3f");
        city.setCityDescription("7yMP6Y5O1Z11rHeA1JdSxPE1NLvlczctgNhgseKwmjEmRMTiQT");
        city.setCityLatitude(2);
        city.setCityLongitude(9);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("RLx4KaeNWU7");
        address.setLongitude("rDjb1beIEHzagG6s0IFnazBmkxRH8s1TGUtxSRkUL7pjKtVLx7");
        address.setAddress1("h0vb5sFSa1nLTlwJG63XZ7Cd33j9EJekAzFOmLbZHhwfOlEbRT");
        address.setLatitude("fYb7XqH3Xv9N9V0h6auyP5sqxOdGCfuPmweDQaVpl95PnlroKQ");
        address.setZipcode("wD0HMu");
        address.setAddress3("HYcIp5oMYHvhdEHv1zwNUVtosTeo2zg6H1jVDIGxe941tNbiKG");
        address.setAddress2("ARlewqtkaYi9IBWYfGzSAhQ0TwYkn4wgDNs9M7O4Rh1X4XzqbF");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("1Gz2vO8kVG8iONJ82dBejgPghhomwMNr5yFnPkGR80yHf29xVF");
        communicationgroup.setCommGroupDescription("HKFsjvbqQMNpflRhp3iIGI0kuvs4IUWcExTxZk855WnrizDGTZ");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeName("CZGMIzTLdvJaK8Hip03qGo2rpCMnKhfVY5qZCEvgtA80bWFOru");
        communicationtype.setCommTypeDescription("QtCtjw0fYw7pRIZFFsfrPY3UOmio8sK6KlqfX3e2DIq1qBVuuy");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey());
        communicationdata.setCommData("EsdS1eOFFG");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        Login login = new Login();
        login.setServerAuthImage("UNUigtuzGyaVKwWjLA8jItjt6kMPmaOJ");
        login.setLoginId("GgjYdYR9fPkiIOWhxXV0Mat89lViHek5yPBSVoHUfL11HvIoB2");
        login.setFailedLoginAttempts(8);
        login.setServerAuthText("XlDZZmaqSChVanzZ");
        user.setUserId(null);
        login.setUser(user);
        login.setIsAuthenticated(true);
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setVersionId(1);
            login.setServerAuthImage("ZZ96wYEamDBlKXV4IPBQ8ZnToJwnDne5");
            login.setLoginId("BdSqJG1ULgCCNhZ6WxYmJo6C9nVVarY7IRRVYIsKGRmBfL2xaM");
            login.setFailedLoginAttempts(2);
            login.setServerAuthText("GlGY14OAaAuxEKBU");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "wGUcnGbkc54x9HDcV245LIIFvLw0cLkpJYnkUaCPQs2xw7DIm565Xtk4RNfXCivnuCpQpnkrEmQbvTRxKLOK1zuCNf4FxBDKc87wpawIc8slv5qcpyMiP2U8DYfR1rCVE1zPHwhmJjOovEkGYnjw6UECasDh361bgEHfhBciUDfpBsNyCF3Y4g8TOsIipewjMtMghAZr8"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "5GE9jpcD88Ot7hRZxdC9DncKPcG6D83qy"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "Muqg2TFI8qseoKlno"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 19));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
