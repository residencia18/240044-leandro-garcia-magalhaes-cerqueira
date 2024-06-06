package br.com.techie.shoppingstore.AP003.service;

import br.com.techie.shoppingstore.AP003.dto.form.UserSystemFORM;
import br.com.techie.shoppingstore.AP003.dto.form.UserSystemUpdateFORM;
import br.com.techie.shoppingstore.AP003.dto.view.UserSystemVIEW;
import br.com.techie.shoppingstore.AP003.infra.exception.EntityNotFoundException;
import br.com.techie.shoppingstore.AP003.infra.exception.PasswordInvalidException;
import br.com.techie.shoppingstore.AP003.mapper.forms.UserFormMapper;
import br.com.techie.shoppingstore.AP003.mapper.updates.UserSystemUpdateMapper;
import br.com.techie.shoppingstore.AP003.mapper.views.UserSystemViewMapper;
import br.com.techie.shoppingstore.AP003.model.UserSystem;
import br.com.techie.shoppingstore.AP003.repository.UserSystemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserSystemServiceTest {

    @InjectMocks
    private UserSystemService userSystemService;

    @Mock
    private UserSystemRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserFormMapper userFormMapper;

    @Mock
    private UserSystemViewMapper userViewMapper;

    @Mock
    private UserSystemUpdateMapper userUpdateMapper;

    private UserSystem userSystem;
    private UserSystemFORM userForm;
    private UserSystemVIEW userView;

    @BeforeEach
    public void setup() {
        userSystem = new UserSystem();
        userSystem.setId(1L);
        userSystem.setEmail("test@example.com");
        userSystem.setUsername("testuser");
        userSystem.setPassword("password");
        userSystem.setRole(UserSystem.Role.ROLE_CLIENT);
        userSystem.setActive(true);

        userForm = new UserSystemFORM(
                "test@example.com",
                "testuser",
                "Password123!",
                "Password123!"
        );

        userView = new UserSystemVIEW(
                1L,
                "testuser",
                "test@example.com",
                UserSystem.Role.ROLE_CLIENT
        );
    }

    @Test
    public void testSave_Success() {
        when(userFormMapper.map(any(UserSystemFORM.class))).thenReturn(userSystem);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(UserSystem.class))).thenReturn(userSystem);
        when(userViewMapper.map(any(UserSystem.class))).thenReturn(userView);

        UserSystemVIEW result = userSystemService.save(userForm);

        assertNotNull(result);
        assertEquals(userView, result);
        verify(userRepository, times(1)).save(any(UserSystem.class));
    }

    @Test
    public void testSave_PasswordMismatch() {
        UserSystemFORM form = new UserSystemFORM(
                "test@example.com",
                "testuser",
                "Password123!",
                "Password1234!"
        );

        assertThrows(PasswordInvalidException.class, () -> userSystemService.save(form));
    }

    @Test
    public void testSearchById_UserFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userSystem));
        when(userViewMapper.map(any(UserSystem.class))).thenReturn(userView);

        UserSystemVIEW result = userSystemService.searchById(1L);

        assertNotNull(result);
        assertEquals(userView, result);
    }

    @Test
    public void testSearchById_UserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(br.com.techie.shoppingstore.AP003.infra.exception.EntityNotFoundException.class, () -> userSystemService.searchById(1L));
    }

    @Test
    public void testEditPassword_Success() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userSystem));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        assertDoesNotThrow(() -> userSystemService.editPassword(1L, "Password123!", "NewPassword123!", "NewPassword123!"));
        verify(userRepository, times(1)).findById(anyLong());
        verify(passwordEncoder, times(1)).matches(anyString(), anyString());
    }

    @Test
    public void testEditPassword_PasswordMismatch() {
        assertThrows(PasswordInvalidException.class, () -> userSystemService.editPassword(1L, "Password123!", "NewPassword123!", "NewPassword124!"));
    }

    @Test
    public void testEditPassword_IncorrectCurrentPassword() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userSystem));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        assertThrows(PasswordInvalidException.class, () -> userSystemService.editPassword(1L, "Password123!", "NewPassword123!", "NewPassword123!"));
    }

    @Test
    public void testUpdate_Success() {
        UserSystemUpdateFORM updateForm = new UserSystemUpdateFORM(1L, "newusername", "new@example.com");

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userSystem));
        when(userUpdateMapper.map(any(UserSystemUpdateFORM.class), any(UserSystem.class))).thenReturn(userSystem);
        when(userRepository.save(any(UserSystem.class))).thenReturn(userSystem);
        when(userViewMapper.map(any(UserSystem.class))).thenReturn(userView);

        UserSystemVIEW result = userSystemService.update(updateForm);

        assertNotNull(result);
        assertEquals(userView, result);
    }

    @Test
    public void testUpdate_UserNotFound() {
        UserSystemUpdateFORM updateForm = new UserSystemUpdateFORM(1L, "newusername", "new@example.com");

        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userSystemService.update(updateForm));
    }

    @Test
    public void testSearchAll() {
        Page<UserSystem> page = new PageImpl<>(Collections.singletonList(userSystem));
        when(userRepository.findAllByActiveTrue(any(Pageable.class))).thenReturn(page);
        when(userViewMapper.map(any(UserSystem.class))).thenReturn(userView);

        List<UserSystemVIEW> result = userSystemService.searchAll(Pageable.unpaged());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(userView, result.get(0));
    }
}

