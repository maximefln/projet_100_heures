package entities;

/*Cette classe permet de définir l'objet Recommandations, de l'instancier, d'accéder et de modifier ces variables de classe
 * @see projet_100h.dao.RecommandationsDaoImpl
 * @see projet_100h.servlets.AdminServlet
 *
 * @author MFillion
 * @version 1.0
 */

public class Recommandation {

    private Integer id;
    private String nom;
    private String prenom;
    private String mail;
    private String type;
    private String adresse;
    private int numero;
    private String rue;
    private String ville;

    /*constructeur qui permet d'instancier une recommandation
     *
     * @param id : l'identifiant de la recommandation (Integer)
     * @param nom : le nom de la personne ayant recommandé l'adresse (String)
     * @param prenom : le prenom de la personne ayant recommandé l'adresse (String)
     * @param mail : l'adresse email de la personne ayant recommandé l'adresse (String)
     * @param type : le type d'adresse recommandée (String)
     * @param adresse : le nom de l'adresse recommandée (String)
     * @param numero : le numéro de rue de l'adresse recommandée (int)
     * @param rue : la rue de l'adresse recommandée (String)
     * @param ville : la ville de l'adresse recommandée (String)
     *
     * Le contructeur retourne une instance de Recommandation
     * @return : une instance de Recommandation
     *
     * @author MFillion
     * @version 1.0
     * @see projet_100h.servlets.BarServlet
     * @see projet_100h.servlets.RestaurantServlet
     * @see projet_100h.servlets.MuseeServlet
     */

    public Recommandation(Integer id, String nom, String prenom, String mail, String type, String adresse, int numero, String rue, String ville) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.type = type;
        this.adresse = adresse;
        this.numero = numero;
        this.rue = rue;
        this.ville = ville;
    }

    //Les getteurs et setteurs de la classe recommandation

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
