package com.example.supplytracker.service;

import com.example.supplytracker.dto.UserDTO;
import com.example.supplytracker.entity.Role;
import com.example.supplytracker.entity.User;
import com.example.supplytracker.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private AutoCloseable closeable;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("securepass");
        user.setRole(Role.Admin); // Use enum here

        userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("John Doe");
        userDTO.setEmail("john@example.com");
        userDTO.setPassword("securepass");
        userDTO.setRole(Role.Admin); // Use enum here
    }

    @Test
    void testCreateUser() {
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            u.setId(1L);
            return u;
        });

        UserDTO result = userService.createUser(userDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals(Role.Admin, result.getRole());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<UserDTO> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals(Role.Admin, result.get(0).getRole());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById_Found() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserDTO result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals("john@example.com", result.getEmail());
        assertEquals(Role.Admin, result.getRole());
    }

    @Test
    void testGetUserById_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UserDTO result = userService.getUserById(1L);

        assertNull(result);
    }

    @Test
    void testUpdateUser_Found() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO updated = userService.updateUser(1L, userDTO);

        assertNotNull(updated);
        assertEquals("John Doe", updated.getName());
        assertEquals(Role.Admin, updated.getRole());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testUpdateUser_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UserDTO updated = userService.updateUser(1L, userDTO);

        assertNull(updated);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testDeleteUser_Exists() {
        when(userRepository.existsById(1L)).thenReturn(true);

        boolean result = userService.deleteUser(1L);

        assertTrue(result);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void testDeleteUser_NotExists() {
        when(userRepository.existsById(1L)).thenReturn(false);

        boolean result = userService.deleteUser(1L);

        assertFalse(result);
        verify(userRepository, never()).deleteById(anyLong());
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }
}
