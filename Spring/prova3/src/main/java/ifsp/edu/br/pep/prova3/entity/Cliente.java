package ifsp.edu.br.pep.prova3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "cliente")
@EqualsAndHashCode(of = "id")
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", unique = true, nullable = false, length = 50)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;
}
