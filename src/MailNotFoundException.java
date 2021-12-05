import javax.mail.MessagingException;

public class MailNotFoundException extends MessagingException {

    private static final long serialVersionUID = 5138468352059653895L;

    public MailNotFoundException() {
        super();
    }

    public MailNotFoundException(String message) {
        super("Aucun compte associe a ce mail");
    }
}
