package eu.qwan.editrain.core;

public class EdiTrainException extends RuntimeException {
    public EdiTrainException(String message) {
        super(message);
    }

    public EdiTrainException(String message, Throwable cause) {
        super(message, cause);
    }
}
