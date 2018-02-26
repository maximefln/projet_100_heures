package utils;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class MotDePasseUtils {
    private static final int LONGUEUR_SEL = 16;
    private static final int LONGUEUR_HASH = 32;

    private static final int NOMBRE_ITERATIONS = 2;
    private static final int MEMOIRE = 65536;
    private static final int PARALLELISME = 1;

    private static Argon2 instancierArgon2() {
        return Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2i, LONGUEUR_SEL, LONGUEUR_HASH);
    }

    public static String genererMotDePasse(String motDePasse) {
        return instancierArgon2().hash(NOMBRE_ITERATIONS, MEMOIRE, PARALLELISME, motDePasse);
    }

    public static boolean validerMotDePasse(String motDePasse, String hashCorrect) {
        return instancierArgon2().verify(hashCorrect, motDePasse);
    }

    public static void main(String[] args) {
        System.out.println(String.format("Le mot de passe crypté est : %s",
            MotDePasseUtils.genererMotDePasse("mdp")));

        System.out.println(validerMotDePasse("mdp", "$argon2i$v=19$m=65536,t=2,p=1$sYgbaigv099/Xb0apPJz2Q$diovm2cDa0SsommbgxZWp+yVNr6gHL2eU5Q3EYtY768"));
    }
}
