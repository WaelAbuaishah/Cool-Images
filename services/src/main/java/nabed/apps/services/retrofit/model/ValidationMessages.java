package nabed.apps.services.retrofit.model;

import java.util.List;



public class ValidationMessages {

    private List<ValidationMessage> validationMessages;


    public ValidationMessages(List<ValidationMessage> messages) {
        validationMessages = messages;
    }

    public List<ValidationMessage> getValidationMessages() {
        return validationMessages;
    }
}
