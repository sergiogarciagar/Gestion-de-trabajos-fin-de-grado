/**
 * 
 */
package es.upm.dit.isst.t4.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Sergio García
 *
 */
@Entity
public class TFG implements Serializable {
	
	private static final long serialVersionUID=01L;
	@Id
	private String autor;
	private String titulo;
	private String resumen;
	private String tutor;
	private String secretario;
	private String fichero;
	private int estado;
	private String memoria;
	
	public TFG(String autor, String titulo, String resumen, String tutor,
			String secretario, String fichero, int estado) {
		this.autor = autor;
		this.titulo = titulo;
		this.resumen = resumen;
		this.tutor = tutor;
		this.secretario = secretario;
		this.fichero = fichero;
		this.estado = estado;
		this.memoria = null;
	}
	
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	public String getTutor() {
		return tutor;
	}
	public void setTutor(String tutor) {
		this.tutor = tutor;
	}
	public String getSecretario() {
		return secretario;
	}
	public void setSecretario(String secretario) {
		this.secretario = secretario;
	}
	public String getFichero() {
		return fichero;
	}
	public void setFichero(String fichero) {
		this.fichero = fichero;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "TFG [autor=" + autor + ", titulo=" + titulo + ", resumen="
				+ resumen + ", tutor=" + tutor + ", secretario=" + secretario
				+ ", fichero=" + fichero + ", estado=" + estado + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TFG other = (TFG) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (estado != other.estado)
			return false;
		if (fichero == null) {
			if (other.fichero != null)
				return false;
		} else if (!fichero.equals(other.fichero))
			return false;
		if (resumen == null) {
			if (other.resumen != null)
				return false;
		} else if (!resumen.equals(other.resumen))
			return false;
		if (secretario == null) {
			if (other.secretario != null)
				return false;
		} else if (!secretario.equals(other.secretario))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (tutor == null) {
			if (other.tutor != null)
				return false;
		} else if (!tutor.equals(other.tutor))
			return false;
		return true;
	}

	public String getMemoria() {
		return memoria;
	}

	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}
	
	
	
	
}
