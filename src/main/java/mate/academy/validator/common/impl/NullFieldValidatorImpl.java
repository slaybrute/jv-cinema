package mate.academy.validator.common.impl;

import mate.academy.lib.Service;
import mate.academy.validator.common.NullFieldValidator;

@Service
public class NullFieldValidatorImpl implements NullFieldValidator {
    @Override
    public void isFieldNotNull(Object object, RuntimeException e) {
        if (object == null) {
            throw e;
        }
    }
}
