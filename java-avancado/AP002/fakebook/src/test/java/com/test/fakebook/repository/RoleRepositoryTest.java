package com.test.fakebook.repository;

import com.test.fakebook.entity.Role;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class RoleRepositoryTest {

    @Mock
    private RoleRepository roleRepository;

    @Test
    public void testFindByName() {
        // Given
        String roleName = "ROLE_USER";
        Role expectedRole = new Role(1L, roleName, null);

        // Stubbing the repository method
        when(roleRepository.findByName(roleName)).thenReturn(expectedRole);

        // When
        Role result = roleRepository.findByName(roleName);

        // Then
        assertNotNull(result);
        assertEquals(expectedRole, result);
    }

    @Test
    public void testFindByName_NotFound() {
        // Given
        String roleName = "NON_EXISTENT_ROLE";

        // Stubbing the repository method to return null
        when(roleRepository.findByName(roleName)).thenReturn(null);

        // When
        Role result = roleRepository.findByName(roleName);

        // Then
        assertNull(result);
    }

    @Test
    public void testSaveRole() {
        // Given
        Role role = new Role(null, "ROLE_USER", null);

        // When
        roleRepository.save(role);

        // Then
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    public void testDeleteRole() {
        // Given
        Long roleId = 1L;

        // When
        roleRepository.deleteById(roleId);

        // Then
        verify(roleRepository, times(1)).deleteById(roleId);
    }
}
