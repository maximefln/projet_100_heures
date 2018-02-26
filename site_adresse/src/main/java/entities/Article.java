package entities;

import java.time.LocalDate;

/*Cette classe permet de définir l'objet Article, de l'instancier, d'accéder et de modifier ces variables de classe
 * @see projet_100h.dao.ArticleDaoImpl
 * @see projet_100h.servlets.ArticleServlet
 * @author MFillion
 * @version 1.0
 */


public class Article {

    private Integer id;
    private LocalDate date_ajout;
    private String nom;
    private String type;
    private String presentation;
    private String Visiteur;
    private double coordonnee_x;
    private double coordonnee_y;
    private String lien_image;
    private String lien_image_accueil;
    private Integer note;
    private int nb_like;
    private int nbdislike;

    /*constructeur qui permet d'instancier un article
     *
     * @param id : l'identifiant de l'article (Integer)
     * @param nom : le nom de l'article (String)
     * @param prenom : le prenom de l'article (String)
     * @param presentation_accueil : la presentation de l'article figurant sur la page d'accueil (String)
     * @param visiteur : le nom de la personne ayant visité l'adresse (String)
     * @param latitude : la composante latitude des coordonnées géographiques de l'adresse (Double)
     * @param longitude : la composante longitude des coordonnées géographiques de l'adresse (Double)
     * @param lien-image : le lien de l'image de l'article (String)
     * @param lien-image_accueil : le lien de l'image présente sur la page accueil de l'article (String)
     * @param note : la note de l'article comprise entre 0 et 5(Integer)
     * @param nb_like : le nombre de like de l'article (Integer)
     * @param nb_dislike : le nombre de dislike de l'article (Integer)
     *
     * Le contructeur retourne une instance de Article
     * @return : une instance de Article
     *
     * @author MFillion
     * @version 1.0
     * @see projet_100h.servlets.ArticleServlet
     */

    public Article(LocalDate date_ajout, Integer id, String nom, String type, String presentation_accueil, String Visiteur, double latittude, double longitude, String lien_image, String lien_image_accueil, Integer note, Integer nb_like, Integer nb_dislike){
        this.date_ajout = date_ajout;
        this.id = id;
        this.nom = nom;
        this.presentation = presentation_accueil;
        this.type = type;
        this.Visiteur = Visiteur;
        this.coordonnee_x = latittude;
        this.coordonnee_y = longitude;
        this.lien_image = lien_image;
        this.lien_image_accueil = lien_image_accueil;
        this.note = note;
        this.nb_like = nb_like;
        this.nbdislike = nb_dislike;
    }

    //Les getteurs et setteurs de la classe article

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

    public String getPresentation() {
        return presentation;
    }

    public void setPrsentation(String présentation_accueil) {
        presentation = présentation_accueil;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVisiteur() {
        return Visiteur;
    }

    public void setVisiteur(String visiteur) {
        this.Visiteur = visiteur;
    }

    public double getCoordonnee_x() {
        return coordonnee_x;
    }

    public void setCoordonnee_x(double coordonnee_x) {
        this.coordonnee_x = coordonnee_x ;
    }

    public double getCoordonnee_y() {
        return coordonnee_y;
    }

    public void getCoordonnee_y(double coordonnee_y) {
        this.coordonnee_y = coordonnee_y;
    }

    public String getLien_image() {
        return lien_image;
    }

    public void setLien_image(String lien_image) {
        this.lien_image = lien_image;
    }

    public String getLien_image_accueil() {
        return lien_image_accueil;
    }

    public void setLien_image_accueil(String lien_image_accueil) {
        this.lien_image_accueil = lien_image_accueil;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Integer getNb_like() {
        return nb_like;
    }

    public void setNb_like(Integer nb_like) {
        this.nb_like = nb_like;
    }

    public Integer getNbdislike() {
        return nbdislike;
    }

    public void setNbdislike(Integer nb_dislike) {
        this.nbdislike = nb_dislike;
    }
}
