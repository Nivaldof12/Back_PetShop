package br.com.PetShop.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "agendamento")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "perfil_pet_id")
    private PerfilPet perfilPet;

    @Column(nullable = false)
    private String dia;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String observacao;

    public Agendamento() {
    }

    public Agendamento(PerfilPet perfilPet, String dia, String tipo, String observacao) {
        this.perfilPet = perfilPet;
        this.dia = dia;
        this.tipo = tipo;
        this.observacao = observacao;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PerfilPet getPerfilPet() {
		return perfilPet;
	}

	public void setPerfilPet(PerfilPet perfilPet) {
		this.perfilPet = perfilPet;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}  
}
