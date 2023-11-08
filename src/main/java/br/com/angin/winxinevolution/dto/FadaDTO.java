package br.com.angin.winxinevolution.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FairyDTO extends RepresentationModel {

    private int id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 50)
    private String lastName;

    @NotBlank
    private int fairyAge;

    @NotBlank
    @Size(min = 5, max = 50)
    private String fairyNationality ;

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

}
