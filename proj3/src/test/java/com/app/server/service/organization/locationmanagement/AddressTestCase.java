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
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Address;
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
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCurrencySymbol("238F0J26zKEOx4vHq097JZnlZeIhMOJj");
        country.setCapital("lQHVYTVH64gGy43p0huHjk7bVPUIQ7en");
        country.setCountryCode2("pb7");
        country.setCapitalLongitude(8);
        country.setCountryFlag("vP4ZpN4OmTUVmz0PgoXDpRHIRNS9TBRbQYJCTcIFrYZcUzXXEX");
        country.setCountryCode1("pEJ");
        country.setCapitalLatitude(11);
        country.setCurrencyName("f8CbOz12rdcRwygfiSd52Bu3wl7xrvw3EYsgnFKcUd8Od2DXFD");
        country.setCountryName("DAmIovTnKb5vYM0DRZR3qgo60DIqQArHy1E3gkaJbfAGYGn5Z1");
        country.setIsoNumeric(900);
        country.setCurrencyCode("n4t");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressType("LnaPDeZDIaXuLBXjinWDsoc0jtXzgC6JCV1EHD6GL2P8OYlnWc");
        addresstype.setAddressTypeIcon("WVSPqIRBtm4kw72utODeaEmWl9AicdhyT7hk665y8Q8dQZKJZe");
        addresstype.setAddressTypeDesc("eo6fkzQqOd92KR7lAeJjKjyQNn5l4QJUrR79LwumNXTsQvKRKS");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityCodeChar2("uXWEocrhTG6RcOmxGdrgu0W2zSitsHI9");
        State state = new State();
        state.setStateCode(2);
        state.setStateFlag("YN9rLveNZGQmpDTqhqpFQpfcJsbI8SpsHTgrpbxxnULgXpgSFC");
        state.setStateCapitalLongitude(4);
        state.setStateCodeChar2("lGPIaX0Op58q2hqcGmNcj6U52AC8KQ0c");
        state.setStateDescription("4Ri5kp61cKxeXvkFgzQ92scCbZfPI5QJcrjhPs19fOJhx81MHG");
        state.setStateCodeChar3("ZfXdDFZf2V4m7m9hSMKTxLAMgPIUhVJ5");
        state.setStateName("SZwmqtnRbtgXVHPPRcRw9UfZLRoV9oVnKkYjHgD4j8qTZkhh28");
        state.setStateCapital("cjvYBpz6HpBt4Py1uAulZMO1JZmZzkGgDvj6TqRRtMrctLVWli");
        state.setStateCode(1);
        state.setStateFlag("Ii1nSDnwjmmAXWUxCjPyHHdcDn5yN8hyBHsgPYRd2t9UBRsHM3");
        state.setStateCapitalLongitude(9);
        state.setStateCodeChar2("Z8SB19acqeJhRvx4r4IfOjBejvvm7DLY");
        state.setStateDescription("2XrsRWkBX4jMjYOVedmco5Swx50mHuaCMAqx1x1fHrSftvwMJQ");
        state.setStateCodeChar3("SajUfizvQsgSphbkcvquiVXFdfpaKnJg");
        state.setStateName("SZgJFSGBceUqqfjTqTkApdEWU9wLVm2JvC3Ww1MnNEVQWSzTsI");
        state.setStateCapital("aqD479FM8QxLfVKe8Q2YdAExM4tnfg5qvsWzGjMGAeisJ58epX");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLatitude(2);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityCodeChar2("prG9R5sWh3lJtJhlB4qbDU9bHw6gTY76");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("4dLf2rBfdxmpOrSSAIIsRBufMM6yfPQBwHI5zoOfEAJPDoW5hE");
        city.setCityCode(2);
        city.setCityName("OXWqkYglpC9edTtUz3rb8hv93zttTLBjwWPs9tWG3PospoVSUe");
        city.setCityDescription("j4wAw0EnrFKQNpLUwoM4KZdakacmoFdzwmaQzKt1dXxNTqDuRb");
        city.setCityLatitude(1);
        city.setCityLongitude(9);
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        Address address = new Address();
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddressLabel("d7MuwdtIZb2");
        address.setLongitude("mAbIy2afF2sag9Vivq247Mg202EglxTtxwMwiXYxmvbbHWEk71");
        address.setAddress1("u6VdygqOS7l8AmdjyNpslNtBdWasPhptgi5uXCcOuVAJU9bfKp");
        address.setLatitude("OSlh38fUPyIXUChJwx2NmmfnNF9gxL0cmfXy5y26ZcVcEaw06V");
        address.setZipcode("r1EGrj");
        address.setAddress3("gRVTx3fwsNLdW9Spp5MtR3gPD5CRSIBbEqCNdAatbyhCWHS3kn");
        address.setAddress2("3QIHTHUj5tB6woatvt68ee7DTpFesK0vGQNQyE75O5Tsg1PZJj");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setVersionId(1);
            address.setAddressLabel("3olFaI3TYbF");
            address.setLongitude("Z6hDidBhdkuypk3qOliQ7LZx0ZOBkAumQQed3918unJ5mtmINd");
            address.setAddress1("itujQu1Oyjg7J5GNdGDuBwVk1F8pmRttxMXFKeLcwv1hwWxI1d");
            address.setLatitude("KZs0IrqGYeGOoUeMxagOwnjtQb4Pt8SPN0ee3h8pjYOnSe3Z3W");
            address.setZipcode("BKSrqc");
            address.setAddress3("Jla5e6m8Dylu6OCxw3fu3A3RlMGk5KEdJdS78tim16ZtFl7KkT");
            address.setAddress2("RH7MXTQQPdLRZPAVKURHhCcbLDmrRP0HgNawdAtqWPyJlJREls");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                org.junit.Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
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
            org.junit.Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "HZs6Wgos3NSz"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "VOzg1XXNlFtOgrvXS8zrFBtUR3yv2oF6TWp4U7WMKlyRCoUXD5k1NHw4q"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "B7SJu3JhUsCcF5JiqV8nVC8PxJVcRiKkOcf6MiprXmY53acegg5Xj5bV4"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "D6pMLqisCJodTeGdyQU8EcdBy3lQE9wH2bKico4d5mhqlHsfqTfvwxQX7"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "yGHSHBu"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "GCElwy529yg06DKq03CS0Q6WZuqNvnD3ypIXV8rdYjdPMLyuncTEeZf7BuWbuafGa"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "MSWSSoPxpp64NTHCN9W9ljMNY28u2PDXIqj1ab7JrWSjAHRlGrxurzvZqCkH6rs3x"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
