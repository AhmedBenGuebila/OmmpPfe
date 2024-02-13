package org.ommp.ommpspring.services;

import org.ommp.ommpspring.IService.IDocumentService;
import org.ommp.ommpspring.IService.IUserPortService;
import org.ommp.ommpspring.entities.Document;
import org.ommp.ommpspring.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService implements IDocumentService
{
    @Autowired
    private DocumentRepository documentRepository;

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
}
