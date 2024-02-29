package org.ommp.ommpspring.controllers;

import io.swagger.annotations.Api;
import org.ommp.ommpspring.IService.IDocumentService;
import org.ommp.ommpspring.IService.IUserService;
import org.ommp.ommpspring.entities.Document;
import org.ommp.ommpspring.entities.User;
import org.ommp.ommpspring.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/api/documents")
@Api(value = "Document Controller", tags = {"Document Management"})
public class DocumentController {

    @Autowired
    private IDocumentService documentService;

    @Autowired
    private IUserService userService;


    @PostMapping
    public ResponseEntity<Document> createDocument(@RequestBody Document document) {
        Document newDocument = documentService.saveDocument(document);
        return new ResponseEntity<>(newDocument, HttpStatus.CREATED);
    }

    @PutMapping("/{documentId}")
    public ResponseEntity<Document> updateDocument(@PathVariable Long documentId, @RequestBody Document document) {
        Optional<Document> existingDocumentOptional = documentService.getDocumentById(documentId);
        if (existingDocumentOptional.isPresent()) {
            document.setIdDocument(documentId);
            Document updatedDocument = documentService.updateDocument(document);
            return new ResponseEntity<>(updatedDocument, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{documentId}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long documentId) {
        Optional<Document> documentOptional = documentService.getDocumentById(documentId);
        if (documentOptional.isPresent()) {
            documentService.deleteDocument(documentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long documentId) {
        Optional<Document> documentOptional = documentService.getDocumentById(documentId);
        return documentOptional.map(document -> new ResponseEntity<>(document, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Document>> getAllDocuments() {
        List<Document> documents = documentService.getAllDocuments();
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @PostMapping("/affecter/{documentId}/{userId}")
    public ResponseEntity<Document> affecterUtilisateurAuDocument(@PathVariable Long documentId, @PathVariable Long userId) throws ChangeSetPersister.NotFoundException {
        Document updatedDocument = documentService.affecterUtilisateur(documentId, userId);
        if (updatedDocument != null) {
            return new ResponseEntity<>(updatedDocument, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/document-url/{documentId}")
    public ResponseEntity<String> getDocumentUrl(@PathVariable Long documentId) {
        Optional<Document> documentOptional = documentService.getDocumentById(documentId);

        if (documentOptional.isPresent()) {
            Document document = documentOptional.get();
            String url = document.getUrl();
            return ResponseEntity.ok(url);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/{userId}/documents")
    public ResponseEntity<Set<Document>> getDocumentsByUserId(@PathVariable Long userId) {
        try {
            Set<Document> documents = documentService.getDocumentsByUserId(userId);
            return new ResponseEntity<>(documents, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/desaffecter/{documentId}/{userId}")
    public ResponseEntity<Document> desaffecterUtilisateurDuDocument(@PathVariable Long documentId, @PathVariable Long userId) {
        Document updatedDocument = documentService.desaffecterUtilisateur(documentId, userId);
        if (updatedDocument != null) {
            return new ResponseEntity<>(updatedDocument, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
