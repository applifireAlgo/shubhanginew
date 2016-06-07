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
import com.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import com.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebConfigExtended.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleName("tNOj6pL7Y1djoaMT4kayK2JTMmHjreJgEQeSkOnk78jeKwj2RW");
        roles.setRoleHelp("SrQXFfo8cAkjqukOQAymUmccYwEGWF0KHNz3dIjoe75I7fbmmH");
        roles.setRoleDescription("XhBz4yFtWrpK0dGoVwnfMB4A3YT8FndNYhi7HApNoTrJwvdl20");
        roles.setRoleIcon("1U5MP0PNPGG4O55evLHKM2zPkVwwn1ZizKqmqiyxttMLlMpDjR");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        AppMenus appmenus = new AppMenus();
        appmenus.setAppId("uKavFbMmJc7kLcT6L1frFIuJtxlWUilzYAewn328NZF0dA9fs2");
        appmenus.setAppType(2);
        appmenus.setAutoSave(true);
        appmenus.setMenuLabel("eWX2FsfzFaZl9GXPft3dG4Cq2cCuNbx0oP9sbFGUPbWLhEpdMN");
        appmenus.setMenuTreeId("WgN0edhUcy8CkvymCHdRWD18GpYOaVHH8eMuTH2437APH6wSTv");
        appmenus.setMenuAction("gZcEIlUz6OmCM6EvHfWvb64pzCJtMcnVHZKcjbvS4ioFyn3t2d");
        appmenus.setMenuCommands("9Yv6i2GlqNGrau1LtQo7T0L8xlAYiYpEjwkgcE4Bvm8qjGp58x");
        appmenus.setMenuIcon("AnWxq2FMiv6hrXFkTQaXPwMOs74uLzpWiwh2kpt16DoGiixOHC");
        appmenus.setUiType("3ld");
        appmenus.setRefObjectId("mzADuYLE4d9kpNnA2vtSw3t0lXSkcAG8dDGqmZbMPZxUT6jJLo");
        appmenus.setMenuAccessRights(9);
        appmenus.setMenuHead(true);
        appmenus.setMenuDisplay(true);
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setIsRead(true);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleName("78g2kd8hc5sOR5iSlEBeePTDcfmwtWfCMknkVZJmza25gLdHjF");
            roles.setRoleHelp("LQyW8DAiq8NGzKcRqdmsTdPKPYNA2ZAzQ85TEH3t4t1zusQieM");
            roles.setRoleDescription("VTBHhKdxTWioTsRvoT9Bj8V86agSdbEfn89B7OcjYuh4oxTCx3");
            roles.setVersionId(1);
            roles.setRoleIcon("CsBcOHU3LIYE8RwBtJjYJpssjdkzBYcLUz3IWZFeTsIvnQcVRP");
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            org.junit.Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            org.junit.Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityContraints = new java.util.ArrayList<EntityTestCriteria>();
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "yrHgB0OVDJDYd9qgwxv2BKNc3KQbQ4FJ6kChXg7cEAcZxnK7zJWcmSJUGph4Wymqiz7cuMTmQpx68kgyqkmqkybgUzzMdpTvaJVOaIUR3Zo2flKUsrd8GqoCkxVdHrJ0ADDxYBG3obkwL8P3fpO6wrOi7tB0886QSbKWkxXVEdklfzFnrVkpyzjxmzm4mgqvdvae0obnuG8hEE0tFNj4WWgddoT4cevT3ixw42D6DdphuGhDGB0S4HDvJ5QluJsqx"));
        entityContraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "juYKEf0nEEpOGC0cY6W3j0HLvciDGMtgmKFVV50nlQVOQ7WT4fjLQg4CMwA9E4KHlgC9fu6VXvQ0e4TVUqSA05GJWJd4Mg6uVbY4l823NiSqFEJoVuxwWb2woNiF2qbe7beQRLNbQCHZ28SkNl77s7f01rzEpAJvkJj0bO9zSrc5hXM2NQIXsxzqHmvPRC9Js0j82FPSr8wcwHdOr27JUnKz0GqqgbLEVMSRKKZmOF2EZAZTWW6VaR0YlyxDFwIsB"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "laaYeFAOjLv7WHjmWEJlLF2paYKa1UiIc7xOBFtxDMm9mp6gHbnmNbX5P6RgqZsOJ"));
        entityContraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "8oAZzKN2QPZRCcxuuFGx4MDMfBynfbQUMo1ag4fpruFAERPJlOgcP6ZCuSzali0BG38FcNIwEHkDBDVXJuq9DCCYsm6i1Ap0F7FXie43JOUQejN4NhQlKdHo6eGLceABsqufC0KXxHrpfm3BkpFtrXGTPRifNE30fICNqIlzaGnAVWuOTodWhl5VCBX3c3ybR5JJ53HOfASOVtXDvv4VQCy7SrOHI8KOj0QVff8dyFCMdUnOxuXdtITO4r2jy5o9W"));
        return entityContraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityContraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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
