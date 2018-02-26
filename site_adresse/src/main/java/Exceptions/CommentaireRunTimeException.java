package Exceptions;

import entities.Commentaire;

public class CommentaireRunTimeException extends RuntimeException {

    private static final long serialVersionUID = 9025730660535871559L;

    public CommentaireRunTimeException() {
        super();
    }

    public CommentaireRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CommentaireRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommentaireRunTimeException(String message) {
        super(message);
    }

    public CommentaireRunTimeException(Throwable cause) {
        super(cause);
    }
}
