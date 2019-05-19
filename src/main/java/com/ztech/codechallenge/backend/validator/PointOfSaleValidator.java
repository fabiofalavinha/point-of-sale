package com.ztech.codechallenge.backend.validator;

import com.ztech.codechallenge.backend.dto.PointOfSaleDTO;
import org.springframework.stereotype.Component;

@Component
class PointOfSaleValidator implements Validator<PointOfSaleDTO> {

    @Override
    public ValidationResult validate(PointOfSaleDTO obj) {
        final ValidationResult validationResult = new ValidationResult();
        if (obj == null) {
            validationResult.setErrorMessage("Missing point of sale information");
        } else if (obj.getTradingName() == null || obj.getTradingName().isEmpty()) {
            validationResult.setErrorMessage("Tranding name is required");
        } else if (obj.getOwnerName() == null || obj.getOwnerName().isEmpty()) {
            validationResult.setErrorMessage("Owner name is required");
        } else if (obj.getDocument() == null || obj.getDocument().isEmpty()) {
            validationResult.setErrorMessage("Document name is required");
        } else if (!isValidDocument(obj.getDocument())) {
            validationResult.setErrorMessage("Invalid document");
        } else if (obj.getAddress() == null) {
            validationResult.setErrorMessage("Address is required");
        } else if (obj.getCoverageArea() == null) {
            validationResult.setErrorMessage("Coverage area is required");
        }
        return validationResult;
    }

    private boolean isValidDocument(String document) {
        if (document == null || document.isEmpty()) {
            return false;
        }

        document = document.replaceAll("\\.", "").replaceAll("-", "").replaceAll("/", "");

        if (document.length() != 14) {
            return false;
        }

        try {
            Long.parseLong(document);
        } catch (NumberFormatException e) {
            return false;
        }

        int sum = 0;
        String documentCalculated = document.substring(0, 12);

        char documentArray[] = document.toCharArray();
        for (int i = 0; i < 4; i++) {
            if (documentArray[i] - 48 >= 0 && documentArray[i] - 48 <= 9) {
                sum += (documentArray[i] - 48) * (6 - (i + 1));
            }
        }

        for (int i = 0; i < 8; i++) {
            if (documentArray[i + 4] - 48 >= 0 && documentArray[i + 4] - 48 <= 9) {
                sum += (documentArray[i + 4] - 48) * (10 - (i + 1));
            }
        }

        int digit = 11 - sum % 11;
        documentCalculated = new StringBuilder(documentCalculated).append(digit != 10 && digit != 11 ? Integer.toString(digit) : "0").toString();
        sum = 0;
        for (int i = 0; i < 5; i++) {
            if (documentArray[i] - 48 >= 0 && documentArray[i] - 48 <= 9) {
                sum += (documentArray[i] - 48) * (7 - (i + 1));
            }
        }

        for (int i = 0; i < 8; i++) {
            if (documentArray[i + 5] - 48 >= 0 && documentArray[i + 5] - 48 <= 9) {
                sum += (documentArray[i + 5] - 48) * (10 - (i + 1));
            }
        }

        digit = 11 - sum % 11;
        documentCalculated = new StringBuilder(documentCalculated).append(digit != 10 && digit != 11 ? Integer.toString(digit) : "0").toString();

        return document.equals(documentCalculated);
    }
}
