package com.mtyoung.persistence;

import com.mtyoung.entity.UserRole;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mike on 2/15/17.
 */
public class UserRoleDaoTest {

    UserRole role;
    UserRoleDao dao;
    int newRole = 0;

    @Before
    public void setUp() throws Exception {
        dao = new UserRoleDao();
        role = new UserRole();

        role.setDescription("CHIPS n SALSA");
    }

    @After
    public void tearDown() throws Exception {
        if (newRole != 0) {
            dao.deleteRole(newRole);
        }
    }

    @Test
    public void getAllRoles() throws Exception {
        newRole = dao.addRole(role);
        List<UserRole> roles = dao.getAllRoles();
        assertTrue("no roles returned", roles.size() > 0);
    }

    @Test
    public void getRole() throws Exception {
        newRole = dao.addRole(role);
        assertEquals("role Id not returned", role.getRoleid(), dao.getRole(newRole).getRoleid());
        assertEquals("role description not returned", role.getDescription(), dao.getRole(newRole).getDescription());
    }

    @Test
    public void addRole() throws Exception {
        newRole = dao.addRole(role);
        assertEquals("role Id not added", role.getRoleid(), dao.getRole(newRole).getRoleid());
        assertEquals("role description not added", role.getDescription(), dao.getRole(newRole).getDescription());
    }

    @Test
    public void deleteRole() throws Exception {
        dao.addRole(role);
        dao.deleteRole(role.getRoleid());
        assertNull("role not deleted", dao.getRole(role.getRoleid()));
    }

    @Test
    public void updateRole() throws Exception {
        newRole = dao.addRole(role);
        assertEquals("role Id not inserted", role.getRoleid(), dao.getRole(newRole).getRoleid());
        assertEquals("role description not inserted", role.getDescription(), dao.getRole(newRole).getDescription());

        role.setDescription("PASTA FAZOOOL");
        dao.updateRole(role);

        assertEquals("role Id not updated", role.getRoleid(), dao.getRole(newRole).getRoleid());
        assertEquals("role description not updated", role.getDescription(), dao.getRole(newRole).getDescription());

    }

}