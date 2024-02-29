package org.ommp.ommpspring.services;

import org.ommp.ommpspring.IService.IDocumentService;
import org.ommp.ommpspring.entities.Document;
import org.ommp.ommpspring.entities.User;
import org.ommp.ommpspring.repositories.DocumentRepository;
import org.ommp.ommpspring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DocumentService implements IDocumentService
{

    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Document saveDocument(Document document) {
        return documentRepository.save(document);
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
}
