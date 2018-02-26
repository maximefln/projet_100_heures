package entities;

import java.time.LocalDate;

/*Cette classe permet de définir l'objet Commentaire, de l'instancier, d'accéder et de modifier ces variables de classe
 * @see projet_100h.dao.CommentaireDaoImpl
 * @see projet_100h.servlets.ArticleServlet
 *
 * @author MFillion
 * @version 1.0
 */

public class Commentaire {

    private Integer id;
    private String nom;
    private String prenom;
    private String commentaire;
    private LocalDate date;
    private Integer id_article;

    /*constructeur qui permet d'instancier un commentaire
     *
     * @param id : l'identifiant du commentaire (Integer)
     * @param nom : le nom de la personne ayant commenté (String)
     * @param prenom : le prenom de la personne ayant commenté (String)
     * @param commentaire : le commentaire (String)
     * @param date : la date du commentaire (LocalDate)
     * @param id_article : l'identifiant de l'article dont le commentaire fait référence
     *
     * Le contructeur retourne une instance de Commentaire
     * @return : une instance de Commentaire
     *
     * @author MFillion
     * @version 1.0
     * @see projet_100h.servlets.ArticleServlet
     */

    public Commentaire(Integer id, String nom, String prenom, String commentaire, LocalDate date, Integer id_article) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.commentaire = commentaire;
        this.date = date;
        this.id_article = id_article;
    }

    //Les getteurs et setteurs de la classe commentaire

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getId_article() {
        return id_article;
    }

    public void setId_article(Integer id_article) {
        this.id_article = id_article;
    }
}
