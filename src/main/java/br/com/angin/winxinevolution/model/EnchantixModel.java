package br.com.angin.winxinevolution.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "enchantix")
public class EnchantixModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "requisitosEnchantix", nullable = false, length = 50)
    private String requisitosEnchantix;
    @Column(name = "aparenciaEnchantix", nullable = false, length = 50)
    private String aparenciaEnchantix;

    @Column(name = "poderesEnchantix", nullable = false, length = 50)
    private String poderesEnchantix;
    @ManyToOne
    @JoinColumn(name = "fada_id")
    private FadaModel fadaModel;
}