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
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("DljpzdibWub2wdEgZzic7VMDvi0ihO4WmxUx8jjSWnhSbJssvI");
        useraccesslevel.setLevelHelp("7kcyNKzpJ6bYWijbBws8Nx5RrDkaxqF5IEaoleRJr5vPevzbPa");
        useraccesslevel.setLevelDescription("qKfgtmnro3i11TViZytDGTgPRFGMU0CFtoQWKnLSPNNzocj4Ai");
        useraccesslevel.setLevelIcon("mtQoNS7yDvrfsYUgCPEzll0hqKEfs0C0KAQFzc2UEyg6rEfKt2");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelName("QT6Vz27m3kgSykswAPWPRuRDWMSGri8xwq0KtOVhrdVeTC02Qi");
            useraccesslevel.setLevelHelp("TJhK4fPoxNKk3atZQr4OXov7XIIVehfrHhNXoQ39KvBNcSRCVq");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelDescription("bOV4VOQuqNx3bLqfTZfogKP0Klyg3CvqWwt9e45O9mtA7wULVQ");
            useraccesslevel.setLevelIcon("36Uhn5qqkTxQxWVqKg84dGxsZcaHOUyLjenzvjIUWgxJ18VhPO");
            useraccesslevel.setUserAccessLevel(36294);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 105279));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "5HxOTxw5wvZzX6qrVa4pVdYsNkIfF5Ekd59Z4bkk9prqdVg7qSa0GGJ6PZTQYiZkJelLFndnxKqCgKl0uj1eQDN3TpbyFuu6Vuhg8P3OcVlc1Fa6ZlGs4qBQJfHaDXwUUW03ARzEcv1b4IUHZZkDzjCDVmtC5W8PsXRBejX9Lu6MZDbeQ9mF8omMJjtkluol7vnMHVNRp9nZfNNln4ita2VFhxVxb5uQpk1LRG3LSfzeSgbfmjSuM9LnZ8Oky0dJB"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "J4nwc51lXkuCsxASFdgi19JNdWqlKeWn6lRrFx31SnKq5pqWf9ONCxarJI2ddDryLzYF5w04ScxXJyOytTXB0hGALbmEWiXJeKI3akk3QXCAj8ezIAhnlhB0CNRtoLOk8hVQt9xCLkn8Zf82TUjjclxMswjlb7s1bV0iaA0KyH7vnBsrykGGZZhFSpfvVpw8U2dbrXL1qktvf82sIwYxqypjLTIDedevM4qwiWGyb6vRcC1TfTU3pyDHQUSyoVUAe"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "wdTxcOTaACXhvkOmXPFuu8wiqLeAs8eq2RAMvJuUPLOCgVpTB4172TwCWLuLxHQJSbDBFchmG0IETwVTzpKtZpDTPCk4oSuiLeFtvNyoAv36Wp7gg1ilpuJHJPDHWt30j4CMlOfEqoRzPW9879XkF2eTIlRIv6QpAJw6B7laiaaYXG6zcWLNNx2Okvr8geYVJSsY1jyTFH5PzpDZrbCa5NI4bIUoItJ5U54Cn2utgJir26reiyxoEYG0VUGwvQ4wr"));
        entityContraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
