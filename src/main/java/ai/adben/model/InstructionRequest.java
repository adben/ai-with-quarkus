package ai.adben.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record InstructionRequest(
        /**
         * Holds the specific promt to process
         */
        @JsonProperty(required = true)
        String prompt,

        /**
         * gives the instruction for the OpenAI operation
         */
        @JsonProperty(required = true)
        String instruction) {
}
