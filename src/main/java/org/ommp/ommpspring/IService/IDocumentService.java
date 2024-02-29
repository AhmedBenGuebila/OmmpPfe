package org.ommp.ommpspring.IService;


import org.ommp.ommpspring.entities.Document;
import org.ommp.ommpspring.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IDocumentService {
    Document saveDocument(Document document);

    Document updateDocument(Document document);

    void deleteDocument(Long documentId);

    Optional<Document> getDocumentById(Long documentId);

    List<Document> getAllDocuments();

    Document affecterUtilisateur(Long documentId, Long userId);


    Set<Document> getDocumentsByUserId(Long userId) ;

    Document desaffecterUtilisateur(Long documentId, Long userId);
}
