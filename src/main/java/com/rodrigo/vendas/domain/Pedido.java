package com.rodrigo.vendas.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rodrigo.vendas.domain.enums.StatusPedido;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itemPedidos;
	private LocalDate dataPedido;

	

	public Pedido(Integer id, Cliente cliente, LocalDate dataPedido,StatusPedido status) {
		super();
		this.id = id;
		this.cliente = cliente;
		
		this.dataPedido = dataPedido;
		this.status = status;
	}

	public StatusPedido getStatus() {
		return this.status;
	}



	public void setStatus(String status) {
		this.status = StatusPedido.toEnum(status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}

	

	 



	



	

	
	
}
