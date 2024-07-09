package org.ommp.ommpspring.services;

import org.ommp.ommpspring.IService.IDocumentService;
import org.ommp.ommpspring.configuration.FilesStorageService;
import org.ommp.ommpspring.entities.Document;
import org.ommp.ommpspring.entities.User;
import org.ommp.ommpspring.repositories.DocumentRepository;
import org.ommp.ommpspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DocumentService implements IDocumentService
{



    private final DocumentRepository documentRepository;
    private final FilesStorageService storageService;
    private final UserRepository userRepository ;


    public boolean existsByTitre(String title) {
        return documentRepository.existsByTitre(title);
    }

    public boolean existsByUrl(String fileName) {
        return documentRepository.existsByUrl(fileName);
    }
    @Autowired
    public DocumentService(DocumentRepository documentRepository, FilesStorageService storageService, UserRepository userRepository) {
        this.documentRepository = documentRepository;
        this.storageService = storageService;
        this.userRepository = userRepository;
    }
    @Override
    public Document saveDocument(MultipartFile file, Document document) {
        if (!storageService.isInitialized()) {
            storageService.init();
        }

        try {
            storageService.save(file);
            document.setUrl(file.getOriginalFilename());
            return documentRepository.save(document);
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Document updateDocument(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public void deleteDocument(Long documentId) {
        documentRepository.deleteById(documentId);
    }

    @Override
    public Optional<Document> getDocumentById(Long documentId) {
        return documentRepository.findById(documentId);
    }

    @Override
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }
    @Override
    public Set<Document> getDocumentsByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getDocuments();
        } else {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }
    @Override
    public Document affecterUtilisateur(Long documentId, Long userId) {
        Optional<Document> documentOptional = documentRepository.findById(documentId);
        if (documentOptional.isPresent()) {
            Document document = documentOptional.get();
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                document.addUser(user);
                return documentRepository.save(document);
            } else {
                throw new RuntimeException("User not found with ID: " + userId);
            }
        } else {
            throw new RuntimeException("Document not found with ID: " + documentId);
        }
    }



    @Override
    public Document desaffecterUtilisateur(Long documentId, Long userId) {
        Optional<Document> documentOptional = documentRepository.findById(documentId);
        if (documentOptional.isPresent()) {
            Document document = documentOptional.get();
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                document.removeUser(user);
                return documentRepository.save(document);
            } else {
                throw new RuntimeException("User not found with ID: " + userId);
            }
        } else {
            throw new RuntimeException("Document not found with ID: " + documentId);
        }
    }

    @Override
    public void desaffecterTousLesUtilisateurs(Long documentId) {
        Optional<Document> documentOptional = documentRepository.findById(documentId);
        if (documentOptional.isPresent()) {
            Document document = documentOptional.get();
            Set<User> users = document.getUsers();
            for (User user : users) {
                user.getDocuments().remove(document);
                userRepository.save(user);
            }
            document.getUsers().clear();
            documentRepository.save(document);
        }
    }
    @Override
    public void affecterTousLesUtilisateurs(Long documentId) {
        Optional<Document> documentOptional = documentRepository.findById(documentId);

        if (documentOptional.isPresent()) {
            Document document = documentOptional.get();
            List<User> allUsers = userRepository.findAll();

            for (User user : allUsers) {
                document.addUser(user);

            documentRepository.save(document);
        }
    }
    }

}
