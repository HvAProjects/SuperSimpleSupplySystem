package nl.soffware.supersimplesupplysystem.dto;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class ApiResponse {
    @NonNull
    Boolean success;
    @NonNull
    String message;
}
