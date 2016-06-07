package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.app.config.WebConfigExtended;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setAppId("JLnR2MZYVbiwOo72R8oafHrSpFjtL8Z9lZlh39A9i5tGsY3ZuE");
        appmenus.setAppType(1);
        appmenus.setAutoSave(true);
        appmenus.setMenuLabel("OIgJg2KhMoewaiTYk72w7tfrkTDMaXeigXVQhILmN4KWoSbXF8");
        appmenus.setMenuTreeId("Q7HnbHgpVCaF0UklLqaf21aC5EDUrpcP2s9qyMaOLLntHlNrD4");
        appmenus.setMenuAction("T8vO9F4nBxi2Wmfm70032vYNpNJPkUreO1eOUOQe03kpw1coOr");
        appmenus.setMenuCommands("20tTGzyDGL5w9tgpc4g58LQcR6lup3kuAGg5QH2Z8dyXoNJa3m");
        appmenus.setMenuIcon("pKxuKpKuSNbuw1WEFhL0iH4Gv0mHN4L8IMN8gnhRSaTxrX3WDv");
        appmenus.setUiType("wNI");
        appmenus.setRefObjectId("C6YLArOFYfUWUcjGeU4x294XshMux3XiKqoVRzJCZieqGnfvwi");
        appmenus.setMenuAccessRights(5);
        appmenus.setMenuHead(true);
        appmenus.setMenuDisplay(true);
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setAppId("LuOhedQQWfICkrRJ1ZEptQz00y383ePBsdA8p28J8ysZMZG1x9");
            appmenus.setAppType(2);
            appmenus.setVersionId(1);
            appmenus.setMenuLabel("Ou3eKA4PjFPj0lfuiukqRYlxrbEYTGca798IJfQZfYOPcokFaz");
            appmenus.setMenuTreeId("tFljUXpaF2w7eufuzjUe0cAi08XJVyCVjAEnN6Xi5ZvMFRdODj");
            appmenus.setMenuAction("FFhNx6KJryU4MSqiLMfZLUsLidJ8ugndz0RnDXMwnJbRPwtzWi");
            appmenus.setMenuCommands("VGvXjcjZiVI4qYZddEizjYhR2VmvxAeCTGkXXpWQtDsvBqr51n");
            appmenus.setMenuIcon("CFQ67sOCnxNxlCYAxhtw1KjcnwP3digzBuU2SKZTAcQtRgsatS");
            appmenus.setUiType("Tvk");
            appmenus.setRefObjectId("FgT5xKQbwkl8IWUj42EkOFOJ4zx1by4Le5tk7Ryk0JuXQBawcf");
            appmenus.setMenuAccessRights(3);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "iWTjXbvmGOREBkXqSpf8fn6kNjFooXwxwJQNXQl9uvsQTAYCTnsziwoPWfjcbRpuRh1O2Ly7Un1UiNKxQ6sDgvUZ29BErJxLBWOt4kRcmg2lejlmZgPmj3XAeDgjq90dHVQpq1W4hLSqxtfpw4faihAMh6LCrFWAvfES98zPJwWaMqTizxv4HvCrYv4dWauzXi2QZglliiKCLy5Z1mF5BHXWfOxDOSuHyIrORSqocCnn5DjSSX78DxKLZ68AgVW6N"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "GyCUOMtxAOwhGKzJosoUyyffYS182Fm8f8ROwQKWpoMvzaInoXoIptAiKLKiolmgwCAkK4w1qXwLoV7Pe1pW8BgJang55xnIXmH1VMjTdsxWKuEkslNUdhvshJOFzP3r6SUPYFSXOII2wD3qvz94LP8kM6NFKFCEwlmkXdd4aJ7PaGnS4FbmzxVstBmAbJS4qqV6lYVLpGZhYGAn4YyohXjPrfi5Zzs5Wg9Jo44miuaMRzL1apExh336r4g32wAaE"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "QFiORXWcxFYBBET7x6xXSTKruHYqq5eyuYNgBUsVEqSHkaRiBQzManDVuYda2PUuH2IuAXFyuWatY7e47nAJrTKwtQQLe9VW6y3bxsZ5NuoD26Ynsj9WmRAlI2hrWXXpPKSPf9PV41Ew5k8tBfSl5ZjUw8Y4rHfC5ziqPGXHwP0l7TT0M42utVTrt7B8rjMRN4fPHPt3AG30CtxVGtbAMnN9QNjOSpFepOFFnIXDAzZJEQDxIFDuCHfvuPQHOTtvz"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "zC2vNdoptERjqaTGhaCAGSg5hav4ZayoYCgVJQrOsiO0LXeDQbJuX3YxwvmA5xlwC"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "uESLMYLWd3AjJ6G9Wf0KBTHmmpKRY1T8aO4LEMrSUgwsurN1gWU2rAlvtylLJZNIeQJ6LQIZbliUzz14ZfIwF4beX5RN0d2MifIkmiTkohc25XtnRUNoE0nudkcfd9zNgGPPZYpXvqwx5QXGP0aHjQtmEJvWZii2EptgDnBNGHVHYdyC5VX9wmZFhCUG0ntSPo8ptMkQoDSBlSBzcvuaITLiUdrcpbfOXQ8F2ZOJcsg7L3g6HvlG9XRcWi5ljuLEM"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "msyh"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "kPOSA3uUvunXoYFVHilFlCbKqtWQiuWFcWi1VDaFtiOb2qrFWctDE8txg8vV8aViq7oS2uelqfMPTvKSsHzKZDYRBlmoQsvnj8tBVHW6yTHCS7OEYWUhvYc3LrSdxau7hlG5TCWv1CjYVrvf0OZ6iWnucGp4V9ANy5i1p9YRaYelfRzDX9Q3usHxLbsDVqEkQTGzojkby0wzssKHTsESQ0M1v7DEWmuAvs7BjiaIXAWae0G8HsGz91EmxyyCT73kX"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 13));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "sgsR6AzzaW9uzwGKGxzvQblJVGZ315BzWyJC7eFF9045sklWalr8HXt33HTjsyMfHu82u9geqh9NcdGkV8Rce6VMe1gLLFgMLAC8bfrQyQQOOCz6i9YyQ7oF3DIzp20HLtdLKVbGuSprnZHjndTvioeG1WKO9OqoIrLZgnHnAbbW0TJf5TwXKmq9oPqxLaZhudtkr7Kn56ORUnzSpYc8wRnCdTyyJiitI7h8aq0wuTMPRTWMGARJW4gvlVWffG6dv"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
