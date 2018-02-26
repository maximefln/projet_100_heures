package dao;

import entities.Commentaire;

import java.util.List;

public interface CommentaireDao {

    List<Commentaire> listCommentaire();
    void addCommentaire(Commentaire commentaire, Integer id_article);
    void deleteCommentaire(int id);
}
