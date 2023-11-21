package br.com.Winx.dto;

import br.com.Winx.model.WinxModel;
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
public class TransfDTO extends RepresentationModel {
    private int id;
    @NotBlank
    @Size(min = 1, max = 50)
    private String name;
    private WinxModel winxModel;
}
