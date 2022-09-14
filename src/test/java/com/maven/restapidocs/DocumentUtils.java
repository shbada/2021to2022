package com.maven.restapidocs;

import org.springframework.restdocs.operation.preprocess.OperationPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.snippet.Attributes;

import static org.springframework.restdocs.snippet.Attributes.key;

public interface DocumentUtils {
    /**
     * request pretty
     * @return
     */
    static OperationRequestPreprocessor getDocumentRequest() {
        return Preprocessors.preprocessRequest(new OperationPreprocessor[]{Preprocessors.prettyPrint()});
    }

    /**
     * response pretty
     * @return
     */
    static OperationResponsePreprocessor getDocumentResponse() {
        return Preprocessors.preprocessResponse(new OperationPreprocessor[]{Preprocessors.prettyPrint()});
    }

    /**
     * Text 줄바꿈
     * @return
     */
    static String newLine() {
        return " +" + "\n";
    }

    /**
     * example 속성
     * @param example
     * @return
     */
    static Attributes.Attribute getExampleAttribute(Object example) {
        return key("example").value(example);
    }

    /**
     * validation 속성
     * @param validation
     * @return
     */
    static Attributes.Attribute getValidationAttribute(Object validation) {
        return key("validation").value(validation);
    }
}
