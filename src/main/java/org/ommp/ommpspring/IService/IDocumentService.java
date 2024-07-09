package org.ommp.ommpspring.IService;


import org.ommp.ommpspring.entities.Document;
import org.ommp.ommpspring.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IDocumentService {
    Document saveDocument(MultipartFile file, Document document);

    Document updateDocument(Document document);

    void deleteDocument(Long documentId);

    Optional<Document> getDocumentById(Long documentId);

    List<Document> getAllDocuments();

    Document affecterUtilisateur(Long documentId, Long userId);


    Set<Document> getDocumentsByUserId(Long userId) ;

    Document desaffecterUtilisateur(Long documentId, Long userId);

    void desaffecterTousLesUtilisateurs(Long documentId);
    void affecterTousLesUtilisateurs(Long documentId);

    boolean existsByTitre(String title);

    boolean existsByUrl(String url);
}
