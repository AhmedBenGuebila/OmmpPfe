package org.ommp.ommpspring.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.ommp.ommpspring.configuration.FilesStorageService;
import org.ommp.ommpspring.entities.Document;
import org.ommp.ommpspring.entities.User;
import org.ommp.ommpspring.repositories.DocumentRepository;
import org.ommp.ommpspring.repositories.UserRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class DocumentServiceTest {

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private FilesStorageService storageService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DocumentService documentService;

    private Document document;
    private User user;

    @BeforeEach
    public void setUp() {
        document = new Document();
        document.setIdDocument(1L);
        document.setTitre("Test Document");
        document.setUrl("test-url");
        document.setUsers(new HashSet<>());

        user = new User();
        user.setIdUser(1L);
        user.setNom("Test User");
        user.setMatricule(8787L);

        user.setEmail("test@example.com");
        user.setDocuments(new HashSet<>());
    }


    public void testAffecterUtilisateur() {
        when(documentRepository.findById(1L)).thenReturn(Optional.of(document));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(documentRepository.save(any())).thenReturn(document);

        Document result = documentService.affecterUtilisateur(1L, 1L);

        assertNotNull(result);
        assertTrue(result.getUsers().contains(user));
        assertTrue(user.getDocuments().contains(document));
    }


    public void testDesaffecterUtilisateur() {
        document.getUsers().add(user);
        user.getDocuments().add(document);

        when(documentRepository.findById(1L)).thenReturn(Optional.of(document));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(documentRepository.save(any())).thenReturn(document);

        Document result = documentService.desaffecterUtilisateur(1L, 1L);

        assertNotNull(result);
        assertFalse(result.getUsers().contains(user));
        assertFalse(user.getDocuments().contains(document));
    }


    // Ajoutez d'autres tests comme précédemment
}
