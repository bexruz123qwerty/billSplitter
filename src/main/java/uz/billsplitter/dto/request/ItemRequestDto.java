package uz.billsplitter.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDto {

    @NotBlank
    private String name;

    @Positive
    private Long price;

    @NotBlank
    private String personName;
}
