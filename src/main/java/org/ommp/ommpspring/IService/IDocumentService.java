package org.ommp.ommpspring.IService;


import org.ommp.ommpspring.entities.Document;

import java.util.List;
import java.util.Optional;

public interface IDocumentService {
    Document saveDocument(Document document);

    Document updateDocument(Document document);

    void deleteDocument(Long documentId);

    Optional<Document> getDocumentById(Long documentId);

    List<Document> getAllDocuments();
}
