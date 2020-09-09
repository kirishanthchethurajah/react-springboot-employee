package com.kira.employeespringboot.validation;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyValidator implements ConstraintValidator<PasswordPolicy, String> {

    private static final int MIN_COMPLEX_RULES = 3;
    private static final int MIN_UPPER_CASE = 1;
    private static final int MIN_LOWER_CASE = 1;
    private static final int MIN_DIGIT = 1;
    private static final int MIN_SPECIAL_CASE = 1;
    private static final int MAX_REPEAT_CHARS = 3;



    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        List<Rule> passwordRules = new ArrayList();
        passwordRules.add(new LengthRule(10,128));
        CharacterCharacteristicsRule characteristicsRule = new CharacterCharacteristicsRule(MIN_COMPLEX_RULES,
        new CharacterRule(EnglishCharacterData.UpperCase,MIN_UPPER_CASE),
        new CharacterRule(EnglishCharacterData.LowerCase,MIN_LOWER_CASE),
        new CharacterRule(EnglishCharacterData.Digit,MIN_DIGIT),
                new CharacterRule(EnglishCharacterData.Special,MIN_SPECIAL_CASE));
        passwordRules.add(characteristicsRule);
        passwordRules.add(new RepeatCharacterRegexRule(MAX_REPEAT_CHARS));
        PasswordValidator passwordValidator = new PasswordValidator(passwordRules);
        PasswordData passwordData = new PasswordData(password);
        RuleResult ruleResult = passwordValidator.validate(passwordData);
        return ruleResult.isValid();
    }
}
